package ru.ktelabs.producingwebservice.services.rest;

import ru.ktelabs.producingwebservice.entity.Doctor;
import ru.ktelabs.producingwebservice.exception.NullUserException;
import ru.ktelabs.producingwebservice.exception.NotFoundException;
import ru.ktelabs.producingwebservice.exception.UsersNotFoundException;
import ru.ktelabs.producingwebservice.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public Doctor createDoctor(String fullNme) {
        if (!fullNme.isEmpty()) {
            Doctor doctor = new Doctor(fullNme);
            doctorRepository.save(doctor);

            log.info("Успешное сохранение врача в БД");
            return doctor;
        } else {
            log.error("Ошибка сохранение врача в БД!!! Передано пустое поле!");
            throw new NullUserException("Имя не может быть пустым! Назначьте ФИО врача", "NUFN-0099");
        }
    }

    public void deleteDoctorByID(Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            doctorRepository.deleteById(id);

            log.info("Успешное удаление врача с id {}", id);
        } else {
            log.error("Ошибка удаления врача! Не найден врач с id {}", id);
            throw new NotFoundException("Врач с ID " + id + " не найден!", "NFDID-0098");
        }
    }

    public Optional<Doctor> updateDoctor(Long id, String updatedFullName) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if (doctor.isPresent()) {
            doctor.get().setFullName(updatedFullName);
            doctorRepository.save(doctor.get());

            log.info("Успешное обновление информации о враче!");
            return doctor;
        } else {
            log.error("Ошибка обновления! Не найден врач с id {}", id);
            throw new NotFoundException("Врач с ID " + id + " не найден!", "NFDID-0097");
        }
    }

    public List<Doctor> getAllDoctors() {
        if (!doctorRepository.findAll().isEmpty()) {
            log.info("Успешное получение всех врачей!");
            return doctorRepository.findAll();
        } else {
            log.error("Ошибка получения врачей!");
            throw new UsersNotFoundException("Список врачей пуст!", "NFDL-0096");
        }
    }
}

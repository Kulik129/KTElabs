package ru.ktelabs.producingwebservice.services.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ktelabs.producingwebservice.entity.Patient;
import ru.ktelabs.producingwebservice.exception.NotFoundException;
import ru.ktelabs.producingwebservice.exception.NullUserException;
import ru.ktelabs.producingwebservice.exception.UsersNotFoundException;
import ru.ktelabs.producingwebservice.repository.PatientRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    private final PatientRepository patientRepository;

    public Patient createPatient(String fullNme, Date birthDate) {
        Patient createPatient = new Patient(fullNme, birthDate);
        if (!fullNme.isEmpty() && !birthDate.equals(null)) {

            patientRepository.save(createPatient);
            log.info("Успешное сохранение пациента в БД");
        } else {
            if (fullNme.isEmpty()) {
                log.error("Ошибка сохранение пациента в БД!!! Передано пустое поле в ФИО!");
                throw new NullUserException("Имя не может быть пустым! Назначьте ФИО пациента", "EFN-0079");
            }
            if (birthDate.equals(null)) {
                log.error("Ошибка сохранение пациента в БД!!! Передано пустое поле в дату рождения!");
                throw new NullUserException("Дата рождения не может быть пустой! Назначьте дату", "EDB-0078");
            }
        }
        return createPatient;
    }

    public void deletePatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patientRepository.deleteById(id);
            log.info("Успешное удаление пациента с id {}", id);
        } else {
            log.error("Ошибка удаления пациента! Не найден врач с id {}", id);
            throw new NotFoundException("Пациент с ID " + id + " не найден!", "NFDID-0078");
        }
    }

    public Optional<Patient> updatePatient(Long id, Date updatedBirthDate, String updatedFullName) {
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isPresent()) {
            patient.get().setBirthDate(updatedBirthDate);
            patient.get().setFullName(updatedFullName);
            patientRepository.save(patient.get());

            log.info("Успешное обновление информации о пациенте!");
            return patient;
        } else {
            log.error("Ошибка обновления! Не найден пациент с id {}", id);
            throw new NotFoundException("Пациент с ID " + id + " не найден!", "NFDID-0077");
        }
    }

    public List<Patient> getAllPatients() {
        if (!patientRepository.findAll().isEmpty()) {
            log.info("Успешное получение всех пациентов!");
            return patientRepository.findAll();
        } else {
            log.error("Ошибка получения пациентов!");
            throw new UsersNotFoundException("Список пациентов пуст!", "NFDL-0076");
        }
    }
}
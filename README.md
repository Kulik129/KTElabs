# Этот проект представляет собой веб-приложение для записи на прием к врачу.

---
### _**Имеет следующий функционал:**_
> * Создание талонов по переданным правилам
> * Получение свободных слотов времени к указанному врачу
> * Занятие слота времени по его ID
> * Получение всех слотов времени, занятых одним пациентом
> * Добавление пациента
> * Обновление данных о пациенте
> * Список всех пациентов
> * Удаление пациента
> * Добавление врача
> * Назначение талона врачу
> * Обновление информации о враче
> * Список всех врачей
> * Удаление врача

## Технологии

- > * Java 17
- > * Spring Boot
- > * Hibernate/JPA
- > * PostgreSQL
- > * SWAGGER
- > * REST-сервис
- > * SOAP-сервис
- > * Maven

___


### Для тестирования REST сервиса используется SWAGGER.

> После запуска приложения следует перейти по ссылке:  http://localhost:8080/swagger-ui/index.html#/

### Для тестирования SOAP сервиса потребуется Postman.

> После запуска приложения следует отправить POST запрос на адрес: http://localhost:8080/ws

С телом запроса:
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
				  xmlns:gs="https://ktelabs.ru">
   <soapenv:Header/>
   <soapenv:Body>
      <gs:setTicketRequest>
        <gs:startTime>2024-03-03T10:00:00</gs:startTime>
        <gs:quantity>5</gs:quantity>
        <gs:duration>1</gs:duration>
      </gs:setTicketRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

- _startTime_ - это время начала
- _quantity_ - количество талонов
- _duration_ - продолжительность приема (в часах)

### В этой конфигурации также используется преобразование сервлета местоположения WSDL: http://localhost:8080/ws/tickets.wsdl
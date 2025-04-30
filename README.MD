# Медицинская система записи на приём
## Описание системы
Система предоставляет REST API для управления записями пациентов на приём к врачам. Архитектура основана на микросервисах с использованием:

1. API Service - входная точка для HTTP-запросов
2. Data Service - обработка данных и работа с БД
3. Kafka - асинхронная обработка новых записей
4. PostgreSQL - хранение данных

## Функционал
* Создание новых записей на приём
* Поиск существующих записей по параметрам
* Получение отчётов:
  * Топ врачей по количеству записей
  * Статистика записей по дням
  * Самые активные пациенты

## Запуск системы
1. Клонируйте репозиторий:

`git clone https://github.com/anastas-gud/medical-appointment-system.git`
   
`cd medical-appointment-system`
2. Запустите систему:

`docker-compose up --build -d`

(после билда стоит подождать пару минут, пока все инициализируется)
## Тестирование API

### 1. Создание новой записи (POST)

`curl -X POST http://localhost:8080/api/appointments -H "Content-Type: application/json" -d "{\"patientName\":\"anna\",\"patientPhone\":\"89161234567\",\"appointmentTime\":\"2025-05-15 14:30\",\"doctorId\":1,\"status\":\"CONFIRMED\"}"`

### 2. Поиск записей (GET)

Поиск всех записей

`curl "http://localhost:8080/api/appointments/search"`

Поиск по имени пациента

`curl "http://localhost:8080/api/appointments/search?patientName=anna"`

Поиск по ID врача

`curl "http://localhost:8080/api/appointments/search?doctorId=1"`

Поиск по статусу

`curl "http://localhost:8080/api/appointments/search?status=CONFIRMED"`

(параметры можно комбинировать)

### 3. Получение отчётов

Топ врачей

`curl "http://localhost:8080/api/reports/top-doctors"`

Статистика по дням

`curl "http://localhost:8080/api/reports/appointments-by-day"`

Самые активные пациенты

`curl "http://localhost:8080/api/reports/frequent-patients"`


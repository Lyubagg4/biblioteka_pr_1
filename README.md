# Library Management System (Spring MVC)

Монолитное веб-приложение для учета книг и читателей в библиотеке. Реализовано на основе классического Spring MVC без использования Spring Boot, что демонстрирует глубокое понимание конфигурации инфраструктуры "руками".

---

## 🚀 Технологический стек (Stack)

*   **Backend:** Java, Spring Framework 6+ (MVC, Core, JDBC)
*   **Database:** PostgreSQL, JDBC Template
*   **View Layer:** Thymeleaf, HTML
*   **Build Tool:** Maven
*   **Validation:** Bean Validation (Hibernate Validator)
*   **Configuration:** Java-based Spring Configuration (`@Configuration`), кастный `DispatcherServlet`
*   **View Resolver:** Thymeleaf `SpringTemplateEngine` (сконфигурирован вручную)

## 📖 Ключевые возможности (Features)

*   **Управление книгами:** CRUD операции для книг.
*   **Управление читателями:** CRUD операции для читателей.
*   **Выдача и возврат книг:** Назначение книги читателю и освобождение книги.
*   **Валидация данных:** Проверка вводимых данных на стороне сервера.

## 🏗️ Архитектура

*   **Архитектура:** Монолитная, MVC (Model-View-Controller)
*   **Доступ к данным:** Паттерн DAO (Data Access Object) с использованием `JdbcTemplate`.
*   **Шаблонизатор:** Thymeleaf для динамического рендеринга HTML-страниц.

## 🚀 Запуск приложения (Local Development)

1.  **Настройка базы данных:**
    ```sql
    CREATE DATABASE library_db;
    ```
2.  **Обновите настройки в `database.properties`:**
    ```properties
    driver=org.postgresql.Driver
    url=jdbc:postgresql://localhost:5432/library_db
    username=your_username
    password=your_password
    ```
3.  **Соберите и запустите приложение в вашем IDE или через Maven.**
4.  Приложение будет доступно по адресу: `http://localhost:8080/`

## 🔄 API Endpoints (Полный список)

### 📚 Управление книгами (`/books`)

| Метод | URL | Описание |
|-------|-----|-----------|
| GET | `/books` | Список всех книг |
| GET | `/books/new` | Форма для добавления новой книги |
| POST | `/books` | Создание новой книги |
| GET | `/books/{id}` | Просмотр информации о конкретной книге |
| GET | `/books/{id}/edit` | Форма для редактирования книги |
| PATCH | `/books/{id}` | Обновление данных книги |
| DELETE | `/books/{id}` | Удаление книги |
| PATCH | `/books/{id}/addPerson` | Выдать книгу читателю |

### 👥 Управление читателями (`/people`)

| Метод | URL | Описание |
|-------|-----|-----------|
| GET | `/people` | Список всех читателей |
| GET | `/people/new` | Форма для добавления нового читателя |
| POST | `/people` | Создание нового читателя |
| GET | `/people/{id}` | Просмотр информации о читателе и его книгах |
| GET | `/people/{id}/edit` | Форма для редактирования данных читателя |
| PATCH | `/people/{id}` | Обновление данных читателя |
| DELETE | `/people/{id}` | Удаление читателя |
| PATCH | `/people/{book_id}/free` | Освободить книгу (вернуть в библиотеку) |
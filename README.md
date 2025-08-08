# spring-task1
# Веб-приложение для создания, просмотра, обновления и удаления задач с пагинацией

Технологии:
- Java 17+
- Spring MVC 6, Spring Data JPA 3
- Hibernate ORM 7
- HikariCP
- Thymeleaf 3.1
- MySQL 8
- Maven
- Деплой: WAR на Tomcat 10.1+ (без Spring Boot)

Подготовка БД
Укажите параметры подключения в src/main/resources/application.properties.
Выполните скрипт src/main/resources/sql/script.sql 

Запуск
Добавьте артефакт spring-task1:war exploded в конфигурацию Tomcat.
Application context: / 
Браузер откроется автоматически на http://localhost:8080/

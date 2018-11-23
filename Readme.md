## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Postgre, MySQL, H2 (for local development)

## Steps to setup

**1. Clone the application**

```bash
git clone https://github.com/huy3890/convertium.git
```

**2. Change the configuration in application.yml to match with DB**


**3. Build and run the app using maven**

```bash
cd convertium-assignment
mvn spring-boot:run
```
### Technologies:

**Flyway**
I am using Flyway for database migration. Create SQL script also,
db/migration/local

**Spring Boot**
Framework create web restful api

**Thymeleaf**
To render data on html

**Spring Jpa**
persistent data into database

**Bootstrap 4.0**
Help to define layout html

**Jquery**
Handle action on js


# Open Bank Backend
__Серверная часть приложения, имитирующего работу банковского ПО__
<br>Стек технологий:
1. [Java 8](https://java.com/)
1. [Spring Framework](https://spring.io/)
1. [Spring Data](https://spring.io/projects/spring-data/)
1. [Spring Security](https://spring.io/projects/spring-security/)

<br>Позволяет выполнять следующие действия:
1. создавать / удалять пользовательские счета (банковские аккаунты)
1. получать список всех аккаунтов
1. переводить указанную сумму с одного банковского счета на другой

### Окружение
Для запуска приложения необходимо запустить БД (*PostgresSQL*) и накатить схему, используя **[openbank_db](https://github.com/implicitly86/openbank_db/)**

### Сборка
Для того, что собрать проект в корне проекта необходимо выполнить команду
```bash
gradle clean build
```
В папке **build/libs** будет находится собранный исполняемый jar файл.

### Запуск
Для запуска приложения необходимо выполнить следующее:
```bash
java -jar -Xmx256m build/libs/openbank_backend-0.0.1.jar \
    --JDBC_URL="jdbc:postgresql://localhost:5432/ap" \
    --DB_USER=user \
    --DB_PASSWORD=password
```
где
```text
1. JDBC_URL - строка подключения к БД
2. DB_USER - пользователь, под которым необходимо подключиться к БД
3. DB_PASSWORD - пароль пользователя
```

### Swagger
Документирование API выполнено при помощи [Swagger](https://swagger.io/)
<br>После запуска приложения просмотреть документацию можно по адресу:
```text
http://localhost:8080/api/v1/
```

### Сборка в docker контейнер
В корне проекта необходимо выполнить следующие команды
1. сборка контейнера

```bash
docker build -t openbank_backend .
```

2. запуск контейнера

```bash
docker run --name ap_backend -d -p 8080:8080 \
    -e JDBC_URL="jdbc:postgresql://localhost:5432/ap" \
    -e DB_USER="user" \
    -e DB_PASSWORD="password" \
    openbank_backend
```

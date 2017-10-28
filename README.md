# Spring Data JPA with Hibernate using Postgres 
This project is a fork of Spring Data JPa and Hibernate example (https://github.com/TechPrimers/spring-jpa-hibernate-mysql-example)
The goal is to highlight a likely bug in hibernate

## Description
This Project shows the list of Users which are stored in the MySql Database. Using the following endpoints, different operations can be achieved:
- `/rest/users/all` - This returns the list of Users in the Users table which is created in MySql Table (users)
- `/rest/users/{name}` - This returns the details of the Users passed in URL
- `/rest/id/{id}` - This returns the details of the Users for the user Id passed in URL
- `/rest/update/{id}/{name}` - This updates the name of the user for the userId passed in the URL

## Libraries used
- Spring Boot
- Spring MVC (Spring Web)
- Spring Data JPA with Hibernate
- Postgres
- Hibernate 5.0.12

## Tools used
- Git 2.10.0
- IntelliJ IDEA 2017.1
- Postgres running locally

## Compilation Command
- `mvn clean install` - Plain maven clean and install

## create database with Docker
- `docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres`

## launch application
- `mvn spring-boot:run`

## The database

```
CREATE TABLE public.users (
	uuid uuid NOT NULL,
	id int4 NOT NULL,
	"name" varchar(255) NULL,
	salary int4 NULL,
	CONSTRAINT uk_6jvqtxgs6xvh0h0t261hurgqo UNIQUE (uuid),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.teams (
	user_id int4 NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT teams_users_fk FOREIGN KEY (user_id) REFERENCES public.users(id)
);

INSERT INTO public.users (uuid,id,"name",salary) VALUES ('cbfaed14-bb65-11e7-abc4-cec278b6b50a',1,'name',12);

INSERT INTO public.users (uuid,id,"name",salary) VALUES ('44af28c2-bbdb-11e7-abc4-cec278b6b50a',2,'name 2',20);

INSERT INTO public.teams (user_id,"name") VALUES (2,'team 1');
```



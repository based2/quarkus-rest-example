Microprofile REST Example
==================

This example shows a simple REST-API with intern H2 Database access running on Payara Micro.

Technologies
------------

- Payara Micro
- Eclipse MicroProfile
- Hibernate
- H2 Database

Preparation
----------

Download the current Payara Micro Server from [https://www.payara.fish/products/downloads/all-downloads/]()

Build
----------

Run `./mvnw clean package` or `mvnw.cmd clean package` (Windows) to build this project.

Run
----------

`java -jar payara-micro-{VERSION}.jar target/microprofile-rest-example-1.0.war`

REST call examples
----------

*_Get all contacts_*
```
curl -X GET http://localhost:8080/microprofile-rest-example-1.0/api/contacts
```

*_Get single contact_*
```
curl -X GET http://localhost:8080/microprofile-rest-example-1.0/api/contacts/-1
```

*_Add contact_*
```
curl -X POST -H "Content-Type: application/json" -d '{"companyName":"J+J Doe Inc.","firstName":"Jane","lastName":"Doe"}' http://localhost:8080/microprofile-rest-example-1.0/api/contacts
```

*_Update contact_*
```
curl -X PUT -H "Content-Type: application/json" -d '{"companyName":"J+J Doe Inc.","firstName":"Jane Q.","lastName":"Doe"}' http://localhost:8080/microprofile-rest-example-1.0/api/contacts/1
```

*_Delete contact_*
```
curl -X DELETE http://localhost:8080/microprofile-rest-example-1.0/api/contacts/-1
```



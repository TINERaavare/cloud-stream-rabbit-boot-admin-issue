# cloud-stream-rabbit-boot-admin-issue

Stackoverflow question:

https://stackoverflow.com/questions/44683347/spring-cloud-stream-and-rabbitmq-health-check

Steps to reproduce:

* Start eureka-server (port 8761)
* Start boot-admin (port 8080)
* Start RabbitMQ and update configuration in cloud-stream-rabbit\src\main\resource\application.yml
* Start cloud-stream-rabbit and watch logs (and boot-admin dashboard). Logs should show helth check failures against rabbitmq.

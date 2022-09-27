# airlines-backend-microservices - MASTER THESIS PROJECT

### Monolith Backend repo:
https://github.com/pawelklejka/airlines-backend
### React Frontend repo:
https://github.com/pawelklejka/airlines-frontend

#### This is repo which contains: 
- Spring Boot Config Server
- Spring Boot Gateway
- Spring Boot Eureka Discovery Server
- Spring Boot Flight, Ticket, Tourist and Ticket PDF Generator(TODO) services, which are connected to MongoDB 
- Spring Boot microservice responsible for mail sending (TODO)

![arilines-microservices](https://user-images.githubusercontent.com/24233415/190186417-b7fa368c-f4ee-48d3-a72a-1060ecc7f668.png)

#### To get all the endpoints you can use visit:
- ``` {GATEWAY_URL}/swagger-ui.html ```

![swagger](https://user-images.githubusercontent.com/24233415/192568981-e41c7df5-eb15-4d24-a753-9cdbe3456b08.png)

#### To visit RabbitMQ firstly:
- ``` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.10-management ```
#### Then visit and type in username and password:
- ``` localhost:15672 ```
- ``` username: guest ```
- ``` password: guest ```


![rabbit](https://user-images.githubusercontent.com/24233415/192576195-06c5eff3-5420-4da1-89f5-ab901a72e4ad.png)


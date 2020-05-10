# Product-Order
Sample microservices application with Spring Boot, Zuul, Eureka and H2

      Tools and Technologies:
			
			Java 8
			Spring Boot 
			Spring Cloud - Open Feign 
			Netflix Zuul
			Netflix Eureka Client/Server 
			Netflix Ribbon 
			Spring Data JPA
			Hibernate
			H2 Database
			Maven
			
 Product service: Provides API for managing products.
 
 Order service: Manage orders for products.
 
 Service discovery: Netflix Eureka service that discovers and registers other service instances. By default it runs on port 8761.
 
 API gateway: Netflix Zuul API gateway that sits on the top of the product and order services, providing a gateway for those services. 

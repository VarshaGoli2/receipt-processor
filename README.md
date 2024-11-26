I've used Java 21 and springboot for this project.
Can be run locally or in a Docker container.
I've tested the 2 inputs given - Target, M&M Corner Market (Retailers).

Prerequisites

  1.  Install Java (change version if it not 21 in dockerfile).
	2.	Install Maven.
	3.	Install Docker (optional, if running in a container).

To build- mvn clean package
To run - mvn spring-boot:run


Build and Run the Docker Image

docker build -t receipt-processor .
docker run -p 8080:8080 receipt-processor

Key files- ReceiptController, ReceiptService, ReceiptModel

 

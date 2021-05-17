FROM openjdk:8
ADD target/orderservice-0.0.1-SNAPSHOT.jar order-service-image
EXPOSE 8082
ENTRYPOINT ["java","-jar","order-service-image"]
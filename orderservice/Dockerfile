# sıfır bir makinede yani freedos
# bu uygulamayı nasıl ayağa kaldırabiliriz.

#bir layer ı alınca içindeki layerlar da geliyor
#oracle linux, open jdk 17 ve maven 3.8.3 geldi aşağıdaki satırla
FROM maven:3.8.3-openjdk-17 as build
#kaynak kodlaı attık makineye kopyalayarak
WORKDIR /app
COPY .. /app/
RUN mvn clean package -DskipTests
#run image oluşturur
#çalıştırma ksımı bu da
FROM openjdk:17-jdk
COPY --from=build /app/target/orderservice-0.0.1-SNAPSHOT.jar /app/orderservice.jar
EXPOSE 8081
CMD java -jar /app/orderservice.jar

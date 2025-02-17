docker ps

docker-compose up -d

mvn clean install

mvn spring-boot:run in both payment and order

place order

curl -X POST "http://localhost:8080/orders/create?product=Laptop&amount=1000"


# Quote API

A tiny API built with Java + Spark.

## Run
mvn clean package
java -jar target/quote-api-1.0.0.jar

## Endpoints
- GET /        → random quote + student info
- GET /health  → { "status": "ok" }
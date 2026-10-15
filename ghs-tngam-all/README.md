# GHS Inc Asset Management System

A source-first starter project for enterprise asset inventory and lifecycle management.

## Stack
- Java 21, Spring Boot 3.4.x, Maven
- MongoDB as the system of record
- Apache Kafka for asset domain events
- Elasticsearch for search
- Angular frontend starter
- Docker Compose for local development
- AWS deployment guidance in `docs/AWS_DEPLOYMENT.md`

## Included functionality
- Create, list, retrieve, update and retire assets
- Assign and return assets
- Basic status validation and asset-tag uniqueness
- Kafka domain event publication
- Elasticsearch indexing/search endpoint
- Angular inventory UI starter
- Dockerfiles, local infrastructure, sample requests and tests

> This is a runnable starter, not a certified production system. Before production use, complete the hardening checklist in `docs/PRODUCTION_CHECKLIST.md`. Direct DB-save-then-Kafka publication has a failure window; the production guide describes an outbox upgrade.

## Prerequisites
- JDK 21
- Maven 3.9+
- Docker Desktop / Docker Engine with Compose
- Node.js 20+ and npm (for frontend)

## Run infrastructure
```bash
docker compose up -d
```

## Run backend
```bash
cd backend
mvn spring-boot:run
```
Backend: `http://localhost:8080`
Health: `http://localhost:8080/actuator/health`

## Run frontend
```bash
cd frontend
npm install
npm start
```
Frontend: `http://localhost:4200`

The Angular dev server proxies `/api` to the backend using `proxy.conf.json`.

## Build backend JAR
```bash
cd backend
mvn clean package
```
Output: `backend/target/ghs-asset-management-1.0.0.jar`

## Sample API calls
```bash
curl -X POST http://localhost:8080/api/assets \
  -H 'Content-Type: application/json' \
  -d '{
    "assetTag":"GHS-LAP-0001",
    "name":"Developer Laptop",
    "category":"LAPTOP",
    "serialNumber":"SN-10001",
    "manufacturer":"Example",
    "model":"Pro 14",
    "location":"Chennai"
  }'

curl 'http://localhost:8080/api/assets?page=0&size=20'
curl 'http://localhost:8080/api/assets/search?q=laptop'
```

## Important
- Local Compose disables Elasticsearch security and uses single-node services. Do not expose these ports to the public internet.
- Set `MONGODB_URI`, `KAFKA_BOOTSTRAP_SERVERS`, and `ELASTICSEARCH_URIS` via environment variables in deployed environments.
- This ZIP does not contain cloud credentials, secrets, or a precompiled JAR. Build the JAR with Maven using the command above.

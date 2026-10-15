# API examples

## Create
```http
POST /api/assets
Content-Type: application/json

{
  "assetTag": "GHS-LAP-0001",
  "name": "Developer Laptop",
  "category": "LAPTOP",
  "serialNumber": "SN-10001",
  "manufacturer": "Example",
  "model": "Pro 14",
  "location": "Chennai",
  "purchaseCost": 85000
}
```

## Assign
```http
POST /api/assets/{id}/assign
Content-Type: application/json

{ "employeeId": "EMP-100" }
```

## Return / retire
```http
POST /api/assets/{id}/return
POST /api/assets/{id}/retire
```

## Search
```http
GET /api/assets/search?q=laptop
```
Search results are eventually consistent because Elasticsearch is updated asynchronously from Kafka.

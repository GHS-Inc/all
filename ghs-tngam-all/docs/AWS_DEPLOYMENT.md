# AWS Deployment Options

## Recommended production layout
- Angular static build: Amazon S3 + CloudFront
- Spring Boot container: Amazon ECS on Fargate behind an Application Load Balancer
- MongoDB: MongoDB Atlas on AWS (preferred if MongoDB compatibility is required); evaluate Amazon DocumentDB only after testing driver/operator compatibility
- Kafka: Amazon MSK
- Search: Amazon OpenSearch Service (validate API and client compatibility; managed OpenSearch is not identical to every Elasticsearch version)
- Secrets: AWS Secrets Manager
- Container registry: Amazon ECR
- Logs/metrics: CloudWatch
- DNS/TLS: Route 53 + ACM

## Deployment outline
1. Create separate dev, test and production VPCs or isolated environments; use private subnets for data services.
2. Provision Atlas/MSK/OpenSearch with private connectivity where supported. Enable TLS, authentication, encryption at rest, backups and least-privilege access.
3. Build the backend image:
   `docker build -t ghs-asset-management:1.0.0 ./backend`
4. Push the image to ECR and deploy it through an ECS task definition. Set `MONGODB_URI`, `KAFKA_BOOTSTRAP_SERVERS`, `ELASTICSEARCH_URIS` and credentials through Secrets Manager.
5. Build Angular:
   `cd frontend && npm ci && npm run build`
   Upload the generated browser output to a private S3 bucket and serve it through CloudFront with HTTPS.
6. Configure the ALB to route `/api/*` to ECS. Configure the frontend API base path to the same origin.
7. Add health checks, autoscaling, alarms, log retention, vulnerability scanning and rollback deployments.
8. Run smoke tests and verify asset create/update/assign/search end-to-end.

## Cost and operational note
AWS costs depend on region, instance sizing, traffic, retention and managed-service choices. Start with a non-production environment and estimate costs in the AWS Pricing Calculator before provisioning. Do not expose MongoDB, Kafka, or Elasticsearch/OpenSearch directly to the public internet.

## Apigee alternative
Apigee is an API management gateway, not a replacement for compute, MongoDB, Kafka or search. If using Google Cloud Apigee, host the backend and data services on a suitable cloud platform, then route API traffic through Apigee with OAuth/OIDC, quotas, analytics and policies.

# Production hardening checklist

- [ ] Add authentication (OIDC/JWT) and role-based authorization.
- [ ] Add DTO separation, field-level validation and consistent Problem Details errors.
- [ ] Replace direct Kafka publication with a transactional outbox and retryable publisher.
- [ ] Make event consumers idempotent by `eventId`; define retry and dead-letter policies.
- [ ] Add optimistic concurrency/atomic conditional state transitions to prevent double assignment.
- [ ] Add asset history and user identity attribution for every lifecycle change.
- [ ] Configure MongoDB unique index migration and verify index creation on deployment.
- [ ] Configure Elasticsearch mappings, analyzers, aliases, reindexing and reconciliation.
- [ ] Add integration tests using Testcontainers and end-to-end tests for Angular.
- [ ] Add rate limiting, CORS allowlists, security headers, request-size limits and audit logs.
- [ ] Use TLS, managed secrets, private networking, encryption, backups and tested restore procedures.
- [ ] Add metrics, distributed tracing, alerts, dashboards, SLOs and runbooks.
- [ ] Add CI/CD, dependency scanning, SBOM generation and image vulnerability scanning.
- [ ] Define retention, privacy, data deletion and disaster recovery policies.
- [ ] Load test with realistic asset counts and concurrent assignment attempts.

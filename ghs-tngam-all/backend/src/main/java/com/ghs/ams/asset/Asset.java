package com.ghs.ams.asset;

import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("assets")
public record Asset(
    @Id String id,
    @Indexed(unique = true) String assetTag,
    String name,
    String category,
    String serialNumber,
    String manufacturer,
    String model,
    AssetStatus status,
    String assignedTo,
    String location,
    BigDecimal purchaseCost,
    Instant purchaseDate,
    Instant warrantyExpiry,
    Instant createdAt,
    Instant updatedAt,
    long version
) {}

package com.ghs.ams.event;

import java.time.Instant;

public record AssetEvent(
    String eventId,
    int schemaVersion,
    String eventType,
    String assetId,
    String assetTag,
    Instant occurredAt
) {}

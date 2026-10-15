package com.ghs.ams.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "assets")
public record AssetSearchDocument(
    @Id String id, String assetTag, String name, String category,
    String serialNumber, String status, String assignedTo, String location
) {}

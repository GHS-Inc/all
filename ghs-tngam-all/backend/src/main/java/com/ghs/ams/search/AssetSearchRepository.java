package com.ghs.ams.search;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AssetSearchRepository extends ElasticsearchRepository<AssetSearchDocument, String> {
    List<AssetSearchDocument> findByNameContainingOrAssetTagContainingOrSerialNumberContaining(
        String name, String assetTag, String serialNumber);
}

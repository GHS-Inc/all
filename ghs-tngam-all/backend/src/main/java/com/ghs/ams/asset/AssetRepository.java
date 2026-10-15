package com.ghs.ams.asset;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetRepository extends MongoRepository<Asset, String> {
    Optional<Asset> findByAssetTag(String assetTag);
    boolean existsByAssetTag(String assetTag);
}

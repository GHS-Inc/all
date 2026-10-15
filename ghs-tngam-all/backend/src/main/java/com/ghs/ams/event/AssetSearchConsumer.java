package com.ghs.ams.event;

import com.ghs.ams.asset.Asset;
import com.ghs.ams.asset.AssetRepository;
import com.ghs.ams.search.AssetSearchDocument;
import com.ghs.ams.search.AssetSearchRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AssetSearchConsumer {
    private final AssetRepository assets;
    private final AssetSearchRepository search;

    public AssetSearchConsumer(AssetRepository assets, AssetSearchRepository search) {
        this.assets = assets;
        this.search = search;
    }

    @KafkaListener(topics = AssetEventPublisher.TOPIC, groupId = "ghs-asset-search-indexer")
    public void onAssetEvent(AssetEvent event) {
        assets.findById(event.assetId()).ifPresent(this::index);
    }

    private void index(Asset a) {
        search.save(new AssetSearchDocument(a.id(), a.assetTag(), a.name(), a.category(),
            a.serialNumber(), a.status().name(), a.assignedTo(), a.location()));
    }
}

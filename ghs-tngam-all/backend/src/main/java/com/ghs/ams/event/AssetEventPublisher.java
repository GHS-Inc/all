package com.ghs.ams.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AssetEventPublisher {
    public static final String TOPIC = "asset-events";
    private final KafkaTemplate<String, AssetEvent> kafkaTemplate;

    public AssetEventPublisher(KafkaTemplate<String, AssetEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(AssetEvent event) {
        kafkaTemplate.send(TOPIC, event.assetId(), event);
    }
}

package com.ghs.ams.asset;

import com.ghs.ams.event.AssetEvent;
import com.ghs.ams.event.AssetEventPublisher;
import java.time.Instant;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AssetService {
    private final AssetRepository repository;
    private final AssetEventPublisher events;

    public AssetService(AssetRepository repository, AssetEventPublisher events) {
        this.repository = repository;
        this.events = events;
    }

    public Asset create(AssetController.CreateAssetRequest r) {
        String tag = r.assetTag().trim().toUpperCase();
        if (repository.existsByAssetTag(tag)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Asset tag already exists");
        }
        Instant now = Instant.now();
        Asset asset = new Asset(null, tag, r.name().trim(), r.category().trim().toUpperCase(),
            r.serialNumber(), r.manufacturer(), r.model(), AssetStatus.AVAILABLE, null,
            r.location(), r.purchaseCost(), r.purchaseDate(), r.warrantyExpiry(), now, now, 0);
        Asset saved = repository.save(asset);
        publish(saved, "ASSET_CREATED");
        return saved;
    }

    public Page<Asset> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Asset getById(String id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset not found"));
    }

    public Asset update(String id, AssetController.UpdateAssetRequest r) {
        Asset old = getById(id);
        if (old.status() == AssetStatus.RETIRED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Retired assets cannot be updated");
        }
        Asset updated = new Asset(old.id(), old.assetTag(),
            r.name() == null ? old.name() : r.name(),
            r.category() == null ? old.category() : r.category(),
            r.serialNumber() == null ? old.serialNumber() : r.serialNumber(),
            r.manufacturer() == null ? old.manufacturer() : r.manufacturer(),
            r.model() == null ? old.model() : r.model(),
            old.status(), old.assignedTo(),
            r.location() == null ? old.location() : r.location(),
            r.purchaseCost() == null ? old.purchaseCost() : r.purchaseCost(),
            old.purchaseDate(), old.warrantyExpiry(), old.createdAt(), Instant.now(), old.version() + 1);
        Asset saved = repository.save(updated);
        publish(saved, "ASSET_UPDATED");
        return saved;
    }

    public Asset assign(String id, String employeeId) {
        Asset old = getById(id);
        if (old.status() != AssetStatus.AVAILABLE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Only available assets can be assigned");
        }
        Asset saved = change(old, AssetStatus.ASSIGNED, employeeId, "ASSET_ASSIGNED");
        return saved;
    }

    public Asset returnAsset(String id) {
        Asset old = getById(id);
        if (old.status() != AssetStatus.ASSIGNED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Only assigned assets can be returned");
        }
        return change(old, AssetStatus.AVAILABLE, null, "ASSET_RETURNED");
    }

    public Asset retire(String id) {
        Asset old = getById(id);
        if (old.status() == AssetStatus.ASSIGNED) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Return asset before retiring");
        }
        return change(old, AssetStatus.RETIRED, null, "ASSET_RETIRED");
    }

    private Asset change(Asset old, AssetStatus status, String assignee, String eventType) {
        Asset saved = repository.save(new Asset(old.id(), old.assetTag(), old.name(), old.category(),
            old.serialNumber(), old.manufacturer(), old.model(), status, assignee, old.location(),
            old.purchaseCost(), old.purchaseDate(), old.warrantyExpiry(), old.createdAt(),
            Instant.now(), old.version() + 1));
        publish(saved, eventType);
        return saved;
    }

    private void publish(Asset asset, String type) {
        events.publish(new AssetEvent(UUID.randomUUID().toString(), 1, type,
            asset.id(), asset.assetTag(), Instant.now()));
    }
}

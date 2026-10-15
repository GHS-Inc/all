package com.ghs.ams.asset;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService service;

    public AssetController(AssetService service) { this.service = service; }

    public record CreateAssetRequest(
        @NotBlank String assetTag, @NotBlank String name, @NotBlank String category,
        String serialNumber, String manufacturer, String model, String location,
        BigDecimal purchaseCost, Instant purchaseDate, Instant warrantyExpiry
    ) {}
    public record UpdateAssetRequest(
        String name, String category, String serialNumber, String manufacturer,
        String model, String location, BigDecimal purchaseCost
    ) {}
    public record AssignmentRequest(@NotBlank String employeeId) {}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Asset create(@Valid @RequestBody CreateAssetRequest request) {
        return service.create(request);
    }

    @GetMapping
    public Page<Asset> list(Pageable pageable) { return service.findAll(pageable); }

    @GetMapping("/{id}")
    public Asset get(@PathVariable String id) { return service.getById(id); }

    @PutMapping("/{id}")
    public Asset update(@PathVariable String id, @RequestBody UpdateAssetRequest request) {
        return service.update(id, request);
    }

    @PostMapping("/{id}/assign")
    public Asset assign(@PathVariable String id, @Valid @RequestBody AssignmentRequest request) {
        return service.assign(id, request.employeeId());
    }

    @PostMapping("/{id}/return")
    public Asset returnAsset(@PathVariable String id) { return service.returnAsset(id); }

    @PostMapping("/{id}/retire")
    public Asset retire(@PathVariable String id) { return service.retire(id); }
}

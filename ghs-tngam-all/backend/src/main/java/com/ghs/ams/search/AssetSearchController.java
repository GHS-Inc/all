package com.ghs.ams.search;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assets")
public class AssetSearchController {
    private final AssetSearchService service;
    public AssetSearchController(AssetSearchService service) { this.service = service; }

    @GetMapping("/search")
    public List<AssetSearchDocument> search(@RequestParam String q) {
        return service.search(q);
    }
}

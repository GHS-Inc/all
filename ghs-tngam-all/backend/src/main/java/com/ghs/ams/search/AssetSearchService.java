package com.ghs.ams.search;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AssetSearchService {
    private final AssetSearchRepository repository;
    public AssetSearchService(AssetSearchRepository repository) { this.repository = repository; }

    public List<AssetSearchDocument> search(String q) {
        String term = q == null ? "" : q.trim();
        if (term.isBlank()) return List.of();
        return repository.findByNameContainingOrAssetTagContainingOrSerialNumberContaining(term, term, term);
    }
}

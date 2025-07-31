package com.bitwarden.server.controller;

import com.bitwarden.server.dto.CollectionDto;
import com.bitwarden.server.entity.Collection;
import com.bitwarden.server.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collections")
@PreAuthorize("hasAuthority('Application')")
public class CollectionsController {

    private final CollectionService collectionService;

    @Autowired
    public CollectionsController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionDto> getCollection(@PathVariable UUID id) {
        Collection collection = collectionService.findById(id);
        return ResponseEntity.ok(convertToDto(collection));
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<CollectionDto>> getCollectionsByOrganization(@PathVariable UUID organizationId) {
        List<Collection> collections = collectionService.findByOrganizationId(organizationId);
        List<CollectionDto> dtos = collections.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<CollectionDto> createCollection(@RequestBody CollectionDto collectionDto) {
        Collection collection = convertToEntity(collectionDto);
        Collection savedCollection = collectionService.createCollection(collection);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(savedCollection));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionDto> updateCollection(@PathVariable UUID id, @RequestBody CollectionDto collectionDto) {
        collectionDto.setId(id);
        Collection collection = convertToEntity(collectionDto);
        Collection updatedCollection = collectionService.updateCollection(collection);
        return ResponseEntity.ok(convertToDto(updatedCollection));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable UUID id) {
        collectionService.deleteCollection(id);
        return ResponseEntity.noContent().build();
    }

    private CollectionDto convertToDto(Collection collection) {
        CollectionDto dto = new CollectionDto();
        dto.setId(collection.getId());
        dto.setOrganizationId(collection.getOrganizationId());
        dto.setName(collection.getName());
        dto.setExternalId(collection.getExternalId());
        return dto;
    }

    private Collection convertToEntity(CollectionDto dto) {
        Collection collection = new Collection();
        collection.setId(dto.getId());
        collection.setOrganizationId(dto.getOrganizationId());
        collection.setName(dto.getName());
        collection.setExternalId(dto.getExternalId());
        return collection;
    }
}

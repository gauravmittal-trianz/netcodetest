package com.bitwarden.server.service;

import com.bitwarden.server.entity.Collection;
import com.bitwarden.server.exception.NotFoundException;
import com.bitwarden.server.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CollectionService {

    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public Collection findById(UUID id) {
        return collectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Collection not found"));
    }

    public List<Collection> findByOrganizationId(UUID organizationId) {
        return collectionRepository.findByOrganizationIdOrderByName(organizationId);
    }

    public Collection createCollection(Collection collection) {
        if (collectionRepository.existsByOrganizationIdAndName(
                collection.getOrganizationId(), collection.getName())) {
            throw new IllegalArgumentException("Collection with this name already exists in the organization");
        }

        collection.setId(UUID.randomUUID());
        collection.setCreationDate(LocalDateTime.now());
        collection.setRevisionDate(LocalDateTime.now());

        return collectionRepository.save(collection);
    }

    public Collection updateCollection(Collection collection) {
        Collection existingCollection = findById(collection.getId());
        
        collection.setRevisionDate(LocalDateTime.now());
        return collectionRepository.save(collection);
    }

    public void deleteCollection(UUID id) {
        Collection collection = findById(id);
        collectionRepository.delete(collection);
    }

    public long countByOrganizationId(UUID organizationId) {
        return collectionRepository.countByOrganizationId(organizationId);
    }
}

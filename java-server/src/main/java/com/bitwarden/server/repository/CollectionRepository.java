package com.bitwarden.server.repository;

import com.bitwarden.server.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, UUID> {

    List<Collection> findByOrganizationId(UUID organizationId);

    @Query("SELECT c FROM Collection c WHERE c.organizationId = :organizationId ORDER BY c.name")
    List<Collection> findByOrganizationIdOrderByName(@Param("organizationId") UUID organizationId);

    @Query("SELECT COUNT(c) FROM Collection c WHERE c.organizationId = :organizationId")
    long countByOrganizationId(@Param("organizationId") UUID organizationId);

    boolean existsByOrganizationIdAndName(UUID organizationId, String name);
}

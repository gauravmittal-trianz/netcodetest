package com.bitwarden.server.repository;

import com.bitwarden.server.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    List<Device> findByUserId(UUID userId);

    List<Device> findByUserIdAndActive(UUID userId, boolean active);

    Optional<Device> findByUserIdAndIdentifier(UUID userId, String identifier);

    @Query("SELECT d FROM Device d WHERE d.userId = :userId AND d.pushToken IS NOT NULL")
    List<Device> findByUserIdWithPushToken(@Param("userId") UUID userId);

    void deleteByUserId(UUID userId);

    @Query("SELECT COUNT(d) FROM Device d WHERE d.userId = :userId AND d.active = true")
    long countActiveDevicesByUserId(@Param("userId") UUID userId);
}

package com.bitwarden.server.service;

import com.bitwarden.server.entity.Device;
import com.bitwarden.server.exception.NotFoundException;
import com.bitwarden.server.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device findById(UUID id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Device not found"));
    }

    public List<Device> findByUserId(UUID userId) {
        return deviceRepository.findByUserId(userId);
    }

    public List<Device> findActiveDevicesByUserId(UUID userId) {
        return deviceRepository.findByUserIdAndActive(userId, true);
    }

    public Device createDevice(Device device) {
        device.setId(UUID.randomUUID());
        device.setCreationDate(LocalDateTime.now());
        device.setRevisionDate(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    public Device updateDevice(Device device) {
        Device existingDevice = findById(device.getId());
        device.setRevisionDate(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    public void deleteDevice(UUID id) {
        Device device = findById(id);
        deviceRepository.delete(device);
    }

    public void deleteDevicesByUserId(UUID userId) {
        deviceRepository.deleteByUserId(userId);
    }

    public long countActiveDevicesByUserId(UUID userId) {
        return deviceRepository.countActiveDevicesByUserId(userId);
    }
}

package com.bitwarden.server.controller;

import com.bitwarden.server.dto.DeviceDto;
import com.bitwarden.server.entity.Device;
import com.bitwarden.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/devices")
@PreAuthorize("hasAuthority('Application')")
public class DevicesController {

    private final DeviceService deviceService;

    @Autowired
    public DevicesController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDevice(@PathVariable UUID id) {
        Device device = deviceService.findById(id);
        return ResponseEntity.ok(convertToDto(device));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DeviceDto>> getDevicesByUser(@PathVariable UUID userId) {
        List<Device> devices = deviceService.findByUserId(userId);
        List<DeviceDto> dtos = devices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto) {
        Device device = convertToEntity(deviceDto);
        Device savedDevice = deviceService.createDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(savedDevice));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDevice(@PathVariable UUID id, @RequestBody DeviceDto deviceDto) {
        deviceDto.setId(id);
        Device device = convertToEntity(deviceDto);
        Device updatedDevice = deviceService.updateDevice(device);
        return ResponseEntity.ok(convertToDto(updatedDevice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    private DeviceDto convertToDto(Device device) {
        DeviceDto dto = new DeviceDto();
        dto.setId(device.getId());
        dto.setUserId(device.getUserId());
        dto.setName(device.getName());
        dto.setType(device.getType());
        dto.setIdentifier(device.getIdentifier());
        dto.setActive(device.isActive());
        return dto;
    }

    private Device convertToEntity(DeviceDto dto) {
        Device device = new Device();
        device.setId(dto.getId());
        device.setUserId(dto.getUserId());
        device.setName(dto.getName());
        device.setType(dto.getType());
        device.setIdentifier(dto.getIdentifier());
        device.setActive(dto.isActive());
        return device;
    }
}

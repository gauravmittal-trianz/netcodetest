package com.bitwarden.server.entity;

import com.bitwarden.server.enums.DeviceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(name = "UserId")
    private UUID userId;

    @Size(max = 50)
    @Column(name = "Name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Type")
    private DeviceType type;

    @Size(max = 50)
    @Column(name = "Identifier")
    private String identifier;

    @Column(name = "PushToken", columnDefinition = "TEXT")
    private String pushToken;

    @CreationTimestamp
    @Column(name = "CreationDate")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "RevisionDate")
    private LocalDateTime revisionDate;

    @Column(name = "EncryptedUserKey", columnDefinition = "TEXT")
    private String encryptedUserKey;

    @Column(name = "EncryptedPublicKey", columnDefinition = "TEXT")
    private String encryptedPublicKey;

    @Column(name = "EncryptedPrivateKey", columnDefinition = "TEXT")
    private String encryptedPrivateKey;

    @Column(name = "Active")
    private boolean active = true;

    public Device() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDateTime revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getEncryptedUserKey() {
        return encryptedUserKey;
    }

    public void setEncryptedUserKey(String encryptedUserKey) {
        this.encryptedUserKey = encryptedUserKey;
    }

    public String getEncryptedPublicKey() {
        return encryptedPublicKey;
    }

    public void setEncryptedPublicKey(String encryptedPublicKey) {
        this.encryptedPublicKey = encryptedPublicKey;
    }

    public String getEncryptedPrivateKey() {
        return encryptedPrivateKey;
    }

    public void setEncryptedPrivateKey(String encryptedPrivateKey) {
        this.encryptedPrivateKey = encryptedPrivateKey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

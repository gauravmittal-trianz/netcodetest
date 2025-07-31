package com.bitwarden.server.entity;

import com.bitwarden.server.enums.GatewayType;
import com.bitwarden.server.enums.KdfType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Size(max = 50)
    private String name;

    @NotNull
    @Email
    @Size(max = 256)
    @Column(unique = true)
    private String email;

    @Column(name = "EmailVerified")
    private boolean emailVerified = false;

    @JsonIgnore
    @Size(max = 300)
    @Column(name = "MasterPassword")
    private String masterPassword;

    @Size(max = 50)
    @Column(name = "MasterPasswordHint")
    private String masterPasswordHint;

    @Size(max = 10)
    @Column(name = "Culture")
    private String culture = "en-US";

    @NotNull
    @Size(max = 50)
    @Column(name = "SecurityStamp")
    private String securityStamp;

    @Column(name = "TwoFactorProviders", columnDefinition = "TEXT")
    private String twoFactorProviders;

    @Size(max = 32)
    @Column(name = "TwoFactorRecoveryCode")
    private String twoFactorRecoveryCode;

    @Column(name = "EquivalentDomains", columnDefinition = "TEXT")
    private String equivalentDomains;

    @Column(name = "ExcludedGlobalEquivalentDomains", columnDefinition = "TEXT")
    private String excludedGlobalEquivalentDomains;

    @Column(name = "AccountRevisionDate")
    private LocalDateTime accountRevisionDate = LocalDateTime.now();

    @Column(name = "Key", columnDefinition = "TEXT")
    private String key;

    @Column(name = "PublicKey", columnDefinition = "TEXT")
    private String publicKey;

    @Column(name = "PrivateKey", columnDefinition = "TEXT")
    private String privateKey;

    @Column(name = "Premium")
    private boolean premium = false;

    @Column(name = "PremiumExpirationDate")
    private LocalDateTime premiumExpirationDate;

    @Column(name = "RenewalReminderDate")
    private LocalDateTime renewalReminderDate;

    @Column(name = "Storage")
    private Long storage;

    @Column(name = "MaxStorageGb")
    private Short maxStorageGb;

    @Enumerated(EnumType.STRING)
    @Column(name = "Gateway")
    private GatewayType gateway;

    @Size(max = 50)
    @Column(name = "GatewayCustomerId")
    private String gatewayCustomerId;

    @Size(max = 50)
    @Column(name = "GatewaySubscriptionId")
    private String gatewaySubscriptionId;

    @Column(name = "ReferenceData", columnDefinition = "TEXT")
    private String referenceData;

    @Size(max = 100)
    @Column(name = "LicenseKey")
    private String licenseKey;

    @NotNull
    @Size(max = 30)
    @Column(name = "ApiKey")
    private String apiKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "Kdf")
    private KdfType kdf = KdfType.PBKDF2_SHA256;

    @Column(name = "KdfIterations")
    private int kdfIterations = 100000;

    @Column(name = "KdfMemory")
    private Integer kdfMemory;

    @Column(name = "KdfParallelism")
    private Integer kdfParallelism;

    @CreationTimestamp
    @Column(name = "CreationDate")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "RevisionDate")
    private LocalDateTime revisionDate;

    @Column(name = "ForcePasswordReset")
    private boolean forcePasswordReset = false;

    @Column(name = "UsesKeyConnector")
    private boolean usesKeyConnector = false;

    @Column(name = "FailedLoginCount")
    private int failedLoginCount = 0;

    @Column(name = "LastFailedLoginDate")
    private LocalDateTime lastFailedLoginDate;

    @Size(max = 7)
    @Column(name = "AvatarColor")
    private String avatarColor;

    @Column(name = "LastPasswordChangeDate")
    private LocalDateTime lastPasswordChangeDate;

    @Column(name = "LastKdfChangeDate")
    private LocalDateTime lastKdfChangeDate;

    @Column(name = "LastKeyRotationDate")
    private LocalDateTime lastKeyRotationDate;

    @Column(name = "LastEmailChangeDate")
    private LocalDateTime lastEmailChangeDate;

    @Column(name = "VerifyDevices")
    private boolean verifyDevices = true;

    public User() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public String getMasterPasswordHint() {
        return masterPasswordHint;
    }

    public void setMasterPasswordHint(String masterPasswordHint) {
        this.masterPasswordHint = masterPasswordHint;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getSecurityStamp() {
        return securityStamp;
    }

    public void setSecurityStamp(String securityStamp) {
        this.securityStamp = securityStamp;
    }

    public String getTwoFactorProviders() {
        return twoFactorProviders;
    }

    public void setTwoFactorProviders(String twoFactorProviders) {
        this.twoFactorProviders = twoFactorProviders;
    }

    public String getTwoFactorRecoveryCode() {
        return twoFactorRecoveryCode;
    }

    public void setTwoFactorRecoveryCode(String twoFactorRecoveryCode) {
        this.twoFactorRecoveryCode = twoFactorRecoveryCode;
    }

    public String getEquivalentDomains() {
        return equivalentDomains;
    }

    public void setEquivalentDomains(String equivalentDomains) {
        this.equivalentDomains = equivalentDomains;
    }

    public String getExcludedGlobalEquivalentDomains() {
        return excludedGlobalEquivalentDomains;
    }

    public void setExcludedGlobalEquivalentDomains(String excludedGlobalEquivalentDomains) {
        this.excludedGlobalEquivalentDomains = excludedGlobalEquivalentDomains;
    }

    public LocalDateTime getAccountRevisionDate() {
        return accountRevisionDate;
    }

    public void setAccountRevisionDate(LocalDateTime accountRevisionDate) {
        this.accountRevisionDate = accountRevisionDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public LocalDateTime getPremiumExpirationDate() {
        return premiumExpirationDate;
    }

    public void setPremiumExpirationDate(LocalDateTime premiumExpirationDate) {
        this.premiumExpirationDate = premiumExpirationDate;
    }

    public LocalDateTime getRenewalReminderDate() {
        return renewalReminderDate;
    }

    public void setRenewalReminderDate(LocalDateTime renewalReminderDate) {
        this.renewalReminderDate = renewalReminderDate;
    }

    public Long getStorage() {
        return storage;
    }

    public void setStorage(Long storage) {
        this.storage = storage;
    }

    public Short getMaxStorageGb() {
        return maxStorageGb;
    }

    public void setMaxStorageGb(Short maxStorageGb) {
        this.maxStorageGb = maxStorageGb;
    }

    public GatewayType getGateway() {
        return gateway;
    }

    public void setGateway(GatewayType gateway) {
        this.gateway = gateway;
    }

    public String getGatewayCustomerId() {
        return gatewayCustomerId;
    }

    public void setGatewayCustomerId(String gatewayCustomerId) {
        this.gatewayCustomerId = gatewayCustomerId;
    }

    public String getGatewaySubscriptionId() {
        return gatewaySubscriptionId;
    }

    public void setGatewaySubscriptionId(String gatewaySubscriptionId) {
        this.gatewaySubscriptionId = gatewaySubscriptionId;
    }

    public String getReferenceData() {
        return referenceData;
    }

    public void setReferenceData(String referenceData) {
        this.referenceData = referenceData;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public KdfType getKdf() {
        return kdf;
    }

    public void setKdf(KdfType kdf) {
        this.kdf = kdf;
    }

    public int getKdfIterations() {
        return kdfIterations;
    }

    public void setKdfIterations(int kdfIterations) {
        this.kdfIterations = kdfIterations;
    }

    public Integer getKdfMemory() {
        return kdfMemory;
    }

    public void setKdfMemory(Integer kdfMemory) {
        this.kdfMemory = kdfMemory;
    }

    public Integer getKdfParallelism() {
        return kdfParallelism;
    }

    public void setKdfParallelism(Integer kdfParallelism) {
        this.kdfParallelism = kdfParallelism;
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

    public boolean isForcePasswordReset() {
        return forcePasswordReset;
    }

    public void setForcePasswordReset(boolean forcePasswordReset) {
        this.forcePasswordReset = forcePasswordReset;
    }

    public boolean isUsesKeyConnector() {
        return usesKeyConnector;
    }

    public void setUsesKeyConnector(boolean usesKeyConnector) {
        this.usesKeyConnector = usesKeyConnector;
    }

    public int getFailedLoginCount() {
        return failedLoginCount;
    }

    public void setFailedLoginCount(int failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }

    public LocalDateTime getLastFailedLoginDate() {
        return lastFailedLoginDate;
    }

    public void setLastFailedLoginDate(LocalDateTime lastFailedLoginDate) {
        this.lastFailedLoginDate = lastFailedLoginDate;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }

    public LocalDateTime getLastPasswordChangeDate() {
        return lastPasswordChangeDate;
    }

    public void setLastPasswordChangeDate(LocalDateTime lastPasswordChangeDate) {
        this.lastPasswordChangeDate = lastPasswordChangeDate;
    }

    public LocalDateTime getLastKdfChangeDate() {
        return lastKdfChangeDate;
    }

    public void setLastKdfChangeDate(LocalDateTime lastKdfChangeDate) {
        this.lastKdfChangeDate = lastKdfChangeDate;
    }

    public LocalDateTime getLastKeyRotationDate() {
        return lastKeyRotationDate;
    }

    public void setLastKeyRotationDate(LocalDateTime lastKeyRotationDate) {
        this.lastKeyRotationDate = lastKeyRotationDate;
    }

    public LocalDateTime getLastEmailChangeDate() {
        return lastEmailChangeDate;
    }

    public void setLastEmailChangeDate(LocalDateTime lastEmailChangeDate) {
        this.lastEmailChangeDate = lastEmailChangeDate;
    }

    public boolean isVerifyDevices() {
        return verifyDevices;
    }

    public void setVerifyDevices(boolean verifyDevices) {
        this.verifyDevices = verifyDevices;
    }

    public String getBillingEmailAddress() {
        return email != null ? email.toLowerCase().trim() : null;
    }

    public String getBillingName() {
        return name;
    }

    public String getSubscriberName() {
        return name != null && !name.trim().isEmpty() ? name : email;
    }

    public boolean isExpired() {
        return premiumExpirationDate != null && premiumExpirationDate.isBefore(LocalDateTime.now());
    }

    public boolean hasMasterPassword() {
        return masterPassword != null;
    }

    public long getStorageBytesRemaining() {
        if (maxStorageGb == null) {
            return 0;
        }
        return getStorageBytesRemaining(maxStorageGb);
    }

    public long getStorageBytesRemaining(short maxStorageGb) {
        long maxStorageBytes = maxStorageGb * 1073741824L;
        if (storage == null) {
            return maxStorageBytes;
        }
        return maxStorageBytes - storage;
    }
}

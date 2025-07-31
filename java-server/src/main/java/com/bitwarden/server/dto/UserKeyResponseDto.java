package com.bitwarden.server.dto;

import java.util.UUID;

public class UserKeyResponseDto {
    private UUID userId;
    private String publicKey;

    public UserKeyResponseDto() {}

    public UserKeyResponseDto(UUID userId, String publicKey) {
        this.userId = userId;
        this.publicKey = publicKey;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}

package com.bitwarden.server.service;

import com.bitwarden.server.entity.User;
import com.bitwarden.server.exception.NotFoundException;
import com.bitwarden.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Autowired
    public UserService(UserRepository userRepository, 
                      PasswordEncoder passwordEncoder,
                      MailService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }

        user.setId(UUID.randomUUID());
        user.setSecurityStamp(UUID.randomUUID().toString());
        user.setApiKey(UUID.randomUUID().toString());
        user.setCreationDate(LocalDateTime.now());
        user.setRevisionDate(LocalDateTime.now());
        user.setAccountRevisionDate(LocalDateTime.now());

        if (user.getMasterPassword() != null) {
            user.setMasterPassword(passwordEncoder.encode(user.getMasterPassword()));
        }

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        User existingUser = findById(user.getId());
        
        user.setRevisionDate(LocalDateTime.now());
        user.setAccountRevisionDate(LocalDateTime.now());
        
        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    public String getPublicKey(UUID userId) {
        return userRepository.findPublicKeyById(userId)
                .orElseThrow(() -> new NotFoundException("Public key not found for user"));
    }

    public LocalDateTime getAccountRevisionDate(UUID userId) {
        return userRepository.findAccountRevisionDateById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public boolean verifyPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getMasterPassword());
    }

    public void changePassword(UUID userId, String newPassword) {
        User user = findById(userId);
        user.setMasterPassword(passwordEncoder.encode(newPassword));
        user.setLastPasswordChangeDate(LocalDateTime.now());
        user.setRevisionDate(LocalDateTime.now());
        user.setAccountRevisionDate(LocalDateTime.now());
        userRepository.save(user);
    }

    public void sendPasswordHint(String email) {
        Optional<User> userOpt = findByEmail(email);
        if (userOpt.isEmpty()) {
            return;
        }

        User user = userOpt.get();
        if (user.getMasterPasswordHint() == null || user.getMasterPasswordHint().trim().isEmpty()) {
            mailService.sendNoPasswordHintEmail(email);
        } else {
            mailService.sendPasswordHintEmail(email, user.getMasterPasswordHint());
        }
    }

    public List<User> findExpiredPremiumUsers() {
        return userRepository.findExpiredPremiumUsers(LocalDateTime.now());
    }

    public long countPremiumUsers() {
        return userRepository.countPremiumUsers();
    }

    public void updateAccountRevisionDate(UUID userId) {
        User user = findById(userId);
        user.setAccountRevisionDate(LocalDateTime.now());
        userRepository.save(user);
    }
}

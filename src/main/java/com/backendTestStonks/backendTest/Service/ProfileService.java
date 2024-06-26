package com.backendTestStonks.backendTest.Service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backendTestStonks.backendTest.Model.Profile;
import com.backendTestStonks.backendTest.Model.SignupRequest;
import com.backendTestStonks.backendTest.Repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Profile signup(SignupRequest signupRequest) {
        if (profileRepository.findByEmail(signupRequest.getEmail()).isPresent() || 
            profileRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Email or Username already exists!");
        }

        Profile profile = new Profile();
        profile.setFullName(signupRequest.getFullName());
        profile.setUsername(signupRequest.getUsername());
        profile.setEmail(signupRequest.getEmail());
        profile.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        profile.setActive(true);
        profile.setCreatedAt(Timestamp.from(Instant.now()));
        profile.setUpdatedAt(Timestamp.from(Instant.now()));

        return profileRepository.save(profile);
    }
}

package com.backendTestStonks.backendTest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.backendTestStonks.backendTest.Model.Profile;
import com.backendTestStonks.backendTest.Model.SignupRequest;
import com.backendTestStonks.backendTest.Service.ProfileService;


@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/signup")
    public ResponseEntity<Profile> signup(@RequestBody SignupRequest signupRequest) {
        Profile profile = new Profile();
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Application is running";
    }
}

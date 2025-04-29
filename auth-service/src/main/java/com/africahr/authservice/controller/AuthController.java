package com.africahr.authservice.controller;

import com.africahr.authservice.model.User;
import com.africahr.authservice.repository.UserRepository;
import com.africahr.authservice.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @GetMapping("/login/oauth2/success")
    public String login(OAuth2AuthenticationToken authentication) {
        var attributes = authentication.getPrincipal().getAttributes();

        String googleId = attributes.get("sub").toString();
        String email = attributes.get("email").toString();
        String name = attributes.get("name").toString();
        String picture = attributes.get("picture").toString();

        // Save or update user
        User user = userRepository.findByEmail(email)
                .orElse(User.builder()
                        .googleId(googleId)
                        .email(email)
                        .fullName(name)
                        .profilePicture(picture)
                        .role(User.Role.STAFF)
                        .build());

        userRepository.save(user);

        // Generate JWT
        String token = jwtProvider.generateToken(email, user.getRole().name());

        return token;
    }
}


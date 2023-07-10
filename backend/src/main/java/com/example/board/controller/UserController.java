package com.example.board.controller;

import com.example.board.dto.JoinRequestDto;
import com.example.board.security.UserDetailsImpl;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtEncoder encoder;

    @PostMapping("/users")
    public void join(
            @RequestBody @Valid JoinRequestDto joinRequestDto
    ) {
        userService.join(joinRequestDto);
    }

    @PostMapping("/auth/token")
    public String login(
            Authentication authentication
    ) {
        Instant now = Instant.now();

        long expiry = 36000L;

        Long userId = ((UserDetailsImpl) authentication.getPrincipal()).getUser().getId();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(String.valueOf(userId))
                .claim("scope", scope)
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}

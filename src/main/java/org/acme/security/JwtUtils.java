package org.acme.security;

import java.time.Instant;
import java.util.Set;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

public class JwtUtils {
    private static final String ISSUER = "https://issuer.org";
    private static final long DURATION = 1800;
    private static final String SECRET = "4453fd5e8408dc24655669d0a37ef72e";

    public static String generateToken(String username, Set<String> roles) {
        JwtClaimsBuilder claimsBuilder = Jwt.claims()
                .issuer(ISSUER)
                .subject(username)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(DURATION))
                .groups(roles);
        return claimsBuilder.jws().signWithSecret(SECRET);
    }
}

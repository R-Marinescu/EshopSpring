package com.eshop.config.TokenConfig;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class TokenGenerator {

    @Value("${spring.security.jwt.secret}")
    private String secretKey;

    public String generateSessionToken(String username) {
        try {
            // Create a new JWT claims set
            JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder();
            claimsSetBuilder.subject(username); // Set the subject (username)
            claimsSetBuilder.issueTime(Date.from(Instant.now())); // Set the issue time
            // Set the expiration time (e.g., 1 hour from now)
            claimsSetBuilder.expirationTime(Date.from(Instant.now().plusSeconds(3600)));

            // Build the JWT claims set
            JWTClaimsSet claimsSet = claimsSetBuilder.build();

            // Create a JWS header with the HS256 algorithm
            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256).build();

            // Create a new signed JWT with the header and claims set
            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            // Create a MAC signer with the injected secret key
            MACSigner macSigner = new MACSigner(secretKey.getBytes());

            // Sign the JWT with the MAC signer
            signedJWT.sign(macSigner);

            // Serialize the signed JWT to a string
            return signedJWT.serialize();
        } catch (JOSEException e) {
            // Handle exception
            e.printStackTrace();
            return null;
        }
    }
}

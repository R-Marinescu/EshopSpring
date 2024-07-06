package com.eshop.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class JwtTokenProvider {

    @Value("${spring.security.jwt.secret}")
    private String secretKey;

    public String getUsernameFromJWT(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSObject jwsObject = JWSObject.parse(token);

            return jwsObject.verify(new MACVerifier(secretKey.getBytes()));
        } catch (JOSEException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}

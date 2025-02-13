package com.jiayee.walkin.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.jwt-token-provider")
public class JwtTokenProvider {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

  @Setter
  private String jwtSecret;

  @Setter
  private int jwtExpirationInMs;

  public String generateToken(Authentication authentication) {
    CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
    return Jwts.builder()
        .setSubject(Long.toString(customUserDetails.getId()))
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  Long getUserIdFromJwt(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();
    return Long.parseLong(claims.getSubject());
  }

  boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      LOGGER.error("Invalid JWT signature");
    } catch (MalformedJwtException e) {
      LOGGER.error("Invalid JWT token");
    } catch (ExpiredJwtException e) {
      LOGGER.error("Expired JWT token");
    } catch (UnsupportedJwtException e) {
      LOGGER.error("Unsupported JWT token");
    } catch (IllegalArgumentException e) {
      LOGGER.error("JWT claims string is empty.");
    }
    return false;
  }

}

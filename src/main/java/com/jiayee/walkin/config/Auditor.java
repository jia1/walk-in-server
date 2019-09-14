package com.jiayee.walkin.config;

import com.jiayee.walkin.security.CustomUserDetails;
import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

class Auditor implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null
        || !authentication.isAuthenticated()
        || authentication instanceof AnonymousAuthenticationToken) {
      return Optional.empty();
    }
    CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
    return Optional.ofNullable(userPrincipal.getId());
  }

}

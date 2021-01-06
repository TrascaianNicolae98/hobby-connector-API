package com.api.repository;

import com.api.entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
  ConfirmationToken findConfirmationTokenByConfirmationToken(String confirmationToken);
}

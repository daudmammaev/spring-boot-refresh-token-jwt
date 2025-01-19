package com.jwt.jwts.repository;

import java.util.Optional;

import com.jwt.jwts.models.ERole;
import com.jwt.jwts.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}

package com.fahimshahrierrasel.rokomari_demo.repository;

import com.fahimshahrierrasel.rokomari_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}

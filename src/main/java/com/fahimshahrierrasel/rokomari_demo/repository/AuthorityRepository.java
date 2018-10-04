package com.fahimshahrierrasel.rokomari_demo.repository;

import com.fahimshahrierrasel.rokomari_demo.model.Authority;
import com.fahimshahrierrasel.rokomari_demo.model.UserRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}

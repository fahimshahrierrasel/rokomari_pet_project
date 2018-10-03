package com.fahimshahrierrasel.rokomari_demo.repository;

import com.fahimshahrierrasel.rokomari_demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}

package com.fahimshahrierrasel.rokomari_demo.repository;

import com.fahimshahrierrasel.rokomari_demo.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

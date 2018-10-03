package com.fahimshahrierrasel.rokomari_demo.controller;

import com.fahimshahrierrasel.rokomari_demo.exception.ResourceNotFoundException;
import com.fahimshahrierrasel.rokomari_demo.model.Doctor;
import com.fahimshahrierrasel.rokomari_demo.model.Patient;
import com.fahimshahrierrasel.rokomari_demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    // Get all Patients
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Add new Patient
    @PostMapping("/insert/patient/new")
    public Patient addPatient(@Valid @RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // Get a Patient by id
    @RequestMapping(value = "/patients", method = RequestMethod.GET, headers = {"patient_id"})
    public Patient getAPatient(@RequestHeader("patient_id") Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id" , id));
    }

    // Update Patient by id
    @RequestMapping(value = "/update/patients", method = RequestMethod.PUT, headers = {"patient_id"})
    public Map<String, String> updatePatient(@RequestHeader("patient_id") Long id, @Valid @RequestBody Patient updatedPatient) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        patient.setName(updatedPatient.getName());
        patient.setMobile(updatedPatient.getMobile());
        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setOccupation(updatedPatient.getOccupation());
        patient.setSymptom_summery(updatedPatient.getSymptom_summery());

        patientRepository.save(patient);

        return Collections.singletonMap("status", "updated");
    }

    // Delete Patient by id
    @RequestMapping(value = "/delete/patients", method = RequestMethod.DELETE, headers = {"patient_id"})
    public Map<String, String> deletePatient(@RequestHeader("patient_id") Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));

        patientRepository.delete(patient);

        return Collections.singletonMap("status", "deleted");
    }
}

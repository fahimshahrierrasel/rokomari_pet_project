package com.fahimshahrierrasel.rokomari_demo.controller;

import com.fahimshahrierrasel.rokomari_demo.exception.ResourceNotFoundException;
import com.fahimshahrierrasel.rokomari_demo.model.Doctor;
import com.fahimshahrierrasel.rokomari_demo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    // Get All Doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Add new Doctor
    @PostMapping("/insert/doctor/new")
    public Doctor addDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get a Doctor by id
    @RequestMapping(value = "/doctors", method = RequestMethod.GET, headers = {"doctor_id"})
    public Doctor getADoctor(@RequestHeader("doctor_id") Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor", "id" , id));
    }

    // Update Doctor by id
    @RequestMapping(value = "/update/doctors", method = RequestMethod.PUT, headers = {"doctor_id"})
    public Map<String, String> updateDoctor(@RequestHeader("doctor_id") Long id, @Valid @RequestBody Doctor updatedDoctor) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));

        doctor.setName(updatedDoctor.getName());
        doctor.setDept(updatedDoctor.getDept());

        doctorRepository.save(doctor);

        return Collections.singletonMap("status", "updated");
    }

    // Delete Doctor by id
    @RequestMapping(value = "/delete/doctors", method = RequestMethod.DELETE, headers = {"doctor_id"})
    public Map<String, String> deleteDoctor(@RequestHeader("doctor_id") Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));

        doctorRepository.delete(doctor);

        return Collections.singletonMap("status", "deleted");
    }
}

package com.example.testovoe.data.repo;

import com.example.testovoe.data.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepo extends JpaRepository<Patient, Long> {

}

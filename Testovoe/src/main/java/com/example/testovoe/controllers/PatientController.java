package com.example.testovoe.controllers;

import com.example.testovoe.request.PatientRequestUpdate;
import com.example.testovoe.request.PatientRequestCreate;
import com.example.testovoe.responses.PatientResponse;
import com.example.testovoe.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
  private final PatientService patientService;

  @GetMapping("/{id}")
  public PatientResponse getPatientById(@PathVariable Long id) {
    return patientService.getPatientById(id);
  }

  @DeleteMapping("/{id}")
  public void deletePatientById(@PathVariable Long id) {
    patientService.deletePatient(id);
  }

  @PostMapping("/create")
  public void create(@RequestBody PatientRequestCreate patientRequestCreate) {
    patientService.createPatient(patientRequestCreate);
  }

  @PutMapping("/update")
  public void update(@RequestBody PatientRequestUpdate patientRequestUpdate) {
    patientService.updatePatient(patientRequestUpdate);
  }
}

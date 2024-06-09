package com.example.testovoe.controllers;

import com.example.testovoe.request.PatientRequestUpdate;
import com.example.testovoe.request.PatientRequestCreate;
import com.example.testovoe.responses.PatientResponse;
import com.example.testovoe.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
  private final PatientService patientService;

  @Operation(summary = "Получить пациента по ID")
  @PreAuthorize("hasAnyAuthority('ROLE_practitioner', 'ROLE_patient')")
  @GetMapping("/{id}")
  public PatientResponse getPatientById(@PathVariable Long id) {
    return patientService.getPatientById(id);
  }


  @Operation(summary = "Удалить пациента")
  @PreAuthorize("hasAuthority('ROLE_practitioner')")
  @DeleteMapping("/{id}")
  public void deletePatientById(@PathVariable Long id) {
    patientService.deletePatient(id);
  }


  @Operation(summary = "Создать нового пациента")
  @PreAuthorize("hasAuthority('ROLE_practitioner')")
  @PostMapping("/create")
  public void create(@RequestBody PatientRequestCreate patientRequestCreate) {
    patientService.createPatient(patientRequestCreate);
  }
  @Operation(summary = "Обновить пациента")
  @PreAuthorize("hasAuthority('ROLE_practitioner')")
  @PutMapping("/update")
  public void update(@RequestBody PatientRequestUpdate patientRequestUpdate) {
    patientService.updatePatient(patientRequestUpdate);
  }
}

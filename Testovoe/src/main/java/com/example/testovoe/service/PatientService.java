package com.example.testovoe.service;


import com.example.testovoe.ProblemType;
import com.example.testovoe.data.entity.Patient;
import com.example.testovoe.data.repo.PatientRepo;
import com.example.testovoe.mapper.PatientMapper;
import com.example.testovoe.request.PatientRequestUpdate;
import com.example.testovoe.request.PatientRequestCreate;
import com.example.testovoe.responses.PatientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepo patientRepo;

  public PatientResponse getPatientById(Long id) {
    Patient patient = patientRepo.findById(id)
      .orElseThrow(() -> new ResponseStatusException(
        ProblemType.PATIENT_NOT_FOUND_ID.getHttpStatus(),
        ProblemType.PATIENT_NOT_FOUND_ID.getMessage()
      ));
    return PatientMapper.toResponse(patient);
  }

  public void createPatient(PatientRequestCreate patientRequestCreate) {

    patientRepo.save(PatientMapper.toEntity(patientRequestCreate));
  }

  public void updatePatient(PatientRequestUpdate patpatientRequestUpdate) {
    Patient patient = patientRepo.findById(patpatientRequestUpdate.id())
      .orElseThrow(() -> new ResponseStatusException(
        ProblemType.PATIENT_NOT_FOUND_ID.getHttpStatus(),
        ProblemType.PATIENT_NOT_FOUND_ID.getMessage()
      ));

    patient.setName(patpatientRequestUpdate.name());
    patient.setGender(patpatientRequestUpdate.gender());
    patient.setBirthDate(patpatientRequestUpdate.birthDate());

    patientRepo.save(patient);
  }

  public void deletePatient(Long id) {
    if (!patientRepo.existsById(id)) {
      throw new ResponseStatusException(
        ProblemType.PATIENT_NOT_FOUND_ID.getHttpStatus(),
        ProblemType.PATIENT_NOT_FOUND_ID.getMessage()
      );
    }
    patientRepo.deleteById(id);
  }

}

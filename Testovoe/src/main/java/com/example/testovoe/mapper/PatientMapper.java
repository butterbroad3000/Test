package com.example.testovoe.mapper;

import com.example.testovoe.data.entity.Patient;
import com.example.testovoe.request.PatientRequestCreate;
import com.example.testovoe.responses.PatientResponse;


public class PatientMapper {
  public static PatientResponse toResponse(Patient patient) {
    return new PatientResponse(
      patient.getId(),
      patient.getName(),
      patient.getGender(),
      patient.getBirthDate()
    );
  }

  public static Patient toEntity(PatientRequestCreate request) {
    return new Patient(
      null,
      request.name(),
      request.gender(),
      request.birthDate()
    );
  }

}

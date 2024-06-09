package com.example.testovoe;

import com.example.testovoe.request.PatientRequestCreate;
import lombok.RequiredArgsConstructor;
import org.instancio.Instancio;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class GeneatorSevrice implements CommandLineRunner {
  private final PatientClient patientClient;

  @Override
  public void run(String... args) throws Exception {
    for (int i = 0; i < 100; i++) {
      PatientRequestCreate patient = Instancio.create(PatientRequestCreate.class);
      patientClient.createPatient(patient);
    }
  }
}

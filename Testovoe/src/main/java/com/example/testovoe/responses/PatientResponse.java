package com.example.testovoe.responses;

import com.example.testovoe.data.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientResponse(
  Long id,

  String name,

  Gender gender,

  LocalDateTime birthDate
) {
}

package com.example.testovoe.request;

import com.example.testovoe.data.Gender;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientRequestUpdate(
  Long id,
  String name,
  Gender gender,
  LocalDateTime birthDate
) {
}
package com.example.testovoe.request;

import com.example.testovoe.data.Gender;

import java.time.LocalDateTime;

public record PatientRequestCreate(

  String name,
  Gender gender,
  LocalDateTime birthDate
) {
}

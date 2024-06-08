package com.example.testovoe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProblemType {
  PATIENT_NOT_FOUND_ID("Patient not found with such id", HttpStatus.NOT_FOUND),
  PATIENT_NOT_FOUND_NAME("Patient not found with such name", HttpStatus.NOT_FOUND);

  private final String message;
  private final HttpStatus httpStatus;
}

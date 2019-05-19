package com.coollime.vehicle.exception;

public class RecordNotFoundException extends Exception {

  private String message;

  public RecordNotFoundException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}

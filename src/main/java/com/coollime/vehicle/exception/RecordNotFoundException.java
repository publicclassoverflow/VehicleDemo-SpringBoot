package com.coollime.vehicle.exception;

public class RecordNotFoundException extends Exception {

  private String message;

  /**
   * Thrown when there are no corresponding records found in the database per request
   * @param message Specified message of the exception
   */
  public RecordNotFoundException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}

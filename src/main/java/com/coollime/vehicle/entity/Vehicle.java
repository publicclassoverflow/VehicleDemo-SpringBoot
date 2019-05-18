package com.coollime.vehicle.entity;

import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

  @Id
  private int id;
  private int year;
  private String make;
  private String model;

  /**
   * Construct a Vehicle object using builder
   * This may look unnecessary, but when the Vehicle class evolves in the future,
   * the builder pattern will handle it well
   * @param builder The VehicleBuilder instance to build the Vehicle
   */
  private Vehicle(VehicleBuilder builder) {
    this.id = builder.id;
    this.year = builder.year;
    this.make = builder.make;
    this.model = builder.model;
  }

  public int getId() {
    return id;
  }

  /**
   * When database generates an id after successful insertion, the vehicle object needs to
   * be updated with the id
   * @param id The auto-generated id after database insertion
   */
  public void setId(int id) {
    this.id = id;
  }

  public int getYear() {
    return year;
  }

  public String getMake() {
    return make;
  }
  public String getModel() {
    return model;
  }

  public JSONObject toJsonObject() {
    JSONObject object = new JSONObject();
    try {
      object.put("id", id);
      object.put("year", year);
      object.put("make", make);
      object.put("model", model);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return object;
  }

  /**
   * The builder class for Vehicle objects
   */
  public static class VehicleBuilder {
    private int id;
    private int year;
    private String make;
    private String model;

    public void setId(int id) {
      this.id = id;
    }

    public void setYear(int year) {
      this.year = year;
    }

    public void setMake(String make) {
      this.make = make;
    }

    public void setModel(String model) {
      this.model = model;
    }

    public Vehicle build() {
      return new Vehicle(this);
    }
  }
}

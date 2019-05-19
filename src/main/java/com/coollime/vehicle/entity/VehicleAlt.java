package com.coollime.vehicle.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class VehicleAlt {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(nullable = false)
  @Range(min = 1950, max = 2050)
  private int year;

  @Column(nullable = false)
  private String make;

  @Column(nullable = false)
  private String model;

  public VehicleAlt() {

  }

  /**
   * Construct a Vehicle object using builder
   * This may look unnecessary, but when the Vehicle class evolves in the future,
   * the builder pattern will handle it well
   * @param builder The VehicleBuilder instance to build the Vehicle
   */
  private VehicleAlt(VehicleBuilder builder) {
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

    public VehicleAlt build() {
      return new VehicleAlt(this);
    }
  }
}

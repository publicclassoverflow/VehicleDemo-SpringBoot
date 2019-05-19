package com.coollime.vehicle.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

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

  public Vehicle() {

  }

  /**
   * Construct a Vehicle object using builder
   * This may look unnecessary, but when the Vehicle class evolves in the future,
   * the builder pattern will handle it well
   *
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

    public VehicleBuilder setId(int id) {
      this.id = id;
      return this;
    }

    public VehicleBuilder setYear(int year) {
      this.year = year;
      return this;
    }

    public VehicleBuilder setMake(String make) {
      this.make = make;
      return this;
    }

    public VehicleBuilder setModel(String model) {
      this.model = model;
      return this;
    }

    public Vehicle build() {
      return new Vehicle(this);
    }
  }

}

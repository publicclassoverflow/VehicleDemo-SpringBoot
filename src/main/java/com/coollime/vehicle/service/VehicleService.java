package com.coollime.vehicle.service;

import com.coollime.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleService {

  /**
   * Get all vehicles in the table
   *
   * @return A list of all saved vehicles in the table
   */
  List<Vehicle> getAllVehicles();

  /**
   * Get a specific vehicle given a vehicle id
   *
   * @param id The id of the querying vehicle
   * @return The vehicle with the specific id
   */
  Vehicle getVehicleById(int id);

  /**
   * Insert a vehicle into the table
   *
   * @param vehicle The vehicle entity to be inserted
   * @return The vehicle object that gets saved successfully
   */
  Vehicle saveVehicle(Vehicle vehicle);

  /**
   * Update an existing vehicle
   *
   * @param vehicle The vehicle with updated information
   */
  void updateVehicle(Vehicle vehicle);

  /**
   * Delete a vehicle from the table
   *
   * @param id The id of the vehicle to be deleted
   */
  void deleteVehicle(int id);
}

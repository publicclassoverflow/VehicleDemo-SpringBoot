package com.coollime.vehicle.service.impl;

import com.coollime.vehicle.dao.VehicleRepository;
import com.coollime.vehicle.entity.Vehicle;
import com.coollime.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

  private VehicleRepository vehicleRepository;

  /**
   * Get all saved vehicles using the findAll() method from JpaRepository
   *
   * @return A list of all saved vehicles
   */
  @Override
  public List<Vehicle> getAllVehicles() {
    return vehicleRepository.findAll();
  }

  /**
   * Get the vehicle object given a specified id using the findById() method from CrudRepository
   *
   * @param id The id of the querying vehicle
   * @return The vehicle object with the specified id
   */
  @Override
  public Vehicle getVehicleById(int id) {
    return vehicleRepository.findById(id).orElse(null);
  }

  /**
   * Save the vehicle object using the save() method from CrudRepository
   *
   * @param vehicle The vehicle entity to be inserted
   * @return The vehicle object that has been saved
   */
  @Override
  public Vehicle saveVehicle(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }

  /**
   * Update the vehicle with new information if it exists in the database
   * using the defined update() method in VehicleRepository
   *
   * @param vehicle The vehicle with updated information
   */
  @Override
  public void updateVehicle(Vehicle vehicle) {
    vehicleRepository.update(vehicle.getId(), vehicle.getYear(), vehicle.getMake(),
        vehicle.getModel());
  }

  /**
   * Delete a vehicle geven a specified id using the deleteById() method from CrudRepository
   *
   * @param id The id of the vehicle to be deleted
   */
  @Override
  public void deleteVehicle(int id) {
    vehicleRepository.deleteById(id);
  }

  @Autowired
  public void setVehicleRepository(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }
}

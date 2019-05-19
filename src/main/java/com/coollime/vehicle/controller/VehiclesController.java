package com.coollime.vehicle.controller;

import com.coollime.vehicle.entity.Vehicle;
import com.coollime.vehicle.exception.RecordNotFoundException;
import com.coollime.vehicle.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

  private VehicleServiceImpl vehicleServiceImpl;

  /**
   * Handle GET request to /vehicles
   * Response with all saved vehicles
   *
   * @return A list of all the vehicles saved in the database; empty list if no saved vehicles
   */
  @GetMapping
  public List<Vehicle> getAllVehicles() {
    return vehicleServiceImpl.getAllVehicles();
  }

  /**
   * Handle GET request to /vehicles/{id}
   * Reponse with the vehicle given the specified id
   *
   * @param id The ID of the specified vehicle
   * @return The specified vehicle if it is present. Null otherwise.
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Integer id) {
    // Try to fetch the vehicle of the given id
    Vehicle vehicle = vehicleServiceImpl.getVehicleById(id);
    // Set HTTP status code
    // 1. 200 if found
    // 2. 404 if no such a vehicle stored in the database
    HttpStatus status = vehicle == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    return new ResponseEntity<>(vehicle, status);
  }

  /**
   * Handle POST request to /vehicles
   * Reponse with the saved vehicle object
   *
   * @param requestBody The JSON request body which is parsed into a map
   * @return The vehicle object that has been built and saved
   */
  @PostMapping(consumes = "application/json")
  public Vehicle saveVehicle(@RequestBody Map<String, String> requestBody) {
    // Build the vehicle object from the JSON request body
    Vehicle vehicle = new Vehicle.VehicleBuilder()
        .setMake(requestBody.get("make"))
        .setModel(requestBody.get("model"))
        .setYear(Integer.parseInt(requestBody.get("year")))
        .build();

    return vehicleServiceImpl.saveVehicle(vehicle);
  }

  /**
   * Handle DELETE request to /vehicles/{id}
   *
   * @param id The ID of the vehicle that is specified to be deleted
   */
  @DeleteMapping(value = "/{id}")
  public void deleteVehicle(@PathVariable("id") Integer id) {
    vehicleServiceImpl.deleteVehicle(id);
  }

  /**
   * Handle PUT request to /vehicles
   * Update an existing vehicle with new information
   *
   * @param requestBody The JSON request body which is parsed into a map
   * @throws RecordNotFoundException
   */
  @PutMapping(consumes = "application/json")
  public void updateVehicle(@RequestBody Map<String, String> requestBody)
      throws RecordNotFoundException {
    // Check if a vehicle with the given id is present
    if (vehicleServiceImpl.getVehicleById(Integer.parseInt(requestBody.get("id"))) == null) {
      throw new RecordNotFoundException("No such vehicle");
    }
    // Update the vehicle with new info
    Vehicle newVehicle = new Vehicle.VehicleBuilder()
        .setId(Integer.parseInt(requestBody.get("id")))
        .setYear(Integer.parseInt(requestBody.get("year")))
        .setMake(requestBody.get("make"))
        .setModel(requestBody.get("model"))
        .build();
    vehicleServiceImpl.updateVehicle(newVehicle);
  }

  @Autowired
  public void setVehicleServiceImpl(VehicleServiceImpl vehicleServiceImpl) {
    this.vehicleServiceImpl = vehicleServiceImpl;
  }
}

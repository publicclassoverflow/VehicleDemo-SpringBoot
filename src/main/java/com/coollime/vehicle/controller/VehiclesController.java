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

  @GetMapping
  public List<Vehicle> getAllVehicles() {
    return vehicleServiceImpl.getAllVehicles();
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") Integer id) {
    Vehicle vehicle = vehicleServiceImpl.getVehicleById(id);
    return vehicle == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
        new ResponseEntity<>(vehicle, HttpStatus.OK);
  }

  @PostMapping(consumes = "application/json")
  public Vehicle saveVehicle(@RequestBody Map<String, String> payload) {
    Vehicle vehicle = new Vehicle.VehicleBuilder()
        .setMake(payload.get("make"))
        .setModel(payload.get("model"))
        .setYear(Integer.parseInt(payload.get("year")))
        .build();

    return vehicleServiceImpl.saveVehicle(vehicle);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteVehicle(@PathVariable("id") Integer id) {
    vehicleServiceImpl.deleteVehicle(id);
  }

  @PutMapping(consumes = "application/json")
  public void updateVehicle(@RequestBody Map<String, String> payload)
      throws RecordNotFoundException {
    // Check if a vehicle with the given id is present
    Vehicle oldVehicle = vehicleServiceImpl.getVehicleById(Integer.parseInt(payload.get("id")));
    if (oldVehicle == null) {
      throw new RecordNotFoundException("No such vehicle");
    }
    // Update the vehicle with new info
    Vehicle newVehicle = new Vehicle.VehicleBuilder()
        .setId(Integer.parseInt(payload.get("id")))
        .setYear(Integer.parseInt(payload.get("year")))
        .setMake(payload.get("make"))
        .setModel(payload.get("model"))
        .build();
    vehicleServiceImpl.updateVehicle(newVehicle);
  }

  @Autowired
  public void setVehicleServiceImpl(VehicleServiceImpl vehicleServiceImpl) {
    this.vehicleServiceImpl = vehicleServiceImpl;
  }
}

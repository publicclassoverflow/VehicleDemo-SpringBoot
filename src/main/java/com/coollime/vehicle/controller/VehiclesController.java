package com.coollime.vehicle.controller;

import com.coollime.vehicle.entity.Vehicle;
import com.coollime.vehicle.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

  private VehicleServiceImpl vehicleServiceImpl;

  @GetMapping
  public List<Vehicle> getAllVehicles() {
    return vehicleServiceImpl.getAllVehicles();
  }

  @Autowired
  public void setVehicleServiceImpl(VehicleServiceImpl vehicleServiceImpl) {
    this.vehicleServiceImpl = vehicleServiceImpl;
  }
}

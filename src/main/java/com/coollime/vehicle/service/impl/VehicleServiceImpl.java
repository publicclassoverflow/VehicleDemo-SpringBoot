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

  @Override
  public List<Vehicle> getAllVehicles() {
    List<Vehicle> allVehicles = vehicleRepository.findAll();

    return allVehicles;
  }

  @Override
  public Vehicle getVehicleById(int id) {
    return null;
  }

  @Override
  public boolean saveVehicle(Vehicle vehicle) {
    return false;
  }

  @Override
  public boolean updateVehicle(Vehicle vehicle) {
    return false;
  }

  @Override
  public boolean deleteVehicle(int id) {
    return false;
  }

  @Autowired
  public void setVehicleRepository(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }
}

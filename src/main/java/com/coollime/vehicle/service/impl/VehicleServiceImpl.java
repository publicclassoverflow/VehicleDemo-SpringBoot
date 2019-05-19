package com.coollime.vehicle.service.impl;

import com.coollime.vehicle.dao.VehicleRepository;
import com.coollime.vehicle.entity.Vehicle;
import com.coollime.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

  private VehicleRepository vehicleRepository;

  @Override
  public List<Vehicle> getAllVehicles() {
    return vehicleRepository.findAll();
  }

  @Override
  public Vehicle getVehicleById(int id) {
    return vehicleRepository.findById(id).orElse(null);
  }

  @Override
  public Vehicle saveVehicle(Vehicle vehicle) {
    return vehicleRepository.save(vehicle);
  }

  @Override
  public void updateVehicle(Vehicle vehicle) {
    vehicleRepository.updateVehicle(vehicle.getId(), vehicle.getYear(), vehicle.getMake(),
        vehicle.getModel());
  }

  @Override
  public void deleteVehicle(int id) {
    vehicleRepository.deleteById(id);
  }

  @Autowired
  public void setVehicleRepository(VehicleRepository vehicleRepository) {
    this.vehicleRepository = vehicleRepository;
  }
}

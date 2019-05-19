package com.coollime.vehicle.service.impl;

import com.coollime.vehicle.entity.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VehicleServiceImplTest {

  private VehicleServiceImpl vehicleServiceImpl;

  private List<Vehicle> allVehicles;

  private Vehicle mini;
  private Vehicle civic;

  /**
   * Set up the testing variables/dependencies
   * Initialize the testing database with two vehicles
   * id 1: 2016 Mini Cooper
   * id 2: 2018 Honda Civic
   * Use @Transactional annotation to avoid un-wanted operations on the database
   */
  @BeforeEach
  @Transactional
  public void setUp() {
    mini = new Vehicle.VehicleBuilder().setYear(2016).setMake("Mini")
        .setModel("Cooper").build();
    civic = new Vehicle.VehicleBuilder().setYear(2018).setMake("Honda")
        .setModel("Civic").build();
    vehicleServiceImpl.saveVehicle(mini);
    vehicleServiceImpl.saveVehicle(civic);
    allVehicles = new ArrayList<>();
    allVehicles.add(mini);
    allVehicles.add(civic);
  }

  @Test
  void whenGetVehicleByIdOne_returnVehicleWithIdOne() {
    Vehicle actual = vehicleServiceImpl.getVehicleById(1);
    assertEquals(mini, actual);
  }

  @Test
  void whenGetVehicleByIdTwo_returnVehicleWithIdTwo() {
    Vehicle actual = vehicleServiceImpl.getVehicleById(2);
    assertEquals(civic, actual);
  }

  @Test
  void whenGetVehicleByIdNotExists_returnNull() {
    assertNull(vehicleServiceImpl.getVehicleById(3));
  }

  @Test
  void whenGetAllVehicles_returnAllVehicles() {
    List<Vehicle> actualVehicles = vehicleServiceImpl.getAllVehicles();
    assertEquals(allVehicles, actualVehicles);
  }

  @Test
  void whenSaveVehicle_returnSavedVehicle() {
    Vehicle sti = new Vehicle.VehicleBuilder().setYear(2015).setMake("Subaru")
        .setModel("Sti").build();
    Vehicle saved = vehicleServiceImpl.saveVehicle(sti);
    assertEquals(sti, saved);
  }

  @Test
  void whenUpdateVehicleIdOne_checkVehicleIdOneGetsNewInfo() {
    mini = new Vehicle.VehicleBuilder().setId(1).setYear(2018).setMake("MINI")
        .setModel("Cooper S").build();
    vehicleServiceImpl.updateVehicle(mini);
    Vehicle updated = vehicleServiceImpl.getVehicleById(1);
    assertEquals(mini, updated);
  }

  @Test
  void whenDeleteVehicleIdOne_findVehicleIdOneAndReturnNull() {
    vehicleServiceImpl.deleteVehicle(1);
    assertNull(vehicleServiceImpl.getVehicleById(1));
  }

  @Autowired
  public void setVehicleServiceImpl(VehicleServiceImpl vehicleServiceImpl) {
    this.vehicleServiceImpl = vehicleServiceImpl;
  }
}

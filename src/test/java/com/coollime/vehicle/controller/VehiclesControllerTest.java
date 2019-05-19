package com.coollime.vehicle.controller;

import com.coollime.vehicle.entity.Vehicle;
import com.coollime.vehicle.service.impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class VehiclesControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private VehicleServiceImpl vehicleServiceImpl;

  private List<Vehicle> allVehicles;

  private Vehicle mini;
  private Vehicle civic;

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
  void whenGetAllVehicles_returnAllVehicles() {
    webTestClient.get()
        .uri("/vehicles")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .isEqualTo(
            "[" +
                "{\"id\":1,\"year\":2016,\"make\":\"Mini\",\"model\":\"Cooper\"}," +
                "{\"id\":2,\"year\":2018,\"make\":\"Honda\",\"model\":\"Civic\"}" +
            "]"
        );
  }

  @Test
  void whenGetVehicleByIdOne_returnVehicleWithIdOne() {
    webTestClient.get()
        .uri("/vehicles/1")
        .exchange()
        .expectStatus().isOk()
        .expectBody(String.class)
        .isEqualTo("{\"id\":1,\"year\":2016,\"make\":\"Mini\",\"model\":\"Cooper\"}");
  }

}

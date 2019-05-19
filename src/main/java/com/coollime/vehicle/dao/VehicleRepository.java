package com.coollime.vehicle.dao;

import com.coollime.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

  /**
   * Query the database to update a vehicle with new information if it is present
   *
   * @param id    The id of the vehicle
   * @param year  The (new) year of the vehicle
   * @param make  The (new) make of the vehicle
   * @param model The (new) model of the vehicle
   */
  @Modifying
  @Transactional
  @Query("UPDATE Vehicle v SET v.make = :make, v.model = :model, v.year = :year WHERE v.id = :id")
  void update(@Param("id") int id,
              @Param("year") int year,
              @Param("make") String make,
              @Param("model") String model
  );
}

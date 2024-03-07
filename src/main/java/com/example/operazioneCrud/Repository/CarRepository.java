package com.example.operazioneCrud.Repository;

import com.example.operazioneCrud.Entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car,Long> {
}

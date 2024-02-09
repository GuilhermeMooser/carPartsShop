package moser.carShop.carShop.controllers;

import moser.carShop.carShop.dto.CarDTO;
import moser.carShop.carShop.entities.Car;
import moser.carShop.carShop.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping(value = "/{idCar}")
    public ResponseEntity<CarDTO> findById(@PathVariable Long idCar) {
        CarDTO c = carService.findById(idCar);
        return ResponseEntity.ok().body(c);
    }

    @GetMapping(value = "/allCars")
    public ResponseEntity<List<CarDTO>> findAll() {
        List<CarDTO> c = carService.findAll();
        return ResponseEntity.ok().body(c);
    }

    @PostMapping(value = "")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        Car c = carService.createCar(carDTO.transformToObject());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
        return ResponseEntity.created(uri).body(new CarDTO(c));
    }

    @DeleteMapping(value = "/{idCar}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long idCar) {
        carService.delete(idCar);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idCar}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long idCar, @RequestBody CarDTO carDTO) {
        Car car = carService.update(idCar, carDTO);
        return ResponseEntity.ok().body(new CarDTO(car));
    }

}

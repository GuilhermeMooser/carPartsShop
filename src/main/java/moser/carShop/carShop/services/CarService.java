package moser.carShop.carShop.services;

import jakarta.persistence.EntityNotFoundException;
import moser.carShop.carShop.dto.CarDTO;
import moser.carShop.carShop.entities.Car;
import moser.carShop.carShop.repositories.CarRepository;
import moser.carShop.carShop.services.exceptions.DatabaseException;
import moser.carShop.carShop.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public CarDTO findById(Long idCar) {
        Optional<Car> car = repository.findById(idCar);

        if(car.get() != null) {
            CarDTO carDTO = new CarDTO(car.get());
            return carDTO;
        } else {
            throw new ResourceNotFoundException(idCar);
        }
    }

    public List<CarDTO> findAll() {
        List<Car> carList = repository.findAll();
        List<CarDTO> carDTOS = new ArrayList<>();
        for (Car c : carList) {
            carDTOS.add(new CarDTO(c));
        }

        return carDTOS;
    }

    public Car createCar(Car car) {
        car.setDtCriacao(new Date());
        Car c = repository.save(car);
        return c;
    }

    public void delete(Long idCar) {
        try {
            repository.deleteById(idCar);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(idCar);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Car update(Long idCar, CarDTO carDTO) {
        try {

            Car originalCar = repository.getReferenceById(idCar);
            updateCar(originalCar, carDTO.transformToObject());

            return repository.save(originalCar);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(idCar);
        }
    }

    public void updateCar(Car originalCar, Car c) {
        originalCar.setNome(c.getNome());
        originalCar.setAno(c.getAno());
        originalCar.setPiece(c.getPiece());
    }

}

package com.example.operazioneCrud.CarsController;

import com.example.operazioneCrud.Entity.Car;
import com.example.operazioneCrud.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    //crea nuova Car
    @PostMapping("/newcar")
    public Car createCar(@RequestBody Car car) {
        //queste metodo acetta un oggetto car come input
        Car savedCar = carRepository.save(car);
        //qui salva il oggeto car dentro il repository
        return savedCar;
        //torna un oggeto car che già è salvato
    }

    //restituisce la lista di tutte le Car
    @GetMapping("/listcars")
    public List<Car> getAllCars() {
        //questo metodo non acetta nessun parametro,ma restituice una lista di oggitti car
        List<Car> cars = (List<Car>) carRepository.findAll();
        //qui ti fa vedere tutti le oggti che abbiamo nell DB tra repository
        return cars;
        // tornera li oggtti dentro la lista
    }

    //prendi una macchina usando id
    @GetMapping("/{id}")
    public Optional<Car> getCar(@PathVariable long id) {
        //queste metodo prende come parametri un id
        Optional<Car> car = carRepository.findById(id);
        //usiamo il metodo findById che permette trovare un oggeto dentro del repository con i parametri
        return car;
        //per tornare un oggeto car che stava nel DB
    }


    //aggiorna type di una macchina esistente
    @PutMapping("/{id}")
    public Car updateCarType(@PathVariable Long id, @RequestParam Car carToUpdate) {
     // qui carichiamo il oggetto del DB
        Optional<Car> carFound = carRepository.findById(id);
        // modifichiamo il valore dell type con nell nuovo valore del campo
        carFound.get().setType(carToUpdate.getType());
        //qui salvo il objeto con il nuovo valoro del DB
        carRepository.save(carFound.get());
        //faccio tornare il objeto a postman al frondten
        return carFound.get();
    }

    //cancella Cars tra id
    @DeleteMapping("/{id}")
    public void deleteSingleCar(@PathVariable long id){
        //queste metodo prende un id come parametro per cancellare un oggetto
        carRepository.deleteById(id);
        //queste acetta il parametro per cancellare
    }

    //cancella tutte le Cars in db
    @DeleteMapping("/deleteAll")
    public void deleteAll(@RequestParam List<Long> ids){
        //Queste metodo prendre anche il id peroò per cancellare tutti oggetti dentro della lista
        carRepository.deleteAllById(ids);
        //queste acetta il id per cancellare la lista di ogetti
    }
}




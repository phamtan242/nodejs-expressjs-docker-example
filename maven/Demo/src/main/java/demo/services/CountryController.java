package demo.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import demo.entities.CountryEntity;
import demo.repositories.CountryRepository;
 
@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    CountryRepository countryRepository;
    
    @GetMapping
    public ResponseEntity<List<CountryEntity>> findAll() {
        List<CountryEntity> list = new ArrayList<CountryEntity>();
        countryRepository.findAll().forEach(list::add);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryEntity> getById(@PathVariable("id") int id) {
        Optional<CountryEntity> data = countryRepository.findById(id);

        if (data.isPresent()) {
            System.out.println(data.get().toString());
            System.out.println(data.get().getName());
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CountryEntity> create(@RequestBody CountryEntity country) {
        try {
            CountryEntity countryEntity = countryRepository
                .save(new CountryEntity(country.getName()));
            return new ResponseEntity<>(countryEntity, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryEntity> updateById(@PathVariable("id") int id, @RequestBody CountryEntity country) {
        Optional<CountryEntity> countryEntity = countryRepository.findById(id);
    
        if (countryEntity.isPresent()) {
            CountryEntity entity = countryEntity.get();
            entity.setName(country.getName());
            return new ResponseEntity<>(countryRepository.save(entity), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") int id) {
        try {
            countryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  
    
}
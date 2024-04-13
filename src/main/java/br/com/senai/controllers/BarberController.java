package br.com.senai.controllers;

import br.com.senai.models.Barber;
import br.com.senai.repositories.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/barber")
public class BarberController {
    @Autowired
    BarberRepository barberRepository;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Barber> getAllBarber(){
        return barberRepository.findAll();
    }

    @PostMapping(value="/createBarber",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Barber createBarber(@RequestBody Barber barber){
        Barber newBarber = new Barber();
        newBarber.setName(barber.getName());
        newBarber.setImg(barber.getImg());
        return barberRepository.save(newBarber);
    }

    @PutMapping(value="/updateBarber",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Barber updateBarber(@RequestBody Barber barber){
        Barber getBarber = barberRepository
                .findById(barber.getId()).orElseThrow();
        Barber updateBarber = new Barber();
        updateBarber.setName(barber.getName());
        updateBarber.setImg(barber.getImg());
        return barberRepository.save(updateBarber);
    }

    @DeleteMapping(value="/deleteBarber/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Barber deleteBarber(@PathVariable Long id){
        Barber getBarber = barberRepository.findById(id).orElseThrow();
        barberRepository.delete(getBarber);
        return getBarber;
    }
}

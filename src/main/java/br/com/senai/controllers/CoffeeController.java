package br.com.senai.controllers;

import br.com.senai.models.Coffee;
import br.com.senai.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {
    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Coffee> getAllCoffee(){
        return coffeeRepository.findAll();
    }

    @PostMapping(value="/createCoffee",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Coffee createCoffee(@RequestBody Coffee coffee){
        //Cria um novo objeto Coffee
        Coffee newCoffee = new Coffee();
        //Seta as propriedades do Coffee
        newCoffee.setName(coffee.getName());
        newCoffee.setPrice(coffee.getPrice());
        //Chama o método save para salvar o objeto no banco de dados
        return coffeeRepository.save(newCoffee);
    }
}

package springboot.vote_for_cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.DishRepository;
import springboot.vote_for_cafe.service.DishService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DishController {


    private DishService service;


    private DishRepository repository;

    @Autowired
    public DishController(DishService service,  DishRepository repository )
    {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("cafe/{cafeId}/dishes") // зачем model в параметрах?
    public List<Dish> getAllDishes( @PathVariable int cafeId) {
        return service.getAllDishes(cafeId);

    }

    @PostMapping(value = "/admin/cafe/{cafeId}/dishes")
    public ResponseEntity<Dish> save (@PathVariable int cafeId, @RequestBody Dish dish) {
         Dish created = service.save(cafeId, dish);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dishes" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/admin/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

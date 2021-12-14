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

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class DishController {

    private DishRepository repository;

    @Autowired
    public DishController(DishRepository repository) {
        this.repository = repository;
    }

    @GetMapping("cafe/{cafeId}/dishes") // это правильный url?
    public List<Dish> getDishes(Model model, @PathVariable int cafeId) {
        return repository.getAll(cafeId);

    }

    @PostMapping(value = "/admin/dishes")
    public ResponseEntity<Dish> save (@RequestBody Dish dish) {
        checkNew(dish);// посметреть validationUtil
        Dish created = repository.saveAndFlush(dish); //как добавить cafe id? непонятно, как это работает в topjava  в случае crudRepository
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dishes" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/admin/cafe/{cafeId}/dishes/{id}") //должна ли в url быть привязка к кафе и его id?
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int cafeId) {
       repository.delete(id, cafeId);
    }
}

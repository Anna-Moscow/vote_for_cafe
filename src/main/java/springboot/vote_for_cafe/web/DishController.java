package springboot.vote_for_cafe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.service.DishService;
import springboot.vote_for_cafe.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class DishController {

    private final DishService dishService;

    @Autowired
    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("api/cafes/{cafeId}/dishes")
    public List<Dish> getAllDishes(@PathVariable int cafeId) {
        return dishService.getAllDishes(cafeId);
    }

    @PostMapping(value = "api/admin/cafes/{cafeId}/dishes")
    public ResponseEntity<Dish> create(@PathVariable int cafeId, @Valid @RequestBody Dish dish) {
        ValidationUtil.checkNew(dish);
        Dish created = dishService.save(cafeId, dish);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dishes" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("api/admin/cafes/{cafeId}/dishes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int cafeId) {
        dishService.delete(id, cafeId);
    }

}

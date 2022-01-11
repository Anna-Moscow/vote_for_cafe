package springboot.vote_for_cafe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.service.DishService;
import springboot.vote_for_cafe.util.ValidationUtil;

import java.net.URI;
import java.util.List;

@RestController
public class DishController {


    private final DishService service;



    @Autowired
    public DishController(DishService service)
    {
        this.service = service;
        }

    @GetMapping("api/cafes/{cafeId}/dishes")
    public List<Dish> getAllDishes( @PathVariable int cafeId) {
        return service.getAllDishes(cafeId);

    }

    @PostMapping(value = "api/admin/cafes/{cafeId}/dishes")
    public ResponseEntity<Dish> create (@PathVariable int cafeId, @RequestBody Dish dish) {

        ValidationUtil.checkNew(dish);
         Dish created = service.save(cafeId, dish);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dishes" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("api/admin/dishes/{id}") // по правилам REST api/admin/cafes/{cafeId}/dishes/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)  //можно ли сделать свой delete c cafeId в репозитории?
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
    // сделать как delete meal, с проверкой и кафе id вместо user id
}

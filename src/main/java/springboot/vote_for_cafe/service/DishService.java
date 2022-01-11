package springboot.vote_for_cafe.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.DishRepository;
import springboot.vote_for_cafe.util.ValidationUtil;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {

  public DishRepository dishRepository;

  public CafeRepository cafeRepository;


    public DishService(DishRepository dishRepository, CafeRepository cafeRepository) {
        this.dishRepository = dishRepository;
        this.cafeRepository = cafeRepository;
    }

    public List<Dish> getAllDishes(int cafeId) {
        return dishRepository.getAll(cafeId);

    }


    public Dish save ( int cafeId,  Dish dish)  {
        dish.setCafe(cafeRepository.getById(cafeId));

        return dishRepository.save(dish);
            }


    public void delete( int id) {
        dishRepository.deleteById(id);
    }
}

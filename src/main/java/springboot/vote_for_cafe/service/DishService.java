package springboot.vote_for_cafe.service;


import org.springframework.stereotype.Service;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.DishRepository;

import java.util.List;

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

    public Dish save(int cafeId, Dish dish) {
        dish.setCafe(cafeRepository.getById(cafeId));
        return dishRepository.save(dish);
    }

    public void delete(int id, int cafeId) {
        Dish dish = dishRepository.getById(id);
        dishRepository.checkBelong(id, cafeId);
        dishRepository.delete(dish);
    }

}

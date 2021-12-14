package springboot.vote_for_cafe.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.model.User;

import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.cafe.id=:cafeId")
    List<Dish> getAll(@Param("cafeId") int cafeId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.cafe.id=:cafeId")
    int delete(@Param("id") int id, @Param("cafeId") int cafeId);
}

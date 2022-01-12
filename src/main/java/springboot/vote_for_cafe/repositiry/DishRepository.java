package springboot.vote_for_cafe.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import springboot.vote_for_cafe.error.DataConflictException;
import springboot.vote_for_cafe.model.Dish;

import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.cafe.id=:cafeId")
    List<Dish> getAll(@Param("cafeId") int cafeId);

    @Query("SELECT d FROM Dish d WHERE d.id = :id and d.cafe.id = :cafeId")
    Optional<Dish> get(int id, int cafeId);

    default Dish checkBelong(int id, int cafeId) {
        return get(id, cafeId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " doesn't belong to Cafe id=" + cafeId));
    }

}

package springboot.vote_for_cafe.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springboot.vote_for_cafe.model.Cafe;


@Repository
@Transactional(readOnly = true)
public interface CafeRepository extends JpaRepository<Cafe, Integer> {

}



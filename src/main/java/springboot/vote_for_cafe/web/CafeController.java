package springboot.vote_for_cafe.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.service.CafeService;
import springboot.vote_for_cafe.to.CafeTo;
import springboot.vote_for_cafe.util.CafeUtil;
import springboot.vote_for_cafe.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class CafeController {

    private final CafeService cafeService;

    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @PostMapping(value = "api/admin/cafes")
    public ResponseEntity<Cafe> create(@Valid @RequestBody Cafe cafe) {

        ValidationUtil.checkNew(cafe);
        Cafe created = cafeService.save(cafe);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cafes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("api/cafes")
    public List<CafeTo> getAll() {
        return CafeUtil.getTos(cafeService.getAll(), cafeService.getAllWithVotes());
    }


}

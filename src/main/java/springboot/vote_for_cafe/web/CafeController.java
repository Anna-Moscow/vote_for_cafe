package springboot.vote_for_cafe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.service.CafeService;
import springboot.vote_for_cafe.to.CafeTo;
import springboot.vote_for_cafe.util.CafeUtil;
import springboot.vote_for_cafe.util.ValidationUtil;

import java.net.URI;
import java.util.*;

@RestController
public class CafeController {

    private final CafeService cafeService;



    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @PostMapping(value = "api/admin/cafes")
    public ResponseEntity<Cafe> create(@RequestBody Cafe cafe) {

        ValidationUtil.checkNew(cafe);
        Cafe created = cafeService.save(cafe);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cafes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }


    @GetMapping("api/cafes") // убрала отсюда votes по принципам rest, правильно?
    public List<CafeTo> getAll() {

        return CafeUtil.getTos(cafeService.getAll(), cafeService.getAllWithVotes());
    }

}

package springboot.vote_for_cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.model.User;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.VoteRepository;
import springboot.vote_for_cafe.service.CafeService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CafeController {

    private CafeService cafeService;


    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @PostMapping(value = "/admin/cafes")
    // должны ли быть отдельные контроллеры для админа и юзеров?
    public ResponseEntity<Cafe> save(@RequestBody Cafe cafe) {
        Cafe created = cafeService.save(cafe);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cafes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }
//
//    @GetMapping("/cafes")
//    public List<Cafe> getCafes(Model model) {
//        return cafeRepository.findAll();
//
//    }


    @GetMapping("/cafes/votes")
    public Map<String, Integer> getAllWithVotes() {

      return cafeService.getAllWithVotes();
    }

}

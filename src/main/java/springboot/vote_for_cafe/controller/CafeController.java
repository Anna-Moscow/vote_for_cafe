package springboot.vote_for_cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;

@RestController
public class CafeController {

    private CafeRepository repository;

    @Autowired
    public CafeController(CafeRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = "/admin/cafes")
    // должны ли быть отдельные контроллеры для админа и юзеров?
    public ResponseEntity<Cafe> save(@RequestBody Cafe cafe) {
       Cafe created = repository.saveAndFlush(cafe);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cafes" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/cafes")
    public List<Cafe> getCafes(Model model) {
        return repository.findAll();

    }

    @PutMapping("/cafes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //как сделать, чтобы апдейт был только +1 к рейтингу?
    // пользователь передумал, как отнять рейтинг у предыдущего кафе и нужно ли это?
    public void update(@RequestBody Cafe cafe, @PathVariable int id) {
        if(LocalTime.now().isBefore(LocalTime.of(11, 0, 0))) {
            repository.saveAndFlush(cafe);
        }
    }
/*
    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:meals";
    }
    */

}

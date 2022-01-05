package springboot.vote_for_cafe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.env.Environment;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import springboot.vote_for_cafe.controller.DishController;
import springboot.vote_for_cafe.repositiry.DishRepository;
import springboot.vote_for_cafe.service.DishService;
import springboot.vote_for_cafe.TestData;

import javax.annotation.PostConstruct;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.vote_for_cafe.TestData.CAFE1_ID;
import static springboot.vote_for_cafe.TestData.DISH1_ID;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class DishControllerTest {


    @Autowired
    private MockMvc mockMvc;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }



    @Autowired
    private DishRepository dishRepository;



    @Test
    void getAllDishes() throws Exception {
        perform(MockMvcRequestBuilders.get("/cafe/" + CAFE1_ID + "/dishes"))
                .andExpect(status().isOk())
                .andDo(print());

        // нужно ли вставлять, если без фронтэнда?
        //.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                //.andExpect(MEAL_MATCHER.contentJson(meal1));
    }


    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete("/admin/dishes/" + DISH1_ID))
                .andExpect(status().isNoContent());
      // нужна ли такая проверка и как сделать? в образце NotFoundException
      //assertThrows(ChangeSetPersister.EntityNotFoundException.class, () -> dishRepository.getById(DISH1_ID));

        // нужен ли ValidationUtil из образца?

        // нужно ли тестировать отдельно репозитории? в образце на spring boot этого нет
        // как сделать тест на create без json?

    }
}

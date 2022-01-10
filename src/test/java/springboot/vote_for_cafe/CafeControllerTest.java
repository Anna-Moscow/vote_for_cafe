package springboot.vote_for_cafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.DishRepository;
import springboot.vote_for_cafe.util.JsonUtil;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.vote_for_cafe.TestData.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class CafeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }


    @Autowired
    private CafeRepository cafeRepository;

    @Test
    @WithUserDetails(value = TestData.USER_MAIL)
    public void getAllWithVotes() throws Exception {

        perform(MockMvcRequestBuilders.get("/api/cafes/votes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //postman - там подробнее
              // .andExpect(content().string(JsonUtil.writeValue(dishes))); // лайфхак
               // .andExpect(CAFE_MATCHER.contentJson(dishes));
        .andExpect(content().string(JsonUtil.writeValue(TestData.votes)));
       // assertThat(result, hasEntry("Japan", false));
    }



    @Test
    @WithUserDetails(value = TestData.ADMIN_MAIL)
    void save() throws Exception {
        Cafe newCafe = TestData.getNewCafe();
        ResultActions action = perform(MockMvcRequestBuilders
                .post("/api/admin/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newCafe)));

        Cafe created = CAFE_IGNORE_MATCHER.readFromJson(action);
        int newId = created.id();
        newCafe.setId(newId);
        CAFE_IGNORE_MATCHER.assertMatch(created, newCafe);
        CAFE_IGNORE_MATCHER.assertMatch(cafeRepository.getById(newId), newCafe);
    }
}

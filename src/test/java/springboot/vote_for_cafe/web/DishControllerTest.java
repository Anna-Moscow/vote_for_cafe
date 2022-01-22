package springboot.vote_for_cafe.web;

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
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.repository.DishRepository;
import springboot.vote_for_cafe.web.util.JsonUtil;
import springboot.vote_for_cafe.web.util.TestData;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.vote_for_cafe.web.util.TestData.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DishRepository dishRepository;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    @Test
    @WithUserDetails(value = TestData.USER_MAIL)
    void getAllDishes() throws Exception {
        perform(MockMvcRequestBuilders.get("/api/cafes/" + CAFE1_ID + "/dishes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishes));
    }

    @Test
    @WithUserDetails(value = TestData.ADMIN_MAIL)
    void create() throws Exception {
        Dish newDish = TestData.getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders
                .post("/api/admin/cafes/" + CAFE1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = DISH_IGNORE_CAFE_MATCHER.readFromJson(action);
        int newId = TestData.getDishId(created);
        newDish.setId(newId);
        DISH_IGNORE_CAFE_MATCHER.assertMatch(created, newDish);
        DISH_IGNORE_CAFE_MATCHER.assertMatch(dishRepository.getById(newId), newDish);
    }

    @Test
    void unauthorizedGetAllDishes() throws Exception {
        perform(MockMvcRequestBuilders.get("/api/cafes/" + CAFE1_ID + "/dishes"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = TestData.ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.
                delete("/api/admin/cafes/" + CAFE1_ID + "/dishes/" + DISH1_ID))
                .andExpect(status().isNoContent());
        assertFalse(dishRepository.findById(DISH1_ID).isPresent());
    }
}

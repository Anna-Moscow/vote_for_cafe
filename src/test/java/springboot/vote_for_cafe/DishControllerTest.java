package springboot.vote_for_cafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import springboot.vote_for_cafe.model.Dish;
import springboot.vote_for_cafe.repositiry.DishRepository;
import springboot.vote_for_cafe.util.JsonUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.vote_for_cafe.TestData.CAFE1_ID;
import static springboot.vote_for_cafe.TestData.DISH1_ID;
import static springboot.vote_for_cafe.TestData.dishes;
import static springboot.vote_for_cafe.TestData.DISH_MATCHER;
import static springboot.vote_for_cafe.TestData.DISH_IGNORE_CAFE_MATCHER;


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
    @WithUserDetails(value = TestData.USER_MAIL)
    void getAllDishes() throws Exception {
        perform(MockMvcRequestBuilders.get("/api/cafes/" + CAFE1_ID + "/dishes"))
                .andExpect(status().isOk())
                .andDo(print())
        // нужно ли вставлять Json, если без фронтэнда?
                // если нет, то MatcherFactory & JsonUtil надо удалить
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //postman - там подробнее
//                .andExpect(content().string(JsonUtil.writeValue(dishes))); // лайфхак
                .andExpect(DISH_MATCHER.contentJson(dishes));
                          }

    @Test
    @WithUserDetails(value = TestData.ADMIN_MAIL) //почему игнорируется кафе? как это работает?
    void create() throws Exception {
        Dish newDish = TestData.getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders
                .post("/api/admin/cafes/" + CAFE1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = DISH_IGNORE_CAFE_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_IGNORE_CAFE_MATCHER.assertMatch(created, newDish);
        DISH_IGNORE_CAFE_MATCHER.assertMatch(dishRepository.getById(newId), newDish);
    }
/*
    // для примера пердачи парметров не в get
    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        Meal updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        MEAL_MATCHER.assertMatch(mealRepository.getById(MEAL1_ID), updated);
    } */

    @Test
    void unauthorizedGetAllDishes() throws Exception {
        perform(MockMvcRequestBuilders.get("/api/cafes/" + CAFE1_ID + "/dishes"))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @WithUserDetails(value = TestData.ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete("/api/admin/dishes/" + DISH1_ID))
                .andExpect(status().isNoContent());
        assertFalse(dishRepository.findById(DISH1_ID).isPresent());

         // нужно ли тестировать отдельно репозитории? в образце на spring boot этого нет



    }
}

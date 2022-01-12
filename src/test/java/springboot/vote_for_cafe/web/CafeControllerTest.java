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
import springboot.vote_for_cafe.model.Cafe;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.util.CafeUtil;
import springboot.vote_for_cafe.web.util.JsonUtil;
import springboot.vote_for_cafe.web.util.TestData;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.vote_for_cafe.web.util.TestData.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class CafeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CafeRepository cafeRepository;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    @Test
    @WithUserDetails(value = TestData.USER_MAIL)
    public void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get("/api/cafes"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(CAFE_TO_MATCHER.contentJson(CafeUtil.getTos(cafes, votes)));
    }

    @Test
    @WithUserDetails(value = TestData.ADMIN_MAIL)
    void create() throws Exception {
        Cafe newCafe = TestData.getNewCafe();
        ResultActions action = perform(MockMvcRequestBuilders
                .post("/api/admin/cafes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newCafe)));

        Cafe created = CAFE_MATCHER.readFromJson(action);
        int newId = TestData.getCafeId(created);
        newCafe.setId(newId);
        CAFE_MATCHER.assertMatch(created, newCafe);
        CAFE_MATCHER.assertMatch(cafeRepository.getById(newId), newCafe);
    }
}

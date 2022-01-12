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
import springboot.vote_for_cafe.model.CafeVote;
import springboot.vote_for_cafe.repositiry.CafeVoteRepository;
import springboot.vote_for_cafe.web.util.JsonUtil;
import springboot.vote_for_cafe.web.util.TestData;

import static springboot.vote_for_cafe.web.util.TestData.CAFE1_ID;
import static springboot.vote_for_cafe.web.util.TestData.CAFE_VOTE_MATCHER;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CafeVoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CafeVoteRepository cafeVoteRepository;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    @Test
    @WithUserDetails(value = TestData.USER_MAIL)
    void create() throws Exception {
        CafeVote newCafeVote = TestData.getNewCafeVote();
        ResultActions action = perform(MockMvcRequestBuilders
                .post("/api/cafes/" + CAFE1_ID + "/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newCafeVote)));

        CafeVote created = CAFE_VOTE_MATCHER.readFromJson(action);
        int newId = TestData.getCafeVoteId(created);
        newCafeVote.setId(newId);
        CAFE_VOTE_MATCHER.assertMatch(created, newCafeVote);
        CAFE_VOTE_MATCHER.assertMatch(cafeVoteRepository.getById(newId), newCafeVote);
    }

}

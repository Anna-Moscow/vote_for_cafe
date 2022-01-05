package springboot.vote_for_cafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.vote_for_cafe.repositiry.CafeRepository;
import springboot.vote_for_cafe.repositiry.DishRepository;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.vote_for_cafe.TestData.CAFE1_ID;

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
    public void getAllWithVotes() throws Exception {

        perform(MockMvcRequestBuilders.get("/cafes/votes"))
                .andExpect(status().isOk())
                .andDo(print());
    }         //не работает !!! (с аннотацией Param  в Repo  и без нее тоже)
}

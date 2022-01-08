package springboot.vote_for_cafe.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springboot.vote_for_cafe.util.JsonUtil;

@Configuration
public class JsonUtilConfig {

    @Autowired
    private void setMapper(ObjectMapper objectMapper) {
        JsonUtil.setMapper(objectMapper);
    }
}

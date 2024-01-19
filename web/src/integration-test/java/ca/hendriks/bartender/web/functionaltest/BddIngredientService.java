package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.common.IngredientVO;
import ca.hendriks.bartender.common.exception.UnexpectedBartenderException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class BddIngredientService {

    private final ObjectMapper objectMapper;
    private final BddMockMvcService mockMvc;

    private MvcResult mvcResult;

    public BddIngredientService(final ObjectMapper objectMapper, final BddMockMvcService mockMvc) {
        this.objectMapper = objectMapper;
        this.mockMvc = mockMvc;
    }

    public void addIngredient(IngredientVO ingredient) {
        mvcResult = mockMvc.post("/ingredients", ingredient);
    }

    public List<IngredientVO> findIngredients() {
        final MvcResult mvcResult = mockMvc.get("/ingredients");
        return deserializeResult(mvcResult);
    }

    private List<IngredientVO> deserializeResult(final MvcResult mvcResult) {
        try {
            final String contentAsString = mvcResult.getResponse().getContentAsString();
            return objectMapper.readValue(contentAsString, new TypeReference<>() {
            });
        } catch (final UnsupportedEncodingException | JsonProcessingException e) {
            throw new UnexpectedBartenderException(e);
        }
    }

}

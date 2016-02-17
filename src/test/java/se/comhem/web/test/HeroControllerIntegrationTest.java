package se.comhem.web.test;

import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.comhem.web.test.controllers.HeroController;
import se.comhem.web.test.domain.Hero;
import se.comhem.web.test.services.HeroService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class contains JUnit test cases for HeroConroller
 * @author Prashant Pathania
 */
public class HeroControllerIntegrationTest {
    @Mock
    private HeroService heroServiceMock;
    @InjectMocks
    private HeroController heroController;
    private MockMvc mockMvc;

    /**
     * This method is called before executing JUnit test case
     * @return void
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(heroController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    /**
     * Method Under test: list()
     * Scenario: When data is not available
     * Expectation: it will reurn "heroes not defined" message
     */
    @Test
    public void testListHeroesWhenDataIsNotAvailable() throws Exception {
        this.mockMvc.perform(get("/heroes").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(content().string("\"Heroes are not defined\""));
    }

    /**
     * Method Under test: list()
     * Scenario: When data is available
     * Expectation: it will return json object
     */
    @Test
    public void testListHeroesWhenDataIsAvailable() throws Exception {
        Mockito.when(heroServiceMock.list()).thenReturn(new HashMap<Integer, Hero>());
        this.mockMvc.perform(get("/heroes").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    /**
     * This method is called after every JUnit test case to release memory
     * @return void
     */
    @After
    public void tearDown() {
        mockMvc = null;
        heroController = null;
    }
}

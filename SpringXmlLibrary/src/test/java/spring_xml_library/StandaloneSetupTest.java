package spring_xml_library;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spring_xml_library.controllers.OptionsController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class StandaloneSetupTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new OptionsController()).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/options/add")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(view().name("pages/add_options.jsp"));
    }
}

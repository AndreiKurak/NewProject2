package spring_xml_library;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring_xml_library.controllers.OptionsController;

import javax.servlet.ServletContext;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration/*("classpath:META-INF")*/
@ContextConfiguration("classpath:test_config.xml")
public class OptionsControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    LibService libServiceT;

    @Autowired
    OptionsController optionsControllerT;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        verifyRootWacSupport();
        given(this.libServiceT.list()).willReturn(new ArrayList<>());
    }

    @Test
    public void shouldPerformMethodAddAndReturnItsOptionsPage() throws Exception {
        this.mockMvc.perform(get("/options/add")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(view().name("pages/add_options.jsp"));
    }

    @Test
    public void shouldPerformMethodDeleteAndReturnItsOptionsPage() throws Exception {
        this.mockMvc.perform(get("/options/delete")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(view().name("pages/delete_options.jsp"));
    }

    private void verifyRootWacSupport() {
        assertNotNull(libServiceT);
        assertNotNull(optionsControllerT);

        /*ApplicationContext parent = wac.getParent();
        assertNotNull(parent);
        assertTrue(parent instanceof WebApplicationContext);
        WebApplicationContext root = (WebApplicationContext) parent;*/

        ServletContext childServletContext = wac.getServletContext();
        /*assertNotNull(childServletContext);
        ServletContext rootServletContext = root.getServletContext();
        assertNotNull(rootServletContext);
        assertSame(childServletContext, rootServletContext);

        assertSame(root, rootServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
        assertSame(root, childServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));*/
    }
}

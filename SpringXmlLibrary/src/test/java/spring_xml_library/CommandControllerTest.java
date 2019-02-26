package spring_xml_library;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import spring_xml_library.command_options.AddCommandOptions;
import spring_xml_library.controllers.CommandController;
import spring_xml_library.controllers.OptionsController;

import javax.servlet.ServletContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration/*("classpath:META-INF")*/
@ContextConfiguration("classpath:test_config.xml")
public class CommandControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    LibService libServiceT;

    @Autowired
    CommandController commandControllerT;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        //MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
        verifyRootWacSupport();

        List<Book> books = new ArrayList<>();
        books.add(new Book("Korvo", "Adventures", "1890"));
        given(this.libServiceT.list()).willReturn(books);
    }

    @Test
    public void shouldPerformAddCommandAndReturnToHomePage() throws Exception {
        this.mockMvc.perform(post("/command/add")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("home")).
                andExpect(view().name("home"));
        verify(libServiceT, times(1)).add(any());
    }

    @Test
    public void shouldPerformAddMethodOfLibServiceOnceInControllersAddMethod() throws Exception {
        this.mockMvc.perform(post("/command/add")).andDo(print()).
                andExpect(status().isOk());
        verify(libServiceT, times(1)).add(any());
    }

    @Test
    public void listMethodShouldReturnProperModelAttribute() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Korvo", "Adventures", "1890"));

        this.mockMvc.perform(post("/command/list")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(forwardedUrl("pages/view.jsp")).
                andExpect(view().name("pages/view.jsp")).
                andExpect(model().attribute("message", books)); /*org.hamcrest.Matchers.hasProperty;*/
    }

    @Test
    public void testValidation() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Korvo", "Adventures", "1890"));

        this.mockMvc.perform(post("/command/add").contentType(MediaType.APPLICATION_FORM_URLENCODED).
                param("year", "abc").
                param("author", "").
                param("title", "Falling").
                requestAttr("command", new AddCommandOptions())).

                andDo(print()).
                andExpect(status().isOk()).
                andExpect(model().attributeHasErrors("command")).
                andExpect(model().attributeHasFieldErrors("command", "author")).
                andExpect(forwardedUrl("options/add")).
                andExpect(model().attribute("message", books));
    }

    private void verifyRootWacSupport() {
        assertNotNull(libServiceT);
        assertNotNull(commandControllerT);

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

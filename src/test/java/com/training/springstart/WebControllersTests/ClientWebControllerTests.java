package com.training.springstart.WebControllersTests;

import com.training.springstart.controller.WebController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(WebController.class)
public class ClientWebControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectFactory<HttpSession> httpSessionFactory;

    @Test
    public void testRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(content().string(
                        containsString("REGISTER")));
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(content().string(
                        containsString("LOGIN")));
    }

//    @Test
//    public void testHomePage() throws Exception {
//        mockMvc.perform(get("/personal-area"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("personal-area"))
//                .andExpect(content().string(
//                        containsString("HOME")));
//    }

}

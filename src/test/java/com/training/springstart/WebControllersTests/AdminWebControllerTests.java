package com.training.springstart.WebControllersTests;

import com.training.springstart.controller.WebController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebController.class)
public class AdminWebControllerTests {

    @Autowired
    private MockMvc mockMvc;
//
//    @Test
//    public void testAdminLoginPage() throws Exception {
//        mockMvc.perform(get("/admin/admin-login"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("admin-login"))
//                .andExpect(content().string(
//                        containsString("ADMIN LOGIN")));
//    }
//
//    @Test
//    public void testAdminClientsPage() throws Exception {
//        mockMvc.perform(get("/admin/clients"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("clients"))
//                .andExpect(content().string(
//                        containsString("CLIENTS PANEL")));
//    }
//
//    @Test
//    public void testAdminOrdersPage() throws Exception {
//        mockMvc.perform(get("/admin/orders"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("orders"))
//                .andExpect(content().string(
//                        containsString("ORDERS PANEL")));
//    }
//
//    @Test
//    public void testAdminPanelPage() throws Exception {
//        mockMvc.perform(get("/admin/admin-area"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("admin-area"))
//                .andExpect(content().string(
//                        containsString("ADMIN PANEL")));
//    }

}

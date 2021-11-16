package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class LoginControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenGetLoginPageThenReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void whenGetLoginPageWithErrorThenReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login?error=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "Username or Password is incorrect !!"))
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void whenGetLoginPageWithLogoutThenReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login?logout=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "You have been successfully logged out !!"))
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void whenGetLogoutPageThenRedirectToLoginPageWithLogout() throws Exception {
        mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout=true"));
    }
}
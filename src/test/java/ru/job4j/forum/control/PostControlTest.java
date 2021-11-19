package ru.job4j.forum.control;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.ForumService;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private ForumService forumService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenGetCreatePageThenReturnEditPageWithEmptyFields() throws Exception {
        mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("post", hasProperty("id", is(0))))
                .andExpect(view().name("post/edit"));
    }

    @Test
    @WithMockUser
    public void whenGetUpdatePageThenReturnEditPageWithFilledFields() throws Exception {
        Post post = Post.of("name", "description");
        post.setId(1);

        when(forumService.findPostById(1)).thenReturn(post);

        mockMvc.perform(get("/update").param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("post", hasProperty("id", is(1))))
                .andExpect(view().name("post/edit"));
    }

    @Test
    public void whenGetIndexWithoutLoginThenRedirectToLoginPage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(forumService).save(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }
}
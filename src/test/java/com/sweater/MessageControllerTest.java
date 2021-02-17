package com.sweater;
import com.sweater.controllers.MessageController;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "kolya")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql","/message-list-before.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/message-list-after.sql","/create-user-after.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MessageController messageController;

    @Test
    public void mainPageTest() throws Exception{
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='navbarSupportedContent']/div").string("kolya"));
    }
    @Test
    public void messageListTest() throws Exception{
        this.mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(4));
    }

    @Test
    public void filterMessageTest() throws Exception{
        this.mockMvc.perform(get("/main").param("filter","1"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(1))
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=1]").exists());
    }
    @Test
    public void addMessageToListTest() throws Exception{
        MockHttpServletRequestBuilder multipart = multipart("/main")
                .file("file","123".getBytes())
                .param("text","fifth")
                .param("tag","5")
                .with(SecurityMockMvcRequestPostProcessors.csrf());
        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='message-list']/div").nodeCount(5))
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=5]").exists())
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=5]/div/span").string("fifth"))
                .andExpect(xpath("//div[@id='message-list']/div[@data-id=5]/div/i").string("5"));
    }


}

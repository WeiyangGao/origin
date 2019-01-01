package com.tcps.origin;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OriginApplicationTests {

    private static final Logger log= LoggerFactory.getLogger(OriginApplicationTests.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void initMocMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    //测试Get请求
    @Test
    public void querySuccess() throws Exception {
        mockMvc.perform(get("/test/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                //期望返回的集合的长度为3/期望返回的Bean的字段数量为3.
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    //测试Get请求
    @Test
    public void findUserByPathValue() throws Exception {
        String result = mockMvc.perform(get("/test/user/2")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    //测试Get请求
    @Test
    public void findUserByParam() throws Exception {
        String result = mockMvc.perform(get("/test/user/param")
                .param("userid", "3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    //测试Post请求
    @Test
    public void createUser() throws Exception {
        String content = new JSONObject()
                .put("username", "demoName")
                .put("birthday", new Date().getTime() + "")
                .put("password", "secret")
                .toString();
        String result = mockMvc.perform(post("/test/user/create")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    //测试Post请求
    @Test
    public void updateUser() throws Exception {
        String content = new JSONObject()
                .put("username", "updateName")
                .put("birthday", new Date().getTime() + "")
                .put("password", "updateSecret")
                .toString();
        String result = mockMvc.perform(put("/test/user/update")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    //测试Delete
    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(delete("/test/user/delete/1")
//                .param("userid", "3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void queryUser() throws Exception {
        String result = mockMvc.perform(get("/test/user/query/testName/11111111")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }

    @Test
    public void queryParamUser() throws Exception {
        String result = mockMvc.perform(get("/test/user/query")
                .param("userid", "3")
                .param("username", "testUsername")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.info(result);
    }
}


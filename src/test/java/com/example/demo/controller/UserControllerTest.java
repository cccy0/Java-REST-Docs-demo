package com.example.demo.controller;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springfox.documentation.staticdocs.SwaggerResultHandler;

import static org.junit.Assert.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
//                .alwaysDo(document("{method-name}/{step}/"))
                .build();
    }

    @Test
    public void SwaggerTest() throws Exception {
        mockMvc.perform(get("/v2/api-docs")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(SwaggerResultHandler.outputDirectory("target/swagger").build())
                .andExpect(status().isOk())
                .andReturn();
        Swagger2MarkupConverter.from("target/swagger/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withExamples("target/generated-snippets")
                .build()
                .intoFolder("target/swagger/out");
    }

//    @Test
//    public void list() throws Exception {
//        MvcResult result = this.mockMvc.perform(get("/api/users")
//                .characterEncoding("UTF-8")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("用户列表"))
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void getTest() throws Exception {
//        MvcResult result = this.mockMvc.perform(get("/api/users/1")
//                .characterEncoding("UTF-8")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("按id获取用户"))
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void createTest() throws Exception {
//        MvcResult result = this.mockMvc.perform(post("/api/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content("{\"name\":\"新用户\", \"age\":18}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("创建用户"))
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void changePassword() throws Exception {
//        MvcResult result = this.mockMvc.perform(put("/api/users/1/password")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content("{\"name\":\"修改密码的用户\", \"age\":18}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("修改密码"))
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void deleteTest() throws Exception {
//        MvcResult result = this.mockMvc.perform(delete("/api/users/1")
//                .characterEncoding("UTF-8")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(document("删除用户"))
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//    }
}
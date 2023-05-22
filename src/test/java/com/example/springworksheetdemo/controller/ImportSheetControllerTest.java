package com.example.springworksheetdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ImportSheetControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testUploadFileSuccess() throws Exception {

        this.mockMvc
                .perform(multipart("file")
                        .file(new MockMultipartFile("candidate-correct.xlsx",
                                new ClassPathResource("datatest/candidate-correct.xlsx").getInputStream())))
                .andExpect(flash().attribute("message", "Upload success"));

    }
}

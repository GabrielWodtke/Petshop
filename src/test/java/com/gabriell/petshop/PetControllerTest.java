package com.gabriell.petshop;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void consegueaddPet() throws Exception {

        String json = """
                {
                    "nome": "fofinho",
                    "especie": "gato",
                    "raca" : "vira-lata",
                    "dataNascimento": "2021-08-05"
                }
                """;
        mvc.perform(post("/pet/add/1").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER")).
                        content(json)).
                andExpect(status().isOk());
    }

    @Test
    void consegueEditarPet() throws Exception {
        String json = """
                {
                    "nome": "alice",
                    "especie": "gato",
                    "raca" : "siames",
                    "dataNascimento": "2023-06-20"
                }
                """;
        mvc.perform(put("/pet/edit/1").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER")).
                        content(json)).
                andExpect(status().isOk());
    }

    @Test
    void consegueBuscarPet() throws Exception {
        mvc.perform(get("/pet/1").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER"))).
                andExpect(status().isOk());
    }

    @Test
    void consegueDeletarPet() throws Exception {
        mvc.perform(delete("/pet/remove/2").with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                with(user("gabriel").password("123").roles("USER"))).
                andExpect(status().isOk());
    }
}

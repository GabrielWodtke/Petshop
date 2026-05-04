package com.gabriell.petshop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriell.petshop.entities.Cliente;
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
public class ClienteControllerTest {
    @Autowired
    private MockMvc mvc;


    void consegueaddCliente() throws Exception {

        String json = """
                {
                    "nome": "gabriel",
                    "email": "teste@gmail.com",
                    "telefone" : 1234567,
                    "senha": "123321"
                }
                """;
        var responseRaw = mvc.perform(post("/cliente/add").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER")).
                        content(json)).
                    andExpect(status().isOk()).andReturn();
        String response = responseRaw.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Cliente cliente = mapper.readValue(response, Cliente.class);
    }

    @Test
    void consegueBuscarCliente() throws Exception {
        mvc.perform(get("/cliente/buscar/1").with(csrf()).
                contentType(MediaType.APPLICATION_JSON).
                with(user("gabriel").password("123").roles("USER"))).
                andExpect(status().isOk());
    }

    @Test
    void consegueAtualizarCliente() throws Exception {
        String json = """
                {
                    "nome": "bomdia",
                    "email": "testeinho@hotmail.com",
                    "telefone" : 9988,
                    "senha": "9876542"
                }
                """;
        mvc.perform(put("/cliente/update/2").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER")).
                        content(json)).
                andExpect(status().isOk());
    }

    @Test
    void ConsegueRemoverCliente() throws Exception {
        mvc.perform(delete("/cliente/remove/2").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER"))).
                andExpect(status().isOk());
    }
}

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
public class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void consegueaddConsulta() throws Exception {

        String json = """
                {
                    "diagnostico": "conjuntivite",
                    "tratamento": "remedio",
                    "descricao" : "vira-lata",
                    "data": "2025-08-05T10:00:00"
                }
                """;
        mvc.perform(post("/Consulta/agendar/1").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER")).
                        content(json)).
                andExpect(status().isOk());
    }

    @Test
    void consegueBuscarConsulta() throws Exception {
        mvc.perform(get("/Consulta/buscar/1").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER"))).
                andExpect(status().isOk());
    }

    @Test
    void consegueAtualizarCliente() throws Exception {
        String json = """
                {
                    "diagnostico": "conjuntivite",
                    "tratamento": "remedio",
                    "descricao" : "vira-lata",
                    "data": "2025-08-05T15:00:00"
                }
                """;
        mvc.perform(put("/Consulta/editar/1").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER")).
                        content(json)).
                andExpect(status().isOk());
    }

    @Test
    void ConsegueRemoverConsulta() throws Exception {
        mvc.perform(delete("/Consulta/deletar/1").with(csrf()).
                        contentType(MediaType.APPLICATION_JSON).
                        with(user("gabriel").password("123").roles("USER"))).
                andExpect(status().isOk());
    }
}

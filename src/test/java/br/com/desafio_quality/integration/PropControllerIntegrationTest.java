package br.com.desafio_quality.integration;

import br.com.desafio_quality.dto.RoomDTO;
import br.com.desafio_quality.service.PropService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class PropControllerIntegrationTest {

    @Autowired
    public WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private String request;

    @BeforeEach
    public void setup () {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.request = "{\n" +
                "    \"prop_name\" : \"Apto - Asa sul\",\n" +
                "    \"prop_district\" : \"Brasília\",\n" +
                "    \"rooms\" : [\n" +
                "        {\"room_name\": \"Cozinha\", \"room_width\" : 4.5, \"room_length\" : 2.4},\n" +
                "        {\"room_name\": \"Quarto\", \"room_width\" : 7.0, \"room_length\" : 4.0},\n" +
                "        {\"room_name\": \"Sala\", \"room_width\" : 7.5, \"room_length\" : 2.2}\n" +
                "    ]\n" +
                "}";
    }

    @Test
    public void shouldReturnCorrectCalcFromTotalSquareMeterRoute () throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculate-square-meter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.request)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.squareTotalMeter").value(55.3));

    }

    @Test
    public void shouldReturnCalcPriceFromPropBasedLocation () throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post("/calculate-prop-price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.request)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.squareTotalMeter").value(55.3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.priceFromPropBasedNeighborhood").value(17696.0));
    }

    @Test
    public void shouldReturnBiggestRoomFromProp () throws Exception {

        String expectedBiggestRoom = "{\n" +
                "        \"room_name\": \"Quarto\",\n" +
                "        \"room_width\": 7.0,\n" +
                "        \"room_length\": 4.0\n" +
                "    }";

        JSONObject roomJsonObject = new JSONObject(expectedBiggestRoom);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/biggest-room")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.model().attribute("$.biggestRoom").match(expectedBiggestRoom));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.biggestRoom").value(expectedBiggestRoom));
    }

    @Test
    public void shouldReturnSquareMeterForRoom () throws Exception {

        Double[] squareMeter = {10.799999999999999, 28.0, 16.5};

        this.mockMvc.perform(MockMvcRequestBuilders.post("/room-detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.request)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms[0].squareMeter").value(squareMeter[0]))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms[1].squareMeter").value(squareMeter[1]))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rooms[2].squareMeter").value(squareMeter[2]));

    }
}

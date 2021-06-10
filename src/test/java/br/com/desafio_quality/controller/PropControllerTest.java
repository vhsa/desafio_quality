package br.com.desafio_quality.controller;

import br.com.desafio_quality.dto.PropDTO;
import br.com.desafio_quality.dto.RoomDTO;
import br.com.desafio_quality.service.PropService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropControllerTest {

    PropService propService = new PropService();
    List<RoomDTO> listRoom = new ArrayList<>();
    PropDTO propDTO;

    @BeforeEach
    public void setup () {
        this.listRoom.add(new RoomDTO("Cozinha", 4.5, 2.4));
        this.listRoom.add(new RoomDTO("Quarto", 7.0, 4.0));
        this.listRoom.add(new RoomDTO("Sala", 7.5, 2.2));

        this.propDTO = new PropDTO("Apto - Asa sul", "Brasília", this.listRoom);
    }

    // first test
    @Test
    public void shouldReturnCorrectCalcTotalSquareMeterFromProp () {
        Assertions.assertEquals(55.3, this.propService.calculateSquareMeterFromProp(this.propDTO).getSquareTotalMeter());
    }

    // second test
    @Test
    public void shouldReturnDistricExists () {
        Assertions.assertTrue(this.propService.verifyPropDisctricExits(this.propDTO));
    }

    // third test
    @Test
    public void shouldReturnBiggestRoom () {
        Assertions.assertNotNull(this.propService.biggestRoom(propDTO).getBiggestRoom());
    }

    // fourth test
    @Test
    public void shouldReturnAllSquareMeterFromRoom () {}
}

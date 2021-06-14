package br.com.desafio_quality.unit;

import br.com.desafio_quality.dto.PropDTO;
import br.com.desafio_quality.dto.RoomDTO;
import br.com.desafio_quality.service.PropService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PropControllerUnitTest {

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

    // first test - US-0001
    @Test
    public void shouldReturnCorrectCalcTotalSquareMeterFromProp () {

        Double response = this.propService
                                .calculateSquareMeterFromProp(this.propDTO)
                                .getSquareTotalMeter();

        Assertions.assertEquals(55.3, response);
    }

    // second test - US-0002
    @Test
    public void shouldReturnDistricExists () {
        Assertions.assertTrue(this.propService.verifyPropDisctricExits(this.propDTO));
    }

    // second test fail - US-0002
    @Test
    public void shouldReturnInvalidDistrict () {
        Assertions.assertThrows(Exception.class, () -> {
            propDTO.setProp_district("Jacutinga");
            propService.verifyPropDisctricExits(propDTO);
        });
    }

    // third test US-0003
    @Test
    public void shouldReturnBiggestRoom () {
        Assertions.assertEquals(this.propService.ListBiggestRoom(propDTO.getRooms()), this.propService.biggestRoom(propDTO).getBiggestRoom());
    }

    // fourth test US-0004
    @Test
    public void shouldReturnAllSquareMeterFromRoom () {
        Double[] squareMeter = {10.799999999999999, 28.0, 16.5};
        Assertions.assertArrayEquals(this.propService.squareMeterForRoom(propDTO)
                .getRooms()
                .stream()
                .map( rr -> rr.getSquareMeter()).toArray(),
                squareMeter
        );
    }
}

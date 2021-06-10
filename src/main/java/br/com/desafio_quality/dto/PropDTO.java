package br.com.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropDTO {
    private String prop_name;
    private String prop_district;
    private List<RoomDTO> rooms;

    // part from return
    private Double squareTotalMeter;
    private Double priceFromPropBasedNeighborhood;
    private RoomDTO biggestRoom;

    public PropDTO(String prop_name, String prop_district, List<RoomDTO> rooms) {
        this.prop_name = prop_name;
        this.prop_district = prop_district;
        this.rooms = rooms;
    }
}

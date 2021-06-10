package br.com.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
    private String room_name;
    private Double room_width;
    private Double room_length;

    // var from return
    private Double biggestRoom;
    private short squareMeter;

    public RoomDTO(String room_name, Double room_width, Double room_length, Double biggestRoom) {
        this.room_name = room_name;
        this.room_width = room_width;
        this.room_length = room_length;
        this.biggestRoom = biggestRoom;
    }

    public RoomDTO(short squareMeter, String room_name, Double room_width, Double room_length) {
        this.squareMeter = squareMeter;
        this.room_name = room_name;
        this.room_width = room_width;
        this.room_length = room_length;
    }

}

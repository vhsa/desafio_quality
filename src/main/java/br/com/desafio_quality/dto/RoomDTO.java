package br.com.desafio_quality.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {

    @NotBlank(message = "O nome do cômodo não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do cômodo não pode exceder 30 caracteres.")
    @Pattern(regexp = "[A-Z].*", message = "O nome do cômodo deve começar com uma letra maiúscula.")
    private String room_name;

    @NotNull(message = "A largura do cômodo não pode estar vazia.")
    @Range(max = 25, message = "A largura máxima permitida por cômodo é de 25 metros")
    private Double room_width;

    @NotNull(message = "O comprimento do cômodo não pode estar vazio.")
    @Range(max = 33, message = "O comprimento máximo permitido por cômodo é de 33 metros.")
    private Double room_length;

    // var from return
    private Double biggestRoom;
    private Double squareMeter;

    public RoomDTO(String room_name, Double room_width, Double room_length) {
        this.room_name = room_name;
        this.room_width = room_width;
        this.room_length = room_length;
    }

//    public RoomDTO(String room_name, Double room_width, Double room_length, Double biggestRoom) {
//        this.room_name = room_name;
//        this.room_width = room_width;
//        this.room_length = room_length;
//        this.biggestRoom = biggestRoom;
//    }

    public RoomDTO(Double squareMeter, String room_name, Double room_width, Double room_length) {
        this.squareMeter = squareMeter;
        this.room_name = room_name;
        this.room_width = room_width;
        this.room_length = room_length;
    }

}

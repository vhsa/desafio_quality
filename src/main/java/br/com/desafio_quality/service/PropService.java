package br.com.desafio_quality.service;

import br.com.desafio_quality.dto.PropDTO;
import br.com.desafio_quality.dto.RoomDTO;
import br.com.desafio_quality.exception.PropException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropService {

    // US-0001
    public PropDTO calculateSquareMeterFromProp (PropDTO propDTO) {

        Double squareMeter = 0.0;

        inputValidations(propDTO);

        for ( RoomDTO r : propDTO.getRooms() ) {
            squareMeter += r.getRoom_length() * r.getRoom_width();
        }

        return PropDTO.builder()
                .squareTotalMeter(squareMeter)
                .prop_name(propDTO.getProp_name())
                .prop_district(propDTO.getProp_district())
                .rooms(propDTO.getRooms())
                .build();

    }

    // US-0002
    public PropDTO calculatePriceFromPropBasedLocation (PropDTO propDTO) {

        Double totalSquareMeter = calculateSquareMeterFromProp(propDTO).getSquareTotalMeter();

        Map<String, Double> priceFromPropBasedOnTotalSquareMeter = priceFromProp(propDTO);

        if (priceFromPropBasedOnTotalSquareMeter.containsKey(propDTO.getProp_district())) {
            Double priceFromNeighborhood = priceFromPropBasedOnTotalSquareMeter.get(propDTO.getProp_district());

            return PropDTO.builder()
                    .priceFromPropBasedNeighborhood(totalSquareMeter * priceFromNeighborhood)
                    .squareTotalMeter(totalSquareMeter)
                    .prop_name(propDTO.getProp_name())
                    .prop_district(propDTO.getProp_district())
                    .rooms(propDTO.getRooms())
                    .build();
        }

        throw new PropException("Vizinhaça não encontrada");
    }

    // calculate price from prop based in your location
    public Map<String, Double> priceFromProp (PropDTO propDTO) {

        Map<String, Double> listOfLocations = new HashMap<>();

        listOfLocations.put("Brasília", 320.0);
        listOfLocations.put("São Paulo", 750.0);
        listOfLocations.put("Rio de Janeiro", 230.0);
        listOfLocations.put("Belo Horizonte", 180.0);

        return listOfLocations;
    }

    // validations
    public void inputValidations (PropDTO propDTO){
        validatePropName(propDTO);
        validatePropDistric(propDTO);
        validateRoomName(propDTO);
        validateRoomWidth(propDTO);
        validateRoomLength(propDTO);
    }

    // prop_name
    private void validatePropName (PropDTO propDTO) {

        // not null
        if ( propDTO.getProp_name() == null || propDTO.getProp_name().isEmpty() ) throw new PropException("O nome da propriedade não pode estar vazio.");

        // starts with upperletter
        if ( !Character.isUpperCase( propDTO.getProp_name().charAt(0) )) throw new PropException("O nome da propriedade deve começar com uma letra maiúscula.");

        // max 30 char
        if ( propDTO.getProp_name().length() > 30 ) throw new PropException("O comprimento do nome não pode exceder 30 caracteres.");

    }

    // prop_district
    private void validatePropDistric (PropDTO propDTO) {
        // not null
        if ( propDTO.getProp_district() == null || propDTO.getProp_district().isEmpty() ) throw new PropException("O bairro não pode estar vazio.");

        // max 45 char
        if ( propDTO.getProp_district().length() > 45 ) throw new PropException("O comprimento do bairro não pode exceder 45 caracteres.");
    }

    //room_name
    private void validateRoomName (PropDTO propDTO) {

        for (RoomDTO r : propDTO.getRooms() ) {
            //not null
            if ( r.getRoom_name() == null || r.getRoom_name().isEmpty() ) throw new PropException("O nome do cômodo não pode estar vazio.");

            // starts with uppercase
            if ( !Character.isUpperCase( r.getRoom_name().charAt(0) )) throw new PropException("O nome do cômodo deve começar com uma letra maiúscula.");

            if ( r.getRoom_name().length() > 30 ) throw new PropException("O nome do cômodo não pode exceder 30 caracteres.");

        }
    }

    // room_width
    private void validateRoomWidth ( PropDTO propDTO ) {
        for ( RoomDTO r : propDTO.getRooms() ) {
            if ( r.getRoom_width() == null || r.getRoom_width().isNaN() ) throw new PropException("A largura do cômodo não pode estar vazia.");
            if ( r.getRoom_width() > 25 ) throw new PropException("A largura máxima permitida por cômodo é de 25 metros");
        }
    }

    // room_length
    private void validateRoomLength ( PropDTO propDTO ) {
        for ( RoomDTO r : propDTO.getRooms() ) {
            if ( r.getRoom_length() == null || r.getRoom_length().isNaN() ) throw new PropException("O comprimento do cômodo não pode estar vazio");
            if ( r.getRoom_length() > 33 ) throw new PropException("O comprimento máximo permitido por cômodo é de 33 metros.");
        }
    }
}
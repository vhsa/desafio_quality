package br.com.desafio_quality.service;

import br.com.desafio_quality.dto.PropDTO;
import br.com.desafio_quality.dto.RoomDTO;
import br.com.desafio_quality.exception.PropException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropService {

    // US-0001
    public PropDTO calculateSquareMeterFromProp (PropDTO propDTO) {

        Double squareMeter = 0.0;

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

        if (this.verifyPropDisctricExits(propDTO)) {
            Map<String, Double> locationDictionary = this.createPriceFromProp(propDTO);

            Double priceFromNeighborhood = locationDictionary.get(propDTO.getProp_district());

            return PropDTO.builder()
                    .priceFromPropBasedNeighborhood(totalSquareMeter * priceFromNeighborhood)
                    .squareTotalMeter(totalSquareMeter)
                    .prop_name(propDTO.getProp_name())
                    .prop_district(propDTO.getProp_district())
                    .rooms(propDTO.getRooms())
                    .build();
        }

        throw new PropException("Bairro não encontrada");
    }

    // US-0003
    public PropDTO biggestRoom ( PropDTO propDTO ) {

        RoomDTO biggestRoom = ListBiggestRoom(propDTO.getRooms());

        biggestRoom.setBiggestRoom(null);

        return PropDTO.builder()
                .prop_name(propDTO.getProp_name())
                .prop_district(propDTO.getProp_district())
                .biggestRoom(biggestRoom)
                .build();
    }

    // us-0004
    public PropDTO squareMeterForRoom (PropDTO propDTO) {

        RoomDTO roomDTO;
        List<RoomDTO> roomDTOList = new ArrayList<>();

        for ( RoomDTO r : propDTO.getRooms() ) {

            Double squareMeter = r.getRoom_width() * r.getRoom_length();

            roomDTO = new RoomDTO(squareMeter, r.getRoom_name(), r.getRoom_width(), r.getRoom_length());

            roomDTOList.add(roomDTO);
        }

        return PropDTO.builder()
                .prop_name(propDTO.getProp_name())
                .prop_district(propDTO.getProp_district())
                .rooms(roomDTOList)
                .build();
    }

    // verify prop distric exits
    public boolean verifyPropDisctricExits (PropDTO propDTO) {

        Map<String, Double> priceFromPropBasedOnTotalSquareMeter = this.createPriceFromProp(propDTO);

        if (priceFromPropBasedOnTotalSquareMeter.containsKey(propDTO.getProp_district())) {
            return priceFromPropBasedOnTotalSquareMeter.containsKey(propDTO.getProp_district());
        }

        throw new PropException("Bairro não localizado");
    }

    // calculate price from prop based in your location
    public Map<String, Double> createPriceFromProp (PropDTO propDTO) {

        Map<String, Double> listOfLocations = new HashMap<>();

        listOfLocations.put("Brasília", 320.0);
        listOfLocations.put("São Paulo", 750.0);
        listOfLocations.put("Rio de Janeiro", 230.0);
        listOfLocations.put("Belo Horizonte", 180.0);

        return listOfLocations;
    }

    public RoomDTO ListBiggestRoom(List<RoomDTO> rooms){
        return rooms
                .stream()
                .max(Comparator.comparing(c -> (c.getRoom_width() * c.getRoom_length())))
                .orElse(null);
    }
}

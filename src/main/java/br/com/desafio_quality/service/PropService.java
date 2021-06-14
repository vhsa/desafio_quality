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

        // calculate square meter from room
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

        // get value from total square meter
        Double totalSquareMeter = calculateSquareMeterFromProp(propDTO).getSquareTotalMeter();

        // verify if district exists
        if (this.verifyPropDisctricExits(propDTO)) {
            Map<String, Double> locationDictionary = this.createPriceFromProp(propDTO);

            // get price from neighborhood
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
        // get list from biggest room
        RoomDTO biggestRoom = ListBiggestRoom(propDTO.getRooms());

        // set null to not appear in response
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

        // calculate square meter for all rooms from a prop and set in a constructor
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

    // verify prop district exits
    public boolean verifyPropDisctricExits (PropDTO propDTO) {

        Map<String, Double> priceFromPropBasedOnTotalSquareMeter = this.createPriceFromProp(propDTO);

        // verify if location is on dictionary list
        if (priceFromPropBasedOnTotalSquareMeter.containsKey(propDTO.getProp_district())) {
            return priceFromPropBasedOnTotalSquareMeter.containsKey(propDTO.getProp_district());
        }

        throw new PropException("Bairro não localizado");
    }

    // calculate price from prop based in your location
    public Map<String, Double> createPriceFromProp (PropDTO propDTO) {

        Map<String, Double> listOfLocations = new HashMap<>();

        // setting a list of districts
        listOfLocations.put("Brasília", 320.0);
        listOfLocations.put("São Paulo", 750.0);
        listOfLocations.put("Rio de Janeiro", 230.0);
        listOfLocations.put("Belo Horizonte", 180.0);

        return listOfLocations;
    }

    public RoomDTO ListBiggestRoom(List<RoomDTO> rooms){

        // return biggest room from a prop
        return rooms
                .stream()
                .max(Comparator.comparing(c -> (c.getRoom_width() * c.getRoom_length())))
                .orElse(null);
    }
}

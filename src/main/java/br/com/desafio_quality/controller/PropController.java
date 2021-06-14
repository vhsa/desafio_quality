package br.com.desafio_quality.controller;

import br.com.desafio_quality.dto.PropDTO;
import br.com.desafio_quality.service.PropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PropController {

    @Autowired
    PropService propService;

    // US-0001
    @PostMapping("/calculate-square-meter")
    public ResponseEntity<PropDTO> calculateSquareMeterFromProp (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.calculateSquareMeterFromProp(propDTO), HttpStatus.OK);
    }

    // US-0002
    @PostMapping("/calculate-prop-price")
    public ResponseEntity<PropDTO> calculatePriceFromPropBasedLocation (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.calculatePriceFromPropBasedLocation(propDTO), HttpStatus.OK);
    }

    // US-0003
    @PostMapping("/biggest-room")
    public ResponseEntity<PropDTO> biggestRoom (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.biggestRoom(propDTO), HttpStatus.OK);
    }

    // US-0004
    @PostMapping("/room-detail")
    public ResponseEntity<PropDTO> squareMeterForRoom (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.squareMeterForRoom(propDTO), HttpStatus.OK);
    }
}

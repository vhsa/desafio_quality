package br.com.desafio_quality.controller;

import br.com.desafio_quality.dto.PropDTO;
import br.com.desafio_quality.exception.PropException;
import br.com.desafio_quality.service.PropService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PropController {

    @Autowired
    PropService propService;

    @PostMapping("/calculate-square-meter")
    public ResponseEntity<PropDTO> calculateSquareMeterFromProp (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.calculateSquareMeterFromProp(propDTO), HttpStatus.OK);
    }


    @PostMapping("/calculate-prop-price")
    public ResponseEntity<PropDTO> calculatePriceFromPropBasedLocation (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.calculatePriceFromPropBasedLocation(propDTO), HttpStatus.OK);
    }

    @PostMapping("/biggest-room")
    public ResponseEntity<PropDTO> biggestRoom (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.biggestRoom(propDTO), HttpStatus.OK);
    }

    @PostMapping("/room-detail")
    public ResponseEntity<PropDTO> squareMeterForRoom (@Valid @RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.squareMeterForRoom(propDTO), HttpStatus.OK);
    }
}

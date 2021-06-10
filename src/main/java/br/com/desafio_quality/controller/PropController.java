package br.com.desafio_quality.controller;

import br.com.desafio_quality.dto.PropDTO;
import br.com.desafio_quality.service.PropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class PropController {

    @Autowired
    PropService propService;

    @PostMapping("/calculate-square-meter")
    public ResponseEntity<PropDTO> calculateSquareMeterFromProp (@RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.calculateSquareMeterFromProp(propDTO), HttpStatus.OK);
    }

    @PostMapping("/calculate-prop-price")
    public ResponseEntity<PropDTO> calculatePriceFromPropBasedLocation (@RequestBody PropDTO propDTO) {
        return new ResponseEntity<>(propService.calculatePriceFromPropBasedLocation(propDTO), HttpStatus.OK);
    }
}

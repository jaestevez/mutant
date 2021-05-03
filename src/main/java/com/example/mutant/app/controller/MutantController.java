package com.example.mutant.app.controller;

import com.example.mutant.app.dto.MutantDTO;
import com.example.mutant.app.exception.UnsupportedDimensionArray;
import com.example.mutant.app.exception.UnsupportedLetter;
import com.example.mutant.app.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MutantController {
    @Autowired
    private MutantService mutantService;

    @PostMapping(value = "/mutant", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity verifyMutant(@RequestBody final MutantDTO dna) {
        ResponseEntity response = null;
        if (mutantService.validateMutant(dna)) {
            response = ResponseEntity.ok().build();
        } else {
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return response;
    }

    @ExceptionHandler(UnsupportedLetter.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUnsupportedLetter(UnsupportedLetter exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UnsupportedDimensionArray.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUnsupportedDimensionArray(UnsupportedDimensionArray exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}

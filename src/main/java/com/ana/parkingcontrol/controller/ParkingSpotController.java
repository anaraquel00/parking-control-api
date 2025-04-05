package com.ana.parkingcontrol.controller;


import com.ana.parkingcontrol.exception.ResourceNotFoundException;
import com.ana.parkingcontrol.model.ParkingSpot;
import com.ana.parkingcontrol.repository.ParkingSpotRepository;
import com.ana.parkingcontrol.service.ParkingSpotService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/parkingspot")
public class ParkingSpotController {
    @Autowired
    public ParkingSpotService service;
    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

@GetMapping
public List<ParkingSpot>getAllParkingSpots(){
        return service.findAll();
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("parkingSpot", new ParkingSpot());
        return "parking-spot-form";
    }


@GetMapping("/{id}")
public ResponseEntity<ParkingSpot> getParkingSpotById(@PathVariable Long id){
        Optional<ParkingSpot> parkingSpot = service.findById(id);
        return parkingSpot.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

@PostMapping
public ResponseEntity<?> createParkingSpot(@RequestBody @Valid ParkingSpot parkingSpot,  BindingResult bindingResult) {
    // Verifica se placa já existe
    if (bindingResult.hasErrors()) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
    if (service.existsByPlacaCarro(parkingSpot.getPlacaCarro())) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Placa do carro já está em uso!");
    }
    // Verifica se vaga já está ocupada
    if (service.existsByNumVaga(parkingSpot.getNumVaga())) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga já está em uso!");
    }
    if (service.existsByCpf(parkingSpot.getCpf())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Cpf já está em uso!");
    }
    if (service.existsByEmail(parkingSpot.getEmail())){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Email já cadastrado!");
    }
    parkingSpot.setHoraChegada(LocalDateTime.now());

    ParkingSpot savedSpot = service.save(parkingSpot);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedSpot);
}
@PutMapping("/{id}")
public ResponseEntity <?> updateParkingSpot(@PathVariable Long id,
                                               @RequestBody @Valid ParkingSpot parkingSpotDetails,
                                               BindingResult bindingResult){

    if (bindingResult.hasErrors()) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    try {
        ParkingSpot updateParkingSpot = service.updateParkingSpot(id, parkingSpotDetails);

        return ResponseEntity.ok(updateParkingSpot);
    } catch (ResourceNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}


@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteParkingSpot(@PathVariable Long id){
    Optional<ParkingSpot> parkingSpot = service.findById(id);
    if(parkingSpot.isPresent()){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
        return ResponseEntity.notFound().build();

}

}

package com.ana.parkingcontrol.controller;

import com.ana.parkingcontrol.model.ParkingSpot;
import com.ana.parkingcontrol.service.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/parking-spots")
public class ParkingSpotControlador {

    @Autowired
    private ParkingSpotService service;

    // MOSTRA O FORMULÁRIO (GET)
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("parkingSpot", new ParkingSpot());
        return "cadastro-vaga"; // Nome do template HTML
    }

    // PROCESSAR O FORMULÁRIO (POST)
    @PostMapping("/save")
    public String saveSpot(@Valid ParkingSpot parkingSpot, BindingResult result) {
        if (result.hasErrors()) {
            return "cadastro-vaga"; // Volta pro form se tiver erros
        }
        parkingSpot.setHoraChegada(LocalDateTime.now()); // 👈 Define a hora atual!
        service.save(parkingSpot);
        return "redirect:/parking-spots/form?success"; // Redireciona com mensagem
        //  redirectAttributes.addFlashAttribute("successMessage",
        //  "Vaga adicionada com sucesso!"); // 👈 Mensagem
    }


}

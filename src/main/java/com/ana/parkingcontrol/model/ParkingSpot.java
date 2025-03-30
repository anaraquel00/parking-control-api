package com.ana.parkingcontrol.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "parkingspot_tb")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @NotBlank
    @Column (name = "nome", length = 100, nullable = false, unique = true)
    private String nome;
    @NotBlank
    @Size(min = 11, max = 11)
    @Column (name = "cpf", length = 50, nullable = false, unique = true)
    private String cpf;
    @NotBlank
    @Email
    @Column (name = "email", length = 30, unique = true)
    private String email;
    @NotBlank
    @Column (name = "telefone", length = 20, unique = true)
    private String telefone;
    @NotBlank(message = "Número da vaga é obrigatório")
    @Column (name = "numero_da_vaga", length = 20)
    private String numVaga;
    @NotBlank(message = "Placa do carro é obrigatória")
    @Size(min = 7, max = 7)
    @Pattern(regexp = "[A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4}",
            message = "Placa deve seguir o padrão AAA1A11 ou AAA1111")
    @Column (name = "placa_do_carro", length = 20, nullable = false, unique = true)
    private String placaCarro;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column (name = "data_da_chegada")
    private LocalDateTime horaChegada;

    public ParkingSpot() {

    }


   }

package com.ana.parkingcontrol.repository;

import com.ana.parkingcontrol.model.ParkingSpot;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    ParkingSpot findFirstBy();

    boolean existsByPlacaCarro(@NotBlank @Size(min = 7, max = 7) @Pattern(regexp = "[A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4}",
            message = "Placa deve seguir o padrão AAA1A11 ou AAA1111") String placaCarro);

    boolean existsByNumVaga(@NotBlank String numVaga);

    boolean existsByCpf(@NotBlank @Size(min = 11, max = 11) String cpf);

    boolean existsByEmail(@NotBlank @Email String email);

    boolean existsByNome(@NotBlank String nome);

    boolean existsByTelefone(@NotBlank String telefone);
}

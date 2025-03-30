package com.ana.parkingcontrol.service;

import com.ana.parkingcontrol.model.ParkingSpot;
import com.ana.parkingcontrol.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotService {
    @Autowired
    private ParkingSpotRepository repository;

    public List<ParkingSpot> findAll() {
        return repository.findAll();
    }

    public Optional<ParkingSpot> findById(Long id) {
        return repository.findById(id);
    }

    public ParkingSpot save(ParkingSpot parkingSpot) {
        return repository.save(parkingSpot);
    }
    @Transactional
    public ParkingSpot updateParkingSpot(Long id, ParkingSpot updatedSpot) {
        ParkingSpot parkingSpot = repository.findById(id).map(existingSpot -> {
                    updatedSpot.setId(existingSpot.getId());
                    // Valida placa única (se alterada)
                    if (!existingSpot.getPlacaCarro().equals(updatedSpot.getPlacaCarro()) &&
                            repository.existsByPlacaCarro(updatedSpot.getPlacaCarro())) {
                        throw new DataIntegrityViolationException("Placa já está em uso");
                    }
                    if (!existingSpot.getEmail().equals(updatedSpot.getEmail())
                            && repository.existsByEmail(updatedSpot.getEmail())) {
                        throw new DataIntegrityViolationException("Email já está em uso ");
                    }
                    if (!existingSpot.getCpf().equals(updatedSpot.getCpf())
                            && repository.existsByCpf(updatedSpot.getCpf())) {
                        throw new DataIntegrityViolationException("Cpf já está em uso");
                    }
                    if (!existingSpot.getNome().equals(updatedSpot.getNome()) &&
                            repository.existsByNome(updatedSpot.getNome())) {
                        throw new DataIntegrityViolationException("Nome já está em uso");
                    }

                    if (!existingSpot.getTelefone().equals(updatedSpot.getTelefone())
                            && repository.existsByTelefone(updatedSpot.getTelefone())) {
                        throw new DataIntegrityViolationException("Telefone já está em uso");
                    }

                    // Atualiza campos
                    existingSpot.setNome(updatedSpot.getNome());
                    existingSpot.setCpf(updatedSpot.getCpf());
                    existingSpot.setTelefone(updatedSpot.getTelefone());
                    existingSpot.setEmail(updatedSpot.getEmail());
                    existingSpot.setPlacaCarro(updatedSpot.getPlacaCarro());
                    existingSpot.setNumVaga(updatedSpot.getNumVaga());

                    BeanUtils.copyProperties(updatedSpot, existingSpot, "id");

                    return repository.save(existingSpot);
                })
                .orElseThrow();
        return parkingSpot;

        }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public boolean existsByPlacaCarro(@NotBlank @Size(min = 7, max = 7) @Pattern(regexp = "[A-Z]{3}[0-9][A-Z][0-9]{2}|[A-Z]{3}[0-9]{4}",
            message = "Placa deve seguir o padrão AAA1A11 ou AAA1111") String placaCarro) {
        return repository.existsByPlacaCarro(placaCarro);
    }

    public boolean existsByNumVaga(@NotBlank String numVaga) {
        return repository.existsByNumVaga(numVaga);
    }

    public boolean existsByCpf(@NotBlank @Size(min = 11, max = 11) String cpf) {
        return repository.existsByCpf(cpf);
    }

    public boolean existsByEmail(@NotBlank @Email String email) {
        return repository.existsByEmail(email);
    }
}

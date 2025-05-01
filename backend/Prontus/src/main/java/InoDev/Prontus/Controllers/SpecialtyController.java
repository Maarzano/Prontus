package InoDev.Prontus.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import InoDev.Prontus.DTO.Specialty.*;
import InoDev.Prontus.Service.SpecialtyService;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> createSpecialty(@Validated @RequestBody CreateSpecialtyDTO dto) {
        SpecialtyDTO specialty = specialtyService.createSpecialty(dto);
        return new ResponseEntity<>(specialty, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> updateSpecialty(@PathVariable Long id, @Validated @RequestBody UpdateSpecialtyDTO dto) {
        SpecialtyDTO specialty = specialtyService.updateSpecialty(id, dto);
        return ResponseEntity.ok(specialty);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO> getSpecialtyById(@PathVariable Long id) {
        SpecialtyDTO specialty = specialtyService.getSpecialtyById(id);
        return ResponseEntity.ok(specialty);
    }

    @GetMapping
    public ResponseEntity<List<SpecialtyDTO>> getAllSpecialties() {
        List<SpecialtyDTO> specialties = specialtyService.getAllSpecialties();
        return ResponseEntity.ok(specialties);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialty(@PathVariable Long id) {
        specialtyService.deleteSpecialty(id);
        return ResponseEntity.noContent().build();
    }
}
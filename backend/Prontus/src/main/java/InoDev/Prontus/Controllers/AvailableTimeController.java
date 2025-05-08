package InoDev.Prontus.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import InoDev.Prontus.DTO.AvailableTime.*;
import InoDev.Prontus.Service.AvailableTimeService;

@RestController
@RequestMapping("/api/available-times")
public class AvailableTimeController {

    private final AvailableTimeService availableTimeService;

    public AvailableTimeController(AvailableTimeService availableTimeService) {
        this.availableTimeService = availableTimeService;
    }

    @PostMapping
    public ResponseEntity<AvailableTimeDTO> createAvailableTime(@Validated @RequestBody CreateAvailableTimeDTO dto) {
        AvailableTimeDTO availableTime = availableTimeService.createAvailableTime(dto);
        return new ResponseEntity<>(availableTime, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailableTimeDTO> updateAvailableTime(@PathVariable Long id, @Validated @RequestBody UpdateAvailableTimeDTO dto) {
        AvailableTimeDTO availableTime = availableTimeService.updateAvailableTime(id, dto);
        return ResponseEntity.ok(availableTime);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailableTimeDTO> getAvailableTimeById(@PathVariable Long id) {
        AvailableTimeDTO availableTime = availableTimeService.getAvailableTimeById(id);
        return ResponseEntity.ok(availableTime);
    }

    @GetMapping
    public ResponseEntity<List<AvailableTimeDTO>> getAllAvailableTimes() {
        List<AvailableTimeDTO> availableTimes = availableTimeService.getAllAvailableTimes();
        return ResponseEntity.ok(availableTimes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailableTime(@PathVariable Long id) {
        availableTimeService.deleteAvailableTime(id);
        return ResponseEntity.noContent().build();
    }
}
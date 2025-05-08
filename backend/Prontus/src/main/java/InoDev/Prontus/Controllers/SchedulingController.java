package InoDev.Prontus.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import InoDev.Prontus.DTO.Scheduling.*;
import InoDev.Prontus.Service.SchedulingService;

@RestController
@RequestMapping("/api/schedulings")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping
    public ResponseEntity<SchedulingResponseDTO> createScheduling(@Validated @RequestBody CreateSchedulingDTO dto) {
        SchedulingResponseDTO scheduling = schedulingService.createScheduling(dto);
        return new ResponseEntity<>(scheduling, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponseDTO> updateScheduling(@PathVariable Long id, @Validated @RequestBody UpdateSchedulingDTO dto) {
        SchedulingResponseDTO scheduling = schedulingService.updateScheduling(id, dto);
        return ResponseEntity.ok(scheduling);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponseDTO> getSchedulingById(@PathVariable Long id) {
        SchedulingResponseDTO scheduling = schedulingService.getSchedulingById(id);
        return ResponseEntity.ok(scheduling);
    }

    @GetMapping
    public ResponseEntity<List<SchedulingResponseDTO>> getAllSchedulings() {
        List<SchedulingResponseDTO> schedulings = schedulingService.getAllSchedulings();
        return ResponseEntity.ok(schedulings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable Long id) {
        schedulingService.deleteScheduling(id);
        return ResponseEntity.noContent().build();
    }
}
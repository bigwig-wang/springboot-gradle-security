package com.thoughtworks.api;

import com.thoughtworks.dto.ScheduleDTO;
import com.thoughtworks.dto.group.AddOperation;
import com.thoughtworks.dto.group.UpdateOperaton;
import com.thoughtworks.entity.Schedule;
import com.thoughtworks.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public void addSchedule(@Validated(AddOperation.class) @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.addSchedule(scheduleDTO);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @PreAuthorize("#scheduleDTO.id == authentication.principal.username")
    public void updateSchedule(@Validated(UpdateOperaton.class) @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.updateSchedule(scheduleDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Schedule getSchedule(@PathVariable String id) {
        return scheduleService.getSchedule(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<Schedule> getSchedules(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return scheduleService.getSchedules(pageNum, pageSize);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSchedule(@PathVariable String id) {
        scheduleService.deleteSchedule(id);
    }
}

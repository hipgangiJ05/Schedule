package com.schedule.controller;

import com.schedule.dto.*;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public CreateScheduleResponse save(@RequestBody CreateScheduleRequest request){
        return scheduleService.save(request);
    }

    @GetMapping("/schedule?author={author}")
    public List<GetScheduleResponse> getAll(@RequestParam(required = false) String author){
        return scheduleService.getAll();
    }

    @GetMapping("/schedule/{id}")
    public GetScheduleResponse getOne(@PathVariable Long id){
        return scheduleService.getOne(id);
    }

    @PutMapping("/schedule/{id}")
    public UpdateScheduleResponse update(@PathVariable Long id, @RequestBody UpdateScheduleRequest request) {
        return scheduleService.update(id, request);
    }

    @DeleteMapping("/schedule/{id}")
    public void delete(@PathVariable Long id, @RequestBody DeleteScheduleRequest request){
        scheduleService.delete(id);
    }

}

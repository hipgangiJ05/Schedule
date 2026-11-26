package com.schedule.service;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.dto.CreateScheduleResponse;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword());
        Schedule savedShedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(savedShedule.getId(), savedShedule.getTitle(), savedShedule.getContent(), savedShedule.getAuthor(), savedShedule.getCreatedAt(), savedShedule.getModifiedAt());
    }
}

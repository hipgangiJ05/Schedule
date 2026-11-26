package com.schedule.service;

import com.schedule.dto.CreateScheduleRequest;
import com.schedule.dto.CreateScheduleResponse;
import com.schedule.dto.GetScheduleResponse;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse getScheduleResponse = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(getScheduleResponse);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}

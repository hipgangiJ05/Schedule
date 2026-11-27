package com.schedule.service;

import com.schedule.dto.*;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), request.getAuthor(), request.getPassword());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent(), savedSchedule.getAuthor(), savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt());
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

    @Transactional
    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        schedule.update(request.getTitle(), request.getAuthor());
        return new UpdateScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getAuthor(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional
    public void delete(Long id) {
        boolean existence = scheduleRepository.existsById(id);
        if (!existence) {throw new IllegalStateException("존재하지 않는 유저입니다");}
        scheduleRepository.deleteById(id);

    }
}

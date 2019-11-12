package com.thoughtworks.service;

import com.thoughtworks.dto.ScheduleDTO;
import com.thoughtworks.entity.Schedule;
import com.thoughtworks.exception.BaseException;
import com.thoughtworks.repository.ScheduleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.thoughtworks.exception.ErrorCode.SCHEDULE_NOT_FOUND;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    public void addSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        schedule.setId(UUID.randomUUID().toString().replace("-", ""));
        schedule.setType(scheduleDTO.getScheduleType());
        scheduleRepository.save(schedule);
    }

    public void updateSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleRepository.findOne(scheduleDTO.getId());
        BeanUtils.copyProperties(scheduleDTO, schedule);
        scheduleRepository.save(schedule);
    }

    public Schedule getSchedule(String id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new BaseException(SCHEDULE_NOT_FOUND));
        return schedule;
    }

    public Page<Schedule> getSchedules(Integer pageNum, Integer pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        return scheduleRepository.findAll(pageRequest);
    }

    public void deleteSchedule(String id) {
        scheduleRepository.delete(id);
    }
}

package com.thoughtworks.repository;

import com.thoughtworks.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    Optional<Schedule> findById(String id);
}

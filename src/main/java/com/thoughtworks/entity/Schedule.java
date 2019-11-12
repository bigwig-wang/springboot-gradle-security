package com.thoughtworks.entity;

import com.thoughtworks.dto.enu.ScheduleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Schedule extends BaseEntity {
    @Id
    private String id;

    private Date startTime;

    private Date endTime;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private ScheduleType type;

    private boolean finished;
}

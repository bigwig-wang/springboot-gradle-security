package com.thoughtworks.dto;

import com.thoughtworks.dto.enu.ScheduleType;
import com.thoughtworks.dto.group.AddOperation;
import com.thoughtworks.dto.group.UpdateOperaton;
import com.thoughtworks.dto.validator.TimeFieldMatch;
import com.thoughtworks.dto.validator.TwoFieldMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TwoFieldMatch(first = "password", second = "confirmPassword", groups = {AddOperation.class, UpdateOperaton.class})
@TimeFieldMatch(startTimeField = "startTime", endTimeField = "endTime", groups = {AddOperation.class, UpdateOperaton.class})
public class ScheduleDTO {

    @NotBlank(groups = UpdateOperaton.class)
    private String id;

    @NotBlank(groups = {AddOperation.class, UpdateOperaton.class})
    private String title;

    @NotBlank(groups = {AddOperation.class, UpdateOperaton.class})
    private String content;

    private Date startTime;
    private Date endTime;
    @NotNull(groups = {AddOperation.class, UpdateOperaton.class})
    private ScheduleType scheduleType;
    private boolean finished;

    //测试用作校验两个字段必须数据相等
    @NotBlank(groups = {AddOperation.class, UpdateOperaton.class})
    private String password;

    @NotBlank(groups = {AddOperation.class, UpdateOperaton.class})
    private String confirmPassword;
}

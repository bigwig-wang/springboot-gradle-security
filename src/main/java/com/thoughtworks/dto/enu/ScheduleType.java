package com.thoughtworks.dto.enu;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.thoughtworks.exception.InvalidEnumException;

import java.util.Arrays;

public enum  ScheduleType {
    WORK, MOVIE, GAME, READING;

    @JsonCreator
    public static ScheduleType from(String value) {
        return Arrays.stream(ScheduleType.values()).filter(type -> value.equals(type.name()))
                .findFirst().orElseThrow(InvalidEnumException::new);
    }
}

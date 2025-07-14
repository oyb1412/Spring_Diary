package kr.co.myproject.entity;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public abstract class BaseTimeEntity {
    protected LocalDate createdDate;
}
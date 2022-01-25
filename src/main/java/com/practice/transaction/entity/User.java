package com.practice.transaction.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "user")
@Entity
@Getter
@Setter
public class User {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;

    @Column(name = "updated_timestamp")
    private LocalDateTime updatedtimestamp;

    @Column(name = "uniqueNumber")
    private int uniqueNumber;
}

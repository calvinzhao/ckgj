package com.ckgj.models;

import javax.persistence.*;

/**
 * Created by calvin on 3/28/16.
 */
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="employee_cnt")
    private long employeeCnt;
}

package com.ckgj.models;

import javax.persistence.*;
import java.util.Date;

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
    private int employeeCnt = 0;

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getInstitutionCode() {

        return institutionCode;
    }

    @Column(name="institution_code")
    private String institutionCode;

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {

        return dateCreated;
    }

    @Column(name="date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeCnt() {
        return employeeCnt;
    }

    public void setEmployeeCnt(int employeeCnt) {
        this.employeeCnt = employeeCnt;
    }
}

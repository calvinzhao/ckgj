package com.ckgj.models.statement;

import com.ckgj.models.company.Company;
import com.ckgj.models.user.Role;

import javax.persistence.*;

@Entity
@Table(name = "statement")
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
}

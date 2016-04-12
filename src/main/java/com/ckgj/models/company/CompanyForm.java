package com.ckgj.models.company;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by zhaok_000 on 4/9/2016.
 */
public class CompanyForm {
    @NotEmpty
    private String name = "";
    @NotEmpty
    private String institutionCode = "";

    public CompanyForm() {}

    public CompanyForm(Company company) {
        name = company.getName();
        institutionCode = company.getInstitutionCode();
        id = company.getId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public String getInstitutionCode() {
        return institutionCode;
    }

    public long getId() {
        return id;
    }

    private long id=0l;

}

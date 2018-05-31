package com.pmo.dashboard.entity;

import java.util.Date;

public class EmployeeSkill {
    private String id;

    private Date createDate;

    private Date updateDate;

    private String employeeId;

    private String capparamId;

    private String name;

    private String abilityLevel;

    private String mainAbility;

    private String officialAccreditation;

    private String authenticationName;

    private String workExperience;

    private String operateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCapparamId() {
        return capparamId;
    }

    public void setCapparamId(String capparamId) {
        this.capparamId = capparamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbilityLevel() {
        return abilityLevel;
    }

    public void setAbilityLevel(String abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    public String getMainAbility() {
        return mainAbility;
    }

    public void setMainAbility(String mainAbility) {
        this.mainAbility = mainAbility;
    }

    public String getOfficialAccreditation() {
        return officialAccreditation;
    }

    public void setOfficialAccreditation(String officialAccreditation) {
        this.officialAccreditation = officialAccreditation;
    }

    public String getAuthenticationName() {
        return authenticationName;
    }

    public void setAuthenticationName(String authenticationName) {
        this.authenticationName = authenticationName;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }
}
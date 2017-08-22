package com.pmo.dashboard.entity;

public class Employee
{
    
    private String employeeId;
    private String hsbcStaffId;
    private String staffName;
    private String ln;
    private String staffRegion;
    private String staffLocation;
    private String locationType;
    private String onShoreOrOffShore;
    private String sow;
    private String sowExpiredDate;
    private String staffCategory;
    private String engagementType;
    private String hsbcDoj;
    private String graduationDate;
    private String billingEntity;
    private String billCurrency;
    private String billRate;
    private String resourceStatus;
    private String mentionLWD;
    private String reasonForTermination;
    private String eHr;
    private String nicheSkill;
    private String hsbcProjectId;
    private String role;
    private String skill;
    private String csSubDeptId;
    
    public String getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }
    public String getHsbcStaffId()
    {
        return hsbcStaffId;
    }
    public void setHsbcStaffId(String hsbcStaffId)
    {
        this.hsbcStaffId = hsbcStaffId;
    }
    public String getStaffName()
    {
        return staffName;
    }
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    public String getLn()
    {
        return ln;
    }
    public void setLn(String ln)
    {
        this.ln = ln;
    }
    public String getStaffRegion()
    {
        return staffRegion;
    }
    public void setStaffRegion(String staffRegion)
    {
        this.staffRegion = staffRegion;
    }
    public String getStaffLocation()
    {
        return staffLocation;
    }
    public void setStaffLocation(String staffLocation)
    {
        this.staffLocation = staffLocation;
    }
    public String getLocationType()
    {
        return locationType;
    }
    public void setLocationType(String locationType)
    {
        this.locationType = locationType;
    }
    public String getOnShoreOrOffShore()
    {
        return onShoreOrOffShore;
    }
    public void setOnShoreOrOffShore(String onShoreOrOffShore)
    {
        this.onShoreOrOffShore = onShoreOrOffShore;
    }
    public String getSow()
    {
        return sow;
    }
    public void setSow(String sow)
    {
        this.sow = sow;
    }
    public String getSowExpiredDate()
    {
        return sowExpiredDate;
    }
    public void setSowExpiredDate(String sowExpiredDate)
    {
        this.sowExpiredDate = sowExpiredDate;
    }
    public String getStaffCategory()
    {
        return staffCategory;
    }
    public void setStaffCategory(String staffCategory)
    {
        this.staffCategory = staffCategory;
    }
    public String getEngagementType()
    {
        return engagementType;
    }
    public void setEngagementType(String engagementType)
    {
        this.engagementType = engagementType;
    }
    public String getHsbcDoj()
    {
        return hsbcDoj;
    }
    public void setHsbcDoj(String hsbcDoj)
    {
        this.hsbcDoj = hsbcDoj;
    }
    public String getGraduationDate()
    {
        return graduationDate;
    }
    public void setGraduationDate(String graduationDate)
    {
        this.graduationDate = graduationDate;
    }
    public String getBillingEntity()
    {
        return billingEntity;
    }
    public void setBillingEntity(String billingEntity)
    {
        this.billingEntity = billingEntity;
    }
    public String getBillCurrency()
    {
        return billCurrency;
    }
    public void setBillCurrency(String billCurrency)
    {
        this.billCurrency = billCurrency;
    }
    public String getBillRate()
    {
        return billRate;
    }
    public void setBillRate(String billRate)
    {
        this.billRate = billRate;
    }
    public String getResourceStatus()
    {
        return resourceStatus;
    }
    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }
    public String getMentionLWD()
    {
        return mentionLWD;
    }
    public void setMentionLWD(String mentionLWD)
    {
        this.mentionLWD = mentionLWD;
    }
    public String getReasonForTermination()
    {
        return reasonForTermination;
    }
    public void setReasonForTermination(String reasonForTermination)
    {
        this.reasonForTermination = reasonForTermination;
    }
    public String geteHr()
    {
        return eHr;
    }
    public void seteHr(String eHr)
    {
        this.eHr = eHr;
    }
    public String getNicheSkill()
    {
        return nicheSkill;
    }
    public void setNicheSkill(String nicheSkill)
    {
        this.nicheSkill = nicheSkill;
    }
    public String getHsbcProjectId()
    {
        return hsbcProjectId;
    }
    public void setHsbcProjectId(String hsbcProjectId)
    {
        this.hsbcProjectId = hsbcProjectId;
    }
    public String getRole()
    {
        return role;
    }
    public void setRole(String role)
    {
        this.role = role;
    }
    public String getSkill()
    {
        return skill;
    }
    public void setSkill(String skill)
    {
        this.skill = skill;
    }
    public String getCsSubDeptId()
    {
        return csSubDeptId;
    }
    public void setCsSubDeptId(String csSubDeptId)
    {
        this.csSubDeptId = csSubDeptId;
    }
    
    
    
    public Employee(String employeeId, String hsbcStaffId, String staffName,
            String ln, String staffRegion, String staffLocation,
            String locationType, String onShoreOrOffShore, String sow,
            String sowExpiredDate, String staffCategory, String engagementType,
            String hsbcDoj, String graduationDate, String billingEntity,
            String billCurrency, String billRate, String resourceStatus,
            String mentionLWD, String reasonForTermination, String eHr,
            String nicheSkill, String hsbcProjectId, String role, String skill,
            String csSubDeptId)
    {
        super();
        this.employeeId = employeeId;
        this.hsbcStaffId = hsbcStaffId;
        this.staffName = staffName;
        this.ln = ln;
        this.staffRegion = staffRegion;
        this.staffLocation = staffLocation;
        this.locationType = locationType;
        this.onShoreOrOffShore = onShoreOrOffShore;
        this.sow = sow;
        this.sowExpiredDate = sowExpiredDate;
        this.staffCategory = staffCategory;
        this.engagementType = engagementType;
        this.hsbcDoj = hsbcDoj;
        this.graduationDate = graduationDate;
        this.billingEntity = billingEntity;
        this.billCurrency = billCurrency;
        this.billRate = billRate;
        this.resourceStatus = resourceStatus;
        this.mentionLWD = mentionLWD;
        this.reasonForTermination = reasonForTermination;
        this.eHr = eHr;
        this.nicheSkill = nicheSkill;
        this.hsbcProjectId = hsbcProjectId;
        this.role = role;
        this.skill = skill;
        this.csSubDeptId = csSubDeptId;
    }
    public Employee()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

}

package com.pmo.dashboard.entity;

public class EmployeeInfo
{
    private String employeeId;
    private String hsbcStaffId;
    private String eHr;
    private String staffName;
    private String ln;
    private String csDeptName;
    private String csSubDeptName;
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
    public String geteHr()
    {
        return eHr;
    }
    public void seteHr(String eHr)
    {
        this.eHr = eHr;
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
    public String getCsDeptName()
    {
        return csDeptName;
    }
    public void setCsDeptName(String csDeptName)
    {
        this.csDeptName = csDeptName;
    }
    public String getCsSubDeptName()
    {
        return csSubDeptName;
    }
    public void setCsSubDeptName(String csSubDeptName)
    {
        this.csSubDeptName = csSubDeptName;
    }
    public EmployeeInfo(String employeeId, String hsbcStaffId, String eHr,
            String staffName, String ln, String csDeptName,
            String csSubDeptName)
    {
        super();
        this.employeeId = employeeId;
        this.hsbcStaffId = hsbcStaffId;
        this.eHr = eHr;
        this.staffName = staffName;
        this.ln = ln;
        this.csDeptName = csDeptName;
        this.csSubDeptName = csSubDeptName;
    }
    public EmployeeInfo()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
}

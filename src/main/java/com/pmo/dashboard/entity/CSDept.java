package com.pmo.dashboard.entity;

public class CSDept
{

    private String csSubDeptId;
    private String csBuName;
    private String csSubDeptName;
    private String csDeptName;
    private String am;
    public String getCsSubDeptId()
    {
        return csSubDeptId;
    }
    public void setCsSubDeptId(String csSubDeptId)
    {
        this.csSubDeptId = csSubDeptId;
    }
    public String getCsBuName()
    {
        return csBuName;
    }
    public void setCsBuName(String csBuName)
    {
        this.csBuName = csBuName;
    }
    public String getCsSubDeptName()
    {
        return csSubDeptName;
    }
    public void setCsSubDeptName(String csSubDeptName)
    {
        this.csSubDeptName = csSubDeptName;
    }
    public String getCsDeptName()
    {
        return csDeptName;
    }
    public void setCsDeptName(String csDeptName)
    {
        this.csDeptName = csDeptName;
    }
    public String getAm()
    {
        return am;
    }
    public void setAm(String am)
    {
        this.am = am;
    }
    public CSDept(String csSubDeptId, String csBuName, String csSubDeptName,
            String csDeptName, String am)
    {
        super();
        this.csSubDeptId = csSubDeptId;
        this.csBuName = csBuName;
        this.csSubDeptName = csSubDeptName;
        this.csDeptName = csDeptName;
        this.am = am;
    }
    public CSDept()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
}

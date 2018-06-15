package com.pmo.dashboard.entity;

public class EmployeePageCondition extends PageCondition
{
    private String hsbcStaffId;
    private String eHr;
    private String csbuName;
    private String csDeptName;
    private String csSubDeptName;
    private String lob;
    private String resourceStatus;
    private String staffName;
    private String rmUserId;
    private String engagementType;
    private int pageRecordsNum;
    
    private String[] csSubDept;
    private String[] csbuNames;
    
    
    //员工调级记录表中的创建时间
    private String empupgradestartdate;
    
    private String empupgradeenddate;
    
    
    
    
    
    
	public String getEmpupgradestartdate() {
		return empupgradestartdate;
	}
	public void setEmpupgradestartdate(String empupgradestartdate) {
		this.empupgradestartdate = empupgradestartdate;
	}
	public String getEmpupgradeenddate() {
		return empupgradeenddate;
	}
	public void setEmpupgradeenddate(String empupgradeenddate) {
		this.empupgradeenddate = empupgradeenddate;
	}
	public String[] getCsbuNames() {
		return csbuNames;
	}
	public void setCsbuNames(String[] csbuNames) {
		this.csbuNames = csbuNames;
	}
	public String[] getCsSubDept() {
		return csSubDept;
	}
	public void setCsSubDept(String[] csSubDept) {
		this.csSubDept = csSubDept;
	}
	public int getPageRecordsNum() {
		return pageRecordsNum;
	}
	public void setPageRecordsNum(int pageRecordsNum) {
		this.pageRecordsNum = pageRecordsNum;
	}
	public String getEngagementType() {
		return engagementType;
	}
	public void setEngagementType(String engagementType) {
		this.engagementType = engagementType;
	}
	public String getRmUserId() {
		return rmUserId;
	}
	public void setRmUserId(String rmUserId) {
		this.rmUserId = rmUserId;
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
    public String getCsbuName()
    {
        return csbuName;
    }
    public void setCsbuName(String csbuName)
    {
        this.csbuName = csbuName;
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
    public String getLob()
    {
        return lob;
    }
    public void setLob(String lob)
    {
        this.lob = lob;
    }
    public String getResourceStatus()
    {
        return resourceStatus;
    }
    public void setResourceStatus(String resourceStatus)
    {
        this.resourceStatus = resourceStatus;
    }
    public String getStaffName()
    {
        return staffName;
    }
    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }
    public EmployeePageCondition()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public EmployeePageCondition(String currentPage, String pageCount)
    {
        super(currentPage, pageCount);
        // TODO Auto-generated constructor stub
    }
    public EmployeePageCondition(String hsbcStaffId, String eHr,
            String csbuName, String csDeptName, String csSubDeptName,
            String lob)
    {
        super();
        this.hsbcStaffId = hsbcStaffId;
        this.eHr = eHr;
        this.csbuName = csbuName;
        this.csDeptName = csDeptName;
        this.csSubDeptName = csSubDeptName;
        this.lob = lob;
    }
    public EmployeePageCondition(String hsbcStaffId, String eHr,
            String csbuName, String csDeptName, String csSubDeptName,
            String lob, String resourceStatus, String staffName)
    {
        super();
        this.hsbcStaffId = hsbcStaffId;
        this.eHr = eHr;
        this.csbuName = csbuName;
        this.csDeptName = csDeptName;
        this.csSubDeptName = csSubDeptName;
        this.lob = lob;
        this.resourceStatus = resourceStatus;
        this.staffName = staffName;
    }
    
    
}

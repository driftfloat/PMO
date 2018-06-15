package com.pmo.dashboard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class EmployeeUpgradeRecord {
    private String id;

    private String employeeId;

    private Date createDate;

    private String operateId;

    private Date effectDate;

    private String originalLevel;

    private String nowLevel;

    private BigDecimal previousRate;

    private BigDecimal nowRate;
    
    
    /**
     * 表外字段
     * @return
     */
    private String ehr;
    private String staffid;
    private String lob;
    private String stringcreatedate;
    private String stringeffectivedate;
    private String operationname;
    
    
    
    
    
    

    public String getOperationname() {
		return operationname;
	}

	public void setOperationname(String operationname) {
		this.operationname = operationname;
	}

	public String getStringcreatedate() {
		return stringcreatedate;
	}

	public void setStringcreatedate(String stringcreatedate) {
		this.stringcreatedate = stringcreatedate;
	}

	public String getStringeffectivedate() {
		return stringeffectivedate;
	}

	public void setStringeffectivedate(String stringeffectivedate) {
		this.stringeffectivedate = stringeffectivedate;
	}

	public String getEhr() {
		return ehr;
	}

	public void setEhr(String ehr) {
		this.ehr = ehr;
	}

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId == null ? null : operateId.trim();
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public String getOriginalLevel() {
        return originalLevel;
    }

    public void setOriginalLevel(String originalLevel) {
        this.originalLevel = originalLevel == null ? null : originalLevel.trim();
    }

    public String getNowLevel() {
        return nowLevel;
    }

    public void setNowLevel(String nowLevel) {
        this.nowLevel = nowLevel == null ? null : nowLevel.trim();
    }

    public BigDecimal getPreviousRate() {
        return previousRate;
    }

    public void setPreviousRate(BigDecimal previousRate) {
        this.previousRate = previousRate;
    }

    public BigDecimal getNowRate() {
        return nowRate;
    }

    public void setNowRate(BigDecimal nowRate) {
        this.nowRate = nowRate;
    }
}
package com.pmo.dashboard.entity;

public class Demand
{

    private String demandId;
    
    private String rr;
    
    private String jobCode;
    
    private String skill;
    
    private String requestor;
    
    private String position;
    
    private String hsbcSubDeptId;
    
    /** 关联的部门信息*/
    private HSBCDept hsbcDept;
    
    private String location;
    
    private String reqPublishedDate;
    
    private String ageing;
    
    private String profilesNo;
    
    private String interviewedNo;
    
    private String status;
    
    private String staffName;
    
    private String proposedJoiningDate;
    
    private String dcCleared;
    
    private String sowSigned;
    
    private String onboarded;
    
    private String abort;
    
    private String delayed;
    
    private String reason;
    
    private String nextAction;
    
    private String status2;
    
    private String remark;
    
    private String csSubDept;
    
    private String plannedOnboardDate;
    
    private String doNumber;
    
    private String level;
    
    private String hrPriority;
    
    private String reqReceivedDate;
    
    private String ageingReceived;

    public String getDemandId()
    {
        return demandId;
    }

    public void setDemandId(String demandId)
    {
        this.demandId = demandId;
    }

    public String getRr()
    {
        return rr;
    }

    public void setRr(String rr)
    {
        this.rr = rr;
    }

    public String getJobCode()
    {
        return jobCode;
    }

    public void setJobCode(String jobCode)
    {
        this.jobCode = jobCode;
    }

    public String getSkill()
    {
        return skill;
    }

    public void setSkill(String skill)
    {
        this.skill = skill;
    }

    public String getRequestor()
    {
        return requestor;
    }

    public void setRequestor(String requestor)
    {
        this.requestor = requestor;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }


    public HSBCDept getHsbcDept() {
		return hsbcDept;
	}

	public void setHsbcDept(HSBCDept hsbcDept) {
		this.hsbcDept = hsbcDept;
	}

	public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getReqPublishedDate()
    {
        return reqPublishedDate;
    }

    public void setReqPublishedDate(String reqPublishedDate)
    {
        this.reqPublishedDate = reqPublishedDate;
    }

    public String getAgeing()
    {
        return ageing;
    }

    public void setAgeing(String ageing)
    {
        this.ageing = ageing;
    }

    public String getProfilesNo()
    {
        return profilesNo;
    }

    public void setProfilesNo(String profilesNo)
    {
        this.profilesNo = profilesNo;
    }

    public String getInterviewedNo()
    {
        return interviewedNo;
    }

    public void setInterviewedNo(String interviewedNo)
    {
        this.interviewedNo = interviewedNo;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStaffName()
    {
        return staffName;
    }

    public void setStaffName(String staffName)
    {
        this.staffName = staffName;
    }

    public String getProposedJoiningDate()
    {
        return proposedJoiningDate;
    }

    public void setProposedJoiningDate(String proposedJoiningDate)
    {
        this.proposedJoiningDate = proposedJoiningDate;
    }

    public String getDcCleared()
    {
        return dcCleared;
    }

    public void setDcCleared(String dcCleared)
    {
        this.dcCleared = dcCleared;
    }

    public String getSowSigned()
    {
        return sowSigned;
    }

    public void setSowSigned(String sowSigned)
    {
        this.sowSigned = sowSigned;
    }

    public String getOnboarded()
    {
        return onboarded;
    }

    public void setOnboarded(String onboarded)
    {
        this.onboarded = onboarded;
    }

    public String getAbort()
    {
        return abort;
    }

    public void setAbort(String abort)
    {
        this.abort = abort;
    }

    public String getDelayed()
    {
        return delayed;
    }

    public void setDelayed(String delayed)
    {
        this.delayed = delayed;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getNextAction()
    {
        return nextAction;
    }

    public void setNextAction(String nextAction)
    {
        this.nextAction = nextAction;
    }

    public String getStatus2()
    {
        return status2;
    }

    public void setStatus2(String status2)
    {
        this.status2 = status2;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getCsSubDept()
    {
        return csSubDept;
    }

    public void setCsSubDept(String csSubDept)
    {
        this.csSubDept = csSubDept;
    }

    public String getPlannedOnboardDate()
    {
        return plannedOnboardDate;
    }

    public void setPlannedOnboardDate(String plannedOnboardDate)
    {
        this.plannedOnboardDate = plannedOnboardDate;
    }

    public String getDoNumber()
    {
        return doNumber;
    }

    public void setDoNumber(String doNumber)
    {
        this.doNumber = doNumber;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(String level)
    {
        this.level = level;
    }

    public String getHrPriority()
    {
        return hrPriority;
    }

    public void setHrPriority(String hrPriority)
    {
        this.hrPriority = hrPriority;
    }

    public String getReqReceivedDate()
    {
        return reqReceivedDate;
    }

    public void setReqReceivedDate(String reqReceivedDate)
    {
        this.reqReceivedDate = reqReceivedDate;
    }

    public String getAgeingReceived()
    {
        return ageingReceived;
    }

    public void setAgeingReceived(String ageingReceived)
    {
        this.ageingReceived = ageingReceived;
    }
    
    public String getHsbcSubDeptId() {
		return hsbcSubDeptId;
	}

	public void setHsbcSubDeptId(String hsbcSubDeptId) {
		this.hsbcSubDeptId = hsbcSubDeptId;
	}

    public Demand()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	public Demand(String demandId, String rr, String jobCode, String skill, String requestor, String position,
			String hsbcSubDeptId, String location, String reqPublishedDate, String ageing, String profilesNo,
			String interviewedNo, String status, String staffName, String proposedJoiningDate, String dcCleared,
			String sowSigned, String onboarded, String abort, String delayed, String reason, String nextAction,
			String status2, String remark, String csSubDept, String plannedOnboardDate, String doNumber, String level,
			String hrPriority, String reqReceivedDate, String ageingReceived) {
		super();
		this.demandId = demandId;
		this.rr = rr;
		this.jobCode = jobCode;
		this.skill = skill;
		this.requestor = requestor;
		this.position = position;
		this.hsbcSubDeptId = hsbcSubDeptId;
		this.location = location;
		this.reqPublishedDate = reqPublishedDate;
		this.ageing = ageing;
		this.profilesNo = profilesNo;
		this.interviewedNo = interviewedNo;
		this.status = status;
		this.staffName = staffName;
		this.proposedJoiningDate = proposedJoiningDate;
		this.dcCleared = dcCleared;
		this.sowSigned = sowSigned;
		this.onboarded = onboarded;
		this.abort = abort;
		this.delayed = delayed;
		this.reason = reason;
		this.nextAction = nextAction;
		this.status2 = status2;
		this.remark = remark;
		this.csSubDept = csSubDept;
		this.plannedOnboardDate = plannedOnboardDate;
		this.doNumber = doNumber;
		this.level = level;
		this.hrPriority = hrPriority;
		this.reqReceivedDate = reqReceivedDate;
		this.ageingReceived = ageingReceived;
	}

}

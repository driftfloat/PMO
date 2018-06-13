package com.pmo.dashboard.entity;

import java.util.List;

public class QueryModel {
	
	
	   @SuppressWarnings("rawtypes")
	   private List csdeptids;
	   
	   private String status;
	   
	   private String rr;
	   
	   private String jobcode;
	   
	   

	   
	   
	   

	   public String getRr() {
		  return rr;
	   }

	   public void setRr(String rr) {
		  this.rr = rr;
	   }

	   public String getJobcode() {
		  return jobcode;
	   }

	   public void setJobcode(String jobcode) {
		  this.jobcode = jobcode;
	   }

	   @SuppressWarnings("rawtypes")
	   public List getCsdeptids() {
		   return csdeptids;
	   }

	   public void setCsdeptids(@SuppressWarnings("rawtypes") List csdeptids) {
		   this.csdeptids = csdeptids;
	   }

	   public String getStatus() {
		   return status;
	   }

	   public void setStatus(String status) {
		   this.status = status;
	   }
	 

}

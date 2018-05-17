package com.pmo.dashboard.entity;

public class HSBCDept
{
    
	private String id;

    private String name;

    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }
	
	
	
	
   /** private String hsbcSubDeptId;
    private String hsbcSubDeptName;
    private String hsbcDeptName;
    
    
    public String getHsbcSubDeptId()
    {
        return hsbcSubDeptId;
    }
    public void setHsbcSubDeptId(String hsbcSubDeptId)
    {
        this.hsbcSubDeptId = hsbcSubDeptId;
    }
    public String getHsbcSubDeptName()
    {
        return hsbcSubDeptName;
    }
    public void setHsbcSubDeptName(String hsbcSubDeptName)
    {
        this.hsbcSubDeptName = hsbcSubDeptName;
    }
    public String getHsbcDeptName()
    {
        return hsbcDeptName;
    }
    public void setHsbcDeptName(String hsbcDeptName)
    {
        this.hsbcDeptName = hsbcDeptName;
    }
*/
    
}

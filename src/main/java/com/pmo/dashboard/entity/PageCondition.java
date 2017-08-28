package com.pmo.dashboard.entity;

public class PageCondition
{
    private String currentPage;
    private String pageCount;
    private Integer currPage;
    private Integer totalPage;
    private Integer pageSize = 10;
    
    public String getCurrentPage()
    {
        return currentPage;
    }
    public void setCurrentPage(String currentPage)
    {
        this.currentPage = currentPage;
    }
    public String getPageCount()
    {
        return pageCount;
    }
    public void setPageCount(String pageCount)
    {
        this.pageCount = pageCount;
    }
    public Integer getCurrPage() {
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public PageCondition()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public PageCondition(String currentPage, String pageCount)
    {
        super();
        this.currentPage = currentPage;
        this.pageCount = pageCount;
    }
    
}

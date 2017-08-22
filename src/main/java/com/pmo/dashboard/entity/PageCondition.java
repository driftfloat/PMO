package com.pmo.dashboard.entity;

public class PageCondition
{
    private String currentPage;
    private String pageCount;
    
    
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

package com.pmo.dashboard.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.pmo.dashboard.entity.Employee;

import jxl.Sheet;
import jxl.Workbook;

public class InportEmployeeInfo
{
    
    public static void main(String[] args){
        
        List<Employee> list = new ArrayList<Employee>();
        try
        {
            Workbook rwb = Workbook.getWorkbook(new File("C:/Users/nick/Desktop/GSV Engagement Dashboard_170725.xlsx"));
            Sheet rs = rwb.getSheet(4);
            int clos = rs.getColumns();
            int rows = rs.getRows();

            /*for (int i = 1; i < rows; i++)
            {
                */
                    String employeeId = Utils.getUUID();
                    String hsbcStaffId = rs.getCell(2, 1).getContents();
                    String staffName = rs.getCell(2, 1).getContents();
                    String ln = rs.getCell(2, 1).getContents();
                    String staffRegion = rs.getCell(2, 1).getContents();
                    String staffLocation = rs.getCell(2, 1).getContents();
                    String locationType = rs.getCell(2, 1).getContents();
                    String onShoreOrOffShore = rs.getCell(2, 1).getContents();
                    String sow = rs.getCell(2, 1).getContents();
                    String sowExpiredDate = rs.getCell(2, 1).getContents();
                    String staffCategory = rs.getCell(2, 1).getContents();
                    String engagementType = rs.getCell(2, 1).getContents();
                    String hsbcDoj = rs.getCell(2, 1).getContents();
                    int experienceONHSBC = 0;
                    String graduationDate = rs.getCell(2, 1).getContents();
                    int totalExperience = 0;
                    String billingEntity = rs.getCell(2, 1).getContents();
                    String billCurrency = rs.getCell(2, 1).getContents();
                    String billRate = rs.getCell(2, 1).getContents();
                    String resourceStatus = rs.getCell(2, 1).getContents();
                    String mentionLWD = rs.getCell(2, 1).getContents();
                    String reasonForTermination = rs.getCell(2, 1).getContents();
                    String eHr = rs.getCell(2, 1).getContents();
                    String nicheSkill = rs.getCell(2, 1).getContents();
                    String hsbcProjectId = rs.getCell(2, 1).getContents();
                    String role = rs.getCell(2, 1).getContents();
                    String skill = rs.getCell(2, 1).getContents();
                    String csSubDeptId = rs.getCell(2, 1).getContents();
                    
                    

                    /*list.add(new Employee(employeeId,hsbcStaffId,staffName,ln,staffRegion,staffLocation,
                         locationType,onShoreOrOffShore,sow,sowExpiredDate,staffCategory,engagementType,
                         hsbcDoj,experienceONHSBC,graduationDate,totalExperience,billingEntity,billCurrency,
                         billRate,resourceStatus,mentionLWD,reasonForTermination,eHr,nicheSkill,
                         hsbcProjectId,role,skill,csSubDeptId));*/
                
            //}
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return;
    }

}

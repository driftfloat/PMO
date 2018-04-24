package com.pmo.dashboard.dao;

import java.math.BigDecimal;

import com.pmo.dashboard.entity.ChinaWorkHour;
import com.pmo.dashboard.entity.WorkHour;

public interface WorkHourMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkHour record);

    int insertSelective(WorkHour record);

    ChinaWorkHour selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkHour record);

    int updateByPrimaryKey(WorkHour record);
    
    BigDecimal queryWorkHour(WorkHour condition);
}
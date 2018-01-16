package com.pmo.dashboard.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseLogMapper<T, ID extends Serializable>{
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
    public int save(T entity);  
    
    /**
	 * 修改
	 * @param entity
	 * @return
	 */
    public int update(T entity);  
  
    /**
     * 通过ID删除
     * @param clazz
     * @param id
     * @return
     */
    public int deleteById(@SuppressWarnings("unchecked") ID... ids);  
  
    /**
     * 通过ID查询
     * @param clazz
     * @param id
     * @return
     */
    public T getById(@Param("logId") String logId);  
  
    /**
     * 获取所有
     * @param clazz
     * @return
     */
    public List<T> listAll();  
        
      
}

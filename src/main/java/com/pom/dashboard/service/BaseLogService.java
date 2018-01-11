package com.pom.dashboard.service;

import java.io.Serializable;
import java.util.List;

public interface BaseLogService<T, ID extends Serializable>{
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
    public boolean save(T entity);  
    
    /**
	 * 修改
	 * @param entity
	 * @return
	 */
    public boolean update(T entity);  
  
    /**
     * 通过ID删除
     * @param clazz
     * @param id
     * @return
     */
    public boolean deleteById(@SuppressWarnings("unchecked") ID... ids);  
  
    /**
     * 通过ID查询
     * @param clazz
     * @param id
     * @return
     */
    public T getById(Long id);  
  
    /**
     * 获取所有
     * @param clazz
     * @return
     */
    public List<T> listAll();  
}

package com.quhwa.scalehouse.service.scale.service;

import java.util.List;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Device;
import com.quhwa.scalehouse.service.scale.model.Person;


public interface IPersonService extends IBaseService<Person>{
	
	/**
	 * 
	 * @Title:        delete 
	 * @Description:  删除 
	 * @param:        @param id
	 * @param:        @return    
	 * @return:       ResultEntity<String>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:05:01
	 */
	ResultEntity<String> delete(Integer id);
	
	/**
	 * 
	 * @Title:        deleteGroup 
	 * @Description:  批量删除 
	 * @param:        @param ids
	 * @param:        @return    
	 * @return:       ResultEntity<String>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:05:33
	 */
	ResultEntity<String> deleteGroup(String[] ids);
	
	/**
	 * 
	 * @Title:        insert 
	 * @Description:  插入 
	 * @param:        @param record
	 * @param:        @return    
	 * @return:       ResultEntity<String>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:04:51
	 */
	ResultEntity<String> insert(Person record);
	
	ResultEntity<String> insertActive(Person record);
	
	/**
	 * 
	 * @Title:        getOne 
	 * @Description:  获取一条数据 
	 * @param:        @param id
	 * @param:        @return    
	 * @return:       ResultEntity<Person>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:06:15
	 */
	ResultEntity<Person> getOne(Integer id);
	
	/**
	 * 
	 * @Title:        update 
	 * @Description:  更新 
	 * @param:        @param record
	 * @param:        @return    
	 * @return:       ResultEntity<String>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:07:17
	 */
    ResultEntity<String> update(Person record);
    
    ResultEntity<String> updateActive(Person record);
    
    ResultEntity<String> updateEasyui(Person record);
    
    
    ResultEntity<List<Long>> getChartsData(int intervals,String countryCode,String type);
    
    ResultEntity<List<Long>> getPersons(int intervals,String countryCode,String type);
    
    /**
     * 
     * @Title:        getAll 
     * @Description:  获取所用数据 
     * @param:        @param record
     * @param:        @return    
     * @return:       ResultEntity<List<Person>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:08:38
     */
    ResultEntity<List<Person>> getAll(String countryCode,String type);
    
    ResultEntity<List<Person>> queryForList(Person record);
    
    /**
     * 
     * @Title:        queryByAccountAndPassword 
     * @Description:  通过账户和密码查询 
     * @param:        @param record
     * @param:        @return    
     * @return:       ResultEntity<Person>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:08:03
     */
    ResultEntity<Person> queryByAccountAndPassword(Person record);
    
    /**
	 * 找回密码
	 * @param username
	 * @param sid
	 * @param timeFlag
	 * @return
	 */
	ResultEntity<String> resetPwd(String username, String sid,Long timeFlag);

	/**
	 * 进入找回密码
	 * @param username
	 * @return
	 */
	ResultEntity<String> toResetPwd(String username);
	
	ResultEntity<List<Device>> getCountry();
	
	ResultEntity<String> personNum(String countryCode,String type);
	
	ResultEntity<String> todayActivePerN(String countryCode,String type);
}

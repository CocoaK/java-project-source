package com.quhwa.scalehouse.service.scale.service;

import java.util.Date;
import java.util.List;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.BodyInfo;
import com.quhwa.scalehouse.service.scale.model.Person;

public interface IBodyInfoService extends IBaseService<BodyInfo>{
	
	/**
	 * 
	 * @Title:        delete 
	 * @Description:  删除
	 * @param:        @param id
	 * @param:        @return    
	 * @return:       ResultEntity<String>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午1:56:45
	 */
	ResultEntity<String> delete(Integer id);
	
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
	 * @Date          2018-5-17 下午1:59:35
	 */
	ResultEntity<String> insert(BodyInfo record);
	
	ResultEntity<String> insertActive(BodyInfo record);
	
	/**
	 * 
	 * @Title:        getOne 
	 * @Description:  获取一条数据
	 * @param:        @param id
	 * @param:        @return    
	 * @return:       ResultEntity<BodyInfo>    
	 * @throws 
	 * @author        kouzhao
	 * @Date          2018-5-17 下午2:00:06
	 */
    ResultEntity<BodyInfo> getOne(Integer id);
    
    /**
     * 
     * @Title:        update 
     * @Description:  TODO 
     * @param:        @param record
     * @param:        @return    
     * @return:       ResultEntity<String>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:00:14
     */
    ResultEntity<String> update(BodyInfo record);
    
    ResultEntity<String> updateActive(BodyInfo record);
    
    /**
     * 
     * @Title:        getList 
     * @Description:  TODO 
     * @param:        @param per
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:00:23
     */
    ResultEntity<List<BodyInfo>> getList(Person per);
    
    /**
     * 
     * @Title:        getTodayData 
     * @Description:  TODO 
     * @param:        @param per
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:49
     */
    ResultEntity<List<BodyInfo>> getTodayData(Person per);
    
    /**
     * 
     * @Title:        getSevenDaysData 
     * @Description:  TODO 
     * @param:        @param per
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:44
     */
    ResultEntity<List<BodyInfo>> getSevenDaysData(Person per);
    
    /**
     * 
     * @Title:        getCurrentMonthData 
     * @Description:  TODO 
     * @param:        @param per
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:40
     */
    ResultEntity<List<BodyInfo>> getCurrentMonthData(Person per);
    
    /**
     * 
     * @Title:        getByTimesolt 
     * @Description:  TODO 
     * @param:        @param startTime
     * @param:        @param endTime
     * @param:        @param account
     * @param:        @param password
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:35
     */
    ResultEntity<List<BodyInfo>> getByTimesolt(Date startTime,Date endTime,String account,String password);
    
    /**
     * 
     * @Title:        getByPersonId 
     * @Description:  TODO 
     * @param:        @param per
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:30
     */
    ResultEntity<List<BodyInfo>> getByPersonId(Person per);
    
    /**
     * 
     * @Title:        queryByWeight 
     * @Description:  TODO 
     * @param:        @param weight
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:23
     */
    ResultEntity<List<BodyInfo>> queryByWeight(Double weight);
    
    /**
     * 
     * @Title:        queryByHighWeight 
     * @Description:  TODO 
     * @param:        @param weight
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:16
     */
    ResultEntity<List<BodyInfo>> queryByHighWeight(Double weight);
    
    /**
     * 
     * @Title:        getOtherdayData 
     * @Description:  TODO 
     * @param:        @param recordTime
     * @param:        @param account
     * @param:        @param password
     * @param:        @return    
     * @return:       ResultEntity<List<BodyInfo>>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:01:07
     */
    ResultEntity<List<BodyInfo>> getOtherdayData(Date recordTime,String account,String password);
    
    ResultEntity<List<BodyInfo>> queryByPersonId(Person per);
    
    /**
     * 
     * @Title:        insertJsonGroup 
     * @Description:  TODO 
     * @param:        @param jsonGroup
     * @param:        @param account
     * @param:        @param password
     * @param:        @return    
     * @return:       ResultEntity<String>    
     * @throws 
     * @author        kouzhao
     * @Date          2018-5-17 下午2:00:40
     */
    ResultEntity<String> insertJsonGroup(String jsonGroup,String account,String password);
}

package com.papabear.order.dao;

import java.util.List;
import java.util.Map;

import com.papabear.order.entity.MallAfterSalesService;

public interface MallAfterSalesServiceDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallAfterSalesService record);

    int insertSelective(MallAfterSalesService record);

    MallAfterSalesService selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallAfterSalesService record);

    int updateByPrimaryKey(MallAfterSalesService record);
    
    /**
     * shop售后服务信息列表
     * @param map
     * @return
     */
    List<MallAfterSalesService> queryMallAfterSalesServiceByStatus(Map<String, Object> map);
    /**
     * cms售后服务列表查询
     * @param map
     * @return
     */
    List<MallAfterSalesService> queryAfterSalesServices(Map<String, Object> map);
    /**
     * cms售后服务列表查询总记录数
     * @param map
     * @return
     */
    Integer queryAfterSalesServicesCount(Map<String, Object> map);
    
    MallAfterSalesService getByOrderNumber(String orderNumber);
    
    List<MallAfterSalesService> queryServiceByStatus(Byte status);

	int queryMallAfterSalesServicesCount(Map<String, Object> map);

	List<MallAfterSalesService> queryMallAfterSalesServices(
			Map<String, Object> map);
}
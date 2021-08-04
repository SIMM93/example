package com.smartbr.mapper;

import com.smartbr.dao.MyBatisBaseDao;
import com.smartbr.entity.BrOrderRefund;
import org.springframework.stereotype.Repository;

/**
 * BrOrderRefundMapper继承基类
 */
@Repository
public interface WxOrderRefundMapper extends MyBatisBaseDao<BrOrderRefund, Long> {
}

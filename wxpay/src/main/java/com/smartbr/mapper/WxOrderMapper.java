package com.smartbr.mapper;

import com.smartbr.entity.BrReserveOrder;
import com.smartbr.entity.BrWxUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface WxOrderMapper {

    int updateByPrimaryKeySelective(BrReserveOrder brReserveOrder);

}

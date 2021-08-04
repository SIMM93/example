package com.smartbr.mapper;

import com.smartbr.dao.MyBatisBaseDao;
import com.smartbr.entity.BrWxUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

/**
 * BrWxUserMapper继承基类
 */
@Repository
public interface BrWxUserMapper extends MyBatisBaseDao<BrWxUser, Long> {
    /**
     * 根据openid查询数据库是否有数据
     *
     * @param wxUserId Openid
     * @return
     */
    BrWxUser selectByOpenId(@Param("openid") String wxUserId);

    BrWxUser disableWXUser(BrWxUser brWxUser);
}

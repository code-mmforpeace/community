package com.zhongyuanbbs.demo.Mapper;

import com.zhongyuanbbs.demo.domain.ZkNotify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotifyMapper {

    int queryNotifyCountByUserID(int userId);

    List<ZkNotify> queryNotifyByUserID(int userId);

    void createNotify(ZkNotify zkNotify);

    void changeStatus(ZkNotify zkNotify);

}

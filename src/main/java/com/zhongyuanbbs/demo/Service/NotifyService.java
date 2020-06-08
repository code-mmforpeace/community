package com.zhongyuanbbs.demo.Service;

import com.zhongyuanbbs.demo.domain.ZkNotify;
import com.zhongyuanbbs.demo.dto.NotifyDto;
import com.zhongyuanbbs.demo.dto.PageDto;

import java.util.List;

public interface NotifyService {

    int queryNotifyCountByUserID(int userId);

    PageDto<NotifyDto> queryNotifyByUserID(int userId);

    void createNotify(ZkNotify zkNotify);

    void changeStatus(ZkNotify zkNotify);

}

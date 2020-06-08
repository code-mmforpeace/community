package com.zhongyuanbbs.demo.Service.impl;

import com.zhongyuanbbs.demo.Mapper.NotifyMapper;
import com.zhongyuanbbs.demo.Service.NotifyService;
import com.zhongyuanbbs.demo.domain.ZkNotify;
import com.zhongyuanbbs.demo.dto.NotifyDto;
import com.zhongyuanbbs.demo.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyMapper notifyMapper;

    @Override
    public int queryNotifyCountByUserID(int userId) {
        return 0;
    }

    @Override
    public PageDto<NotifyDto> queryNotifyByUserID(int userId) {
        return null;
    }

    @Override
    public void createNotify(ZkNotify zkNotify) {

    }

    @Override
    public void changeStatus(ZkNotify zkNotify) {

    }
}

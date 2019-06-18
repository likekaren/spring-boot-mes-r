package com.karen.mesr.impl;

import com.github.pagehelper.PageHelper;
import com.karen.mesr.bean.Robot;
import com.karen.mesr.bean.RobotVo;
import com.karen.mesr.mapper.RobotMapper;
import com.karen.mesr.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobotServiceImpl implements RobotService {
    @Autowired
    private RobotMapper robotMapper;

    @Override
    public List<Robot> findRobotList(RobotVo robotVo)throws Exception{
        int page = robotVo.getPageQuery().getPage();
        int rows = robotVo.getPageQuery().getRows();

        PageHelper.startPage(page,rows);
        return robotMapper.findRobotList(robotVo);
    }
}

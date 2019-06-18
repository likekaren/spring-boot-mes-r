package com.karen.mesr.service;

import com.karen.mesr.bean.Robot;
import com.karen.mesr.bean.RobotVo;

import java.util.List;

public interface RobotService {
    public List<Robot> findRobotList(RobotVo robotVo)throws Exception;
}

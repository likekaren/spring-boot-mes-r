package com.karen.mesr.mapper;

import com.karen.mesr.bean.Robot;
import com.karen.mesr.bean.RobotVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface RobotMapper {
    public List<Robot> findRobotList(RobotVo robotVo)throws Exception;

}

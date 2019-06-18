package com.karen.mesr.controller;

import com.github.pagehelper.PageInfo;
import com.karen.mesr.bean.PageQuery;
import com.karen.mesr.bean.Robot;
import com.karen.mesr.bean.RobotSolrVO;
import com.karen.mesr.bean.RobotVo;
import com.karen.mesr.mapper.RobotMapper;
import com.karen.mesr.result.DataGridResultInfo;
import com.karen.mesr.service.RobotService;

import com.karen.mesr.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RobotController {

    private final static String BASE_URL = "http://47.103.29.1:8080/solr";
    @Autowired(required = false)
    RobotMapper robotMapper;

    @Autowired
    private SolrService solrService;

    @Autowired
    private RobotService robotService;

    @RequestMapping("/rob")
    public String querybot() throws Exception{
        return "/rob";
    }

    @GetMapping("/main")
    public String bot() throws Exception{
        return "/rob/list";
    }


//    @RequestMapping("/robotlist")
//    public @ResponseBody
//    DataGridResultInfo querystandard_result(RobotVo robotVo,
//                                            Integer rows, Integer page) throws Exception {
//
//                // 非空校验
//        robotVo = robotVo != null ? robotVo
//                : new RobotVo();
//        // 获取 standardCustom
//        Robot robot = robotVo.getRobot();
//        // 非空 校验
//        robot = robot != null ? robot
//                : new Robot();
//        //分页支持
//        PageQuery pageQuery = new PageQuery(page, rows);
//        robotVo.setPageQuery(pageQuery);
//        // 分页查询，向sysuserQueryVo中传入pageQuery
//        List<Robot> list = robotService
//                .findRobotList(robotVo);
////        SolrDocumentList list = solrService.solrQuery();
//        PageInfo<Robot> pageInfo = new PageInfo<>(list);
//        int total = (int) pageInfo.getTotal();
//        DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
//        // 填充 total
//        dataGridResultInfo.setTotal(total);
//        // 填充 rows
//        dataGridResultInfo.setRows(list);
//        return dataGridResultInfo;
//    }
//
    @RequestMapping("/robotlist")
    public @ResponseBody
    DataGridResultInfo querystandard_result(RobotVo robotVo,
                                            Integer rows, Integer page) throws Exception {

        // 非空校验
        robotVo = robotVo != null ? robotVo
                : new RobotVo();
        // 获取 standardCustom
        RobotSolrVO robot = robotVo.getRobotSolrVO();
        // 非空 校验
        robot = robot != null ? robot
                : new RobotSolrVO();
        //分页支持
        PageQuery pageQuery = new PageQuery(page, rows);
        robotVo.setPageQuery(pageQuery);

        //得到前台数据

        String str = robot.getTotal();
        String fq = "total:*";
//        try {
////            if(!str.equals("undefined")){
////                fq = "total:"+ str;
////            }
//
////            if("".equals(str)){
////                fq = "total:"+ str;
////            }
//            //对于可能的情况最为稳妥的条件判断
//            if(!(null == str || str.length() == 0 || str.equals("undefined"))){
//                fq = "total:"+ str;
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }
        //排除空指针的问题
        if(!(null == str || str.length() == 0 || "undefined".equals(str))){
            fq = "total:"+ str;
        }




        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(BASE_URL);
        SolrClient solrClient = builder.build();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFilterQueries(fq);
        QueryResponse response = solrClient.query("mesrobot",query);
        SolrDocumentList results = response.getResults();

        ArrayList list = new ArrayList();
        for (SolrDocument result: results){
            RobotSolrVO rb = new RobotSolrVO();
            rb.setId((String)result.get("id"));
            rb.setRname((String)result.get("rname"));
            rb.setRnote((String)result.get("rnote"));
            rb.setStatus((String)result.get("status"));
            rb.setEx1((String)result.get("ex1"));
            rb.setEx2((String)result.get("ex2"));
            rb.setEx3((String)result.get("ex3"));
            rb.setEx4((String)result.get("ex4"));
            rb.setEx5((String)result.get("ex5"));
            rb.setEx6((String)result.get("ex6"));

            list.add(rb);

        }
        PageInfo<Robot> pageInfo = new PageInfo<>(list);
        int total = (int) pageInfo.getTotal();
        DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
        // 填充 total
        dataGridResultInfo.setTotal(total);
        // 填充 rows
        dataGridResultInfo.setRows(list);
        return dataGridResultInfo;
    }
}

package com.karen.mesr.controller;

import com.karen.mesr.result.ResultInfo;
import com.karen.mesr.service.SolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SolrController {
    @Autowired
    private SolrService solrService;
    /**
     * 导入数据到索引库
     */
    @RequestMapping("/importall")
    @ResponseBody
    public ResultInfo importAllRobots() throws Exception {
        ResultInfo resultInfo = solrService.importAllRobots();
        return resultInfo;
    }

    @RequestMapping("/solrQuery")
    @ResponseBody
    public ResultInfo solrQuery() throws Exception {
        ResultInfo resultInfo = solrService.solrQuery();
        return resultInfo;
    }

    @RequestMapping("/solrDelete")
    @ResponseBody
    public ResultInfo solrDelete() throws Exception {
        ResultInfo resultInfo = solrService.solrDelete();
        return resultInfo;
    }
}

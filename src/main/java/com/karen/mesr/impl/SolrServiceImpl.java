package com.karen.mesr.impl;

import com.karen.mesr.bean.RobotSolrVO;
import com.karen.mesr.mapper.RobotSolrVOMapper;
import com.karen.mesr.result.ResultInfo;
import com.karen.mesr.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {
    @Autowired
    private RobotSolrVOMapper robotSolrVOMapper;
//    @Autowired
//    private SolrClient solrClient;
    // solr url
    private final static String BASE_URL = "http://47.103.29.1:8080/solr";

//    public static HttpSolrClient getSolrClient(){
//        /*
//         * 设置超时时间
//         * .withConnectionTimeout(10000)
//         * .withSocketTimeout(60000)
//         */
//        return new HttpSolrClient.Builder(BASE_URL)
//                .withConnectionTimeout(10000)
//                .withSocketTimeout(60000)
//                .build();
//    }

    @Override
    public ResultInfo importAllRobots() throws Exception {
//        HttpSolrClient solrClient = getSolrClient();
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(BASE_URL);
        SolrClient solrClient = builder.build();

        List<RobotSolrVO> list = robotSolrVOMapper.getRobotAll();

        for (RobotSolrVO robotSolrVO : list) {
            //创建一个SolrInputDocument对象
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id",robotSolrVO.getId());
            document.setField("rname",robotSolrVO.getRname());
            document.setField("status",robotSolrVO.getStatus());
            document.setField("rnote",robotSolrVO.getRnote());
            document.setField("ex1",robotSolrVO.getEx1());
            document.setField("ex2",robotSolrVO.getEx2());
            document.setField("ex3",robotSolrVO.getEx3());
            document.setField("ex4",robotSolrVO.getEx4());
            document.setField("ex5",robotSolrVO.getEx5());
            document.setField("ex6",robotSolrVO.getEx6());

            //写入索引库
            solrClient.add("mesrobot",document);

        }
        //提交修改
        solrClient.commit("mesrobot");
        return null;
    }

    @Override
    public ResultInfo solrQuery() throws Exception {
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(BASE_URL);
        SolrClient solrClient = builder.build();

        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setFilterQueries("status:停止");

        //多条件查询
//        query.setQuery("rnote:不正");
//        query.setQuery("ex1:30.3");

        //设置查询的开始位置和查询条数
//        query.setStart(3);
//        query.setRows(3);
        QueryResponse response = solrClient.query("mesrobot",query);
//        // 定义查询条件
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("q", "*:*");
//        SolrParams mapSolrParams = new MapSolrParams(params);
//        //执行查询 第一个参数是collection，就是我们在solr中创建的core
//        QueryResponse response = solrClient.query("mesrobot",mapSolrParams);
        // 获取结果集
        SolrDocumentList results = response.getResults();
        System.out.println("共查询到记录：" + results.getNumFound() );
        for (SolrDocument result: results) {
            // SolrDocument 数据结构为Map
            System.out.println(result);
//          //选择特定属性
//            System.out.println(result.get("id"));
        }
        return null;
    }

    @Override
    public ResultInfo solrDelete() throws Exception {
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder(BASE_URL);
        SolrClient solrClient = builder.build();
//        solrClient.deleteById("mesrobot","1");
        // 还可以通过查询条件删除
        // solrClient.deleteByQuery("mesrobot", "查询条件");
        // 提交删除
        solrClient.commit("mesrobot");
        return null;
    }


}

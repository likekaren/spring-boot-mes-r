package com.karen.mesr.service;

import com.karen.mesr.result.ResultInfo;

public interface SolrService {
    ResultInfo importAllRobots() throws Exception;
    ResultInfo solrQuery() throws Exception;
    ResultInfo solrDelete() throws Exception;
}

package com.karen.mesr.service;

import com.karen.mesr.bean.RobotSolrVO;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface SearchSolrRepository extends SolrCrudRepository<RobotSolrVO,String> {
    @Query("?0")
    List<RobotSolrVO> findAll(String key, Pageable pageable);

}

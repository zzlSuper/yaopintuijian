package com.example.dao;

import com.example.entity.YaodianInfo;
import com.example.vo.YaodianInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface YaodianInfoDao extends Mapper<YaodianInfo> {
    List<YaodianInfoVo> findByName(@Param("name") String name);
    
    
    
}

package com.example.dao;

import com.example.entity.YufangInfo;
import com.example.vo.YufangInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface YufangInfoDao extends Mapper<YufangInfo> {
    List<YufangInfoVo> findByName(@Param("name") String name);
    
    
    
}

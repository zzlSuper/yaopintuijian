package com.example.dao;

import com.example.entity.ZhinengInfo;
import com.example.vo.ZhinengInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ZhinengInfoDao extends Mapper<ZhinengInfo> {
    List<ZhinengInfoVo> findByName(@Param("name") String name);
    
    
    
    Integer count();
}

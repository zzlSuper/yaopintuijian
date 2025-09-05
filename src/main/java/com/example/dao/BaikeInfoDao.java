package com.example.dao;

import com.example.entity.BaikeInfo;
import com.example.vo.BaikeInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BaikeInfoDao extends Mapper<BaikeInfo> {
    List<BaikeInfoVo> findByName(@Param("name") String name);
    
    
    
}

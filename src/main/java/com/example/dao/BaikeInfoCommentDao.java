package com.example.dao;

import com.example.entity.BaikeInfoComment;
import com.example.vo.BaikeInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BaikeInfoCommentDao extends Mapper<BaikeInfoComment> {
    List<BaikeInfoCommentVo> findAllVo(@Param("name") String name);
    List<BaikeInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}

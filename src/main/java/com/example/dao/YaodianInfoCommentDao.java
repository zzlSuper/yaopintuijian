package com.example.dao;

import com.example.entity.YaodianInfoComment;
import com.example.vo.YaodianInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface YaodianInfoCommentDao extends Mapper<YaodianInfoComment> {
    List<YaodianInfoCommentVo> findAllVo(@Param("name") String name);
    List<YaodianInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}

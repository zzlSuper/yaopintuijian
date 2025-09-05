package com.example.dao;

import com.example.entity.YufangInfoComment;
import com.example.vo.YufangInfoCommentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface YufangInfoCommentDao extends Mapper<YufangInfoComment> {
    List<YufangInfoCommentVo> findAllVo(@Param("name") String name);
    List<YufangInfoCommentVo> findByForeignId (@Param("id") Long id, @Param("parentId") Long parentId);
}

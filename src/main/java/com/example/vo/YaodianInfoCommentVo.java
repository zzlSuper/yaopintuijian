package com.example.vo;

import com.example.entity.YaodianInfoComment;
import java.util.List;

public class YaodianInfoCommentVo extends YaodianInfoComment {

    private String foreignName;

    private List<YaodianInfoCommentVo> children;

    public List<YaodianInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<YaodianInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}
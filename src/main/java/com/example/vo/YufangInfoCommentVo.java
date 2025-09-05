package com.example.vo;

import com.example.entity.YufangInfoComment;
import java.util.List;

public class YufangInfoCommentVo extends YufangInfoComment {

    private String foreignName;

    private List<YufangInfoCommentVo> children;

    public List<YufangInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<YufangInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}
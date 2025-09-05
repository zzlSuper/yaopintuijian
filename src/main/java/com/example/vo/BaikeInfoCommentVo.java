package com.example.vo;

import com.example.entity.BaikeInfoComment;
import java.util.List;

public class BaikeInfoCommentVo extends BaikeInfoComment {

    private String foreignName;

    private List<BaikeInfoCommentVo> children;

    public List<BaikeInfoCommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<BaikeInfoCommentVo> children) {
        this.children = children;
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName;
    }
}
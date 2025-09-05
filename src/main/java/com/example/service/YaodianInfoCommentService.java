package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.YaodianInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.YaodianInfoComment;
import com.example.vo.YaodianInfoCommentVo;
import com.example.entity.Account;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class YaodianInfoCommentService {

    @Resource
    private YaodianInfoCommentDao yaodianInfoCommentDao;

    public YaodianInfoComment add(YaodianInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        yaodianInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        yaodianInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(YaodianInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        yaodianInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public YaodianInfoComment findById(Long id) {
        return yaodianInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<YaodianInfoCommentVo> findAll() {
        return yaodianInfoCommentDao.findAllVo(null);
    }

    public PageInfo<YaodianInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<YaodianInfoCommentVo> all = yaodianInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<YaodianInfoCommentVo> findByForeignId (Long id) {
        List<YaodianInfoCommentVo> all = yaodianInfoCommentDao.findByForeignId(id, 0L);
        for (YaodianInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<YaodianInfoCommentVo> children = new ArrayList<>(yaodianInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}

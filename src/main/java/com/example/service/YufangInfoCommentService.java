package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.YufangInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.YufangInfoComment;
import com.example.vo.YufangInfoCommentVo;
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
public class YufangInfoCommentService {

    @Resource
    private YufangInfoCommentDao yufangInfoCommentDao;

    public YufangInfoComment add(YufangInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        yufangInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        yufangInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(YufangInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        yufangInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public YufangInfoComment findById(Long id) {
        return yufangInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<YufangInfoCommentVo> findAll() {
        return yufangInfoCommentDao.findAllVo(null);
    }

    public PageInfo<YufangInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<YufangInfoCommentVo> all = yufangInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<YufangInfoCommentVo> findByForeignId (Long id) {
        List<YufangInfoCommentVo> all = yufangInfoCommentDao.findByForeignId(id, 0L);
        for (YufangInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<YufangInfoCommentVo> children = new ArrayList<>(yufangInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}

package com.example.service;

import com.example.exception.CustomException;
import com.example.dao.BaikeInfoCommentDao;
import org.springframework.stereotype.Service;
import com.example.entity.BaikeInfoComment;
import com.example.vo.BaikeInfoCommentVo;
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
public class BaikeInfoCommentService {

    @Resource
    private BaikeInfoCommentDao baikeInfoCommentDao;

    public BaikeInfoComment add(BaikeInfoComment commentInfo, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("1001", "请先登录！");
        }
        commentInfo.setName(user.getName());
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        baikeInfoCommentDao.insertSelective(commentInfo);
        return commentInfo;
    }

    public void delete(Long id) {
        baikeInfoCommentDao.deleteByPrimaryKey(id);
    }

    public void update(BaikeInfoComment commentInfo) {
        commentInfo.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        baikeInfoCommentDao.updateByPrimaryKeySelective(commentInfo);
    }

    public BaikeInfoComment findById(Long id) {
        return baikeInfoCommentDao.selectByPrimaryKey(id);
    }

    public List<BaikeInfoCommentVo> findAll() {
        return baikeInfoCommentDao.findAllVo(null);
    }

    public PageInfo<BaikeInfoCommentVo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BaikeInfoCommentVo> all = baikeInfoCommentDao.findAllVo(name);
        return PageInfo.of(all);
    }

    public List<BaikeInfoCommentVo> findByForeignId (Long id) {
        List<BaikeInfoCommentVo> all = baikeInfoCommentDao.findByForeignId(id, 0L);
        for (BaikeInfoCommentVo reserveInfoVo : all) {
            Long parentId = reserveInfoVo.getId();
            List<BaikeInfoCommentVo> children = new ArrayList<>(baikeInfoCommentDao.findByForeignId(id, parentId));
            reserveInfoVo.setChildren(children);
        }
        return all;
    }
}

package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.YufangInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.YufangInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.YufangInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class YufangInfoService {

    @Resource
    private YufangInfoDao yufangInfoDao;

    public YufangInfo add(YufangInfo info) {
        yufangInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        yufangInfoDao.deleteByPrimaryKey(id);
    }

    public void update(YufangInfo info) {
        yufangInfoDao.updateByPrimaryKeySelective(info);
    }

    public YufangInfo findById(Long id) {
        return yufangInfoDao.selectByPrimaryKey(id);
    }

    public List<YufangInfoVo> findAll() {
        return yufangInfoDao.findByName("all");
    }

    public PageInfo<YufangInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<YufangInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<YufangInfoVo> findAllPage(HttpServletRequest request, String name) {
		return yufangInfoDao.findByName(name);
    }

}

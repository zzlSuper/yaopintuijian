package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.YaodianInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.YaodianInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.YaodianInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class YaodianInfoService {

    @Resource
    private YaodianInfoDao yaodianInfoDao;

    public YaodianInfo add(YaodianInfo info) {
        yaodianInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        yaodianInfoDao.deleteByPrimaryKey(id);
    }

    public void update(YaodianInfo info) {
        yaodianInfoDao.updateByPrimaryKeySelective(info);
    }

    public YaodianInfo findById(Long id) {
        return yaodianInfoDao.selectByPrimaryKey(id);
    }

    public List<YaodianInfoVo> findAll() {
        return yaodianInfoDao.findByName("all");
    }

    public PageInfo<YaodianInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<YaodianInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<YaodianInfoVo> findAllPage(HttpServletRequest request, String name) {
		return yaodianInfoDao.findByName(name);
    }

}

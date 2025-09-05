package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.BaikeInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.BaikeInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.BaikeInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BaikeInfoService {

    @Resource
    private BaikeInfoDao baikeInfoDao;

    public BaikeInfo add(BaikeInfo info) {
        baikeInfoDao.insertSelective(info);
        return info;
    }

    public void delete(Long id) {
        baikeInfoDao.deleteByPrimaryKey(id);
    }

    public void update(BaikeInfo info) {
        baikeInfoDao.updateByPrimaryKeySelective(info);
    }

    public BaikeInfo findById(Long id) {
        return baikeInfoDao.selectByPrimaryKey(id);
    }

    public List<BaikeInfoVo> findAll() {
        return baikeInfoDao.findByName("all");
    }

    public PageInfo<BaikeInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<BaikeInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<BaikeInfoVo> findAllPage(HttpServletRequest request, String name) {
		return baikeInfoDao.findByName(name);
    }

}

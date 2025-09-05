package com.example.service;

import cn.hutool.json.JSONUtil;
import com.example.dao.ZhinengInfoDao;
import org.springframework.stereotype.Service;
import com.example.entity.ZhinengInfo;
import com.example.entity.AuthorityInfo;
import com.example.entity.Account;
import com.example.vo.ZhinengInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class ZhinengInfoService {

    @Resource
    private ZhinengInfoDao zhinengInfoDao;

    public ZhinengInfo add(ZhinengInfo zhinengInfo) {
        zhinengInfoDao.insertSelective(zhinengInfo);
        return zhinengInfo;
    }

    public void delete(Long id) {
        zhinengInfoDao.deleteByPrimaryKey(id);
    }

    public void update(ZhinengInfo zhinengInfo) {
        zhinengInfoDao.updateByPrimaryKeySelective(zhinengInfo);
    }

    public ZhinengInfo findById(Long id) {
        return zhinengInfoDao.selectByPrimaryKey(id);
    }

    public List<ZhinengInfoVo> findAll() {
        return zhinengInfoDao.findByName("all");
    }

    public PageInfo<ZhinengInfoVo> findPage(String name, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<ZhinengInfoVo> all = findAllPage(request, name);
        return PageInfo.of(all);
    }

    public List<ZhinengInfoVo> findAllPage(HttpServletRequest request, String name) {
		return zhinengInfoDao.findByName(name);
    }

}

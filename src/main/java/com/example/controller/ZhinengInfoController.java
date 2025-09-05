package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.ZhinengInfo;
import com.example.service.*;
import com.example.vo.ZhinengInfoVo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/zhinengInfo")
public class ZhinengInfoController {
    @Resource
    private ZhinengInfoService zhinengInfoService;

    @PostMapping
    public Result<ZhinengInfo> add(@RequestBody ZhinengInfoVo zhinengInfo) {
        zhinengInfoService.add(zhinengInfo);
        return Result.success(zhinengInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        zhinengInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody ZhinengInfoVo zhinengInfo) {
        zhinengInfoService.update(zhinengInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<ZhinengInfo> detail(@PathVariable Long id) {
        ZhinengInfo zhinengInfo = zhinengInfoService.findById(id);
        return Result.success(zhinengInfo);
    }

    @GetMapping
    public Result<List<ZhinengInfoVo>> all() {
        return Result.success(zhinengInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<ZhinengInfoVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(zhinengInfoService.findPage(name, pageNum, pageSize, request));
    }

    /**
    * 批量通过excel添加信息
    * @param file excel文件
    * @throws IOException
    */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<ZhinengInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(ZhinengInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // 处理一下空数据
            List<ZhinengInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getName())).collect(Collectors.toList());
            for (ZhinengInfo info : resultList) {
                zhinengInfoService.add(info);
            }
        }
        return Result.success();
    }

    @GetMapping("/getExcelModel")
    public void getExcelModel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
		row.put("name", "");

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=zhinengInfoModel.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
}

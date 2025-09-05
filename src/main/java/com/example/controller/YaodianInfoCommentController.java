package com.example.controller;

import com.example.common.Result;
import com.example.entity.YaodianInfoComment;
import com.example.vo.YaodianInfoCommentVo;
import com.example.service.YaodianInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/yaodianInfoComment")
public class YaodianInfoCommentController {
    @Resource
    private YaodianInfoCommentService yaodianInfoCommentService;

    @PostMapping
    public Result<YaodianInfoComment> add(@RequestBody YaodianInfoComment commentInfo, HttpServletRequest request) {
        yaodianInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        yaodianInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody YaodianInfoComment commentInfo) {
        yaodianInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<YaodianInfoComment> detail(@PathVariable Long id) {
        YaodianInfoComment commentInfo = yaodianInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<YaodianInfoCommentVo>> all() {
        return Result.success(yaodianInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<YaodianInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(yaodianInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<YaodianInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(yaodianInfoCommentService.findByForeignId(id));
    }
}

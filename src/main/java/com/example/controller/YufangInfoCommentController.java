package com.example.controller;

import com.example.common.Result;
import com.example.entity.YufangInfoComment;
import com.example.vo.YufangInfoCommentVo;
import com.example.service.YufangInfoCommentService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/yufangInfoComment")
public class YufangInfoCommentController {
    @Resource
    private YufangInfoCommentService yufangInfoCommentService;

    @PostMapping
    public Result<YufangInfoComment> add(@RequestBody YufangInfoComment commentInfo, HttpServletRequest request) {
        yufangInfoCommentService.add(commentInfo, request);
        return Result.success(commentInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        yufangInfoCommentService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody YufangInfoComment commentInfo) {
        yufangInfoCommentService.update(commentInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<YufangInfoComment> detail(@PathVariable Long id) {
        YufangInfoComment commentInfo = yufangInfoCommentService.findById(id);
        return Result.success(commentInfo);
    }

    @GetMapping
    public Result<List<YufangInfoCommentVo>> all() {
        return Result.success(yufangInfoCommentService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<YufangInfoCommentVo>> page(@PathVariable String name,
                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                @RequestParam(defaultValue = "5") Integer pageSize,
                                                HttpServletRequest request) {
        return Result.success(yufangInfoCommentService.findPage(name, pageNum, pageSize));
    }

    @GetMapping("/findByForeignId/{id}")
    public Result<List<YufangInfoCommentVo>> findByForeignId (@PathVariable Long id) {
        return Result.success(yufangInfoCommentService.findByForeignId(id));
    }
}

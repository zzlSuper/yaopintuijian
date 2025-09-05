package com.example.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.common.Result;
import com.example.entity.Account;
import com.example.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MenuController {

	@Resource
	private AdminInfoService adminInfoService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private ZhinengInfoService zhinengInfoService;
	@Resource
	private AdvertiserInfoService advertiserInfoService;


    @GetMapping(value = "/getMenu", produces="application/json;charset=UTF-8")
    public String getMenu(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        Integer level;
        if (account == null) {
            level = 1;
        } else {
            level = account.getLevel();
        }
        JSONObject obj = new JSONObject();
        obj.putOpt("code", 0);
        obj.putOpt("msg", "");
        JSONArray dataArray = new JSONArray();

        dataArray.add(getJsonObject("/", "系统首页", "layui-icon-home", "/"));

        JSONObject tableObj = new JSONObject();
        tableObj.putOpt("title", "信息管理");
        tableObj.putOpt("icon", "layui-icon-table");
		if (1 == level) {
			JSONArray array = new JSONArray();
			array.add(getJsonObject("adminInfo", "管理员信息", "layui-icon-table", "adminInfo"));
			array.add(getJsonObject("userInfo", "用户信息", "layui-icon-table", "userInfo"));
			array.add(getJsonObject("yufangInfo", "疾病预防信息", "layui-icon-table", "yufangInfo"));
			array.add(getJsonObject("baikeInfo", "健康百科信息", "layui-icon-table", "baikeInfo"));
			array.add(getJsonObject("yaodianInfo", "药店信息", "layui-icon-table", "yaodianInfo"));
			array.add(getJsonObject("mapInfo", "路线导航信息", "layui-icon-table", "mapInfo"));
			array.add(getJsonObject("zhinengInfo", "智能判断信息", "layui-icon-table", "zhinengInfo"));
			array.add(getJsonObject("advertiserInfo", "公告信息", "layui-icon-table", "advertiserInfo"));
			array.add(getJsonObject("accountAdminInfo", "个人信息", "layui-icon-user", "accountAdminInfo"));
			tableObj.putOpt("list", array);
		}

		if (2 == level) {
			JSONArray array = new JSONArray();
			array.add(getJsonObject("yufangInfo", "疾病预防信息", "layui-icon-table", "yufangInfo"));
			array.add(getJsonObject("baikeInfo", "健康百科信息", "layui-icon-table", "baikeInfo"));
			array.add(getJsonObject("yaodianInfo", "药店信息", "layui-icon-table", "yaodianInfo"));
			array.add(getJsonObject("mapInfo", "路线导航信息", "layui-icon-table", "mapInfo"));
			array.add(getJsonObject("zhinengInfo", "智能判断信息", "layui-icon-table", "zhinengInfo"));
			array.add(getJsonObject("advertiserInfo", "公告信息", "layui-icon-table", "advertiserInfo"));
			array.add(getJsonObject("accountUserInfo", "个人信息", "layui-icon-user", "accountUserInfo"));
			tableObj.putOpt("list", array);
		}


        dataArray.add(tableObj);
		dataArray.add(getJsonObject("yufangInfoComment", "疾病预防评论", "layui-icon-group", "yufangInfoComment"));
		dataArray.add(getJsonObject("baikeInfoComment", "健康百科评论", "layui-icon-group", "baikeInfoComment"));
		dataArray.add(getJsonObject("yaodianInfoComment", "药店评论", "layui-icon-group", "yaodianInfoComment"));

        dataArray.add(getJsonObject("updatePassword", "修改密码", "layui-icon-password", "updatePassword"));
        dataArray.add(getJsonObject("login", "退出登录", "layui-icon-logout", "login"));

        obj.putOpt("data", dataArray);
        return obj.toString();
    }

    private JSONObject getJsonObject(String name, String title, String icon, String jump) {
        JSONObject object = new JSONObject();
        object.putOpt("name", name);
        object.putOpt("title", title);
        object.putOpt("icon", icon);
        object.putOpt("jump", jump);
        return object;
    }

    @GetMapping(value = "/getTotal", produces="application/json;charset=UTF-8")
    public Result<Map<String, Integer>> getTotal() {
        Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("adminInfo", adminInfoService.findAll().size());
		resultMap.put("userInfo", userInfoService.findAll().size());
		resultMap.put("zhinengInfo", zhinengInfoService.findAll().size());
		resultMap.put("advertiserInfo", advertiserInfoService.findAll().size());

        return Result.success(resultMap);
    }
}

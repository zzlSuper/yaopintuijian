package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    private static Map<String, String> resultMap = new HashMap<>();

    @GetMapping(path = {"/mock_cache"})
    public String mockCache(Integer isUpdate) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        resultMap.put("id",userInfo.getId().toString());
        //1为更新全局缓存，2为更新个性化缓存
        if (isUpdate == 1) {
            resultMap.put("A", "A");
        } else{
            resultMap.put("a", "a");

        }
        return JSONObject.toJSONString(resultMap);
    }

}

package com.itdragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ThymeleafController {

    @RequestMapping({"/", "login.html"})
    public String login() {
        return "login";
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf(ModelMap map) {
        map.put("thText", "th:text 设置文本内容 <b>加粗</b>");
        map.put("thUText", "th:utext 设置文本内容 <b>加粗</b>");
        map.put("thValue", "thValue.....");
        map.put("thEach", Arrays.asList("th:each", "遍历列表"));
        map.put("msg", "msg is not null");
        return "grammar/thymeleaf";
    }
}

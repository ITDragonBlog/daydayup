package com.itdragon.controller;

import com.itdragon.entities.ThObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class ThymeleafController {

    @RequestMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping("thymeleaf")
    public String thymeleaf(ModelMap map) {
        map.put("thText", "th:text 设置文本内容 <b>加粗</b>");
        map.put("thUText", "th:utext 设置文本内容 <b>加粗</b>");
        map.put("thValue", "thValue 设置当前元素的value值");
        map.put("thEach", Arrays.asList("th:each", "遍历列表"));
        map.put("thIf", "msg is not null");
        map.put("thObject", new ThObject(1L, "th:object", "用来偷懒的th属性"));
        return "grammar/thymeleaf";
    }

    @RequestMapping("varexpressions")
    public String varexpressions(ModelMap map) {
        map.put("itdragonStr", "itdragonBlog");
        map.put("itdragonBool", true);
        map.put("itdragonArray", new Integer[]{1,2,3,4});
        map.put("itdragonList", Arrays.asList(1,3,2,4,0));
        Map itdragonMap = new HashMap();
        itdragonMap.put("thName", "${#...}");
        itdragonMap.put("desc", "变量表达式内置方法");
        map.put("itdragonMap", itdragonMap);
        map.put("itdragonDate", new Date());
        map.put("itdragonNum", 888.888D);
        return "grammar/varexpressions";
    }
}

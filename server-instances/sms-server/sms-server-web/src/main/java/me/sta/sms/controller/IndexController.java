package me.sta.sms.controller;

import me.sta.sms.model.MenuItem;
import me.sta.sms.pojo.ResModel;
import me.sta.sms.service.ResModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @CreateBy admin
 * @CreateTime 2019/7/9
 * @Description
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    ResModelService resDomainService;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }


    @GetMapping("/menuLeft")
    @ResponseBody
    public Object menuLeft(@RequestParam("pid") Long pid) {

        List<ResModel> list = resDomainService.findLeftMenu(pid);
        List<MenuItem> listL1 = new ArrayList<>();
        for (ResModel l1 : list) {
            if (l1.getLevel() == 2) {
                if (listL1 == null) {
                    listL1 = new LinkedList<MenuItem>();
                }
                MenuItem l1Item = new MenuItem(l1.getName(), l1.getIconcls(), l1.getUrl(), l1.getId());
                List<MenuItem> subset = null;
                for (ResModel l2 : list) {
                    if (l2.getLevel() == 3 && l2.getPid().equals(l1.getId())) {
                        if (subset == null) {
                            subset = new LinkedList<MenuItem>();
                        }
                        MenuItem l2Item = new MenuItem(l2.getName(), l2.getIconcls(), l2.getUrl(), l2.getId());
                        subset.add(l2Item);
                    }
                }
                l1Item.setSubset(subset);
                listL1.add(l1Item);
            }
        }

        Map<String, List<MenuItem>> menu = new HashMap<String, List<MenuItem>>();
        menu.put("data", listL1);
        return menu;
    }

    @GetMapping("/sms/company")
    public String company() {
        return "company/main";
    }


    @GetMapping("/sms/templates")
    public String templates() {
        return "template/main";
    }

    @GetMapping("/sms/recode")
    public String recode() {
        return "log/main";
    }
}

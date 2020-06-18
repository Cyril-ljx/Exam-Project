package cn.edu.lingnan.exam.controller.admin;

import cn.edu.lingnan.exam.common.LoginSession;
import cn.edu.lingnan.exam.service.PgtestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PgTestController {
    @Autowired
    private PgtestService pgtestService;

    @RequestMapping("/pgtestView")
    public String pgtestView(){
        if(!LoginSession.getCurrentUser().getTocheck().equals(1)){
            return "client/html/index";
        }
        return "admin/topic/pgtestList";
    }

}

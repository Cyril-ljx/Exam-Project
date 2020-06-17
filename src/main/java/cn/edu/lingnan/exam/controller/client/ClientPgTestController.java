package cn.edu.lingnan.exam.controller.client;

import cn.edu.lingnan.exam.entity.Pgtest;
import cn.edu.lingnan.exam.service.PgtestService;
import cn.edu.lingnan.exam.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientPgTestController {

    @Autowired
    private PgtestService pgtestService;

    @Autowired
    private TopicService topicService;

    @RequestMapping("/pgtestUi")
    public String pgTestUi(Model model){
        model.addAttribute("topicList",topicService.queryAll());
        //共享数据
        return "client/html/pgtest";
    }

}

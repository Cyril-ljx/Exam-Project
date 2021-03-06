package cn.edu.lingnan.exam.controller.client;

import cn.edu.lingnan.exam.common.LoginSession;
import cn.edu.lingnan.exam.entity.Pgtest;
import cn.edu.lingnan.exam.entity.Topic;
import cn.edu.lingnan.exam.entity.User;
import cn.edu.lingnan.exam.service.PgtestService;
import cn.edu.lingnan.exam.service.TopicService;
import cn.edu.lingnan.exam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Author: chen_man_cheng
 * @Description: //TODO
 * @Date: Create in 21:31 2020/6/17
 */
@Controller
@RequestMapping("pgtest")
public class ClientPgTestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicService topicService;

    @Autowired
    private PgtestService pgTestService;

    @Autowired
    private UserService userService;

    @RequestMapping("/pgtestUi")
    public String clientArticleUi(Integer lessonid, Model model, HttpSession session) {
        //存储选择的考试课程ID号
        session.setAttribute("taotiid",lessonid);
        List<Map<String,Object>> questionsList  = topicService.findExamRadioQuestions(3);
      // List<Map<String,Object>> questionList=topicService.findExamRadioQuestions(lessonid);
        //返回单选题目
        model.addAttribute("radioQuestionsList", questionsList);
//        model.addAttribute("topicList", topicService.selectByList());
        //返回多选题目
        List<Map<String,Object>> questionsList2  = topicService.findExamCheckboxQuestions(3);
        model.addAttribute("checkboxQuestionsList", questionsList2);
        return "client/html/pgtest";
    }

/*    @ResponseBody
    @RequestMapping("/test")
    public Map<String, Object> addPgTest(@RequestParam("count") Integer count) {
        Map<String, Object> mapData = new HashMap<>();
        Pgtest pgTest = new Pgtest();
        if (count >= 90) {
            pgTest.setPgtestResult("典型的外向性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginSession.getCurrentUser().getUsername());
            pgTest.setPgtestScore(count);
            logger.info("------------------------------------");
            //插入数据库
            boolean insert = pgTestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        } else if (count > 70 && count < 90) {
            pgTest.setPgtestResult("稍外向性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginSession.getCurrentUser().getUsername());
            pgTest.setPgtestScore(count);
            logger.info("==========================================");
            //插入数据库
            boolean insert = pgTestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        } else if (count > 50 && count < 70) {
            pgTest.setPgtestResult("外内混合型性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp("test");
            pgTest.setPgtestScore(count);
            logger.info("=================");
            //插入数据库
            boolean insert = pgTestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        } else if (count > 30 && count < 50) {
            pgTest.setPgtestResult("典型的内向性格");
            pgTest.setPgtestTime(new Date());
            //从session中获取
            pgTest.setUserOp(LoginSession.getCurrentUser().getUsername());
            pgTest.setPgtestScore(count);
            logger.info("+++++++++++++++++++++");
            //插入数据库
            boolean insert = pgTestService.insert(pgTest);
            mapData.put("status", insert);
            return mapData;
        }
        mapData.put("status", false);
        return mapData;
    }*/
    @RequestMapping("/postExam")
    public String postExam(@RequestParam Map<String,String> map, Model model, HttpSession session){
        String username= LoginSession.getCurrentUser().getUsername();
        Integer taotiId=null;
        //String abc=null;
        Topic topic=new Topic();
        Set<String> keySet=map.keySet();
        Iterator<String> it=keySet.iterator();
        Integer radioscores=0;     //单选分
        Integer checkscores=0;    //多选分
        Integer total=0;         //总得分
        while (it.hasNext()){
            String key=it.next();
            String value=map.get(key);
            System.out.println(value);
            topic = topicService.findAnswerById(key);
            taotiId=topic.getTaotiid();
            System.out.println(taotiId);
            if(topic.getType().equals("单选")) {

                if(topic.getReferAnswer().equals(value)) {
//                    System.out.println(topic.getReferAnswer());
                    radioscores+=10;
                }
            }
            if(topic.getType().equals("多选")) {

                if(topic.getReferAnswer().equals(value)) {
                    checkscores+=20;
                }
            }
        }

        total=radioscores+checkscores;
        //存储数据到数据库
        Pgtest pgtest=new Pgtest();
        pgtest.setRadioScores(radioscores);
        pgtest.setCheckScores(checkscores);
        pgtest.setTotal(total);
        pgtest.setPgtestTime(new Timestamp(new Date().getTime()));
        pgtest.setTaotiid(taotiId);
        pgtest.setUserOp(LoginSession.getCurrentUser().getUsername());
        boolean addExamResult= pgTestService.insert(pgtest);

        //返回参数给前端显示
        User user= userService.queryByUserName(username);
        String userName=user.getUsername();
        String lessonname=topic.getLabelName();
        Map<String, Integer> resultmap1 = new HashMap<String, Integer>();
        resultmap1.put("radioscores",radioscores);
        resultmap1.put("checkscores", checkscores);
        resultmap1.put("total", total);
        model.addAttribute("resultmap1", resultmap1);

        Map<String, String> resultmap2 = new HashMap<String,String>();
        resultmap2.put("lessonname",lessonname);
        resultmap2.put("userName", userName);

        model.addAttribute("resultmap2", resultmap2);
        return "client/html/examResult";
    }

}

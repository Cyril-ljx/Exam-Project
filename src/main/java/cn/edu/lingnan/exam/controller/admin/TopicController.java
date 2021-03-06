package cn.edu.lingnan.exam.controller.admin;

import cn.edu.lingnan.exam.common.LoginSession;
import cn.edu.lingnan.exam.common.ServerLayResult;
import cn.edu.lingnan.exam.entity.CommonResult;
import cn.edu.lingnan.exam.entity.Topic;
import cn.edu.lingnan.exam.listener.TopicExcelListener;
import cn.edu.lingnan.exam.service.TopicService;
import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Topic)表控制层
 *
 * @author makejava
 * @since 2020-06-15 17:20:58
 */
@Controller
@RequestMapping("admin/topic")
public class TopicController {
    /**
     * 服务对象
     */
    @Resource
    private TopicService topicService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/topicView")
    public String topicView() {
        if (!LoginSession.getCurrentUser().getTocheck().equals(1)) {
            return "client/html/index";
        }
        return "admin/topic/topicList";
    }

    /**
     * 题目列表显示和关键字查询
     *
     * @param page
     * @param limit
     * @param keyword1
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public ServerLayResult list(@RequestParam("page") Integer page,
                                @RequestParam("limit") Integer limit, String keyword1) {
        logger.info("高级查询数据======"+keyword1);
        if (keyword1 != null) {
            List<Topic> topics = topicService.selectByKeyWord(page, limit, keyword1);
            ServerLayResult result = new ServerLayResult(0, "", topics.size(), topics);
            return result;
        }
        //封装数据
        ServerLayResult result = new ServerLayResult(0, "", topicService.count(), topicService.selectAll(page, limit));
        return result;
    }

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/del")
    public Map<String, Object> del(@RequestParam("id") Integer id) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean delete = topicService.deleteByPrimaryKey(id);
        dataMap.put("status", delete);
        return dataMap;
    }

    /**
     * 更新
     *
     * @param topic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map<String, Object> update(@RequestBody Topic topic) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean update = topicService.updateByPrimaryKey(topic);
        dataMap.put("status", update);
        return dataMap;
    }

    /**
     * 增加
     * @param topic
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/add")
    public Map<String, Object> insert(@RequestBody Topic topic) {
        Map<String, Object> dataMap = new HashMap<>();
        boolean insert = topicService.insert(topic);
        dataMap.put("status",insert);
        return dataMap;
    }

    @PostMapping("upload")
    @ResponseBody
    public Object upload(MultipartFile file) {
        CommonResult<String> result = new CommonResult<>();
        try {
            EasyExcel.read(file.getInputStream(), Topic.class, new TopicExcelListener(topicService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            result.setMsg("Excel上传出错");
        }
        result.setData(Collections.singletonList(file.getOriginalFilename()));
        return result;
    }
}
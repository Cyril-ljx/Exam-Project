package cn.edu.lingnan.exam.controller;

import cn.edu.lingnan.exam.entity.Topic;
import cn.edu.lingnan.exam.service.TopicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Topic)表控制层
 *
 * @author makejava
 * @since 2020-06-15 17:20:58
 */
@RestController
@RequestMapping("topic")
public class TopicController {
    /**
     * 服务对象
     */
    @Resource
    private TopicService topicService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Topic selectOne(Integer id) {
        return this.topicService.queryById(id);
    }

}
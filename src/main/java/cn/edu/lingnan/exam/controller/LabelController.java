package cn.edu.lingnan.exam.controller;

import cn.edu.lingnan.exam.entity.Label;
import cn.edu.lingnan.exam.service.LabelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Label)表控制层
 *
 * @author makejava
 * @since 2020-06-15 17:19:57
 */
@RestController
@RequestMapping("label")
public class LabelController {
    /**
     * 服务对象
     */
    @Resource
    private LabelService labelService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Label selectOne(Integer id) {
        return this.labelService.queryById(id);
    }

}
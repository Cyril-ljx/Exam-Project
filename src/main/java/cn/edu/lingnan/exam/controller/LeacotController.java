package cn.edu.lingnan.exam.controller;

import cn.edu.lingnan.exam.entity.Leacot;
import cn.edu.lingnan.exam.service.LeacotService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Leacot)表控制层
 *
 * @author makejava
 * @since 2020-06-15 17:20:06
 */
@RestController
@RequestMapping("leacot")
public class LeacotController {
    /**
     * 服务对象
     */
    @Resource
    private LeacotService leacotService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Leacot selectOne(Integer id) {
        return this.leacotService.queryById(id);
    }

}
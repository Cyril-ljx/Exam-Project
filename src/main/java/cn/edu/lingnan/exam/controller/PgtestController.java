package cn.edu.lingnan.exam.controller;

import cn.edu.lingnan.exam.entity.Pgtest;
import cn.edu.lingnan.exam.service.PgtestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Pgtest)表控制层
 *
 * @author makejava
 * @since 2020-06-15 17:20:32
 */
@RestController
@RequestMapping("pgtest")
public class PgtestController {
    /**
     * 服务对象
     */
    @Resource
    private PgtestService pgtestService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Pgtest selectOne(Integer id) {
        return this.pgtestService.queryById(id);
    }

}
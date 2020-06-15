package cn.edu.lingnan.exam.controller;

import cn.edu.lingnan.exam.entity.Article;
import cn.edu.lingnan.exam.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2020-06-15 17:19:48
 */
@RestController
@RequestMapping("article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Article selectOne(Integer id) {
        return this.articleService.queryById(id);
    }

}
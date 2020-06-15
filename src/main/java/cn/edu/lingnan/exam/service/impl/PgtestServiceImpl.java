package cn.edu.lingnan.exam.service.impl;

import cn.edu.lingnan.exam.entity.Pgtest;
import cn.edu.lingnan.exam.dao.PgtestDao;
import cn.edu.lingnan.exam.service.PgtestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Pgtest)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 17:20:32
 */
@Service("pgtestService")
public class PgtestServiceImpl implements PgtestService {
    @Resource
    private PgtestDao pgtestDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Pgtest queryById(Integer id) {
        return this.pgtestDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Pgtest> queryAllByLimit(int offset, int limit) {
        return this.pgtestDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param pgtest 实例对象
     * @return 实例对象
     */
    @Override
    public Pgtest insert(Pgtest pgtest) {
        this.pgtestDao.insert(pgtest);
        return pgtest;
    }

    /**
     * 修改数据
     *
     * @param pgtest 实例对象
     * @return 实例对象
     */
    @Override
    public Pgtest update(Pgtest pgtest) {
        this.pgtestDao.update(pgtest);
        return this.queryById(pgtest.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.pgtestDao.deleteById(id) > 0;
    }
}
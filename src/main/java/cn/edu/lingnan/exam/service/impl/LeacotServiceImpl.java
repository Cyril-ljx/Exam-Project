package cn.edu.lingnan.exam.service.impl;

import cn.edu.lingnan.exam.entity.Leacot;
import cn.edu.lingnan.exam.dao.LeacotDao;
import cn.edu.lingnan.exam.service.LeacotService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Leacot)表服务实现类
 *
 * @author makejava
 * @since 2020-06-15 17:20:06
 */
@Service("leacotService")
public class LeacotServiceImpl implements LeacotService {
    @Resource
    private LeacotDao leacotDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Leacot queryById(Integer id) {
        return this.leacotDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Leacot> queryAllByLimit(int offset, int limit) {
        return this.leacotDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param leacot 实例对象
     * @return 实例对象
     */
    @Override
    public Leacot insert(Leacot leacot) {
        this.leacotDao.insert(leacot);
        return leacot;
    }

    /**
     * 修改数据
     *
     * @param leacot 实例对象
     * @return 实例对象
     */
    @Override
    public Leacot update(Leacot leacot) {
        this.leacotDao.update(leacot);
        return this.queryById(leacot.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.leacotDao.deleteById(id) > 0;
    }
}
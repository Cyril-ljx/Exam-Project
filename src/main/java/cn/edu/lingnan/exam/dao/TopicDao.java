package cn.edu.lingnan.exam.dao;

import cn.edu.lingnan.exam.entity.Topic;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (Topic)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-15 17:20:58
 */
public interface TopicDao {

    public Topic findAnswerById(String id);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Topic queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Topic> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param topic 实例对象
     * @return 对象列表
     */
    List<Topic> queryAll(Topic topic);

    /**
     * 新增数据
     *
     * @param topic 实例对象
     * @return 影响行数
     */
    int insert(Topic topic);

    /**
     * 修改数据
     *
     * @param topic 实例对象
     * @return 影响行数
     */
    int update(Topic topic);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 查询全部
     * @return
     */
    List<Topic> selectAll();

    List<Topic> selectByKeyWord(String keyword1);

    /**
     * 更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(Topic record);

    int count();
    //返回单选题目

    public List<Map<String,Object>> findExamRadioQuestions(Integer taotiid);

    int insertBatch(@Param("list") List<Topic> list);

    //返回多选题目
    public List<Map<String,Object>> findExamCheckboxQuestions(Integer taotiid);
}
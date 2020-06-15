package cn.edu.lingnan.exam.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Topic)实体类
 *
 * @author makejava
 * @since 2020-06-15 17:20:58
 */
public class Topic implements Serializable {
    private static final long serialVersionUID = 208654286192150135L;
    /**
    * 题目ID
    */
    private Integer id;
    /**
    * 题目内容
    */
    private String  topicContent;
    /**
    * 题目参考答案
    */
    private String referAnswer;
    /**
    * 题目创建时间
    */
    private Date topicTime;
    /**
    * 题目操作用户
    */
    private String userOp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettopicContent() {
        return  topicContent;
    }

    public void settopicContent(String  topicContent) {
        this.topicContent =  topicContent;
    }

    public String getReferAnswer() {
        return referAnswer;
    }

    public void setReferAnswer(String referAnswer) {
        this.referAnswer = referAnswer;
    }

    public Date getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Date topicTime) {
        this.topicTime = topicTime;
    }

    public String getUserOp() {
        return userOp;
    }

    public void setUserOp(String userOp) {
        this.userOp = userOp;
    }

}
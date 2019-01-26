package com.qiqi.dao;

import com.qiqi.model.entity.user.UserMongoBean;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface UserMongoRepository extends MongoRepository<UserMongoBean, String> {

    /**
     * 根据id查询一条信息
     *
     */
    UserMongoBean findTop1ByUserId(long id);

    /**
     * 根据age查询一条信息
     *
     */
    UserMongoBean findTop1ByAge(int age);

    /**
     * 根据age查询所有信息
     */
    List<UserMongoBean> findAllByAge(int age);

    /**
     * 根据name查询一条信息
     *
     */
    UserMongoBean findTop1ByName(String name);

    /**
     * 根据name查询所有信息
     */
    List<UserMongoBean> findAllByName(String name);


    /**
     * 根据user_id删除
     * @return
     */
    @Transactional
    Long deleteAllByUserId(long userId);

    /**
     * 根据age删除
     * @return
     */
    @Transactional
    Long deleteAllByAge(int age);

    /**
     * 根据name删除
     * @return
     */
    @Transactional
    Long deleteAllByName(String name);

}

package com.qiqi.dao;

import com.qiqi.model.entity.user.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface UserRepository extends JpaRepository<UserBean, Long> {

    /**
     * 根据id查询一条信息
     *
     */
    UserBean findTop1ById(long id);

    /**
     * 根据age查询一条信息
     *
     */
    UserBean findTop1ByAge(int age);

    /**
     * 根据age查询所有信息
     */
    List<UserBean> findAllByAge(int age);

    /**
     * 根据name查询一条信息
     *
     */
    UserBean findTop1ByName(String name);

    /**
     * 根据name查询所有信息
     */
    List<UserBean> findAllByName(String name);

    /**
     * 根据name查询用户是否存在
     */
    boolean existsByName(String name);

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

    // void deleteById(ID var1);
    //
    // void delete(T var1);
    //
    // void deleteAll(Iterable<? extends T> var1);
    //
    // void deleteAll();

}

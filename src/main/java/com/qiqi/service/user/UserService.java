package com.qiqi.service.user;

import com.qiqi.dao.UserMongoRepository;
import com.qiqi.dao.UserRepository;
import com.qiqi.exception.UserNotFoundException;
import com.qiqi.model.VO.UserVO;
import com.qiqi.model.entity.user.UserBean;
import com.qiqi.model.entity.user.UserMongoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @date 2018/12/20 13:39
 */
@Service
public class UserService {

    @Autowired
    private UserMongoRepository userMongoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 通过user_id获取用户
     * @param userId
     * @return
     */
    public UserVO getUser(String userId) {
        long id = Long.parseLong(userId);
        UserBean user = userRepository.findTop1ById(id);
        //userMongoRepository.findTop1ByUserId(2);
        if (user != null){
            UserVO userVO = new UserVO(user.getId(), user.getAge(), user.getName());
            return userVO;
        }else {
            return null;
        }
    }

    /**
     * 通过age获取用户
     *
     * List -> Map
     * 需要注意的是：
     * toMap 如果集合对象有重复的key，会报错Duplicate key ....
     *  apple1,apple12的id都为1。
     *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
     */
    public Map<Long, UserVO> getUserByAge(String age){
        int userAge = Integer.parseInt(age);
        List<UserBean> userList = userRepository.findAllByAge(userAge);
        List<UserVO> userVOList = new ArrayList<>();
        if (null != userList){
            for (UserBean user : userList){
                UserVO userVO = new UserVO(user.getId(), user.getAge(), user.getName());
                userVOList.add(userVO);
            }
            Map<Long, UserVO> map =
                userVOList.stream().collect(Collectors.toMap(UserVO::getUserId, a -> a,(k1, k2)->k1));
            return map;
        }else {
            return null;
        }
    }

    /**
     * 保存用户
     * @param userId
     * @return
     */
    public void saveUser(long userId, int age, String name){
        UserBean userBean = new UserBean(userId, age, name);
        userRepository.save(userBean);
        UserMongoBean userMongoBean = new UserMongoBean(userId, age, name);
        // 关于mongo：当主键"_id"不存在时，insert和save都是添加一个新的文档
        // 但主健"_id"存在时，就有些不同了
        // insert:当主键"_id"在集合中存在时，会报主键重复的错误提示
        // save:当主键"_id"在集合中存在时，进行更新

        // userMongoRepository.save(userMongoBean);
        userMongoRepository.insert(userMongoBean);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public void deleteUser(long userId )throws UserNotFoundException {
        //先查询数据是否存在，再删除
        if (userRepository.existsById(userId) == false){
             throw new UserNotFoundException();
        }
        userRepository.deleteById(userId);
        //userRepository.deleteAllByName(name);
        //userRepository.deleteAllByAge(age);
        userMongoRepository.deleteAllByUserId(userId);
        //userMongoRepository.deleteAllByAge(age);
        //userMongoRepository.deleteAllByName(name);
    }

}

package com.qiqi.controller;

import com.qiqi.exception.UserNotFoundException;
import com.qiqi.utils.Response;
import com.qiqi.model.VO.UserVO;
import com.qiqi.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET)

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String RedisUserKey = "USER_ID";
    /**
     * 根据userId获取用户
     * @param: [userId]
     * @return Response
     */
    @RequestMapping(value = "/get" , method = RequestMethod.GET)
    public Response getUser(@RequestParam(value = "user_id", required = true) String userId)
            throws IOException {

        ValueOperations<String, UserVO> operations = redisTemplate.opsForValue();
        UserVO userVO;
        Response response;
        if (null == operations.get(RedisUserKey + userId)){
            userVO = userService.getUser(userId);
            if (null != userVO ) {
                operations.set(RedisUserKey + userId, userVO);
            }
        }else {
            userVO = operations.get(RedisUserKey + userId);
        }
        if (null != userVO ){
            response = new Response("0","获取成功",userVO);
        }else {
            response = new Response("-1","没有userId:"+ userId +"的用户",null);
        }
        logger.info("获取的结果：id="+ userId + "  result="+response.getMsg());
        return response;
    }

    /**
     * 根据age获取用户
     * @param: [userId]
     * @return Response
     */
    @RequestMapping(value = "/get/age" , method = RequestMethod.GET)
    public Response getUserByAge(@RequestParam(value = "age", required = true) String age) {
        HashOperations<String, Long, UserVO> hashOperations = redisTemplate.opsForHash();
        Response response = new Response();
        Map<Long, UserVO> map = hashOperations.entries(RedisUserKey + age) ;
        if ( map.size() == 0 ){
            map = userService.getUserByAge(age);
            if (null != map){
                hashOperations.putAll(RedisUserKey + age, map);
                //设置缓存过期时间,60s后redis会自动删除数据
                redisTemplate.expire(RedisUserKey + age,60,java.util.concurrent.TimeUnit.SECONDS);
                response = new Response("0","获取成功",map);
            }else {
                response = new Response("-1","没有userAge:"+ age +"的用户",null);
            }
        }else {
            response = new Response("0","获取成功",map);
        }
        logger.info("获取的结果：age="+ age + "  result="+response.getMsg());
        return response;
    }

    /**
     * 保存用户
     */
    @RequestMapping(value = "/save" , method = RequestMethod.GET)
    public Response saveUser(@RequestParam(value = "user_id", required = true) String userId,
                             @RequestParam(value = "age", required = true) String age,
                             @RequestParam(value = "name", required = true) String name) {
        Response response = new Response();
        long id = Long.parseLong(userId);
        userService.saveUser(id, Integer.parseInt(age), name);

        response = new Response("0","保存成功",null);
        logger.info("保存用户：id="+ userId + " age=" + age + "  name=" + name + "  result="+response.toString());
        return response;
    }

    /**
     * 删除用户
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Response deleteUser(@RequestParam(value = "user_id", required = true) String userId)
            throws UserNotFoundException {
        Response response = new Response();
        long id = Long.parseLong(userId);
        userService.deleteUser(id);
        response = new Response("0","删除用户成功",null);
        logger.info("删除用户：id="+ userId + "  result="+response.toString());
        return response;
    }

    @Test
    public void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("1","哈哈哈哈032");
        String str =operations.get("1");
        System.out.println(str);
    }

}

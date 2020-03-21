package com.how2java.tmall;

import com.google.common.collect.Lists;
import com.how2java.tmall.dao.UserDAO;
import com.how2java.tmall.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2020/3/21 11:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TmallSpringbootApplicationTests {
    @Autowired
    private UserDAO userDAO;

    public TmallSpringbootApplicationTests() {
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void save() {
        User user = new User();
        user.setId(0);
        user.setPassword("");
        user.setName("");
        user.setSalt("");
        user.setAnonymousName("");
        User save = (User)this.userDAO.save(user);
        System.out.println(save.getId());
    }
    @Test
    public void saveAll() {
        User user = new User();
        user.setId(0);
        user.setPassword("");
        user.setName("batech");
        user.setSalt("");
        user.setAnonymousName("");
        ArrayList<User> userArrayList = Lists.newArrayList(user);
        List<User> save = userDAO.save(userArrayList);
        System.out.println(save);
    }
    @Test
    public void update(){
        User userDAOOne = userDAO.findOne(6);
        userDAOOne.setName("updateName");
        userDAO.saveAndFlush(userDAOOne);
    }
    @Test
    public void getAll(){
        // Sort对象用来指示排序，最简单的Sort对象构造可以传入一个属性名列表（不是数据库列名，是属性名），默认采用升序排序。
        // Sort sort = new Sort("id")；
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        List<User> userDAOAll = userDAO.findAll(sort);
        userDAOAll.forEach(System.out::println);
    }
    @Test
    public void getPages(){
        System.out.println(userDAO.findAll(new PageRequest(1, 5, new Sort(Sort.Direction.DESC, "id"))));
    }
    @Test
    public void exampleTest(){
        // 1 返回单一对象精准匹配
        // 2 多条件，返回集合：
        User user = new User();
        user.setName("owe");
        //创建匹配器，即如何使用查询条件
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().
                withMatcher("name",ExampleMatcher.GenericPropertyMatchers.endsWith())
                //endsWith是categoryName 结尾为喜欢的数据
                .withMatcher("name",
                        ExampleMatcher.GenericPropertyMatchers.startsWith())   //
                .withIgnorePaths("id");
        //isFace字段不参与匹配
        Example<User> userExample = Example.of(user, exampleMatcher);
        System.out.println(userDAO.findAll(userExample));
    }
}

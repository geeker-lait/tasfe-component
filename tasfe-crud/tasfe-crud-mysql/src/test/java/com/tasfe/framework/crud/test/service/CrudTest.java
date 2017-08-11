package com.tasfe.framework.crud.test.service;

import com.tasfe.framework.crud.api.params.Criteria;
import com.tasfe.framework.crud.core.CrudTemplate;
import com.tasfe.framework.crud.test.model.entity.Member;
import com.tasfe.framework.crud.test.model.entity.User;
import com.tasfe.framework.crud.test.mysql.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Lait on 2017/4/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:springs/spring-mybatis.xml"})
public class CrudTest {

    //@Autowired
    //CrudBusiness crudBusiness;

    //@Autowired
    //UserBusiness userBusiness;

    //@Resource(name="mysqlTemplate")
    //CrudOperator<User> mysqlOperator;

    /**
     *  暂未实现
     */
    //@Resource(name="hiveTemplate")
    //CrudOperator<User> hiveOperator;

    @Autowired
    UserMapper userMapper;

    @Resource(name="mysql")
    CrudTemplate crudTemplate;

    private User getUser(){
        User u = new User();
        AtomicLong al = new AtomicLong();
        Random random = new Random();
        u.setUserId(Long.valueOf(random.nextInt()));
        u.setDeptId(1);
        u.setOrderId(random.nextInt());
        u.setEmail("lait.zhang@gmail.com");
        u.setMobilePhone("15801818092");
        return u;
    }

    private List<User> getUsers(int count){
        List<User> userList = new ArrayList<User>();
        for(int i=0;i<count;i++){
            userList.add(getUser());
        }
        return userList;
    }

    @Test
    public void testInsert() throws Exception {

//        crudTemplate.insert(getUser());
        //crudTemplate.insertBatch(getUsers(10));

        Member member = new Member();
        member.setEmail("lait");
        member.setUserId(11L);
        member.setDeptId(1);
        member.setOrderId(111);
        crudTemplate.insert(member);

        // 自定义填充方式
        //User user1 = mysqlTemplate.forParam(user).exec("doXX").fill(User.class);

        //System.out.println("=========" + user);
    }



    @Test
    public void testGet() throws Exception {
        User user = new User();
        user.setId(95L);
        //user = userMapper.getUser(user);


       // user = crudTemplate.get(User.class,25L);

        List<Long> ids = new ArrayList<>();
        ids.add(96L);
        ids.add(98L);
        List<User> users = crudTemplate.gets(User.class,96L,98L);
        System.out.println(user +"====="+ users);

        /*users = crudTemplate.gets(user);
        System.out.println(user +"====="+ users.size());*/

        Criteria criteria = Criteria.from(User.class).where().endWhere();
        //criteria.put(User.ID, Operator.GT,user.getId()).or().;

        Object object = new Object();

        List<User> userass = crudTemplate.find(user,criteria);



        //User user11 = mysqlTemplate.get(User.class,1L);
        //User user1 = mysqlTemplate.forParam(user).exec("getUser").get();

        // 填充
        //User user1 = mysqlTemplate.forParam(user).exec("getUser").fill(User.class);
        //List<User> user1 = mysqlTemplate.get(user);
        //System.out.println(user11+ "--------------------------------" );

        //System.out.println(user +"====="+ users);
    }




    /*@Test
    public void testBusinessInsert() throws Exception {
        User u = getUser();
        crudBusiness.insert(u);


    }

    @Test
    public void testUBusinessInsert() throws Exception {
        User u = getUser();
        userBusiness.findUsers();
    }*/



    @Test
    public void testTemplateInsert() throws Exception {
        User u = getUser();
        crudTemplate.insert(u);
    }

    @Test
    public void testOperationInsert() throws Exception {
        User u = getUser();
        crudTemplate.insert(u);
    }


    @Test
    public void testOperationInsertBatch() throws Exception {
        List<User> userList = getUsers(10);
        //mysqlTemplate.batchInsert(userList);
        System.out.println("====" + userList.size());
        for(User user:userList){
            System.out.println(user.getUserId() + "::::" + user.getDeptId());
        }
    }



    
    
    @Test
    public void testOperationFind() throws Exception {
        List<User> userList = getUsers(10);
        //mysqlTemplate.batchInsert(userList);
        System.out.println("====" + userList.size());
        for(User user:userList){
            System.out.println(user.getUserId() + "::::" + user.getDeptId());
        }
    }
    



}

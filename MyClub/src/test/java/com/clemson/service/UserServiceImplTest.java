package com.clemson.service;

import com.clemson.model.User;
import com.clemson.util.CommonInfo;
import org.restsql.core.Config;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by shiwguo on 2017/4/8.
 */
public class UserServiceImplTest {
    static {
        // Set the restsql properties location
        if (System.getProperty(Config.KEY_RESTSQL_PROPERTIES) == null)
            System.setProperty(Config.KEY_RESTSQL_PROPERTIES, ActivityServiceImplTest.class.getResource(
                    "/properties/restSql.properties").getPath());
    }

    @Test
    public void testInsertUser() throws Exception {
        User user=new User(0,"first","1stuser","the first user",CommonInfo.role.USER.ordinal());
        UserService userService=new UserServiceImpl();
        userService.insertUser(user);
        assertEquals(userService.getUserByLogin("first","1stuser").getId(),2);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        UserService userService=new UserServiceImpl();
        User user=userService.getUserByLogin("admin","123456");
        assertEquals(user.getRole(),CommonInfo.role.ADMIN.ordinal());
    }

}
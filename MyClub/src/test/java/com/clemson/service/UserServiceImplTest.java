package com.clemson.service;

import com.clemson.model.User;
import com.clemson.util.CommonInfo;
import org.restsql.core.Config;
import org.testng.annotations.Test;

import java.util.List;

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
        User user=new User(0,"second","2nduser","the second user",CommonInfo.role.USER.ordinal());
        UserService userService=new UserServiceImpl();
        userService.insertUser(user);
        assertEquals(userService.getUserByLogin("second","2nduser").getId(),3);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        UserService userService=new UserServiceImpl();
        User user=userService.getUserByLogin("admin","123456");
        assertEquals(user.getRole(),CommonInfo.role.ADMIN.ordinal());
    }

    @Test
    public void testGetParticipantsByActivityId()throws Exception{
        UserService userService=new UserServiceImpl();
        List<User> participants=userService.getParticipantByActivityId(1);
        assertEquals(participants.size(),1);
    }
}
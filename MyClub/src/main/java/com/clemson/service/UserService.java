package com.clemson.service;

import com.clemson.model.User;
import org.restsql.core.SqlResourceException;

import java.util.List;

/**
 * Created by shiwguo on 2017/4/5.
 */
public interface UserService {
    public void insertUser(User user) throws SqlResourceException;

    public User getUserByLogin(String name, String password) throws SqlResourceException;

    public List<User> getParticipantByActivityId(int activityId) throws SqlResourceException;
}

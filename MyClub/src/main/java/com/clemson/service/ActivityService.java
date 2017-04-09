package com.clemson.service;

import com.clemson.model.Activity;
import com.clemson.model.User;
import org.restsql.core.SqlResourceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiwguo on 2017/4/6.
 */
public interface ActivityService {
    public List<Activity> getAllActivity() throws SqlResourceException;

    public void insertActivity(Activity activity) throws SqlResourceException;

    public void deleteActivity(Activity activity) throws SqlResourceException;

    public void editActivity(Activity activity) throws SqlResourceException;
}

package com.clemson.service;

import com.clemson.model.Activity;
import org.restsql.core.SqlResourceException;

import java.util.ArrayList;

/**
 * Created by shiwguo on 2017/4/6.
 */
public interface ActivityService {
    public ArrayList<Activity> getAllActivity() throws SqlResourceException;

    public void insertActivity(Activity activity) throws SqlResourceException;

    public void deleteActivity(Activity activity) throws SqlResourceException;

    public void editActivity(Activity activity) throws SqlResourceException;
}

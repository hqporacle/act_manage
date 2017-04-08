package com.clemson.service;

import com.clemson.model.Activity;
import org.restsql.core.Config;
import org.restsql.core.SqlResourceException;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by shiwguo on 2017/4/6.
 */
public class ActivityServiceImplTest {
    static {
        // Set the restsql properties location
        if (System.getProperty(Config.KEY_RESTSQL_PROPERTIES) == null)
            System.setProperty(Config.KEY_RESTSQL_PROPERTIES, ActivityServiceImplTest.class.getResource(
                    "/properties/restSql.properties").getPath());
    }

    @Test
    public void getAllActivityTest() throws SqlResourceException {
        ActivityService activityService = new ActivityServiceImpl();
        List<Activity> result = activityService.getAllActivity();
        assertEquals(result.size(), 2);
    }

    @Test
    public void insertActivityTest() throws SqlResourceException {
        ActivityService activityService = new ActivityServiceImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Activity activity = null;
        try {
            activity = new Activity(0, "unit test activity", sdf.parse("2017-04-10"), sdf.parse("2017-05-10"), sdf.parse("2017-04-05"), "activity for unit test", 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        activityService.insertActivity(activity);
        assertEquals(activityService.getAllActivity().size(), 3);
    }

    @Test
    public void deleteActivityTest() throws SqlResourceException {
        ActivityService activityService = new ActivityServiceImpl();
        Activity activity = new Activity(11, null, null, null, null, null, 0);
        activityService.deleteActivity(activity);
        assertEquals(activityService.getAllActivity().size(), 3);
    }
}
package com.clemson.service;

import com.clemson.model.Activity;
import com.clemson.util.CommonInfo;
import com.clemson.util.StringUtil;
import org.restsql.core.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shiwguo on 2017/4/6.
 */
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {
    static SqlResource sqlResource;

    static {
        // Set the restsql properties location
        if (System.getProperty(Config.KEY_RESTSQL_PROPERTIES) == null)
            System.setProperty(Config.KEY_RESTSQL_PROPERTIES, ActivityService.class.getResource(
                    "/properties/restSql.properties").getPath());
        try {
            sqlResource = Factory.getSqlResource("Activity");
        } catch (SqlResourceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Activity> getAllActivity() throws SqlResourceException {
        // Create the request
        List<RequestValue> params = null;
        List<RequestValue> resId = null;
        List<List<RequestValue>> childrenParams = null;
        RequestLogger requestLogger = Factory.getRequestLogger();
        Request request = Factory.getRequest(Request.Type.SELECT, sqlResource.getName(), params, resId,
                childrenParams, requestLogger);
        List<Map<String, Object>> resultList = sqlResource.read(request);

        ArrayList<Activity> activities = new ArrayList<Activity>();
        for (Map<String, Object> result : resultList) {
            Activity activity = new Activity((Integer) result.get("id"), (String) result.get("name"), (Date) result.get("startDate"), (Date) result.get("endDate"), (Date) result.get("deadline"), (String) result.get("description"), (Integer) result.get("status"));
            activities.add(activity);
        }
        return activities;
    }

    @Override
    public void insertActivity(Activity activity) throws SqlResourceException {
        // Create the row
        final List<RequestValue> params = new ArrayList<RequestValue>();
        params.add(new RequestValue("name", activity.getName()));
        params.add(new RequestValue("startDate", activity.getStartDate()));
        params.add(new RequestValue("endDate", activity.getEndDate()));
        params.add(new RequestValue("deadline", activity.getDeadline()));
        params.add(new RequestValue("description", activity.getDescription()));
        params.add(new RequestValue("status", activity.getStatus()));

        // Create the request
        final List<RequestValue> resIds = null;
        final List<List<RequestValue>> childrenParams = null;
        final RequestLogger requestLogger = Factory.getRequestLogger();
        final Request request = Factory.getRequest(Request.Type.INSERT, sqlResource.getName(), resIds,
                params, childrenParams, requestLogger);

        // Execute the insert request
        final int rowsAffected = sqlResource.write(request).getRowsAffected();

        System.out.println("\t" + requestLogger.getSql());
        System.out.println("\tinserted " + rowsAffected + " row(s)");
    }

    @Override
    public void deleteActivity(Activity activity) throws SqlResourceException {
        // Create the row
        final List<RequestValue> params = new ArrayList<RequestValue>();

        // Create the delete request
        final List<RequestValue> resIds = new ArrayList<RequestValue>(1);
        resIds.add(new RequestValue("id", activity.getId()));
        params.clear();
        params.add(new RequestValue("status", CommonInfo.activity_status.DELETED.ordinal()));
        final List<List<RequestValue>> childrenParams = null;
        final RequestLogger requestLogger = Factory.getRequestLogger();
        final Request request = Factory.getRequest(Request.Type.UPDATE, sqlResource.getName(), resIds,
                params, childrenParams, requestLogger);

        // Execute the delete request
        final int rowsAffected = sqlResource.write(request).getRowsAffected();

        System.out.println("\t" + requestLogger.getSql());
        System.out.println("\tdeleted " + rowsAffected + " row(s)\n");
    }

    @Override
    public void editActivity(Activity activity) throws SqlResourceException {
        // Create the row
        final List<RequestValue> params = new ArrayList<RequestValue>();

        // Create the update request
        final List<RequestValue> resIds = new ArrayList<RequestValue>(1);
        resIds.add(new RequestValue("id", activity.getId()));
        params.clear();
        params.add(new RequestValue("name", activity.getName()));
        params.add(new RequestValue("startDate", activity.getStartDate()));
        params.add(new RequestValue("endDate", activity.getEndDate()));
        params.add(new RequestValue("deadline", activity.getDeadline()));
        params.add(new RequestValue("description", activity.getDescription()));
        params.add(new RequestValue("status", activity.getStatus()));
        final List<List<RequestValue>> childrenParams = null;
        final RequestLogger requestLogger = Factory.getRequestLogger();
        final Request request = Factory.getRequest(Request.Type.UPDATE, sqlResource.getName(), resIds,
                params, childrenParams, requestLogger);

        // Execute the delete request
        final int rowsAffected = sqlResource.write(request).getRowsAffected();

        System.out.println("\t" + requestLogger.getSql());
        System.out.println("\tupdated " + rowsAffected + " row(s)\n");
    }

    @Override
    public List<Activity> getActivityByCondition(Activity activity) throws SqlResourceException {
        // Create the request
        List<RequestValue> params = new ArrayList<RequestValue>();

        if (StringUtil.isNotEmpty(activity.getName()))
            params.add(new RequestValue("name", "%" + activity.getName() + "%"));
        if (activity.getStartDate() != null)
            params.add(new RequestValue("startDate", new SimpleDateFormat("yyyy-MM-dd").format(activity.getStartDate()), RequestValue.Operator.Equals));
        if (activity.getEndDate() != null)
            params.add(new RequestValue("endDate", new SimpleDateFormat("yyyy-MM-dd").format(activity.getEndDate()), RequestValue.Operator.Equals));
        if (activity.getDeadline() != null)
            params.add(new RequestValue("deadline", new SimpleDateFormat("yyyy-MM-dd").format(activity.getDeadline()), RequestValue.Operator.Equals));
        List<RequestValue> resId = null;
        List<List<RequestValue>> childrenParams = null;
        RequestLogger requestLogger = Factory.getRequestLogger();
        Request request = Factory.getRequest(Request.Type.SELECT, sqlResource.getName(), params, resId,
                childrenParams, requestLogger);
        List<Map<String, Object>> resultList = sqlResource.read(request);

        System.out.println("\t" + requestLogger.getSql());

        ArrayList<Activity> activities = new ArrayList<Activity>();
        for (Map<String, Object> result : resultList) {
            Activity a = new Activity((Integer) result.get("id"), (String) result.get("name"), (Date) result.get("startDate"), (Date) result.get("endDate"), (Date) result.get("deadline"), (String) result.get("description"), (Integer) result.get("status"));
            activities.add(a);
        }
        return activities;
    }

}

package com.clemson.service;

import com.clemson.model.User;
import org.restsql.core.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shiwguo on 2017/4/10.
 */
@Service("ParticipationService")
public class ParticipationServiceImpl implements ParticipationService {
    @Override
    public List<User> getParticipantByActivityId(int activityId) throws SqlResourceException {
        // Get the resource object
        SqlResource sqlResource = Factory.getSqlResource("ActivityUser");

        // Create the request
        List<RequestValue> params = new ArrayList<RequestValue>();
        params.add(new RequestValue("id", activityId, RequestValue.Operator.Equals));
        //params.add(new RequestValue("username", name, RequestValue.Operator.Equals));
        List<RequestValue> resId = null;
        List<List<RequestValue>> childrenParams = null;
        RequestLogger requestLogger = Factory.getRequestLogger();
        Request request = Factory.getRequest(Request.Type.SELECT, sqlResource.getName(), params, resId,
                childrenParams, requestLogger);

        // Execute the request
        List<Map<String, Object>> resultList = sqlResource.read(request);
        if (resultList.size() > 0) {
            List<User> result = (ArrayList<User>) resultList.get(0).get("users");
            System.out.println("\t" + requestLogger.getSql());
            return result;
        } else
            return null;
    }

    @Override
    public void deleteParticipantByBothId(int activityId, int userId) throws SqlResourceException {
        // Get the resource object
        SqlResource sqlResource = Factory.getSqlResource("ActivityUser");

        // Create the request
        List<RequestValue> params = null;
        //params.add(new RequestValue("id", activityId, RequestValue.Operator.Equals));
        //params.add(new RequestValue("username", name, RequestValue.Operator.Equals));
        List<RequestValue> resIds = null;
        List<List<RequestValue>> childrenParams = new ArrayList<List<RequestValue>>();
        List<RequestValue> childParam = new ArrayList<RequestValue>();
        childParam.add(new RequestValue("user_id", userId));
        childParam.add(new RequestValue("activity_id", activityId));
        childrenParams.add(childParam);
        RequestLogger requestLogger = Factory.getRequestLogger();
        final Request request = Factory.getRequest(Request.Type.DELETE, sqlResource.getName(), resIds,
                params, childrenParams, requestLogger);

        // Execute the delete request
        final int rowsAffected = sqlResource.write(request).getRowsAffected();

        System.out.println("\t" + requestLogger.getSql());
        System.out.println("\tdeleted " + rowsAffected + " row(s)\n");
    }

    @Override
    public void insertParticipant(int activityId, int userId) throws SqlResourceException {
        // Get the resource object
        SqlResource sqlResource = Factory.getSqlResource("ActivityUser");

        // Create the request
        List<RequestValue> params = null;
        //params.add(new RequestValue("id", activityId, RequestValue.Operator.Equals));
        //params.add(new RequestValue("username", name, RequestValue.Operator.Equals));
        List<RequestValue> resIds = new ArrayList<RequestValue>();
        List<List<RequestValue>> childrenParams = new ArrayList<List<RequestValue>>();
        List<RequestValue> childParam = new ArrayList<RequestValue>();
        childParam.add(new RequestValue("user_id", userId));
        childParam.add(new RequestValue("activity_id", activityId));
        childrenParams.add(childParam);
        RequestLogger requestLogger = Factory.getRequestLogger();
        final Request request = Factory.getRequest(Request.Type.INSERT, sqlResource.getName(), resIds,
                params, childrenParams, requestLogger);

        // Execute the insert request
        final int rowsAffected = sqlResource.write(request).getRowsAffected();

        System.out.println("\t" + requestLogger.getSql());
        System.out.println("\tinserted " + rowsAffected + " row(s)");
    }
}

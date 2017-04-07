package com.clemson.mappers;

import com.clemson.model.User;
import org.restsql.core.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shiwguo on 2017/4/5.
 */
public class UserMapper {
    static {
        // Set the restsql properties location
        System.setProperty(Config.KEY_RESTSQL_PROPERTIES, UserMapper.class.getResource(
                "restSql.properties").getPath());
    }

    public void insertUser(User user) throws SqlResourceException {
        System.out.println("Insert a new user");

        // Get the resource object
        final SqlResource sqlResource = Factory.getSqlResource("User");

        // Create the row
        final List<RequestValue> params = new ArrayList<RequestValue>(3);
        params.add(new RequestValue("name", user.getName()));
        params.add(new RequestValue("password", user.getPassword()));
        params.add(new RequestValue("real_name", user.getRealName()));
        params.add(new RequestValue("role", user.getRole()));

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

    public User getUserByName(String name) throws SqlResourceException {
        // Get the resource object
        SqlResource sqlResource = Factory.getSqlResource("User");

        // Create the request
        List<RequestValue> params = new ArrayList<RequestValue>(1);
        params.add(new RequestValue("name", name, RequestValue.Operator.Equals));
        List<RequestValue> resId = null;
        List<List<RequestValue>> childrenParams = null;
        RequestLogger requestLogger = Factory.getRequestLogger();
        Request request = Factory.getRequest(Request.Type.SELECT, sqlResource.getName(), params, resId,
                childrenParams, requestLogger);

        // Execute the request
        Map<String, Object> results = sqlResource.read(request).get(0);
        return new User((Integer) results.get("id"), (String) results.get("name"), (String) results.get("password"), (String) results.get("realName"), (Integer) results.get("role"));
    }
}

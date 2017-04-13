package com.clemson.service;

import com.clemson.model.User;
import org.restsql.core.SqlResourceException;

import java.util.List;

/**
 * Created by shiwguo on 2017/4/10.
 */
public interface ParticipationService {
    public List<User> getParticipantByActivityId(int activityId) throws SqlResourceException;

    public void deleteParticipantByBothId(int activityId, int userId) throws SqlResourceException;

    public void insertParticipant(int activityId, int userId) throws SqlResourceException;

    public void updateParticipant(int activityId, int userId, int status, String feedback) throws SqlResourceException;
}

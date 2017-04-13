package com.clemson.service;

import com.clemson.model.User;
import com.clemson.util.CommonInfo;
import org.restsql.core.Config;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by shiwguo on 2017/4/10.
 */
public class ParticipationServiceImplTest {
    static {
        // Set the restsql properties location
        if (System.getProperty(Config.KEY_RESTSQL_PROPERTIES) == null)
            System.setProperty(Config.KEY_RESTSQL_PROPERTIES, ActivityServiceImplTest.class.getResource(
                    "/properties/restSql.properties").getPath());
    }

    @Test
    public void testGetParticipantsByActivityId() throws Exception {
        ParticipationService participationService = new ParticipationServiceImpl();
        List<User> participants = participationService.getParticipantByActivityId(12);
        assertEquals(participants.size(), 1);
    }

    @Test
    public void testDeleteParticaipantByBothId() throws Exception {
        ParticipationService participationService = new ParticipationServiceImpl();
        participationService.deleteParticipantByBothId(11, 1);
        assertEquals(participationService.getParticipantByActivityId(11), null);
    }

    @Test
    public void testInsertParticipant() throws Exception {
        ParticipationService participationService = new ParticipationServiceImpl();
        participationService.insertParticipant(1, 1);
        assertEquals(participationService.getParticipantByActivityId(1).size(), 1);
    }

    @Test
    public void testUpdateParticipant() throws Exception {
        ParticipationService participationService = new ParticipationServiceImpl();
        participationService.updateParticipant(1, 0, CommonInfo.participation_status.NEW.ordinal(), "。。。");
        assertEquals(participationService.getParticipantByActivityId(1).size(), 1);
    }

    @Test
    public void testGetActivityByUserId()throws  Exception{
        ParticipationService participationService=new ParticipationServiceImpl();
    }
}
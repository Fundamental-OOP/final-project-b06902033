package org.foop.finalproject.theMessageServer.service;

import org.foop.finalproject.theMessageServer.*;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    public void onReceiveAction(Action action) throws Exception{
        action.getGame().getRound().onTurnProgressing(action);

    }
}

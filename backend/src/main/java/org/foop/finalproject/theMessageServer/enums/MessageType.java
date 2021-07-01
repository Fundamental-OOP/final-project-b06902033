package org.foop.finalproject.theMessageServer.enums;

public enum MessageType {
    //----------------新版-------------------------------------------
    BROADCAST_ROOM_MEMBER_CHANGE("BROADCAST_ROOM_MEMBER_CHANGE"),
    BROADCAST_ALL_PLAYERS_INFO("BROADCAST_ALL_PLAYERS_INFO"),
    BROADCAST_GAME_START("BROADCAST_GAME_START"),
    BROADCAST_ROUND_START_MESSAGE("BROADCAST_ROUND_START_MESSAGE"),
    BROADCAST_TURN_START_MESSAGE("BROADCAST_TURN_START_MESSAGE"),
    BROADCAST_PLAYER_ON_INTELLIGENCE_IN_FRONT("BROADCAST_PLAYER_ON_INTELLIGENCE_IN_FRONT"),

    BROADCAST_PLAYER_START_SELECTING_GAMECARD("BROADCAST_PLAYER_START_SELECTING_GAMECARD"),
    BROADCAST_PLAYER_START_SELECTING_INTELLIGENCE("BROADCAST_PLAYER_START_SELECTING_INTELLIGENCE"),
    BROADCAST_PLAYER_START_SELECTING_RECEIVE("BROADCAST_PLAYER_START_SELECTING_RECEIVE"),
    INFORM_PLAYER_START_SELECTING_TARGET("INFORM_PLAYER_START_SELECTING_TARGET"),

    BROADCAST_GAMECARD_PLAYED("BROADCAST_GAMECARD_PLAYED"),
    // BROADCAST_PLAYER_PASSED("BROADCAST_PLAYER_PASSED"),
    BROADCAST_INTELLIGENCE_SENT("BROADCAST_INTELLIGENCE_SENT"),
    BROADCAST_INTELLIGENCE_RECEIVED("BROADCAST_INTELLIGENCE_RECEIVED"),


    INFORM_INTELLIGENCE_INFORMATION("INFORM_INTELLIGENCE_INFORMATION"),
    BROADCAST_PLAYER_CHOSEN_OPTION_FOR_PROVE("BROADCAST_PLAYER_CHOSEN_OPTION_FOR_PROVE"),
    INFORM_PLAYER_START_CHOOSING_OPTION_FOR_PROVE("INFORM_PLAYER_START_CHOOSING_OPTION_FOR_PROVE"),
    INFORM_PLAYER_START_SELECTING_HANDCARD_TO_DISCARD("INFORM_PLAYER_START_SELECTING_HANDCARD_TO_DISCARD"),
    ////////////////////////////////////////////////////////////////////////////////////////

    BROADCAST_GAME_OVER_MESSAGE("BROADCAST_GAME_OVER_MESSAGE"),
    INFORM_PLAYER_INFORMATION("INFORM_PLAYER_INFORMATION"),
    INFORM_USER_ID("INFORM_USER_ID"),
    BROADCAST_PLAYER_STATUS_CHANGE_MESSAGE("BROADCAST_PLAYER_STATUS_CHANGE_MESSAGE"),
    BROADCAST_ACTION_PERFORMED("BROADCAST_ACTION_PERFORMED");

    public String type;

    MessageType(String type) {
        this.type = type;
    }
}
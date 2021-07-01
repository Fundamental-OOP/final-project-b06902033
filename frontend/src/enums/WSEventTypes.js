const WSEventTypes = {
  INFORM_USER_ID: "INFORM_USER_ID", // 通知UserId
  BROADCAST_ROOM_MEMBER_CHANGE: "BROADCAST_ROOM_MEMBER_CHANGE", // 通知有人加入或離開房間
  BROADCAST_GAME_START: "BROADCAST_GAME_START", // 遊戲開始
  BROADCAST_TURN_START_MESSAGE: "BROADCAST_TURN_START_MESSAGE",
  BROADCAST_PLAYER_START_SELECTING_GAMECARD:
    "BROADCAST_PLAYER_START_SELECTING_GAMECARD",
  BROADCAST_PLAYER_START_SELECTING_TARGET:
    "BROADCAST_PLAYER_START_SELECTING_TARGET",
  BROADCAST_PLAYER_START_SELECTING_INTELLIGENCE:
    "BROADCAST_PLAYER_START_SELECTING_INTELLIGENCE",
  BROADCAST_PLAYER_START_SELECTING_RECEIVE:
    "BROADCAST_PLAYER_START_SELECTING_RECEIVE",
  BROADCAST_ALL_PLAYERS_INFO: "BROADCAST_ALL_PLAYERS_INFO",
  BROADCAST_PLAYER_ON_INTELLIGENCE_IN_FRONT:
    "BROADCAST_PLAYER_ON_INTELLIGENCE_IN_FRONT",
  BROADCAST_GAMECARD_PLAYED: "BROADCAST_GAMECARD_PLAYED",
  BROADCAST_PLAYER_PASSED: "BROADCAST_PLAYER_PASSED",
  BROADCAST_INTELLIGENCE_SENT: "BROADCAST_INTELLIGENCE_SENT",
  BROADCAST_INTELLIGENCE_RECEIVED: "BROADCAST_INTELLIGENCE_RECEIVED",
  INFORM_PLAYER_START_SELECTING_TARGET: "INFORM_PLAYER_START_SELECTING_TARGET",
  INFORM_INTELLIGENCE_INFORMATION: "INFORM_INTELLIGENCE_INFORMATION",
  INFORM_PLAYER_START_CHOOSING_OPTION_FOR_PROVE:
    "INFORM_PLAYER_START_CHOOSING_OPTION_FOR_PROVE",
  INFORM_PLAYER_START_SELECTING_HANDCARD_TO_DISCARD:
    "INFORM_PLAYER_START_SELECTING_HANDCARD_TO_DISCARD",
  BROADCAST_PLAYER_CHOOSE_OPTION_FOR_PROVE:
    "BROADCAST_PLAYER_CHOOSE_OPTION_FOR_PROVE",
  BROADCAST_ACTION_PERFORMED: "BROADCAST_ACTION_PERFORMED",
  BROADCAST_PLAYER_CHOSEN_OPTION_FOR_PROVE:
    "BROADCAST_PLAYER_CHOSEN_OPTION_FOR_PROVE",
  BROADCAST_GAME_OVER_MESSAGE: "BROADCAST_GAME_OVER_MESSAGE",
};
export default WSEventTypes;
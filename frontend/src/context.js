import { createContext } from "react";

export const WSContext = createContext({
  ws: null,
  wsData: { payload: null, type: null },
});

export const UserContext = createContext({
  userId: "",
  userName: "",
  setUserId: () => {},
  setUserName: () => {},
});

export const GameContext = createContext({
  otherPlayers: [
    {
      name: "",
      userId: "",
      playerId: "",
      gamecardsNum: 0,
      intelligences: { red: 0, blue: 0, black: 0 },
      character: "",
      handcards: [],
      camp: "",
    },
  ],
  myPlayer: {
    name: "",
    userId: "",
    playerId: "",
    gamecardsNum: 0,
    intelligences: { red: 0, blue: 0, black: 0 },
    character: "",
    handcards: [],
    camp: "",
  },
  isMyTurn: false,
  selectingStatus: "",
  availableGamecardsId: [],
  availableTargetsId: [],
  currentPlayerId: "",
  currentIntelligenceInFrontPlayerId: "",
  onClickTargetPlayer: (player) => {},
  gameMessages: [{ sender: "", body: "" }],
  isDecodeUsed: false,
  currentIntelligence: { name: "", color: "", type: "" },
  currentIntelligencePosition: { x: null, y: null },
  setCurrentIntelligencePosition: () => {},
  isGameOver: false,
});

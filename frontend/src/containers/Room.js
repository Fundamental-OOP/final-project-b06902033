import { useContext, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { UserContext, WSContext } from "../context";
import WSEventTypes from "../enums/WSEventTypes";
import Game from "./Game";
import PreparePage from "./PreparePage";
import { Prompt } from "react-router-dom";

const Room = () => {
  const { wsData } = useContext(WSContext);
  const { userId } = useContext(UserContext);
  const [users, setUsers] = useState([]);
  const { roomId } = useParams();

  const [myPlayer, setMyPlayer] = useState();
  const [otherPlayers, setOtherPlayers] = useState([]);

  const [isGameStart, setIsGameStart] = useState(false);

  useEffect(() => {
    const onBeforeUnload = (e) => {
      e.preventDefault();
      e.returnValue = `確定要離開${isGameStart ? "遊戲" : "房間"}嗎？`;
    };

    window.addEventListener("beforeunload", onBeforeUnload);

    return () => {
      window.removeEventListener("beforeunload", onBeforeUnload);
    };
  });

  const updatePlayers = (players) => {
    const myPlayerIndex = players.findIndex(
      (player) => player.userId === userId
    );

    setMyPlayer(players[myPlayerIndex]);
    setOtherPlayers([
      ...players.slice(myPlayerIndex + 1),
      ...players.slice(0, myPlayerIndex),
    ]);
  };

  useEffect(() => {
    if (wsData.type === WSEventTypes.BROADCAST_ROOM_MEMBER_CHANGE) {
      setUsers(wsData.payload.users);
    } else if (wsData.type === WSEventTypes.BROADCAST_GAME_START) {
      setIsGameStart(true);
      const players = wsData.payload.players;
      updatePlayers(players);
    }
  }, [wsData]);

  return (
    <>
      <Prompt
        message={() => `確定要離開${isGameStart ? "遊戲" : "房間"}嗎？`}
      />
      {isGameStart ? (
        <Game
          roomId={roomId}
          myPlayer={myPlayer}
          otherPlayers={otherPlayers}
          updatePlayers={updatePlayers}
          setMyPlayer={setMyPlayer}
          setOtherPlayers={setOtherPlayers}
          setIsGameStart={setIsGameStart}
        />
      ) : (
        <PreparePage roomId={roomId} users={users} />
      )}
    </>
  );
};
export default Room;

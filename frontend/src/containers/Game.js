import { Box, Grid, makeStyles, Typography } from "@material-ui/core";
import { useContext, useEffect, useState } from "react";
import { useHistory } from "react-router";
import GameBoard from "../components/GameBoard";
import GameCard from "../components/GameCard";
import MyPlayerSection from "../components/MyPlayerSection";
import PlayersLayout from "../components/PlayersLayout";
import { GameContext, UserContext, WSContext } from "../context";
import WSEventTypes from "../enums/WSEventTypes";
import {
  playerReady,
  receiveIntelligence,
  skip,
  playGameCard,
  selectTarget,
  sendIntelligence,
  chooseProveOption,
  chooseDiscardTargetHandcard,
} from "../axios";
import {
  PlayerName,
  GameCardName,
  IntelligenceColor,
  IntelligenceType,
  CampName,
} from "../components/KeyWord";
import SelectingStatus from "../enums/SelectingStatus";
import useDialog from "../hooks/useDialog";
import Intelligence from "../components/Intelligence";
import PlayerStatus from "../enums/PlayerStatus";
import GameOverCard from "../components/GameOverCard";

const useStyle = makeStyles({
  root: {
    height: "100%",
    width: "100%",
    padding: 20,
    boxSizing: "border-box",
    position: "relative",
  },
  mainSection: {
    height: 500,
    width: "100%",
  },
  middleRow: {
    flexGrow: 1,
  },
});

/**
 * @param {{roomId: String, myPlayer: TypePlayer, otherPlayers: [TypePlayer]}}
 */

const Game = ({
  roomId,
  myPlayer,
  otherPlayers,
  updatePlayers,
  setMyPlayer,
  setOtherPlayers,
  setIsGameStart,
}) => {
  const classes = useStyle();
  const history = useHistory();
  const { userId } = useContext(UserContext);
  const { wsData } = useContext(WSContext);
  const [cardsOnBoard, setCardsOnBoard] = useState([]);
  const [availableGamecardsId, setAvailableGamecardsId] = useState([]);
  const [availableTargetsId, setAvailableTargetsId] = useState([]);

  const [currentPlayerId, setCurrentPlayerId] = useState(null);
  const [
    currentIntelligenceInFrontPlayerId,
    setCurrentIntelligenceInFrontPlayerId,
  ] = useState(null);
  const [currentIntelligencePosition, setCurrentIntelligencePosition] =
    useState({ x: 0, y: 0 });
  const [currentIntelligence, setCurrentIntelligence] = useState(null);
  const [selectingStatus, setSelectingStatus] = useState("");

  const [statusBarMessage, setStatusBarMessage] = useState("");

  const [isDecodeUsed, setIsDecodeUsed] = useState(false);

  const [gameMessages, setGameMessages] = useState([
    { body: "????????????????????????????????????????????????" },
  ]);
  const [showDialog, Dialog] = useDialog();

  const [isGameOver, setIsGameOver] = useState(false);
  const [winningPlayers, setWinningPlayers] = useState([]);

  useEffect(() => {
    const onReady = async () => {
      const result = await playerReady(userId, roomId, myPlayer.playerId);
    };
    onReady();
  }, []);

  const getPlayerById = (playerId) => {
    if (myPlayer.playerId === playerId) {
      return myPlayer;
    } else {
      return otherPlayers.find((player) => player.playerId === playerId);
    }
  };

  /*
    ======================================================================================
    =================================== OnClick Events =================================== 
    ======================================================================================
  */

  const onClickGameCard = (gameCardId) => {
    const gameCard = myPlayer.handcards.find((card) => card.id === gameCardId);
    let title, api;
    switch (selectingStatus) {
      case SelectingStatus.GAMECARD:
        title = "??????????????????????????????";
        api = playGameCard;
        break;
      case SelectingStatus.INTELLIGENCE:
        title = `???????????????????????????`;
        api = sendIntelligence;
        break;
      case SelectingStatus.DISCARD:
        title = "?????????????????????????????????";
        api = chooseDiscardTargetHandcard;
        break;
      default:
        alert("Invalid Action.");
    }

    showDialog({
      title,
      content: <GameCard gameCard={gameCard} unclickable={true} />,
      posButtonAction: async () => {
        const result = await api(roomId, myPlayer.playerId, gameCardId);
        if (result.status === "SUCCESS") {
          setAvailableGamecardsId([]);
        } else {
          alert(result.data);
        }
      },
    });
  };

  const onClickTargetPlayer = (player) => {
    showDialog({
      title:
        player.playerId === myPlayer.playerId ? (
          `???????????????????????????????????????`
        ) : (
          <>
            ???????????????
            <PlayerName text={player.name} />
            ??????????????????
          </>
        ),
      posButtonAction: async () => {
        const result = await selectTarget(
          roomId,
          myPlayer.playerId,
          player.playerId
        );
        if (result.status === "SUCCESS") {
          setAvailableTargetsId([]);
        }
      },
    });
  };

  const onClickSkip = async (e) => {
    const result = await skip(roomId, myPlayer.playerId, e.target.id);
    if (result.status === "FAILED") {
      alert(result.data);
    }
  };

  const onClickReceiveIntelligence = async () => {
    const result = await receiveIntelligence(roomId, myPlayer.playerId);
    if (result.status === "FAILED") {
      alert(result.data);
    }
  };

  const onClickBackToRoom = () => {
    setIsGameStart(false);
  };

  /*
    ======================================================================================
    ======================= Handle Functions for WebSocket Message ======================= 
    ======================================================================================
  */

  const handleTurnStart = ({ player }) => {
    if (myPlayer.playerId === player.playerId) {
      setMyPlayer(player);
      setGameMessages([...gameMessages, { body: "???????????????????????????????????????" }]);
      setStatusBarMessage("???????????????????????????????????????");
    } else {
      const getTurnPlayer = otherPlayers.find(
        (p) => p.playerId === player.playerId
      );

      const turnPlayerIndex = otherPlayers.findIndex(
        (p) => p.playerId === player.playerId
      );
      const newOtherPlayers = [...otherPlayers];
      newOtherPlayers[turnPlayerIndex] = player;
      setOtherPlayers(newOtherPlayers);

      setGameMessages([
        ...gameMessages,
        {
          body: (
            <>
              ??????????????????
              <PlayerName text={getTurnPlayer.name} />
              ??????????????????
            </>
          ),
        },
      ]);
    }
  };

  const handleStartSelectingGamecard = ({
    playerId,
    availableGamecardsId,
    isCounteract,
  }) => {
    setCurrentPlayerId(playerId);
    if (playerId === myPlayer.playerId) {
      setSelectingStatus(SelectingStatus.GAMECARD);
      if (isCounteract) {
        setStatusBarMessage(
          <>
            ?????????????????????
            <GameCardName text="??????" />
            ?????????
          </>
        );
      } else {
        setStatusBarMessage("???????????????????????????");
      }
      setAvailableGamecardsId(availableGamecardsId);
    } else {
      if (isCounteract) {
        setStatusBarMessage(
          <>
            ????????????????????????????????????
            <GameCardName text="??????" />
          </>
        );
      } else {
        setStatusBarMessage("?????????????????????????????????");
      }
    }
  };

  const handleStartSelectingTarget = ({ playerId, availableTargetsId }) => {
    if (playerId === myPlayer.playerId) {
      setSelectingStatus(SelectingStatus.TARGET);
      setStatusBarMessage("???????????????????????????");
      setAvailableTargetsId(availableTargetsId);
    }
  };

  const handleStartSelectingIntelligence = ({
    playerId,
    availableIntelligencesId,
  }) => {
    setCurrentPlayerId(playerId);
    if (playerId === myPlayer.playerId) {
      setSelectingStatus(SelectingStatus.INTELLIGENCE);
      setStatusBarMessage("???????????????????????????");
      setAvailableGamecardsId(availableIntelligencesId);
    } else {
      setStatusBarMessage("??????????????????????????????????????????");
    }
  };

  const handleStartSelectingReceive = ({ playerId, canPass, canReceive }) => {
    setCurrentPlayerId(playerId);
    if (playerId === myPlayer.playerId) {
      setSelectingStatus(SelectingStatus.RECEIVE);
      showDialog({
        content: "???????????????????????????",
        isNegButtonDisabled: !canPass,
        negButtonAction: async () => {
          const result = await skip(roomId, myPlayer.playerId);
          if (result.status === "FAILED") {
            alert(result.data);
          }
        },
        isPosButtonDisabled: !canReceive,
        posButtonAction: async () => {
          const result = await receiveIntelligence(roomId, myPlayer.playerId);
          if (result.status === "FAILED") {
            alert(result.data);
          }
        },
      });
    } else {
      setStatusBarMessage("??????????????????????????????????????????");
    }
  };

  const handleAllPlayersInfo = ({ players, gamecardsOnBoard }) => {
    players.forEach((player) => {
      const prevPlayer = getPlayerById(player.playerId);
      if (prevPlayer.status !== player.status) {
        const name = player.playerId === myPlayer.playerId ? "???" : player.name;
        const isMe = player.playerId === myPlayer.playerId;
        if (player.status === PlayerStatus.LOSE) {
          setGameMessages([
            ...gameMessages,
            {
              body: (
                <>
                  <PlayerName text={name} />
                  ????????????????????????????????????????????????????????????????????????????????????????????????
                  {isMe ? (
                    ""
                  ) : (
                    <>
                      <br />
                      ??????????????????
                      <CampName camp={player.camp} />
                    </>
                  )}
                </>
              ),
            },
          ]);
        } else if (player.status === PlayerStatus.DEAD) {
          setGameMessages([
            ...gameMessages,
            {
              body: (
                <>
                  <PlayerName text={name} />
                  ??????QQ
                  {isMe ? (
                    ""
                  ) : (
                    <>
                      <br />
                      ??????????????????
                      <CampName camp={player.camp} />
                    </>
                  )}
                </>
              ),
            },
          ]);
        }
      }
    });
    updatePlayers(players);
    setCardsOnBoard(gamecardsOnBoard);
  };

  const handlePlayerOnIntelligenceInFront = ({ playerId }) => {
    setCurrentIntelligenceInFrontPlayerId(playerId);
    if (playerId === myPlayer.playerId) {
      setStatusBarMessage("????????????????????????????????????");
    } else {
      // TODO: show intelligence on that player
      const playerName = otherPlayers.find((p) => p.playerId === playerId).name;
      setStatusBarMessage(`????????????????????????${playerName}?????????`);
    }
  };

  const handleGameCardPlayed = ({ playerId, gameCard, targetId }) => {
    let playerName =
      playerId === myPlayer.playerId
        ? "???"
        : otherPlayers.find((p) => p.playerId === playerId).name;
    if (!targetId) {
      setGameMessages([
        ...gameMessages,
        {
          body: (
            <>
              <PlayerName text={playerName} />
              ?????????
              <GameCardName text={gameCard.name} />
            </>
          ),
        },
      ]); // ??????????????????????????????
    } else {
      let targetName =
        playerId === targetId
          ? "??????"
          : targetId === myPlayer.playerId
          ? "???"
          : otherPlayers.find((p) => p.playerId === targetId).name;
      setGameMessages([
        ...gameMessages,
        {
          body: (
            <>
              <PlayerName text={playerName} />
              ???
              <PlayerName text={targetName} />
              ?????????
              <GameCardName text={gameCard.name} />
            </>
          ),
        },
      ]);
    }
  };

  const handleIntelligenceSent = ({ playerId, gameCard, targetId }) => {
    setCurrentIntelligence(gameCard);
    setCurrentIntelligenceInFrontPlayerId(playerId);
    let playerName =
      playerId === myPlayer.playerId
        ? "???"
        : otherPlayers.find((p) => p.playerId === playerId).name;
    if (!targetId) {
      // ???????????????
      setGameMessages([
        ...gameMessages,
        {
          body: (
            <>
              <PlayerName text={playerName} />
              ???????????????
              <IntelligenceType text={gameCard.type} />
              ??????
            </>
          ),
        },
      ]);
    } else {
      let targetName =
        playerId === targetId
          ? "??????"
          : targetId === myPlayer.playerId
          ? "???"
          : otherPlayers.find((p) => p.playerId === targetId).name;
      setGameMessages([
        ...gameMessages,
        {
          body: (
            <>
              <PlayerName text={playerName} />
              ???
              <PlayerName text={targetName} />
              ???????????????
              <IntelligenceType text={gameCard.type} />
              ??????
            </>
          ),
        },
      ]);
    }
  };

  const handleIntelligenceReceived = ({
    receiverId,
    senderId,
    intelligence,
  }) => {
    setCurrentIntelligenceInFrontPlayerId(null);
    setTimeout(() => setCurrentIntelligence(null), 1000);

    const receiver = getPlayerById(receiverId);
    const sender = getPlayerById(senderId);

    setGameMessages([
      ...gameMessages,
      {
        body: (
          <>
            <PlayerName
              text={receiverId === myPlayer.playerId ? "???" : receiver.name}
            />
            ?????????
            <PlayerName
              text={
                senderId === receiverId
                  ? "??????"
                  : senderId === myPlayer.playerId
                  ? "???"
                  : sender.name
              }
            />
            ?????????????????????
            <IntelligenceColor color={intelligence?.color} />
          </>
        ),
      },
    ]);
    setIsDecodeUsed(false);
  };

  // ??????
  const handleIntelligenceInformation = ({ gameCard }) => {
    setIsDecodeUsed(true);
  };

  const handleStartChoosingOptionForProve = ({
    possibleOptions,
    camp,
    performerId,
    targetId,
  }) => {
    const camps = ["????????????", "?????????", "????????????"];
    const otherCamps = camps.filter((c) => c !== camp);

    setSelectingStatus("PROVE");
    if (myPlayer.playerId === targetId) {
      setStatusBarMessage("??????????????????");
      showDialog({
        title: "?????????????????????????????????",
        content: (
          <>
            <Typography>
              <CampName camp={camp} />???{possibleOptions[0]}
            </Typography>
            <Typography>
              <CampName camp={otherCamps[0]} />???
              <CampName camp={otherCamps[1]} />???{possibleOptions[1]}
            </Typography>
          </>
        ),
        negButtonText: <CampName camp={camp} />,
        isNegButtonDisabled: camp !== myPlayer.camp,
        negButtonAction: async () => {
          await chooseProveOption(roomId, myPlayer.playerId, 0); // ???or??????
        },
        posButtonText: (
          <>
            <CampName camp={otherCamps[0]} />???<CampName camp={otherCamps[1]} />
          </>
        ),
        isPosButtonDisabled: camp === myPlayer.camp,
        posButtonAction: async () => {
          await chooseProveOption(roomId, myPlayer.playerId, 1);
        },
      });
    } else if (myPlayer.playerId === performerId) {
      setStatusBarMessage(
        <>
          ???????????????
          <PlayerName text={getPlayerById(targetId)?.name} />
        </>
      );
      setGameMessages([
        ...gameMessages,
        {
          body: (
            <>
              ?????????????????????????????????
              <Typography>
                <CampName camp={camp} />???{possibleOptions[0]}
              </Typography>
              <Typography>
                <CampName camp={otherCamps[0]} />???
                <CampName camp={otherCamps[1]} />???{possibleOptions[1]}
              </Typography>
            </>
          ),
        },
      ]);
    }
  };

  const handleStartSelectingHandcardToDiscard = ({
    playerId,
    availableGamecardsId,
  }) => {
    if (playerId === myPlayer.playerId) {
      setSelectingStatus(SelectingStatus.DISCARD);
      setStatusBarMessage("???????????????????????????");
      setAvailableGamecardsId(availableGamecardsId);
    }
  };

  const handleActionPerformed = ({ messages }) => {
    const performer = getPlayerById(messages[0]);
    const target = getPlayerById(messages[4]);
    if (messages[2] === "??????") {
      setCurrentPlayerId(target?.playerId);
      if (target.playerId === myPlayer.playerId) {
        setStatusBarMessage("??????????????????");
      } else {
        setStatusBarMessage(
          <>
            <PlayerName text={target?.name} />
            ???????????????
          </>
        );
      }
    }

    setGameMessages([
      ...gameMessages,
      {
        body: (
          <>
            <PlayerName
              text={
                performer?.playerId === myPlayer.playerId
                  ? "???"
                  : performer?.name
              }
            />
            {messages[1]}
            <GameCardName text={messages[2]} />
            {messages[3]}
            <PlayerName
              text={
                performer?.playerId === target?.playerId
                  ? "??????"
                  : target?.playerId === myPlayer.playerId
                  ? "???"
                  : target?.name
              }
            />
            {messages[5]}
            <GameCardName text={messages[6]} />
            {messages[7]}
          </>
        ),
      },
    ]);
  };

  const handleProveResult = ({ chosenOption, playerId }) => {
    const player = getPlayerById(playerId);
    setStatusBarMessage(
      <>
        ???????????????
        <PlayerName>
          {myPlayer.playerId === playerId ? "???" : player.name}
        </PlayerName>
        {chosenOption}
      </>
    );
    setGameMessages([
      ...gameMessages,
      {
        body: (
          <>
            ???????????????
            <PlayerName>
              {myPlayer.playerId === playerId ? "???" : player.name}
            </PlayerName>
            {chosenOption}
          </>
        ),
      },
    ]);
  };

  const handleGameOver = ({ players, winnersId }) => {
    updatePlayers(players);
    setWinningPlayers(winnersId.map((id) => getPlayerById(id)));
    setIsGameOver(true);
    setGameMessages([...gameMessages, { body: "????????????????????????????????????" }]);
  };

  /*
    ======================================================================================
    ============================== Handle WebSocket Message ============================== 
    ======================================================================================
  */

  useEffect(() => {
    const { type, payload } = wsData;
    setStatusBarMessage("");
    switch (type) {
      case WSEventTypes.BROADCAST_TURN_START_MESSAGE:
        handleTurnStart(payload);
        break;

      case WSEventTypes.BROADCAST_PLAYER_START_SELECTING_GAMECARD:
        handleStartSelectingGamecard(payload);
        break;

      case WSEventTypes.INFORM_PLAYER_START_SELECTING_TARGET:
        handleStartSelectingTarget(payload);
        break;

      case WSEventTypes.BROADCAST_PLAYER_START_SELECTING_INTELLIGENCE:
        handleStartSelectingIntelligence(payload);
        break;

      case WSEventTypes.BROADCAST_PLAYER_START_SELECTING_RECEIVE:
        handleStartSelectingReceive(payload);
        break;

      case WSEventTypes.BROADCAST_ALL_PLAYERS_INFO:
        handleAllPlayersInfo(payload);
        break;

      case WSEventTypes.BROADCAST_PLAYER_ON_INTELLIGENCE_IN_FRONT:
        handlePlayerOnIntelligenceInFront(payload);
        break;

      case WSEventTypes.BROADCAST_GAMECARD_PLAYED:
        handleGameCardPlayed(payload);
        break;

      case WSEventTypes.BROADCAST_INTELLIGENCE_SENT:
        handleIntelligenceSent(payload);
        break;

      case WSEventTypes.BROADCAST_INTELLIGENCE_RECEIVED:
        handleIntelligenceReceived(payload);
        break;

      case WSEventTypes.INFORM_INTELLIGENCE_INFORMATION:
        handleIntelligenceInformation(payload);
        break;

      case WSEventTypes.INFORM_PLAYER_START_CHOOSING_OPTION_FOR_PROVE: // ???????????????
        handleStartChoosingOptionForProve(payload);
        break;

      case WSEventTypes.INFORM_PLAYER_START_SELECTING_HANDCARD_TO_DISCARD: // ???????????????
        handleStartSelectingHandcardToDiscard(payload);
        break;

      case WSEventTypes.BROADCAST_ACTION_PERFORMED: // ??????????????????
        handleActionPerformed(payload);
        break;
      case WSEventTypes.BROADCAST_PLAYER_CHOSEN_OPTION_FOR_PROVE:
        handleProveResult(payload);
        break;
      case WSEventTypes.BROADCAST_GAME_OVER_MESSAGE: // TODO
        handleGameOver(payload);
        break;
      default:
        break;
    }
  }, [wsData]);

  /*
    ======================================================================================
    ================================== Render Container ================================== 
    ======================================================================================
  */

  return (
    <GameContext.Provider
      value={{
        myPlayer,
        otherPlayers,
        isMyTurn: myPlayer.playerId === currentPlayerId,
        currentPlayerId,
        currentIntelligenceInFrontPlayerId,
        gameMessages,
        onClickTargetPlayer,
        availableGamecardsId,
        availableTargetsId,
        currentIntelligence,
        isDecodeUsed,
        selectingStatus,
        currentIntelligencePosition,
        setCurrentIntelligencePosition,
        isGameOver,
      }}
    >
      <Box className={classes.root}>
        <Grid container className={classes.mainSection} direction="column">
          <PlayersLayout players={otherPlayers} place="top" />
          <Grid
            item
            container
            direction="row"
            justify="space-between"
            alignItems="center"
            className={classes.middleRow}
          >
            <PlayersLayout players={otherPlayers} place="left" />
            {isGameOver ? (
              <GameOverCard
                winningPlayers={winningPlayers}
                onClickBackToRoom={onClickBackToRoom}
              />
            ) : (
              <GameBoard cardsOnBoard={cardsOnBoard} />
            )}

            <PlayersLayout players={otherPlayers} place="right" />
          </Grid>
        </Grid>
        <MyPlayerSection
          me={myPlayer}
          onClickGameCard={onClickGameCard}
          onClickSkip={onClickSkip}
          onClickReceiveIntelligence={onClickReceiveIntelligence}
          myTurn={currentPlayerId === myPlayer.playerId}
          statusBarMessage={statusBarMessage}
        />
      </Box>
      {Dialog}
      <Intelligence isPassing={currentIntelligenceInFrontPlayerId !== null} />
    </GameContext.Provider>
  );
};

export default Game;

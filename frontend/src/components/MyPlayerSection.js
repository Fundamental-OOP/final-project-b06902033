import {
  Avatar,
  Chip,
  Grid,
  GridList,
  Typography,
  makeStyles,
  GridListTile,
  Button,
  Box,
  Card,
  CardActionArea,
  CardContent,
} from "@material-ui/core";
import { Clear, Block, Lock, FiberManualRecord } from "@material-ui/icons";
import GameCard from "./GameCard";
import ArrowLeftIcon from "@material-ui/icons/ArrowLeftRounded";
import ArrowRightIcon from "@material-ui/icons/ArrowRightRounded";
import ChatBox from "./ChatBox";
import { useContext, useEffect, useRef } from "react";
import { GameContext } from "../context";
import SelectingStatus from "../enums/SelectingStatus";
import CharacterTooltip from "./CharacterTooltip";
import PlayerStatus from "../enums/PlayerStatus";
import CharacterAvatar from "./CharacterAvatar";

const useStyle = makeStyles({
  root: {
    position: "absolute",
    width: "100%",
    height: 250,
    left: 0,
    bottom: 0,
    padding: "0px 30px",
    backgroundColor: "#363636",
    boxSizing: "border-box",
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
  },
  handcardSection: {
    height: "100%",
    width: "45%",
  },
  gameHint: {
    position: "relative",
    fontSize: 20,
    height: 54,
    width: "100%",
    margin: 0,
    marginTop: -50,
    padding: "10px 0px",
    border: "2px double skyblue",
    backgroundColor: "#123456",
    borderRadius: 10,
    boxSizing: "border-box",
  },
  skipButton: {
    // width: 100,
    height: 40,
    position: "absolute",
    top: 5,
    right: 10,
    zIndex: "99",
    fontSize: 24,
  },
  gridList: ({ isTakingTurn }) => ({
    minHeight: 172,
    width: "100%",
    margin: "0px 30px",
    flexWrap: "nowrap",
    transform: "translateZ(0)",
    overflowX: "overlay",
    border: "2px dashed yellow",
    borderRadius: 10,
    boxShadow: isTakingTurn ? "0px 0px 7px 7px yellowgreen" : undefined,
    "& li": {
      width: "max-content !important",
    },
    transition: "box-shadow 0.5s",
  }),
  playerContent: {
    width: "max-content",
    marginRight: 22,
    paddingBottom: 5,
  },
  playerInfo: ({ available }) => ({
    height: "90%",
    width: "22%",
    // padding: "0px 10px",
    boxSizing: "border-box",
    boxShadow: available ? "0px 0px 5px 5px firebrick" : undefined,
    transition: "box-shadow 0.5s",
  }),
  playerInfoHead: {
    width: "100%",
    position: "relative",
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
  },
  playerName: {
    fontSize: 28,
    width: 170,
    padding: "5px 10px",
    overflow: "hidden",
    whiteSpace: "nowrap",
    textOverflow: "ellipsis",
    // marginTop: -5,
  },
  playerStatus: ({ status }) => ({
    width: status === PlayerStatus.LOCKEDON ? 120 : 240,
    height: status === PlayerStatus.LOCKEDON ? 120 : 240,
    position: "absolute",
    left: -5,
    top: status === PlayerStatus.LOCKEDON ? 35 : 25,
    margin: 0 - 10,
    zIndex: "999999",
    color: "darkred",
    opacity: "0.8",
  }),
  camp: {
    position: "absolute",
    top: -8,
    right: 13,
    height: 80,
    width: 80,
    objectFit: "contain",
  },
  characterAvatar: {
    margin: "4px 25px",
  },
  icon: {
    objectFit: "contain",
    height: 25,
    width: 25,
  },
  chip: {
    fontSize: 16,
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between",
    padding: "0px 2px",
    width: 54,
  },
});

const MyPlayerSection = ({
  me,
  onClickGameCard,
  onClickSkip,
  onClickReceiveIntelligence,
  statusBarMessage,
}) => {
  const {
    gameMessages,
    availableGamecardsId,
    selectingStatus,
    isMyTurn,
    currentIntelligence,
    currentIntelligenceInFrontPlayerId,
    availableTargetsId,
    myPlayer,
    onClickTargetPlayer,
    currentPlayerId,
    setCurrentIntelligencePosition,
    isGameOver,
  } = useContext(GameContext);

  const rootRef = useRef();
  const available =
    isMyTurn &&
    availableTargetsId.findIndex((id) => myPlayer.playerId === id) !== -1;

  const classes = useStyle({
    isTakingTurn: isMyTurn,
    available,
    status: myPlayer.status,
  });

  useEffect(() => {
    if (
      (currentPlayerId === myPlayer.playerId && !currentIntelligence) ||
      currentIntelligenceInFrontPlayerId === myPlayer.playerId
    ) {
      const { left, top } = rootRef.current.getBoundingClientRect();
      console.log(left, top);
      setCurrentIntelligencePosition({
        x: left + 500,
        y: top - 30,
      });
    }
  }, [currentIntelligenceInFrontPlayerId, currentPlayerId]);

  return (
    <Box className={classes.root} ref={rootRef}>
      <ChatBox messages={gameMessages} />
      <Grid
        className={classes.handcardSection}
        container
        direction="column"
        justify="space-evenly"
      >
        <Typography
          className={classes.gameHint}
          style={{ visibility: isGameOver ? "hidden" : "visible" }}
        >
          {statusBarMessage}
          {isMyTurn && selectingStatus === SelectingStatus.GAMECARD && (
            <Button
              className={classes.skipButton}
              variant="contained"
              onClick={onClickSkip}
              color="primary"
            >
              跳過
            </Button>
          )}
        </Typography>

        <GridList className={classes.gridList} cellHeight="auto">
          {me?.handcards?.map((handcard, i) => (
            <GridListTile key={i} style={{ margin: 5 }}>
              <GameCard
                gameCard={handcard}
                unavailable={
                  !isMyTurn ||
                  availableGamecardsId.findIndex((id) => handcard.id === id) ===
                    -1
                }
                scale={0.8}
                onClick={onClickGameCard}
              />
            </GridListTile>
          ))}
        </GridList>
      </Grid>
      <CharacterTooltip character={myPlayer.character} mine={true}>
        <Card className={classes.playerInfo} variant="outlined">
          <CardActionArea
            style={{ height: "100%", width: "100%" }}
            disabled={!available}
            onClick={() => onClickTargetPlayer(myPlayer)}
          >
            <Grid
              container
              direction="column"
              alignItems="center"
              justify="center"
              component={CardContent}
              style={{ padding: "0px 10px" }}
            >
              <Box className={classes.playerInfoHead}>
                <Typography className={classes.playerName} variant="h6">
                  {me?.name}
                </Typography>
                <img
                  className={classes.camp}
                  src={`/img/${me.camp}.png`}
                  alt={me.camp}
                />
              </Box>
              <Grid container item direction="row" justify="space-between">
                <div className={classes.characterAvatar}>
                  <CharacterAvatar
                    mine={true}
                    size={140}
                    character={myPlayer.character}
                    status={myPlayer.status}
                  />
                </div>
                <Grid
                  className={classes.playerContent}
                  container
                  item
                  direction="column"
                  alignItems="center"
                  justify="flex-end"
                  spacing={1}
                >
                  <Grid item>
                    <Chip
                      size="small"
                      className={classes.chip}
                      avatar={
                        <img
                          className={classes.icon}
                          src="/img/handcards.png"
                          alt="手牌"
                        />
                      }
                      label={me?.handcards?.length ?? 0}
                    />
                  </Grid>
                  <Grid item>
                    <Chip
                      size="small"
                      className={classes.chip}
                      avatar={<FiberManualRecord style={{ color: "red" }} />}
                      label={me?.intelligences?.red}
                    />
                  </Grid>
                  <Grid item>
                    <Chip
                      size="small"
                      className={classes.chip}
                      avatar={<FiberManualRecord style={{ color: "blue" }} />}
                      label={me?.intelligences?.blue}
                    />
                  </Grid>
                  <Grid item>
                    <Chip
                      size="small"
                      className={classes.chip}
                      avatar={<FiberManualRecord style={{ color: "black" }} />}
                      label={me?.intelligences?.black}
                    />
                  </Grid>
                </Grid>
              </Grid>
            </Grid>
          </CardActionArea>
        </Card>
      </CharacterTooltip>
    </Box>
  );
};

export default MyPlayerSection;

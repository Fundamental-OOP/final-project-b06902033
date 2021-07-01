import {
  Avatar,
  Box,
  Card,
  CardActionArea,
  CardContent,
  Chip,
  Grid,
  makeStyles,
  Typography,
} from "@material-ui/core";
import { FiberManualRecord } from "@material-ui/icons";
import { useContext, useEffect, useRef } from "react";
import { GameContext } from "../context";
import PlayerStatus from "../enums/PlayerStatus";
import CharacterAvatar from "./CharacterAvatar";
import CharacterTooltip from "./CharacterTooltip";

const useStyle = makeStyles({
  root: {
    height: 120,
    width: 300,
    position: "relative",
    padding: 10,
  },
  card: ({ isTakingTurn, available }) => ({
    height: "100%",
    width: "100%",
    // margin: 10,
    boxShadow: isTakingTurn
      ? "0px 0px 5px 5px yellowgreen"
      : available
      ? "0px 0px 5px 5px firebrick"
      : undefined,
    "&:hover .MuiCardActionArea-focusHighlight": {
      opacity: 0.08,
    },
    "& .MuiCardActionArea-focusHighlight": {
      zIndex: 100,
    },
    transition: "box-shadow 0.5s",
  }),
  actionArea: {
    height: "100%",
    width: "100%",
  },
  playerName: {
    fontSize: 20,
    overflow: "hidden",
    whiteSpace: "nowrap",
    textOverflow: "ellipsis",
    lineHeight: 1,
  },
  playerContent: {},
  icon: {
    objectFit: "contain",
    height: 25,
    width: 25,
  },
  chip: {
    pointerEvents: "none",
    fontSize: 16,
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between",
    padding: "0px 2px",
    width: 54,
  },
  camp: {
    position: "absolute",
    top: 70,
    left: 85,
    height: 50,
    width: 50,
    objectFit: "contain",
    zIndex: 5,
  },
});

/**
 *
 * @param {{player: {playerId: String, name: String, character: {name: String, mission: Sting, hidden: Boolean},
 * intelligences: {red: Number, blue: Number, black: Number}, handcardsNum: Number, status: String}}}
 * @returns
 */
const Player = ({ player }) => {
  const {
    currentPlayerId,
    currentIntelligenceInFrontPlayerId,
    isMyTurn,
    availableTargetsId,
    onClickTargetPlayer,
    currentIntelligence,
    setCurrentIntelligencePosition,
    isGameOver,
  } = useContext(GameContext);
  const {
    playerId,
    name,
    character,
    intelligences,
    handcardsNum,
    status,
    camp,
  } = player;
  const rootRef = useRef();
  const isTakingTurn = playerId === currentPlayerId;
  const available =
    isMyTurn && availableTargetsId.findIndex((id) => playerId === id) !== -1;
  const classes = useStyle({
    isTakingTurn,
    available,
    status,
  });

  const onActionAreaClicked = (e) => {
    if (!available) {
      e.preventDefault();
    } else {
      onClickTargetPlayer?.(player);
    }
  };

  useEffect(() => {
    if (
      (currentPlayerId === playerId && !currentIntelligence) ||
      currentIntelligenceInFrontPlayerId === playerId
    ) {
      const { left, top } = rootRef.current.getBoundingClientRect();
      console.log(left, top);
      setCurrentIntelligencePosition({
        x: left + 105,
        y: top + 100,
      });
    }
  }, [currentIntelligenceInFrontPlayerId, currentPlayerId]);

  return (
    <Box className={classes.root} ref={rootRef}>
      <CharacterTooltip character={character}>
        <Card className={classes.card} variant="outlined">
          <CardActionArea
            className={classes.actionArea}
            onClick={onActionAreaClicked}
            disabled={!available}
          >
            <CardContent className={classes.playerContent}>
              {(isGameOver ||
                status === PlayerStatus.DEAD ||
                status === PlayerStatus.LOSE) && (
                <img
                  className={classes.camp}
                  src={`/img/${camp}.png`}
                  alt={camp}
                />
              )}
              <Grid
                container
                direction="row"
                alignItems="center"
                justify="space-around"
              >
                <Grid item>
                  <CharacterAvatar character={character} status={status} />
                </Grid>
                <Grid container item spacing={1} xs={6}>
                  <Grid item xs={12}>
                    <Typography className={classes.playerName}>
                      {name}
                    </Typography>
                  </Grid>
                  <Grid item xs={6}>
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
                      label={handcardsNum}
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <Chip
                      size="small"
                      className={classes.chip}
                      avatar={<FiberManualRecord style={{ color: "red" }} />}
                      label={intelligences?.red}
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <Chip
                      size="small"
                      className={classes.chip}
                      avatar={<FiberManualRecord style={{ color: "blue" }} />}
                      label={intelligences?.blue}
                    />
                  </Grid>
                  <Grid item xs={6}>
                    <Chip
                      size="small"
                      className={classes.chip}
                      avatar={<FiberManualRecord style={{ color: "black" }} />}
                      label={intelligences?.black}
                    />
                  </Grid>
                </Grid>
              </Grid>
            </CardContent>
          </CardActionArea>
        </Card>
      </CharacterTooltip>
    </Box>
  );
};

export default Player;

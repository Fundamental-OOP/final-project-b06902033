import { Avatar, Box, makeStyles } from "@material-ui/core";
import {
  Clear as Dead,
  Block as Trapped,
  GpsNotFixed as LockedOn,
} from "@material-ui/icons";
import PlayerStatus from "../enums/PlayerStatus";

const useStyle = makeStyles({
  root: ({ size }) => ({
    width: size,
    height: size,
    position: "relative",
    background: "linear-gradient(#3acfd5, #3a4ed5)",
    boxSizing: "border-box",
    borderRadius: "5%",
  }),
  image: ({ size }) => {
    const margin = Math.round(size * 0.04);
    return {
      position: "absolute",
      top: 0,
      left: 0,
      width: `calc(100% - ${margin * 2}px)`,
      height: `calc(100% - ${margin * 2}px)`,
      objectFit: "cover",
      objectPosition: "center top",
      margin: margin,
      borderRadius: margin,
      backgroundColor: "gray",
    };
  },
  playerStatus: {
    width: "100%",
    height: "100%",
    position: "absolute",
    left: 0,
    top: 0,
    zIndex: 20,
    color: "darkred",
    opacity: "0.8",
    pointerEvents: "none",
  },
  win: {
    width: "60%",
    height: "60%",
    position: "absolute",
    left: "-25%",
    top: "-25%",
    zIndex: 20,
    pointerEvents: "none",
    objectFit: "contain",
    transform: "rotate(-0.5rad)",
  },
});

const CharacterAvatar = ({ mine = false, character, size = 80, status }) => {
  const classes = useStyle({ size });
  const { hidden, name } = character ?? {};

  return (
    <Box className={classes.root}>
      <Avatar
        className={classes.image}
        src={
          hidden && !mine
            ? "/img/character/hidden.png"
            : `/img/character/${name}.jpg`
        }
        alt="角色照片"
        variant="rounded"
      />
      {status === PlayerStatus.LOCKEDON && (
        <LockedOn className={classes.playerStatus} />
      )}
      {status === PlayerStatus.TRAPPED && (
        <Trapped className={classes.playerStatus} />
      )}
      {status === PlayerStatus.LOSE && (
        <img className={classes.playerStatus} src="/img/lose.png" alt="lost" />
      )}
      {status === PlayerStatus.DEAD && (
        <Dead className={classes.playerStatus} />
      )}
      {status === PlayerStatus.WIN && (
        <img className={classes.win} src="/img/win.png" alt="win" />
      )}
    </Box>
  );
};

export default CharacterAvatar;

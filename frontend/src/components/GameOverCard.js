import { useState, forwardRef } from "react";
import {
  Card,
  CardHeader,
  CardContent,
  CardActions,
  Typography,
  Button,
  makeStyles,
  Divider,
  Grid,
  Fade,
  CardActionArea,
} from "@material-ui/core";
import CharacterAvatar from "./CharacterAvatar";

const useStyle = makeStyles({
  root: {
    // height: 300,
    width: "calc(100% - 700px)",
    backgroundColor: "#242424",
    zIndex: 100,
    borderRadius: 10,
  },
  card: {
    // height: "100%",
    // width: "100%",
    // // margin: 10,
    // "&:hover .MuiCardActionArea-focusHighlight": {
    //   opacity: 0.08,
    // },
    // transition: "box-shadow 0.5s",
  },
  actionArea: {
    display: "block",
  },
  playerName: {
    fontSize: 20,
    overflow: "hidden",
    whiteSpace: "nowrap",
    textOverflow: "ellipsis",
    lineHeight: 1,
  },
  button: {
    fontSize: 20,
  },
  content: {
    padding: "10px 10px",
  },
});

const GameOverCard = ({ winningPlayers, onClickBackToRoom }) => {
  const classes = useStyle();

  return (
    <Fade in={true} timeout={1000}>
      <Card className={classes.root} variant="outlined">
        <CardHeader title="遊戲結束" color="RoyalBlue" />
        <Divider />
        <CardContent className={classes.content}>
          <Typography
            variant="h6"
            style={{ margin: "10px 0px 20px 0px", color: "yellow" }}
          >
            🎖️ 🎖️ 🎖️ 獲勝玩家 🎖️ 🎖️ 🎖️
          </Typography>

          <Grid
            container
            direction="row"
            alignItems="center"
            justify="space-evenly"
          >
            {winningPlayers.map((player) => (
              <Grid
                container
                item
                direction="column"
                alignItems="center"
                justify="space-evenly"
                style={{ width: 100, height: 135 }}
                key={player.name}
              >
                <CharacterAvatar
                  mine={true}
                  character={player.character}
                  size={100}
                />
                <Typography variant="body1">{player.name}</Typography>
              </Grid>
            ))}
          </Grid>
        </CardContent>
        <Divider />
        <CardActions className={classes.actionArea}>
          <Button className={classes.button} onClick={onClickBackToRoom}>
            返回房間
          </Button>
        </CardActions>
      </Card>
    </Fade>
  );
};

export default GameOverCard;

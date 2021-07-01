import { Grid, makeStyles } from "@material-ui/core";
import { useContext } from "react";
import { GameContext } from "../context";
import Player from "./Player";

const useStyle = makeStyles({
  root: ({ place }) => ({
    width: place === "top" ? "100%" : "max-content",
    height: place === "top" ? "max-content" : "100%",
  }),
});

const directionMap = {
  right: "column-reverse",
  top: "row-reverse",
  left: "column",
};

const layoutMap = {
  2: [1, 0, 1],
  3: [1, 1, 1],
  4: [1, 2, 1],
  5: [1, 3, 1],
  6: [2, 2, 2],
  7: [2, 3, 2],
  8: [2, 4, 2],
};

/**
 * @param {{place: ("top"|"left"|"right"), players:Array}}
 */

const PlayersLayout = ({ place, players, ...others }) => {
  const classes = useStyle({ place });
  const { currentPlayerId } = useContext(GameContext);

  const getDisplayPlayers = () => {
    if (!players.length) {
      return [];
    }
    const layout = layoutMap[players.length];
    switch (place) {
      case "right":
        return players.slice(0, layout[0]);
      case "top":
        return players.slice(layout[0], layout[0] + layout[1]);
      case "left":
        return players.slice(players.length - layout[2], players.length);

      default:
    }
  };

  return (
    <Grid
      item
      container
      className={classes.root}
      id={`${place}-players-grid`}
      direction={directionMap[place]}
      alignItems="center"
      justify="space-evenly"
      {...others}
    >
      {getDisplayPlayers().map((player, index) => {
        return <Player player={player} key={index} />;
      })}
    </Grid>
  );
};

export default PlayersLayout;

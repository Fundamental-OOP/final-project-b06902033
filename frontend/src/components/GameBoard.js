import { Box, makeStyles } from "@material-ui/core";
import GameCard from "./GameCard";
import ChatBox from "../components/ChatBox";

const useStyle = makeStyles({
  root: {
    height: "350px",
    width: "calc(100% - 700px)",
    position: "relative",
  },
});

const GameBoard = ({ cardsOnBoard }) => {
  const classes = useStyle();
  return (
    <Box className={classes.root}>
      <Box>
        {cardsOnBoard.map((card, index) => (
          <GameCard
            gameCard={card}
            key={"cardOnBoard" + index}
            className={classes.root}
            style={{
              position: "absolute",
              top: "50%",
              left: "50%",
              zIndex: index + 10,
              // transformOrigin: "center bottom",
              transform: `translate(${
                -50 + (index - (cardsOnBoard.length - 1) / 2) * 20
              }%, -50%) rotate(${
                (index - (cardsOnBoard.length - 1) / 2) / 10
              }rad)`,
            }}
            unclickable={true}
          />
        ))}
      </Box>
    </Box>
  );
};

export default GameBoard;

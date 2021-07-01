import { Card, CardContent, makeStyles } from "@material-ui/core";
import { useContext, useEffect, useRef, useState } from "react";
import { GameContext } from "../context";
import GameCardContent from "./GameCardContent";

const useStyle = makeStyles({
  root: ({ isPassing, x, y }) => ({
    width: "max-content",
    height: "max-content",
    left: x,
    top: y,
    backgroundColor: "#202020",
    position: "absolute",
    zIndex: 50,
    pointerEvents: "none",
    opacity: isPassing ? 1 : 0,
    transform: `translate(-50%, -50%) rotate(0.2rad)`,
    transition: isPassing ? "top 1s, left 1s, opacity 1s" : "opacity 1s",
  }),
});

/**
 *
 * @param {{isPassing: Boolean}}
 */
const Intelligence = ({ isPassing }) => {
  const {
    isDecodeUsed,
    currentIntelligence,
    currentIntelligencePosition,
    currentIntelligenceInFrontPlayerId,
  } = useContext(GameContext);

  const classes = useStyle({
    isPassing,
    x: currentIntelligencePosition.x,
    y: currentIntelligencePosition.y,
  });
  const { name, color, type } = currentIntelligence ?? {};

  const [isOnReceive, setIsOnReceive] = useState(false);

  useEffect(() => {
    if (!currentIntelligenceInFrontPlayerId) {
      setIsOnReceive(true);
      setTimeout(() => setIsOnReceive(false), 1000);
    }
  }, [currentIntelligenceInFrontPlayerId]);

  return (
    <Card className={classes.root} variant="outlined">
      <GameCardContent
        name={name}
        color={color}
        type={type}
        scale={0.5}
        cardBack={!isDecodeUsed && type !== "文本" && !isOnReceive}
      />
    </Card>
  );
};

export default Intelligence;

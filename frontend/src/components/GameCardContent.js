import { CardContent, makeStyles, Typography } from "@material-ui/core";

const useStyle = makeStyles({
  content: ({ scale }) => ({
    height: 175 * scale,
    width: 120 * scale,
    position: "relative",
    padding: 0,
    margin: 10 * scale,
    boxSizing: "border-box",
  }),
  cardName: ({ scale }) => ({
    fontSize: 22 * scale,
    fontWeight: 600,
    position: "absolute",
    top: "1.7%",
    left: "5%",
    lineHeight: 1,
  }),
  cardBackground: {
    width: "100%",
    position: "absolute",
    top: 0,
    left: 0,
    objectFit: "contain",
  },
  cardBack: ({ cardBack }) => ({
    width: "100%",
    position: "absolute",
    top: 0,
    left: 0,
    objectFit: "contain",
    zIndex: 10,
    visibility: cardBack ? "visible" : "hidden",
  }),
  cardType: {
    width: "100%",
    position: "absolute",
    top: 0,
    left: 0,
    objectFit: "contain",
  },
  cardImg: {
    width: "100%",
    position: "absolute",
    top: "17.5%",
    left: 0,
    objectFit: "contain",
  },
});

const GameCardContent = ({
  color,
  name,
  type,
  scale = 1,
  cardBack = false,
}) => {
  const classes = useStyle({ scale, cardBack });

  return (
    <CardContent className={classes.content}>
      <img
        className={classes.cardImg}
        src={`/img/intelligence/${name}.png`}
        alt={name}
      />
      <img
        className={classes.cardBackground}
        src={`/img/intelligence/${color}.png`}
        alt={color}
      />
      <img
        className={classes.cardType}
        src={`/img/intelligence/${type}.png`}
        alt={type}
      />
      <img
        className={classes.cardBack}
        src="/img/intelligence/卡背.png"
        alt="卡背"
      />
      <Typography className={classes.cardName}>{name}</Typography>
    </CardContent>
  );
};

export default GameCardContent;

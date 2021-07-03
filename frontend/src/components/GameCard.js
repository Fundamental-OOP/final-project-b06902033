import {
  Card,
  CardActionArea,
  CardContent,
  Divider,
  makeStyles,
  Tooltip,
  Typography,
} from "@material-ui/core";
import GameCardContent from "./GameCardContent";

const useStyle = makeStyles({
  root: ({ unavailable }) => ({
    width: "max-content",
    height: "max-content",
    "&:hover .MuiCardActionArea-focusHighlight": {
      opacity: unavailable ? 0.5 : 0.08,
      backgroundColor: unavailable ? "black" : "currentcolor",
    },
    "& .MuiCardActionArea-focusHighlight": {
      opacity: unavailable ? 0.5 : 0,
      backgroundColor: unavailable ? "black" : "currentcolor",
    },
    backgroundColor: "#202020",
    alignSelf: "center",
  }),

  actionArea: {
    height: "100%",
    cursor: ({ clickable }) => (clickable ? "pointer" : "default"),
  },
  descriptionBox: {
    width: "90%",
    top: "68%",
    left: "5%",
    height: 50,
    position: "absolute",
    fontSize: 1,
    // textOverflow: "ellipsis",
  },
  timingDescription: {
    width: 120,
    height: "20px",
    fontSize: 6,
    overflow: "hidden",
    textOverflow: "ellipsis",
    whiteSpace: "normal",
    WebkitLineClamp: 2,
    display: "-webkit-box",
  },
  effectDescription: {
    height: "20px",
    fontSize: 6,
    overflow: "hidden",
    textOverflow: "ellipsis",
    whiteSpace: "nowrap",
    // padding: "0px 10px",
  },
  tooltip: {
    maxWidth: "none",
    width: 300,
  },
});

/**
 * @param {{gameCard: {color: ("red"|"blue"|"black"), type: ("文本"|"密電"|"直達"), name: String,
 * description: String}, scale: Number, unclickable: Boolean, style: React.CSSProperties }}
 */
const GameCard = ({
  gameCard,
  onClick,
  unclickable = false,
  unavailable = false,
  style,
  scale = 1,
}) => {
  const clickable = !unclickable && !unavailable;
  const classes = useStyle({ clickable, unavailable });
  const { id, color, type, name, effectDescription, timingDescription } =
    gameCard ?? {};

  const getTooltipContent = () => {
    return (
      <div style={{ padding: 10 }}>
        <Typography variant="h6" style={{ textAlign: "center" }}>
          {name}
        </Typography>

        {timingDescription && (
          <>
            <Typography
              variant="body1"
              style={{ fontWeight: 600, color: "yellow" }}
            >
              使用時機
            </Typography>
            <Typography variant="body2">{timingDescription}</Typography>
          </>
        )}
        {timingDescription && effectDescription && (
          <Divider style={{ margin: "10px 0px" }} />
        )}
        {effectDescription && (
          <>
            <Typography
              variant="body1"
              style={{ fontWeight: 600, color: "yellowgreen" }}
            >
              功能牌效果
            </Typography>
            <Typography variant="body2">
              {effectDescription.split("\\n").map((value, index) => {
                return (
                  <span key={index}>
                    {index !== 0 && <br />}
                    {value}
                  </span>
                );
              })}
            </Typography>
          </>
        )}
      </div>
    );
  };
  const onActionAreaClicked = (e) => {
    if (!clickable) {
      e.preventDefault();
    } else {
      onClick?.(id);
    }
  };

  return (
    <Tooltip
      arrow
      title={getTooltipContent()}
      classes={{ tooltip: classes.tooltip }}
    >
      <Card className={classes.root} style={style} variant="outlined">
        <CardActionArea
          className={classes.actionArea}
          onClick={onActionAreaClicked}
          disabled={!clickable}
        >
          <GameCardContent
            name={name}
            color={color}
            type={type}
            scale={scale}
          />
        </CardActionArea>
      </Card>
    </Tooltip>
  );
};

export default GameCard;

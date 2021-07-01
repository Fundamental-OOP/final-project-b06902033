import { makeStyles, Tooltip, Typography } from "@material-ui/core";

const useStyle = makeStyles({
  tooltip: {
    maxWidth: "none",
    width: 200,
  },
});

const CharacterTooltip = ({ mine = false, character, children }) => {
  const classes = useStyle();
  const { name, mission, gender, hidden } = character ?? {};
  const genderMap = {
    man: "男",
    woman: "女",
    both: "男/女",
  };
  const getTooltipContent = () => {
    return (
      <div style={{ padding: 10 }}>
        <Typography variant="h6" style={{ textAlign: "center" }}>
          {hidden && !mine ? "？？？" : `${name}（${genderMap[gender]}）`}
          {hidden && <Typography variant="body1">-潛伏中-</Typography>}
        </Typography>

        {mission && (
          <>
            <Typography
              variant="body1"
              style={{ fontWeight: 600, color: "yellow" }}
            >
              秘密任務
            </Typography>
            <Typography variant="body2">
              {hidden && !mine ? "？？？？？" : mission}
            </Typography>
          </>
        )}
      </div>
    );
  };
  return (
    <Tooltip
      arrow
      title={getTooltipContent()}
      classes={{ tooltip: classes.tooltip }}
    >
      {children}
    </Tooltip>
  );
};

export default CharacterTooltip;

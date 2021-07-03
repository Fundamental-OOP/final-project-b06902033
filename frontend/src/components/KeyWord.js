const PlayerName = ({ text }) => {
  return <span style={{ color: "orange", fontWeight: 600 }}>{text}</span>;
};

const GameCardName = ({ text }) => {
  return <span style={{ color: "yellow", fontWeight: 600 }}>{text}</span>;
};

const CampName = ({ camp }) => {
  const colorMap = {
    潛伏戰線: "red",
    軍情局: "skyblue",
    打醬油的: "lawngreen",
  };
  return <span style={{ color: colorMap[camp], fontWeight: 600 }}>{camp}</span>;
};

const mapColorToText = {
  red: "紅色",
  blue: "藍色",
  black: "黑色",
};

const IntelligenceColor = ({ color }) => {
  return (
    <span
      style={{
        color: color === "blue" ? "dodgerblue" : color,
        fontWeight: 600,
        backgroundColor: color === "black" ? "white" : undefined,
      }}
    >
      {mapColorToText[color]}
    </span>
  );
};

const IntelligenceType = ({ text }) => {
  return <span style={{ color: "deeppink", fontWeight: 600 }}>{text}</span>;
};

export {
  PlayerName,
  GameCardName,
  CampName,
  IntelligenceColor,
  IntelligenceType,
};

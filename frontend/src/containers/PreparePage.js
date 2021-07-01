import {
  Avatar,
  Box,
  Button,
  Chip,
  Grid,
  makeStyles,
  Snackbar,
  Typography,
} from "@material-ui/core";
import { useContext, useState } from "react";
import { useHistory } from "react-router";
import { useParams } from "react-router-dom";
import { UserContext } from "../context";
import { startGame, leaveRoom } from "../axios";

const useStyle = makeStyles({
  root: {
    height: "100%",
    width: "100%",
    padding: 20,
    boxSizing: "border-box",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "flex-start",
  },
  gridContainer: {
    width: "100%",
    flexGrow: 1,
    padding: "30px 50px",
  },
  gridItem: {
    width: "100%",
    height: "30%",
    position: "relative",
  },
  user: {
    width: "80%",
    height: "70%",
    padding: "0px 20px",
    justifyContent: "left",
    cursor: "default",
    backgroundColor: "#121212a0",
    borderWidth: 3,
    "& span": {
      fontSize: "x-large",
      paddingLeft: 30,
    },
  },
  roomHost: {
    width: "15%",
    objectFit: "cover",
    position: "absolute",
    top: "-5%",
    left: "5%",
    transform: "rotate(-0.5rad)",
  },
  buttonRow: {
    height: "20%",
    width: "100%",
    display: "flex",
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
  },
  button: {
    width: "20%",
    height: "90%",
    padding: 20,
    margin: "0px 20px",
    "& span": {
      fontSize: "xxx-large",
    },
  },
  snackBar: {
    textAlign: "center",
    "& > div": {
      backgroundColor: "#124812b0",
      borderRadius: 10,
      color: "greenyellow",
      fontSize: 16,
      display: "flex",
      flexDirection: "row",
      alignItems: "center",
      justifyContent: "center",
    },
  },
  roomId: {
    margin: 30,
    color: "yellow",
  },
});

const PreparePage = ({ roomId, users }) => {
  const classes = useStyle();
  const history = useHistory();
  const { userId, userName } = useContext(UserContext);
  const [isHintOpen, setIsHintOpen] = useState(false);

  const onClickStartGame = async () => {
    const result = await startGame(roomId);
    if (result.status === "FAILED") {
      alert(result.data);
    }
  };

  const onClickLeaveRoom = async () => {
    const result = await leaveRoom(userId, roomId);
    if (result.status === "FAILED") {
      alert(result.data);
    } else {
      history.push("/");
    }
  };

  const copyToClipboard = (textToCopy) => {
    // navigator clipboard api needs a secure context (https)
    if (navigator.clipboard && window.isSecureContext) {
      // navigator clipboard api method'
      return navigator.clipboard.writeText(textToCopy);
    } else {
      // text area method
      let textArea = document.createElement("textarea");
      textArea.value = textToCopy;
      // make the textarea out of viewport
      textArea.style.position = "fixed";
      textArea.style.left = "-999999px";
      textArea.style.top = "-999999px";
      document.body.appendChild(textArea);
      textArea.focus();
      textArea.select();
      return new Promise((res, rej) => {
        // here the magic happens
        document.execCommand("copy") ? res() : rej();
        textArea.remove();
      });
    }
  };

  return (
    <Box className={classes.root}>
      <Typography
        className={classes.roomId}
        variant="h4"
        onClick={async () => {
          setIsHintOpen(true);
          await copyToClipboard(roomId);
        }}
      >
        房間代碼：{roomId}
      </Typography>
      <Box className={classes.buttonRow}>
        {users.findIndex((user) => user.name === userName) === 0 && (
          <Button
            className={classes.button}
            size="large"
            onClick={onClickStartGame}
            disabled={users.length < 3}
          >
            開始遊戲
          </Button>
        )}
        <Button
          className={classes.button}
          size="large"
          onClick={onClickLeaveRoom}
        >
          離開房間
        </Button>
      </Box>
      <Grid
        container
        className={classes.gridContainer}
        xs={12}
        direction="row"
        justify="center"
      >
        {users.map((user, index) => (
          <Grid
            container
            item
            className={classes.gridItem}
            xs={4}
            direction="row"
            alignItems="center"
            justify="center"
            key={index}
          >
            <Chip
              className={classes.user}
              avatar={<Avatar>{user.name[0]}</Avatar>}
              label={user.name}
              variant="outlined"
              color={user.id === userId ? "primary" : "default"}
              clickable
            />
            {index === 0 && (
              <img className={classes.roomHost} src="/img/win.png" alt="host" />
            )}
          </Grid>
        ))}
      </Grid>
      <Snackbar
        className={classes.snackBar}
        open={isHintOpen}
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        message="房間代碼已複製至剪貼簿"
        autoHideDuration={1000}
        onClose={() => setIsHintOpen(false)}
      />
    </Box>
  );
};
export default PreparePage;

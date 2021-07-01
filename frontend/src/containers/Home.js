import {
  Box,
  Button,
  Collapse,
  Divider,
  Grid,
  makeStyles,
  Paper,
  TextField,
  Typography,
} from "@material-ui/core";
import { useContext, useEffect, useState } from "react";
import { useHistory } from "react-router";
import AlertDialog from "../components/dialogs/AlertDialog";
import { UserContext, WSContext } from "../context";
import { createRoom, joinRoom } from "../axios";

const useStyle = makeStyles({
  root: {
    height: "100%",
    width: "100%",
    padding: "0px 30px",
  },
  logo: {
    width: "70%",
  },
  paper: {
    width: "50%",
    backgroundColor: "rgba(0, 0, 0, 0.3)",
    display: "flex",
    padding: 20,
    flexDirection: "column",
    alignItems: "center",
  },
  input: {
    width: "80%",
    marginTop: 20,
    marginBottom: 10,
  },
  button: {
    width: "90%",
    height: "4rem",
    padding: "15px",
    margin: "5px",
    "& span": {
      fontSize: "x-large",
    },
  },
  divider: {
    width: "100%",
    height: 1,
    border: 0,
    backgroundColor: "rgba(255, 255, 255, 0.5)",
    margin: "30px 0px",
  },
  collapse: {
    width: "100%",
  },
});

const HomePageState = {
  BEFORE_ENTER: 0,
  AFTER_ENTER: 1,
  JOIN_ROOM: 2,
};

const Home = () => {
  const classes = useStyle();
  const [roomId, setRoomId] = useState("");
  const { userId, userName, setUserName } = useContext(UserContext);
  const { ws } = useContext(WSContext);
  const history = useHistory();
  const [isAlertDialogOpen, setIsAlertDialogOpen] = useState(false);
  const [homePageState, setHomePageState] = useState(
    HomePageState.BEFORE_ENTER
  );

  useEffect(() => {
    if (userId) {
      setHomePageState(HomePageState.AFTER_ENTER);
    }
  }, [userId]);

  const onClickCreateRoom = async () => {
    const roomId = await createRoom(userId);
    if (!roomId) {
      alert("No network connection.");
    } else {
      history.push(`/room/${roomId}`);
    }
  };

  const onClickJoinRoom = async () => {
    const result = await joinRoom(userId, roomId);
    if (result.status === "FAILED") {
      setRoomId("");
      alert(result.data);
    } else {
      history.push(`/room/${result.data}`);
    }
  };

  return (
    <Grid
      className={classes.root}
      container
      direction="row"
      alignItems="center"
      justify="center"
    >
      <Grid
        item
        container
        xs={6}
        direction="row"
        alignItems="center"
        justify="center"
      >
        <img className={classes.logo} src="img/logo.png" alt="風聲" />
      </Grid>
      <Grid
        item
        container
        xs={6}
        direction="column"
        alignItems="center"
        justify="center"
      >
        <Paper className={classes.paper} elevation={0}>
          <Collapse
            className={classes.collapse}
            in={homePageState === HomePageState.BEFORE_ENTER}
          >
            <TextField
              label="暱稱"
              variant="outlined"
              color="primary"
              className={classes.input}
              value={userName}
              onChange={(e) => {
                setUserName(e.target.value);
              }}
            />
            {/* <hr className={classes.divider} /> */}
            <Button
              className={classes.button}
              onClick={() => {
                ws.send(`start-${userName}`);
                setHomePageState(HomePageState.AFTER_ENTER);
              }}
              disabled={!userName}
            >
              開始
            </Button>
          </Collapse>
          <Collapse
            className={classes.collapse}
            in={homePageState === HomePageState.AFTER_ENTER}
          >
            <Button className={classes.button} onClick={onClickCreateRoom}>
              建立房間
            </Button>

            <Button
              className={classes.button}
              onClick={() => setHomePageState(HomePageState.JOIN_ROOM)}
            >
              加入房間
            </Button>
          </Collapse>
          <Collapse
            className={classes.collapse}
            in={homePageState === HomePageState.JOIN_ROOM}
          >
            <TextField
              label="房間代碼"
              variant="outlined"
              color="primary"
              className={classes.input}
              value={roomId}
              onChange={(e) => setRoomId(e.target.value)}
            />
            <Button
              className={classes.button}
              onClick={onClickJoinRoom}
              disabled={!roomId}
            >
              確認
            </Button>

            <Button
              className={classes.button}
              onClick={() => setHomePageState(HomePageState.AFTER_ENTER)}
            >
              返回
            </Button>
          </Collapse>
        </Paper>
      </Grid>
      <AlertDialog
        title="Error"
        content="Failed to join the room"
        open={isAlertDialogOpen}
        onClose={() => setIsAlertDialogOpen(false)}
      />
    </Grid>
  );
};

export default Home;

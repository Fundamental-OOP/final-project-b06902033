import {
  List,
  ListSubheader,
  ListItem,
  ListItemIcon,
  makeStyles,
  Collapse,
  Box,
  Typography,
} from "@material-ui/core";
import LabelImportantIcon from "@material-ui/icons/LabelImportant";
import { useRef, useEffect } from "react";

const useStyle = makeStyles({
  root: {
    width: 340,
    position: "relative",
    height: "100%",
    paddingTop: 25,
    paddingBottom: 20,
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "space-evenly",
    boxSizing: "border-box",
  },
  title: {
    position: "absolute",
    top: 15,
    backgroundColor: "#363636",
    zIndex: 10,
    padding: "0px 10px",
    lineHeight: 1,
  },
  box: {
    width: "100%",
    padding: "15px 0px 10px 0px",
    height: "100%",
    border: "#A0A0A0 2px solid",
    borderRadius: 10,
    boxSizing: "border-box",
  },
  list: {
    height: "100%",
    overflow: "overlay",
    padding: 0,
    "& .MuiListItemIcon-root": {
      width: "30px",
      minWidth: "30px",
    },
  },
  message: {
    "&::before": {
      content: '"➤"',
      marginLeft: -20,
      marginRight: 10,
    },
  },
});

const ChatBox = ({ messages }) => {
  const classes = useStyle();
  const msgEndRef = useRef();

  useEffect(() => {
    if (msgEndRef.current) {
      msgEndRef.current.scrollIntoView();
    }
  });
  return (
    <Box className={classes.root}>
      <Typography className={classes.title} variant="h6">
        遊戲訊息
      </Typography>
      <Box className={classes.box}>
        <List className={classes.list}>
          {messages?.map((message, index) => (
            <ListItem key={index} style={{ paddingLeft: 40 }}>
              {/* <ListItemIcon>
                <LabelImportantIcon />
              </ListItemIcon> */}
              <span className={classes.message}>{message.body}</span>
            </ListItem>
          ))}
          <div ref={msgEndRef} />
        </List>
      </Box>
    </Box>
  );
};
export default ChatBox;

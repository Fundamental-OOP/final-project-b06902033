import "./App.css";
import { BrowserRouter, Route } from "react-router-dom";
import Home from "./containers/Home";
import Room from "./containers/Room";
import { ThemeProvider } from "@material-ui/styles";
import { createMuiTheme } from "@material-ui/core";
import { UserContext, WSContext } from "./context";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import WSEventTypes from "./enums/WSEventTypes";

function App() {
  const [userId, setUserId] = useState("");
  const [userName, setUserName] = useState("");
  const ws = useRef();
  const [wsData, setWsData] = useState({});
  useEffect(() => {
    ws.current = new WebSocket("ws://140.112.30.33:56789/websocket");

    ws.current.onopen = () => {
      console.log("WebSocket connected.");
    };

    ws.current.onmessage = (event) => {
      const data = JSON.parse(event?.data ?? {});
      console.log(data.type);
      console.log(data.payload);
      if (data?.type === WSEventTypes.INFORM_USER_ID) {
        setUserId(data.payload.userId);
        // sessionStorage.setItem("userId", data.payload.userId);
      } else {
        setWsData(data);
      }
    };

    ws.current.onclose = () => {
      console.log("WebSocket closed.");
    };

    return () => {
      ws.current.close();
    };
  }, []);

  useEffect(() => {
    // if (sessionStorage.getItem("userId")) {
    //   setUserId(sessionStorage.getItem("userId"));
    // }
  }, []);

  useEffect(() => {
    if (sessionStorage.getItem("name")) {
      setUserName(sessionStorage.getItem("name"));
    }
  }, []);

  useEffect(() => {
    if ((sessionStorage.getItem("name") ?? "") !== userName) {
      sessionStorage.setItem("name", userName);
    } else if (!userName && sessionStorage.getItem("name")) {
      setUserName(sessionStorage.getItem("name"));
    }
  }, [userName]);

  return (
    <WSContext.Provider value={{ ws: ws?.current, wsData }}>
      <UserContext.Provider value={{ userId, userName, setUserName }}>
        <ThemeProvider
          theme={createMuiTheme({
            palette: {
              type: "dark",
              primary: {
                light: "#a6d4fa",
                main: "#90caf9",
                dark: "#648dae",
              },
              secondary: {
                light: "#f6a5c0",
                main: "#f48fb1",
                dark: "#aa647b",
              },
            },
            typography: {
              fontFamily: [
                "serif",
                "-apple-system",
                "BlinkMacSystemFont",
                '"Segoe UI"',
                '"Roboto"',
                '"Oxygen"',
                '"Ubuntu"',
                '"Cantarell"',
                '"Fira Sans"',
                '"Droid Sans"',
                '"Helvetica Neue"',
                "sans-serif",
              ].join(","),
            },
          })}
        >
          <div
            className="App"
            style={{
              backgroundImage: 'url("img/bg.jpeg")',
              backgroundSize: "cover",
              backgroundRepeat: "no-repeat",
            }}
          >
            <BrowserRouter>
              <Route exact path="/" component={Home} />
              <Route path="/room/:roomId" component={Room} />
            </BrowserRouter>
          </div>
        </ThemeProvider>
      </UserContext.Provider>
    </WSContext.Provider>
  );
}

export default App;

import axios from "axios";

const instance = axios.create({ baseURL: "http://140.112.30.33:56789" });

const createRoom = async (userId) => {
  const roomId = await instance
    .post(`/api/user/${userId}/room/`)
    .then((res) => {
      console.log(res);
      return res.data.roomId;
    })
    .catch((err) => {
      console.log(err);
    });
  return roomId;
};

const joinRoom = async (userId, roomId) => {
  const result = await instance
    .post(`/api/user/${userId}/room/${roomId}`)
    .then((res) => {
      return {
        status: "SUCCESS",
        data: res.data.roomId,
      };
    })
    .catch((err) => {
      return {
        status: "FAILED",
        data: err.response.data,
      };
    });
  return result;
};

const leaveRoom = async (userId, roomId) => {
  const result = await instance
    .delete(`/api/user/${userId}/room/${roomId}`)
    .then((res) => {
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      return {
        status: "FAILED",
        data: err.response.data,
      };
    });
  return result;
};

const playerReady = async (userId, roomId, playerId) => {
  const result = await instance
    .post(`/api/user/${userId}/room/${roomId}/game/player/${playerId}/ready`)
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const startGame = async (roomId) => {
  const result = await instance
    .post(`/api/room/${roomId}/game/start`)
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const playGameCard = async (roomId, playerId, cardId) => {
  const result = await instance
    .post(`/api/room/${roomId}/game/player/${playerId}/game-card/${cardId}`)
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const selectTarget = async (roomId, playerId, targetPlayerId) => {
  const result = await instance
    .post(
      `/api/room/${roomId}/game/player/${playerId}/target/${targetPlayerId}`
    )
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const skip = async (roomId, playerId) => {
  const result = await instance
    .post(`/api/room/${roomId}/game/player/${playerId}/skip`)
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const sendIntelligence = async (roomId, playerId, cardId) => {
  const result = await instance
    .post(`/api/room/${roomId}/game/player/${playerId}/intelligence/${cardId}`)
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const receiveIntelligence = async (roomId, playerId) => {
  const result = await instance
    .post(`/api/room/${roomId}/game/player/${playerId}/intelligence`)
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
        data: res.data.color,
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const chooseProveOption = async (roomId, playerId, choosedOption) => {
  const result = await instance
    .post(
      `/api/room/${roomId}/game/player/${playerId}/provechoose/${choosedOption}`
    )
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

const chooseDiscardTargetHandcard = async (
  roomId,
  playerId,
  gameCardTargetId
) => {
  const result = await instance
    .post(
      `/api/room/${roomId}/game/player/${playerId}/gameCard/target/${gameCardTargetId}`
    )
    .then((res) => {
      console.log(res);
      return {
        status: "SUCCESS",
      };
    })
    .catch((err) => {
      console.log(err.response);
      return {
        status: "FAILED",
        data: err.response?.data,
      };
    });
  return result;
};

export {
  createRoom,
  joinRoom,
  leaveRoom,
  playerReady,
  startGame,
  playGameCard,
  selectTarget,
  skip,
  sendIntelligence,
  receiveIntelligence,
  chooseProveOption,
  chooseDiscardTargetHandcard,
};

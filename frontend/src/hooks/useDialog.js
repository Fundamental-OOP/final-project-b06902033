import { useCallback, useEffect, useMemo, useState } from "react";
import ReactDOM from "react-dom";
import DualSelectionDialog from "../components/dialogs/DualSelectionDialog";

/**
 *
 * @returns {[showDialog, Dialog]}
 */
const useDialog = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [leftButtonText, setLeftButtonText] = useState("");
  const [isLeftButtonDisabled, setIsLeftButtonDisabled] = useState(false);
  const [leftButtonAction, setLeftButtonAction] = useState(() => {});
  const [rightButtonText, setRightButtonText] = useState("");
  const [isRightButtonDisabled, setIsRightButtonDisabled] = useState(false);
  const [rightButtonAction, setRightButtonAction] = useState(() => {});
  const [isOpen, setIsOpen] = useState(false);

  const Dialog = useMemo(
    () => (
      <DualSelectionDialog
        title={title}
        content={content}
        leftButtonText={leftButtonText}
        isLeftButtonDisabled={isLeftButtonDisabled}
        onClickLeftButton={() => {
          leftButtonAction();
          setIsOpen(false);
        }}
        rightButtonText={rightButtonText}
        isRightButtonDisabled={isRightButtonDisabled}
        onClickRightButton={() => {
          rightButtonAction();
          setIsOpen(false);
        }}
        open={isOpen}
      />
    ),
    [
      title,
      content,
      leftButtonText,
      leftButtonAction,
      isLeftButtonDisabled,
      rightButtonText,
      isRightButtonDisabled,
      rightButtonAction,
      isOpen,
    ]
  );

  const showDialog = ({
    title = "",
    content = "",
    negButtonText = "否",
    isNegButtonDisabled = false,
    negButtonAction = () => {},
    posButtonText = "是",
    isPosButtonDisabled = false,
    posButtonAction = () => {},
  }) => {
    console.log("dialog shown");
    setTitle(title ?? "");
    setContent(content ?? "");
    setLeftButtonText(negButtonText ?? "否");
    setLeftButtonAction(() => negButtonAction ?? (() => () => {}));
    setIsLeftButtonDisabled(isNegButtonDisabled ?? false);
    setRightButtonText(posButtonText ?? "是");
    setRightButtonAction(() => posButtonAction ?? (() => () => {}));
    setIsRightButtonDisabled(isPosButtonDisabled ?? false);
    setIsOpen(true);
  };

  return [showDialog, Dialog];
};

export default useDialog;

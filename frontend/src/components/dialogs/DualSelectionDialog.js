import {
  Dialog,
  DialogActions,
  DialogTitle,
  DialogContent,
  Button,
  makeStyles,
} from "@material-ui/core";

const useStyle = makeStyles({
  content: {
    textAlign: "left",
    display: "flex",
    flexDirection: "column",
    alignItems: "flex-start",
    justifyContent: "center",
  },
});

/**
 *
 * @param {{title: String, content: String, leftButtonText: String, rightButtonText: String,
 *  open: Boolean, onClickLeftButton: ()=>{}, onClickRightButton: ()=>{},
 * isLeftButtonDisabled: Boolean, isRightButtonDisabled: Boolean
 * }}
 */
const DualSelectionDialog = ({
  title,
  content,
  open,
  leftButtonText = "否",
  rightButtonText = "是",
  onClickLeftButton,
  onClickRightButton,
  isLeftButtonDisabled = false,
  isRightButtonDisabled = false,
}) => {
  const classes = useStyle();
  return (
    <Dialog fullWidth variant="outlined" open={open} maxWidth="xs">
      {title && <DialogTitle>{title}</DialogTitle>}
      {content && (
        <DialogContent className={classes.content}>{content}</DialogContent>
      )}
      <DialogActions>
        <Button
          color="primary"
          variant={leftButtonText === "否" ? "text" : "outlined"}
          onClick={onClickLeftButton}
          disabled={isLeftButtonDisabled}
        >
          {leftButtonText}
        </Button>
        <Button
          color="primary"
          variant={rightButtonText === "是" ? "contained" : "outlined"}
          onClick={onClickRightButton}
          disabled={isRightButtonDisabled}
        >
          {rightButtonText}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default DualSelectionDialog;

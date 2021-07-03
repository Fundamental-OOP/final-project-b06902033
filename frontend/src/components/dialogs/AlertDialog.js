import {
  Dialog,
  DialogContent,
  DialogActions,
  DialogTitle,
  Button,
} from "@material-ui/core";

/**
 *
 * @param {{title: String, content: String, open: Boolean, onClose: ()=>{}}}
 */
const AlertDialog = ({ title, content, open, onClose }) => {
  return (
    <Dialog
      fullWidth
      variant="outlined"
      open={open}
      onClose={onClose}
      maxWidth="xs"
    >
      {title && <DialogTitle>{title}</DialogTitle>}
      {content && <DialogContent>{content}</DialogContent>}
      <DialogActions>
        <Button color="primary" onClick={() => onClose()}>
          OK
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default AlertDialog;

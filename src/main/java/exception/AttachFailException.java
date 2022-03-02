package exception;

public class AttachFailException extends Exception{
    public AttachFailException() {
        super("Sorry, an error occured when uploading.");
    }
}

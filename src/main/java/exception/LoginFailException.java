package exception;

public class LoginFailException extends Exception{
	public LoginFailException() {
		super("Incorrect username or password");
	}
}

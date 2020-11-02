package game;

public class MessageLog {
	private String message;

	public void MessageLog() {
		setMessage("");
	}
	public String getMessage() {
		if(message==null) {
			message += "\n";
		}
		return message;
	}
	public void setMessage(String message) {
		this.message += "<br />" + message;
	}
}

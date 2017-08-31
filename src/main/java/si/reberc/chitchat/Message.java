package si.reberc.chitchat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	boolean global;
	String recipient;
	String sender;
	String text;
	Date sent_at;

	public Message() {
	};

	public Message(boolean global, String text) {
		this.global = global;
		this.text = text;
	}

	public Message(boolean global, String recipient, String text) {
		this.global = global;
		this.recipient = recipient;
		this.text = text;
	}

	public Message(boolean global, String recipient, String sender, String text, Date sent_at) {
		this.global = global;
		this.recipient = recipient;
		this.sender = sender;
		this.text = text;
		this.sent_at = sent_at;
	}

	@Override
	public String toString() {
		return "[global=" + global + ", recipient=" + recipient + ", sender=" + sender + ", text=" + text + ", sent_at="
				+ sent_at + "]";
	}

	@JsonProperty("global")
	public boolean isGlobal() {
		return global;
	}

	@JsonProperty("recipient")
	public String getRecipient() {
		return recipient;
	}

	@JsonProperty("sender")
	public String getSender() {
		return sender;
	}

	@JsonProperty("text")
	public String getText() {
		return text;
	}

	@JsonProperty("sent_at")
	public Date getSent_at() {
		return sent_at;
	}
}

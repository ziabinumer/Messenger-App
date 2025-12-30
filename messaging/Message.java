package messaging;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sender;
    private String recipient; // null or empty = broadcast
    private MessageType type;
    private String content;
    private LocalDateTime timestamp;

    public Message(String sender, String recipient, MessageType type, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public String getSender() { return sender; }
    public String getRecipient() { return recipient; }
    public MessageType getType() { return type; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + sender + ": " + content;
    }
}

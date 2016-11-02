package net;

/**
 * Created by NewNet on 2016/9/19.
 */
public class HomeFragmentRequest {

    private MessageBean message;
    private HeadBean head;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public HeadBean getHead() {
        return head;
    }

    public void setHead(HeadBean head) {
        this.head = head;
    }

    public static class MessageBean {
    }

    public static class HeadBean {
    }
}

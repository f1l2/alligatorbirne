package common.messaging;

import javax.jms.MessageListener;

public interface MessageHandler<E> {

    public static final String MESSAGE_TOPIC = "f1l2";

    public static final String PROPERTY = "device_data";

    public void start(common.data.Connection mb);

    public void stop();

    public void produce(E data);

    public void consume(String selector, MessageListener messageListener);

}

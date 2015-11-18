package monitoring.webapp.ui.messaging.view;

import java.util.EventListener;

import org.apache.activemq.broker.jmx.TopicViewMBean;

import monitoring.webapp.ui.view.AbstractView;

public interface MessagingView extends AbstractView {
    public static final String VIEW_NAME = "/messaging";

    public void setInformation(TopicViewMBean topicView);

    public interface MessagingViewListener extends EventListener {

        public void get(String destination);
    }

    public void addMessagingViewListener(MessagingViewListener listener);

}

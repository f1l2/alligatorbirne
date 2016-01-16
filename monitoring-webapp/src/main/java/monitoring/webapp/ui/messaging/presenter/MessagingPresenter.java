package monitoring.webapp.ui.messaging.presenter;

import javax.jms.JMSException;
import javax.management.MalformedObjectNameException;

import org.apache.activemq.broker.jmx.TopicViewMBean;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.messaging.view.MessagingView;
import monitoring.webapp.ui.messaging.view.MessagingView.MessagingViewListener;
import monitoring.webapp.ui.presenter.AbstractPresenter;

public class MessagingPresenter extends AbstractPresenter<MonitoringService, MessagingView>implements MessagingViewListener {

    private long epId;

    public MessagingPresenter(MonitoringService monitoringService) {
        super(monitoringService);
    }

    @Override
    protected void init(MonitoringService model, MessagingView userInterface) {

        userInterface.initBreadcrumb().add("Messaging");
        userInterface.addMessagingViewListener(this);
    }

    @Override
    protected void initUserInterface(MessagingView userInterface) {
    }

    @Override
    protected void initModel(MonitoringService model) {
    }

    public long getEpId() {
        return epId;
    }

    public void setEpId(long epId) {
        this.epId = epId;
    }

    @Override
    public void get(String destination) {
        try {

            TopicViewMBean proxyToTopic = getMonitoringService().getProxyToTopic(destination);

            getUserInterface().setInformation(proxyToTopic);

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}

package monitoring.webapp.ui.messaging.view;

import org.apache.activemq.broker.jmx.TopicViewMBean;
import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import common.messaging.MessageHandler;
import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.event.EventListenerManager;
import monitoring.webapp.ui.i18n.Messages.MESSAGE;
import monitoring.webapp.ui.messaging.presenter.MessagingPresenter;
import monitoring.webapp.ui.navigator.MessagingNavigator;
import monitoring.webapp.ui.utility.UiUtils;
import monitoring.webapp.ui.utility.UiUtils.ICON;
import monitoring.webapp.ui.utility.UiUtils.STYLE;
import monitoring.webapp.ui.view.AbstractViewImpl;
import ru.xpoft.vaadin.VaadinView;

@SuppressWarnings("serial")
@org.springframework.stereotype.Component
@Scope("prototype")
@VaadinView(MessagingView.VIEW_NAME)
public class MessagingViewImpl extends AbstractViewImpl<MessagingNavigator>implements MessagingView {

    private EventListenerManager<MessagingViewListener> eventListenerManager = new EventListenerManager<MessagingViewListener>();

    private TextField consumerCount, producerCount, dequeueCount, enqueueCount;

    private FormLayout formLayout;

    @Override
    protected void init(MonitoringService monitoringService) {
    }

    @Override
    protected void init(VerticalLayout layout) {

        TextField txtFieldDestination = UiUtils.newTextField(MESSAGE.DESTINATION, STYLE.TEXT_FIELD_LARGE);
        txtFieldDestination.setValue(MessageHandler.MESSAGE_TOPIC);

        Button getButton = UiUtils.newButton(ICON.EYE);
        getButton.addClickListener(item -> {
            eventListenerManager.fireEvent(event -> event.get(txtFieldDestination.getValue()));
        });

        FormLayout hLayout = new FormLayout();
        hLayout.setSpacing(true);
        hLayout.addComponent(txtFieldDestination);
        hLayout.addComponent(getButton);

        producerCount = new TextField("# Producer");
        producerCount.setEnabled(false);
        consumerCount = new TextField("# Consumer");
        consumerCount.setEnabled(false);
        enqueueCount = new TextField("# Enqueued");
        enqueueCount.setEnabled(false);
        dequeueCount = new TextField("# Dequeue");
        dequeueCount.setEnabled(false);
        formLayout = new FormLayout();
        formLayout.setVisible(false);
        formLayout.addComponent(producerCount);
        formLayout.addComponent(consumerCount);
        formLayout.addComponent(enqueueCount);
        formLayout.addComponent(dequeueCount);

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setMargin(true);
        vLayout.setSpacing(true);
        vLayout.addComponent(hLayout);
        vLayout.addComponent(formLayout);

        layout.addComponent(vLayout);
    }

    @Override
    protected void enter(MessagingNavigator navigator, MonitoringService monitoringService) {

        MessagingPresenter messagingPresenter = new MessagingPresenter(monitoringService);
        messagingPresenter.setModel(monitoringService);
        messagingPresenter.setUserInterface(this);

    }

    @Override
    protected MessagingNavigator createInstance(String parameters) {
        return new MessagingNavigator(parameters);
    }

    @Override
    public void setInformation(TopicViewMBean topicView) {

        formLayout.setVisible(true);

        try {
            consumerCount.setValue(Long.toString(topicView.getConsumerCount()));

            producerCount.setValue(Long.toString(topicView.getProducerCount()));

            enqueueCount.setValue(Long.toString(topicView.getEnqueueCount()));

            dequeueCount.setValue(Long.toString(topicView.getDequeueCount()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMessagingViewListener(MessagingViewListener listener) {
        eventListenerManager.addEventListener(listener);
    }

}

package monitoring.webapp.ui;

import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.event.ListenerMethod.MethodException;
import com.vaadin.server.ErrorEvent;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.ServerRpcManager.RpcInvocationException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("serial")
public class MonitoringWebAppErrorHandler implements ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringWebAppErrorHandler.class);

    @Override
    public void error(final ErrorEvent errorEvent) {
        Throwable throwable = errorEvent.getThrowable();
        if (throwable instanceof SocketException) {
            LOGGER.debug("SocketException in CommunicationManager.  Most likely client (browser) closed socket.", throwable);
        } else {
            throwable = findRelevantThrowable(throwable);
            String message = getMessage(throwable);
            LOGGER.warn(message, throwable);
            Notification.show("Error", message, Type.ERROR_MESSAGE);
        }

    }

    private String getMessage(final Throwable throwable) {
        String message = null;

        if (throwable != null) {
            Throwable cause = throwable.getCause();
            if (cause != null && StringUtils.startsWith(throwable.getMessage(), cause.getClass().getName())) {
                message = getMessage(cause);
            } else {
                message = StringUtils.trimToNull(throwable.getMessage());
            }
        }
        return message;
    }

    private Throwable findRelevantThrowable(final Throwable throwable) {
        try {
            Throwable cause = null;
            if (throwable instanceof RpcInvocationException && throwable.getCause() instanceof InvocationTargetException) {
                return findRelevantThrowable(throwable.getCause());
            } else if (throwable instanceof MethodException) {
                cause = throwable.getCause();
            } else if (throwable instanceof InvocationTargetException) {
                cause = throwable.getCause();
            } else if (throwable instanceof ExecutionException) {
                cause = throwable.getCause();
            }
            if (cause != null) {
                return cause;
            }
        } catch (Exception ex) {
            // do nothing
        }
        return throwable;
    }
}

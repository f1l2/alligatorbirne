package monitoring.webapp.ui.presenter;

import java.util.Objects;

import monitoring.webapp.service.MonitoringService;
import monitoring.webapp.ui.notify.component.Notify;

public abstract class AbstractPresenter<M, UI extends Notify> {

    private final MonitoringService monitoringService;
    private M model;
    private UI userInterface = null;

    public AbstractPresenter(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    public void setModel(M model) {
        if (!Objects.equals(this.model, model)) {
            this.model = model;
            initModel(this.model);
            if (this.userInterface != null) {
                init(this.model, this.userInterface);
            }
        }
    }

    public void setUserInterface(UI userInterface) {
        if (!Objects.equals(this.userInterface, userInterface)) {
            this.userInterface = userInterface;
            initUserInterface(this.userInterface);
            if (this.model != null) {
                init(this.model, this.userInterface);
            }
        }
    }

    protected MonitoringService getDocumentationPortalService() {
        return monitoringService;
    }

    public M getModel() {
        return model;
    }

    public UI getUserInterface() {
        return userInterface;
    }

    protected abstract void init(M model, UI userInterface);

    protected abstract void initUserInterface(UI userInterface);

    protected abstract void initModel(M model);

}

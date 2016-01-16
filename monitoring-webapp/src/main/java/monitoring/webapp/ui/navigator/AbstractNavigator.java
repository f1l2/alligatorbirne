package monitoring.webapp.ui.navigator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import monitoring.webapp.ui.LazyLoading;
import monitoring.webapp.ui.utility.UiUtils;

public abstract class AbstractNavigator {

    private final List<String> parameters = new ArrayList<String>();

    public AbstractNavigator(String parameters) {
        this(StringUtils.splitPreserveAllTokens(parameters, '/'));
    }

    public AbstractNavigator(String... parameters) {

        if (parameters != null) {
            for (String parameter : parameters) {
                parameter = StringUtils.trimToNull(parameter);

                this.parameters.add(parameter);
            }
        }
    }

    protected void setUriFragment(boolean fireEvents, String... parameters) {

        StringBuilder uriFragment = new StringBuilder("!");

        if (parameters != null) {
            for (String paramter : parameters) {
                uriFragment.append('/').append(StringUtils.trimToEmpty(paramter));
            }
        }
        UI.getCurrent().getPage().setUriFragment(uriFragment.toString(), fireEvents);

    }

    public Component createLink(String caption, String description) {
        return createLink(caption, description, true, null);
    }

    public Component createLink(String caption, String description, boolean enabled, Resource icon) {

        Button linkButton = new Button();
        linkButton.addStyleName("monitoring-link");
        linkButton.setEnabled(enabled && isLinkEnabled());

        if (StringUtils.isNotBlank(caption)) {
            linkButton.setCaption(caption);
        }

        if (icon != null) {
            linkButton.setIcon(icon);
            if (StringUtils.isNotBlank(caption)) {
                linkButton.addStyleName(UiUtils.STYLE.ICON_TEXT_BUTTON.getStyle());
            } else {
                linkButton.addStyleName(UiUtils.STYLE.ICON_BUTTON.getStyle());
            }
        }

        if (StringUtils.isNotBlank(description)) {
            linkButton.setDescription(description);
        }

        linkButton.addClickListener(e -> {
            navigeteTo();
        });

        return linkButton;
    }

    public void navigeteTo() {
        LazyLoading.load(new Runnable() {
            @Override
            public void run() {
                UI.getCurrent().getNavigator().navigateTo(getNavigationState());
            }
        });
    }

    protected abstract boolean isLinkEnabled();

    protected String getParamter(int index) {

        if (index >= parameters.size()) {
            return null;
        }
        return parameters.get(index);
    }

    protected abstract String getViewName();

    protected String getNavigationState() {
        StringBuilder navigationState = new StringBuilder(getViewName());

        for (String paramter : parameters) {
            navigationState.append('/').append(paramter);
        }

        return navigationState.toString();
    }

}

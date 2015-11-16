package monitoring.webapp.ui;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;

@SuppressWarnings("serial")
public class PopupLabel extends CustomComponent {

    private static final int DEFAULT_POPUP_LENGTH = 50;
    private static final ContentMode DEFAULT_CONTENT_MODE = ContentMode.TEXT;
    private Label contentLabel;
    private PopupViewContent popupViewContent;
    private PopupView contentPopupView;
    private int popupLength = DEFAULT_POPUP_LENGTH;

    public PopupLabel() {

        contentLabel = new Label();

        popupViewContent = new PopupViewContent();
        contentPopupView = new PopupView(popupViewContent);
        contentPopupView.addStyleName("esb-link");
        contentPopupView.setHideOnMouseOut(false);

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(contentLabel);
        layout.setComponentAlignment(contentLabel, Alignment.MIDDLE_LEFT);
        layout.addComponent(contentPopupView);

        setCompositionRoot(layout);
        setValue(null);
    }

    public PopupLabel(String content) {
        this(content, DEFAULT_CONTENT_MODE, DEFAULT_POPUP_LENGTH);
    }

    public PopupLabel(String content, int popupLength) {
        this(content, DEFAULT_CONTENT_MODE, popupLength);
    }

    public PopupLabel(String content, ContentMode contentMode) {
        this(content, contentMode, DEFAULT_POPUP_LENGTH);
    }

    public PopupLabel(String content, ContentMode contentMode, int popupLength) {
        this();
        this.popupLength = popupLength;
        setValue(content);
        setContentMode(contentMode);

    }

    public void setContentMode(ContentMode contentMode) {
        contentLabel.setContentMode(contentMode);
        popupViewContent.getPopupComponent().setContentMode(contentMode);
    }

    public void setValue(String value) {
        String content = StringUtils.trimToNull(value);

        if (content != null) {
            setVisible(true);
            if (content.length() > popupLength) {
                contentPopupView.setVisible(true);
                popupViewContent.getPopupComponent().setValue(content);
                contentLabel.setValue(content.substring(0, popupLength));
            } else {
                contentPopupView.setVisible(false);
                contentLabel.setValue(content);
            }

        } else {
            setVisible(false);
        }

        markAsDirtyRecursive();
    }

    private class PopupViewContent implements PopupView.Content {

        private static final String MINIMIZED = "...";

        private Label popupComponent;

        public PopupViewContent() {
            popupComponent = new Label();
            popupComponent.addStyleName("esb-padding-top-small");
            popupComponent.addStyleName("esb-padding-bottom-small");
            popupComponent.addStyleName("esb-padding-left-small");
            popupComponent.addStyleName("esb-padding-right-small");
        }

        @Override
        public String getMinimizedValueAsHTML() {
            return MINIMIZED;
        }

        @Override
        public Label getPopupComponent() {
            return popupComponent;
        }

    }

}

package monitoring.webapp.ui.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.InputSource;

import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import monitoring.webapp.ui.i18n.Messages.MESSAGE;

public final class UiUtils {

    private static final Boolean BORDERLESS = true;

    public enum MARGIN {
        TOP, RIGHT, BUTTOM, LEFT;

        public static MarginInfo marginInfo(MARGIN... margins) {

            Set<MARGIN> values = new HashSet<MARGIN>();

            if (margins != null) {
                for (MARGIN margin : margins) {
                    if (margin != null) {
                        values.add(margin);
                    }
                }
            }

            return new MarginInfo(values.contains(TOP), //
                    values.contains(RIGHT), //
                    values.contains(BUTTOM), //
                    values.contains(LEFT));

        }
    }

    public enum ICON {

        //
        CHECKBOX_UNCHECKED("icons/16/checkbox-unchecked.svg"),
        //
        CHECKBOX_CHECKED("icons/16/checkbox-checked.svg"),
        //
        CHECKMARK("icons/16/checkmark.svg"),
        //
        FILE_PDF("icons/16/file-pdf.svg"),
        //
        FILE_XML("icons/16/file-xml.svg"),
        //
        DOWNLOAD_2("icons/16/download2.svg"),
        //
        DOWNLOAD_3("icons/16/download3.svg"),
        //
        UPLOAD_2("icons/16/upload2.svg"),
        //
        HISTORY("icons/16/history.svg"),
        //
        USER("icons/16/user.svg"),
        //
        USER_3("icons/16/user3.svg"),
        //
        USER_4("icons/16/user4.svg"),
        //
        USER_MINUS("icons/16/user-minus.svg"),
        //
        USER_PLUS("icons/16/user-plus.svg"),
        //
        HOME("icons/16/home.svg"),
        //
        STATS("icons/16/stats.svg"),
        //
        BUG("icons/16/bug.svg"),
        //
        KEY("icons/16/key.svg"),
        //
        EYE("icons/16/eye.svg"),
        //
        PENCIL("icons/16/pencil.svg"),
        //
        CLOSE("icons/16/close.svg"),
        //
        MAIL_2("icons/16/mail2.svg"),
        //
        PHONE("icons/16/phone.svg"),
        //
        REMOVE("icons/16/remove.svg"),
        //
        CANCLE_CIRCLE("icons/16/cancel-circle.svg"),
        //
        DISK("icons/16/disk.svg"),
        //
        METER("icons/16/meter.svg"),
        //
        DISPLAY_24("icons/24/display.png"),
        //
        DISPLAY_32("icons/32/display.png"),
        //
        DISPLAY_48("icons/48/display.png"),
        //
        UNDO("icons/16/undo.svg"),
        //
        REMOVE_SELECTED("icons/16/remove_selected.svg"),
        //
        SCISSORS("icons/16/scissors.svg"),
        //
        UPDATE("icons/16/loop2.svg"),
        //
        ADD("icons/16/plus.svg"),
        //
        DIFF("icons/16/insert-template.svg"),
        //
        TEMPLATE("icons/16/insert-template.svg"),
        //
        CONFIGURATION("icons/16/cogs.svg"),
        //
        LIST("icons/16/list.svg");

        private final Resource icon;

        ICON(String path) {
            icon = new ThemeResource(path);
        }

        public Resource getIcon() {
            return icon;
        }

    }

    public enum STYLE {

        //
        TEXT_FIELD_LARGE("monitoring-textfield-large"),
        //
        EDITABLE("monitoring-editable"),
        //
        ROOT_LAYOUT("monitoring-root-layout"),
        //
        TITLE_LAYOUT("monitoring-title-layout"),
        //
        MENU_LAYOUT("monitoring-menu-layout"),
        //
        CONTENT_LAYOUT("monitoring-content-layout"),
        //
        HEADLINE_LAYOUT("monitoring-headline-layout"),
        //
        CHANGED("monitoring-changed"),
        //
        UNCHANGED("monitoring-unchanged"),
        //
        ADDED("monitoring-added"),
        //
        REMOVED("monitoring-removed"),
        //
        HIDE("monitoring-hide"),
        //
        HORIZONTAL("horizontal"),
        //
        SMALL("small"),
        //
        ICON_TEXT_BUTTON("monitoring-icon-button-txt-btn"),
        //
        ICON_BUTTON("monitoring-icon-button"),
        //
        ICON_LINK("monitoring-icon-link"),
        //
        VISIBLE_HOVER("monitoring-visible-hover"),
        //
        BORDERLESS("borderless");

        private final String style;

        STYLE(String style) {
            this.style = style;
        }

        public String getStyle() {
            return style;
        }

    }

    private UiUtils() {

    }

    public static String xmlPrettyPrint(String content) {
        try {
            Transformer serializer = SAXTransformerFactory.newInstance().newTransformer();
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(content.getBytes())));
            StreamResult res = new StreamResult(new ByteArrayOutputStream());
            serializer.transform(xmlSource, res);
            return new String(((ByteArrayOutputStream) res.getOutputStream()).toByteArray());
        } catch (Exception e) {
            return content;
        }
    }

    public static Panel newPanel(String caption, Component component, STYLE... styles) {
        Panel panel = new Panel();
        panel.setCaption(caption);
        panel.setContent(component);
        return addStyle(panel, styles);
    }

    public static Panel newPanel(String caption, Component component) {
        return newPanel(caption, component, (STYLE[]) null);
    }

    public static Panel newPanel(String caption) {
        return newPanel(caption, null, (STYLE[]) null);
    }

    public static Panel newPanel(String caption, STYLE... styles) {
        return newPanel(caption, null, styles);
    }

    public static Panel newPanel(Component component) {
        return newPanel(null, component, (STYLE[]) null);
    }

    public static Panel newPanel(Component component, STYLE... styles) {
        return newPanel(null, component, styles);
    }

    public static OptionGroup newOptionGroup(MESSAGE caption) {
        return newOptionGroup(caption, (STYLE[]) null);
    }

    public static OptionGroup newOptionGroup(MESSAGE caption, STYLE... styles) {
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setCaption(caption.getMessage());
        return addStyle(optionGroup, styles);
    }

    public static OptionGroup newOptionGroup(ICON icon) {
        return newOptionGroup(icon, (STYLE[]) null);
    }

    public static OptionGroup newOptionGroup(ICON icon, STYLE... styles) {
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setIcon(icon.getIcon());
        return addStyle(optionGroup, styles);
    }

    public static OptionGroup newOptionGroup(MESSAGE caption, ICON icon) {
        return newOptionGroup(caption, icon, (STYLE[]) null);
    }

    public static OptionGroup newOptionGroup(MESSAGE caption, ICON icon, STYLE... styles) {
        OptionGroup optionGroup = new OptionGroup();
        optionGroup.setCaption(caption.getMessage());
        optionGroup.setIcon(icon.getIcon());
        return addStyle(optionGroup, styles);
    }

    public static Link newLink(MESSAGE caption) {
        return newLink(caption, (STYLE[]) null);
    }

    public static Link newLink(MESSAGE caption, STYLE... styles) {
        Link link = new Link();
        link.setCaption(caption.getMessage());
        return addStyle(link, styles);
    }

    public static Link newLink(ICON icon) {
        return newLink(icon, (STYLE[]) null);
    }

    public static Link newLink(ICON icon, Resource resource) {
        return newLink(icon, null, resource);
    }

    public static Link newLink(ICON icon, String description, Resource resource) {
        Link link = newLink(icon, (STYLE[]) null);
        link.setDescription(description);
        link.setResource(resource);
        return link;
    }

    public static Link newLink(ICON icon, STYLE... styles) {
        Link link = new Link();
        link.setIcon(icon.getIcon());
        link = addStyle(link, STYLE.ICON_LINK);
        return addStyle(link, styles);
    }

    public static Link newLink(MESSAGE caption, ICON icon) {
        return newLink(caption, icon, (STYLE[]) null);
    }

    public static Link newLink(MESSAGE caption, ICON icon, STYLE... styles) {
        Link link = new Link();
        link.setCaption(caption.getMessage());
        link.setIcon(icon.getIcon());
        link = addStyle(link, STYLE.ICON_TEXT_BUTTON);
        return addStyle(link, styles);
    }

    public static Button newButton(MESSAGE caption) {
        return newButton(caption, (STYLE[]) null);
    }

    public static Button newButton(MESSAGE caption, STYLE... styles) {
        Button button = new Button(caption.getMessage());
        return addStyle(button, styles);
    }

    public static Button newButton(ICON icon) {
        return newButton(icon, (STYLE[]) null);
    }

    public static Button newButton(ICON icon, STYLE... styles) {
        Button button = new Button(icon.getIcon());
        button.removeStyleName("v-button");
        button = addStyle(button, STYLE.ICON_BUTTON);
        return addStyle(button, styles);
    }

    public static Button newButton(MESSAGE caption, ICON icon) {
        return newButton(caption, icon, (STYLE[]) null);
    }

    public static Button newButton(MESSAGE caption, ICON icon, STYLE... styles) {
        Button button = new Button(caption.getMessage(), icon.getIcon());
        button = addStyle(button, STYLE.ICON_TEXT_BUTTON);
        return addStyle(button, styles);
    }

    public static Label newLabel(MESSAGE caption, String content, STYLE... styles) {
        Label label = new Label(content);
        if (caption != null) {
            label.setCaption(caption.getMessage());
        }
        return addStyle(label, styles);
    }

    public static MenuBar newMenuBar(ICON icon) {
        return newMenuBar(icon, (STYLE[]) null);
    }

    public static MenuBar newMenuBar(STYLE... styles) {
        MenuBar menuBar = new MenuBar();
        return addStyle(menuBar, styles);
    }

    public static MenuBar newMenuBar(ICON icon, STYLE... styles) {
        MenuBar menuBar = new MenuBar();
        menuBar.setIcon(icon.getIcon());
        return addStyle(menuBar, styles);
    }

    public static MenuItem newMenuItem(MenuBar menuBar, String caption, Command command, ICON icon) {
        return menuBar.addItem(caption, icon.getIcon(), command);
    }

    public static MenuItem newMenuItem(MenuItem menuItem, String caption, Command command, ICON icon) {
        return menuItem.addItem(caption, icon.getIcon(), command);
    }

    public static MenuItem newMenuItem(MenuItem menuItem, String caption, Command command) {
        return menuItem.addItem(caption, command);
    }

    public static Label newLabel(MESSAGE caption) {
        return newLabel(caption, null);
    }

    public static Label newLabel(MESSAGE caption, String content) {
        return newLabel(caption, content, (STYLE[]) null);
    }

    public static Label newLabel(String content, STYLE... styles) {
        return newLabel(null, content, styles);
    }

    public static TextArea newTextArea(MESSAGE caption) {
        return newTextArea(caption, (STYLE[]) null);
    }

    public static TextArea newTextArea(MESSAGE caption, STYLE... styles) {
        TextArea textArea = new TextArea();
        textArea.setCaption(caption.getMessage());
        return addStyle(textArea, styles);
    }

    public static ComboBox newComboBox(MESSAGE caption) {
        return newComboBox(caption, (STYLE[]) null);
    }

    public static ComboBox newComboBox(STYLE... styles) {
        ComboBox comboBox = new ComboBox();
        return addStyle(comboBox, styles);
    }

    public static ComboBox newComboBox(MESSAGE caption, STYLE... styles) {
        ComboBox comboBox = new ComboBox();
        comboBox.setCaption(caption.getMessage());
        return addStyle(comboBox, styles);
    }

    public static TextField newTextField(STYLE... styles) {
        TextField textField = new TextField();
        return addStyle(textField, styles);
    }

    public static TextField newTextField(MESSAGE caption) {
        return newTextField(caption, (STYLE[]) null);
    }

    public static TextField newTextField(MESSAGE caption, STYLE... styles) {
        TextField textField = new TextField();
        textField.setCaption(caption.getMessage());
        return addStyle(textField, styles);
    }

    public static <C extends Component> C addStyle(C component, STYLE... styles) {
        if (component != null && styles != null) {
            for (STYLE style : styles) {
                if (style != null) {
                    component.addStyleName(style.getStyle());
                }
            }
        }
        return component;
    }

    public static <C extends Component> C setStyle(C component, STYLE style) {
        if (component != null && style != null) {
            component.setStyleName(style.getStyle());
        }
        return component;
    }

    public static <E> Collection<E> sort(Collection<E> elements, Comparator<E> comparator) {
        if (comparator == null) {
            return elements;
        }
        List<E> sortedElements = new ArrayList<E>(elements);
        Collections.sort(sortedElements, comparator);
        return sortedElements;
    }

    public static Component createPanelStyleCaption(Component filterComponent) {

        Label label = new Label("Search");
        label.addStyleName("monitoring-line-height");

        return createPanelStyleCaption(label, filterComponent);
    }

    public static Component createPanelStyleCaption(Label label, Component filterComponent, Component content) {
        Component caption = createPanelStyleCaption(label, filterComponent);

        VerticalLayout vLayout = new VerticalLayout();
        vLayout.addComponent(caption);
        vLayout.addComponent(content);

        return vLayout;

    }

    public static Component createPanelStyleCaption(Label label, Component filterComponent) {

        label.setWidth(100, Unit.PIXELS);

        CssLayout layout = new CssLayout();
        layout.setWidth(100, Unit.PERCENTAGE);
        layout.setHeight(100, Unit.PERCENTAGE);
        layout.addStyleName("monitoring-panel-caption-style");
        layout.addStyleName("monitoring-padding-small");

        layout.addComponent(label);

        if (null != filterComponent) {
            filterComponent.addStyleName("monitoring-font-weight-normal");
            layout.addComponent(filterComponent);
        } else {
            layout.setHeight(36, Unit.PIXELS);
        }

        return layout;
    }

    public static Panel createPanelOverallStyle(String caption) {

        if (BORDERLESS) {
            Panel panel = new Panel();
            panel.addStyleName("borderless");
            return panel;
        } else {
            Panel panel = new Panel(caption);
            return panel;
        }
    }

    public static Panel panelWrapper(Component component, String headline, Boolean incorporateOverallStyle) {
        Panel panel;
        if (incorporateOverallStyle) {
            panel = UiUtils.createPanelOverallStyle(headline);
        } else {
            panel = new Panel();
            panel.setCaption(headline);
        }

        panel.setContent(component);
        panel.addStyleName("monitoring-spacing-margin-inner");
        return panel;

    }

    public static Panel panelWrapper(Component component, String headline) {
        return panelWrapper(component, headline, true);
    }

    public static Panel panelWrapper(Component component) {
        return panelWrapper(component, null);
    }
}

package monitoring.webapp.service;

import java.util.Comparator;

public interface Display {

    public final static Comparator<Display> DISPLAY_NAME_COMPARATOR = new Comparator<Display>() {

        @Override
        public int compare(final Display display1, final Display display2) {
            return display1.getDisplayName().compareTo(display2.getDisplayName());
        }
    };

    public String getDisplayName();

    public String getDescription();

}

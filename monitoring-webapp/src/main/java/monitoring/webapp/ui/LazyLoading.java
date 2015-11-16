package monitoring.webapp.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public final class LazyLoading {

    private LazyLoading() {

    }

    public static void load(Runnable runnable) {

        setPollInterval(UI.getCurrent(), 250);

        LazyLoadingThread lazyLoadingThread = new LazyLoadingThread(runnable);

        lazyLoadingThread.start();
    }

    public static Component getLazyLoader(UI ui) {
        return ((MonitoringUI) ui).getLazyLoader();
    }

    private static void setPollInterval(UI ui, int intervalInMillis) {

        if (intervalInMillis > -1) {
            getLazyLoader(ui).removeStyleName("esb-hide");
        } else {
            getLazyLoader(ui).addStyleName("esb-hide");
        }

        ui.setPollInterval(intervalInMillis);
    }

    private static class LazyLoadingThread extends Thread {

        private Runnable runnable;

        private LazyLoadingThread(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {

            // Update the UI thread-safely
            UI.getCurrent().access(new Runnable() {
                @Override
                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        setPollInterval(UI.getCurrent(), -1);
                    }

                }
            });

        }

    }
}

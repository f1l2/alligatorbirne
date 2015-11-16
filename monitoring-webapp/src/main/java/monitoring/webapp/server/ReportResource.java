package monitoring.webapp.server;

import com.vaadin.server.ExternalResource;

public final class ReportResource {

    private ReportResource() {
    };

    public static ExternalResource createServiceReport() {
        return createServiceReport(123l);
    }

    public static ExternalResource createServiceReport(Long serviceId) {
        return new ExternalResource(String.format("api/rest/documentationPortalService/createServiceReport/%s", serviceId));
    }

}

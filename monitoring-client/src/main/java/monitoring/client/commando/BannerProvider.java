package monitoring.client.commando;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BannerProvider extends DefaultBannerProvider {

    public String getBanner() {

        File file = new File("src/main/resources/banner.txt");

        StringBuffer banner = new StringBuffer();

        BufferedReader bufferReader;
        try {
            bufferReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferReader.readLine()) != null) {
                banner.append(line);
            }
            bufferReader.close();
        } catch (Exception e) {
            return "";
        } finally {

        }

        return banner.toString();
    }

    public String getVersion() {
        return "1.0";
    }

    public String getWelcomeMessage() {
        return "Welcome to Monitoring Client";
    }

    @Override
    public String getProviderName() {
        return "Monitoring banner provider";
    }
}

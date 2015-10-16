package monitoring.client.commando;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PromptProvider extends DefaultPromptProvider {

    @Override
    public String getPrompt() {
        return "monitoring-shell>";
    }

    @Override
    public String getProviderName() {
        return "Monitoring prompt provider";
    }

}

package commands;

import java.util.Arrays;
import java.util.List;

import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import common.data.Connection;

public class dev extends BaseCommand {

    @Command
    @Usage("ls")
    public String list() {

        RestTemplate template = new RestTemplate();
        ResponseEntity<Connection[]> responseEntity = template.getForEntity("http://localhost:5000/registrations/devices", Connection[].class);

        List<Connection> connections = Arrays.asList(responseEntity.getBody());

        StringBuilder sb = new StringBuilder();

        if (CollectionUtils.isEmpty(connections)) {
            sb.append("No entry");
        } else {
            for (Connection connection : connections) {
                sb.append(connection.toString());
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}

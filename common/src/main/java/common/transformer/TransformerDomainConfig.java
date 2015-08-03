package common.transformer;

import org.springframework.stereotype.Component;

import common.data.Domain;
import common.data.configuration.DomainConfig;

@Component
public class TransformerDomainConfig extends Transformer<DomainConfig, Domain> {
	
	@Override
	public DomainConfig toLocal(Domain remote) {
		DomainConfig domainConfig = new DomainConfig();
		domainConfig.setName(remote.getName());
		
		return domainConfig;
	}

	@Override
	public Domain toRemote(DomainConfig local) {

		Domain domain = new Domain();
		domain.setName(local.getName());
		
		return domain;
	}

}

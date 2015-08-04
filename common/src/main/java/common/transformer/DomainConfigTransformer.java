package common.transformer;

import common.data.DomainInformation;
import common.data.configuration.DomainConfig;


public class DomainConfigTransformer extends Transformer<DomainConfig, DomainInformation> {
	
	@Override
	public DomainConfig toLocal(DomainInformation remote) {
		DomainConfig domainConfig = new DomainConfig();
		domainConfig.setName(remote.getName());
		
		return domainConfig;
	}

	@Override
	public DomainInformation toRemote(DomainConfig local) {

		DomainInformation domain = new DomainInformation();
		domain.setName(local.getName());
		
		return domain;
	}

}

package common.transformer;

import common.data.DomainInformation;
import common.data.configuration.XMLDomainInformation;

public class XMLDomainInformationTransformer extends Transformer<XMLDomainInformation, DomainInformation> {

    @Override
    public XMLDomainInformation toLocal(DomainInformation remote) {
        XMLDomainInformation XMLDomainInformation = new XMLDomainInformation();
        XMLDomainInformation.setName(remote.getName());

        return XMLDomainInformation;
    }

    @Override
    public DomainInformation toRemote(XMLDomainInformation local) {

        DomainInformation domain = new DomainInformation();
        domain.setName(local.getName());

        return domain;
    }

}

package common.transformer;

import common.data.DomainInformation;
import common.data.configuration.XMLDomainInformation;

public class XMLDomainInformationTransformer extends Transformer<XMLDomainInformation, DomainInformation> {

    @Override
    public XMLDomainInformation toLocal(DomainInformation remote) {

        if (null == remote) {
            return null;
        }

        XMLDomainInformation XMLDomainInformation = new XMLDomainInformation();
        XMLDomainInformation.setName(remote.getName());

        return XMLDomainInformation;
    }

    @Override
    public DomainInformation toRemote(XMLDomainInformation local) {

        if (null == local) {
            return null;
        }

        DomainInformation domain = new DomainInformation();
        domain.setName(local.getName());

        return domain;
    }

}

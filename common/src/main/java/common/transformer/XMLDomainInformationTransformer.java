package common.transformer;

import common.data.model.DomainInformation;
import common.data.setting.XMLDomainInformation;

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

        DomainInformation domain = new DomainInformation();

        if (null == local) {
            domain.setName(nullString);
        } else {
            domain.setName(local.getName());
        }

        return domain;
    }

}

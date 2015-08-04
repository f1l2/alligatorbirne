package configuration.management.repo;

import org.springframework.stereotype.Component;

import common.data.EProcInformation;
import common.transformer.Transformer;

import configuration.management.model.EProcJPA;

@Component
public class EProcTransformer extends Transformer<EProcJPA, EProcInformation> {

    @Override
    public EProcJPA toLocal(EProcInformation remote) {

        EProcJPA eProcJPA = new EProcJPA();
        eProcJPA.setName(remote.getName());
        eProcJPA.setUrl(remote.getUrl());

        return eProcJPA;
    }

    @Override
    public EProcInformation toRemote(EProcJPA local) {

        EProcInformation eProcInformation = new EProcInformation();
        eProcInformation.setName(local.getName());
        eProcInformation.setUrl(local.getUrl());

        return eProcInformation;
    }

}

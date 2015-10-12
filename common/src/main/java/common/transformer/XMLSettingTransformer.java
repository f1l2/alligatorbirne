package common.transformer;

import common.data.Setting;
import common.data.setting.XMLConnections;
import common.data.setting.XMLDataSources;
import common.data.setting.XMLSetting;

public class XMLSettingTransformer extends Transformer<XMLSetting, Setting> {

    private XMLConnectionTransformer connectionTransformer = new XMLConnectionTransformer();
    private XMLDataSourceTransformer dataSourceTransformer = new XMLDataSourceTransformer();

    @Override
    public XMLSetting toLocal(Setting remote) {

        XMLSetting xmlSetting = new XMLSetting();

        XMLDataSources xmlDataSources = new XMLDataSources();
        xmlDataSources.getDataSource().addAll(dataSourceTransformer.toLocal(remote.getDataSources()));

        XMLConnections xmlConnections = new XMLConnections();
        xmlConnections.getConnection().addAll(connectionTransformer.toLocal(remote.getConnections()));

        xmlSetting.setDataSources(xmlDataSources);
        xmlSetting.setConnections(xmlConnections);

        return xmlSetting;
    }

    @Override
    public Setting toRemote(XMLSetting local) {

        Setting setting = new Setting();

        setting.setConnections(connectionTransformer.toRemote(local.getConnections().getConnection()));
        setting.setDataSources(dataSourceTransformer.toRemote(local.getDataSources().getDataSource()));

        return setting;
    }

}

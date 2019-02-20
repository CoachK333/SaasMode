package com.boot.data.saas.tenant;

import com.boot.data.saas.dao.SynctargeturiResopory;
import com.boot.data.saas.entiy.Synctargeturi;
import com.boot.data.saas.util.DataSourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TenantDataSource implements Serializable {

    @Autowired
    private SynctargeturiResopory synctargeturiResopory;

    public Map<String, DataSource> getAll() {
        List<Synctargeturi> configList = synctargeturiResopory.findAll();
        Map<String, DataSource> result = new HashMap<>();
        for (Synctargeturi config : configList) {
            Map dbInfo = DataSourceUtil.analysisDataBaseInfo(config);
            String dataSourceKey = dbInfo.get("insCode").toString();
            result.put(dataSourceKey, DataSourceUtil.createAndConfigureDataSource(dbInfo));
        }
        return result;
    }

}

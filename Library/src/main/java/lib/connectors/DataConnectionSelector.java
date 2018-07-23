package lib.connectors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataConnectionSelector {

    List<DataConnection> connectors = new ArrayList<DataConnection>(){{
        add(new FileConnector(""));
        add(new DataBaseConnector());
    }};

    public DataConnection select(Object globalOptions) throws Exception {
        Field[] fields = globalOptions.getClass().getDeclaredFields();
        for (DataConnection connector : connectors)
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                if (fields[j].getName().equals(connector.getName()) && (String) fields[j].get(globalOptions) != null)
                    return connector;
            }
        return null;
    }
}

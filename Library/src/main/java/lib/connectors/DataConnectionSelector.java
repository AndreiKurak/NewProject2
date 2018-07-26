package lib.connectors;

import lib.global_options.GlobalOptions;

public class DataConnectionSelector {

    public DataConnection select(GlobalOptions globalOptions) {
        if (globalOptions.getFile() != null) {
            return new FileConnector("");
        }
        else if (globalOptions.getDatabase() != null) {
            return new DataBaseConnector();
        }
        else return null;
    }
}

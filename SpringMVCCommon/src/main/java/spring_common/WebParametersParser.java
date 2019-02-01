package spring_common;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class WebParametersParser {

    private String command;
    private Map<String, Object> commandOptions = new HashMap<>();
    private Map<String, Object> globalOptions = new HashMap<>();

    public WebParametersParser(/*HttpServletRequest req*/) {
        /*Map<String, String[]> inputMap = req.getParameterMap();
         *//*
        if (inputMap.isEmpty())
            throw new ParseException("Command options not found");
          *//*
        //command = req.getServletPath().replace("/", "").replaceAll(".do", "");
        command = req.getServletPath().replace("/", "");

        inputMap.forEach((key, value) -> {
            if (value[0] != "")
                commandOptions.put(key, value[0]);
        });

        globalOptions.put("database", "test");*/
    }

    public void parse(HttpServletRequest req) {
        Map<String, String[]> inputMap = req.getParameterMap();
        /*
        if (inputMap.isEmpty())
            throw new ParseException("Command options not found");
          */
        //command = req.getServletPath().replace("/", "").replaceAll(".do", "");
        command = req.getServletPath().replace("/", "");

        inputMap.forEach((key, value) -> {
            if (value[0] != "")
                commandOptions.put(key, value[0]);
        });

        globalOptions.put("database", "test");
    }

    public String getCommand() {
        return command;
    }

    public Map<String, Object> getCommandOptions() {
        return commandOptions;
    }

    public Map<String, Object> getGlobalOptions() {
        return globalOptions;
    }
}

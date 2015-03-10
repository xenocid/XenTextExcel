package com.xenocid.xentextexcel.commands;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by xenocid on 3/9/15.
 */
public class CommandController {
    private HashMap<Pattern, GenericCommand> commandsMap;

    public CommandController() {
        super();
        commandsMap = new HashMap<Pattern, GenericCommand>();
        commandsMap.put(Pattern.compile(""), new GetCellCommand());
        commandsMap.put(Pattern.compile(""), new SetCellCommand());
    }

    public void executeCommand(String command) {

    }
}

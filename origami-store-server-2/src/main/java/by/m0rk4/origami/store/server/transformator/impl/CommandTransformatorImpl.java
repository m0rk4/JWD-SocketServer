package by.m0rk4.origami.store.server.transformator.impl;

import by.m0rk4.origami.store.server.command.ChangeRecordCommand;
import by.m0rk4.origami.store.server.command.Command;
import by.m0rk4.origami.store.server.command.GetRecordCommand;
import by.m0rk4.origami.store.server.command.InsertCommand;
import by.m0rk4.origami.store.server.entity.OrigamiRecord;
import by.m0rk4.origami.store.server.transformator.CommandTransformator;
import by.m0rk4.origami.store.server.transformator.model.TransformatorContext;

public class CommandTransformatorImpl implements CommandTransformator {
    private static final String TEXT_COMMAND_SEPARATOR = "\\s+";
    private final TransformatorContext transformationContext;

    public CommandTransformatorImpl(TransformatorContext transformationContext) {
        this.transformationContext = transformationContext;
    }

    // TODO: Add command validation
    @Override
    public Command transform(String textCommand) {
        String[] commandTokens = textCommand.split(TEXT_COMMAND_SEPARATOR);
        if (commandTokens.length < 2) {
            return null;
        }

        switch (commandTokens[0].toUpperCase()) {
            case "GET":
                return new GetRecordCommand(
                        transformationContext.getWriter(),
                        transformationContext.getFileApi(),
                        commandTokens[1]);
            case "INSERT": {
                OrigamiRecord origamiRecord = parseOrigamiRecordFromCommand(commandTokens);
                if (origamiRecord == null) {
                    return null;
                }
                return new InsertCommand(
                        transformationContext.getWriter(),
                        transformationContext.getFileApi(),
                        origamiRecord
                );
            }
            case "UPDATE": {
                OrigamiRecord origamiRecord = parseOrigamiRecordFromCommand(commandTokens);
                if (origamiRecord == null) {
                    return null;
                }
                return new ChangeRecordCommand(
                        transformationContext.getWriter(),
                        transformationContext.getFileApi(),
                        origamiRecord
                );
            }
            default:
                return null;
        }
    }

    private OrigamiRecord parseOrigamiRecordFromCommand(String[] tokens) {
        if (tokens.length < 3) {
            return null;
        }

        try {
            int stepsCount = Integer.parseInt(tokens[2]);
            return new OrigamiRecord(tokens[1], stepsCount);
        } catch (NumberFormatException e) {
            return new OrigamiRecord(tokens[1], 0);
        }
    }
}

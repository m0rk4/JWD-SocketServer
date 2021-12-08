package by.m0rk4.origami.store.server.transformator;

import by.m0rk4.origami.store.server.command.Command;

public interface CommandTransformator {
    Command transform(String textCommand);
}

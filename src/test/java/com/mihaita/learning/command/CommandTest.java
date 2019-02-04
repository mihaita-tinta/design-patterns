package com.mihaita.learning.command;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CommandTest {

    /**
     * Advantages:
     * - decouple sender from processor
     * - object orientated callback
     * - encapsulate request as an object
     * - could support undo functionality
     */
    @Test
    public void testSingleCommand() {

        Invoker invoker = new Invoker();

        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);// new object per command, what to be executed

        invoker.storeAndExecute(command);

        invoker.undoLastCommand();


    }

    @Test
    public void testMacroCommand() {

        Invoker invoker = new Invoker();

        List<Receiver> receivers = Arrays.asList(new Receiver(), new Receiver());
        Command command = new MacroCommand(receivers);

        invoker.storeAndExecute(command);

        invoker.undoLastCommand();


    }

    static class Receiver {

        public void startFeature() {

        }

        public void stopFeature() {

        }
    }

    static class MacroCommand implements Command {

        private final List<Receiver> receivers;

        MacroCommand(List<Receiver> receivers) {
            this.receivers = receivers;
        }

        @Override
        public void execute() {
            receivers.forEach(Receiver::startFeature);
        }

        @Override
        public void undo() {
            receivers.forEach(Receiver::stopFeature);

        }
    }

    static class ConcreteCommand implements Command{
        private final Receiver receiver;

        ConcreteCommand(Receiver receiver) {
            this.receiver = receiver;
        }

        @Override
        public void execute() {
            receiver.startFeature();
        }

        @Override
        public void undo() {
            receiver.stopFeature();
        }
    }

    interface Command {

        void execute();

        void undo();
    }

    static class Invoker {
        private Queue<Command> history = new LinkedList<>();

        public void storeAndExecute(Command command) {
            history.add(command);
            command.execute();
        }
        public void undoLastCommand() {

            Optional.ofNullable(history.poll())
                .ifPresent(command -> command.undo());

        }

    }

}

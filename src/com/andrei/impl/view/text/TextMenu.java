package com.andrei.impl.view.text;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TextMenu extends Command {

    protected final Map<String, Runnable> commands = new LinkedHashMap<>();
    protected boolean running = false;

    public TextMenu(String key, String description, Command... commands) {
        super(key, description);
        Arrays.stream(commands).forEach(command -> this.commands.put(command.getKey(), command));
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            printCommands();
            commands.getOrDefault(scanner.next(), this::wrongCommand).run();
        }
    }

    private void wrongCommand() {
        System.out.println("Wrong input");
    }

    public void printCommands() {
        commands.values().forEach(System.out::println);
    }
}

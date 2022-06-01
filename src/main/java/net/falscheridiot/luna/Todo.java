package net.falscheridiot.luna;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @autho @falscherIdiot
 * @version 1.0
 */
public class Todo {

    private static int id = 0;
    private static HashMap<Integer, Todo> todos;
    private int todoId;
    private String label;
    private String description;
    private int priority;
    private String[] tags;
    private LocalDateTime creationDate;

    /** Constructor */
    public Todo() {
        todos = new HashMap<Integer, Todo>();
        R.events.RegisterTriggerEvent("todo::createTodo", "Creates TODO", args -> {
            // ? R.logger.LOG("Creating new Todo");
            String tlabel;
            String tdescription;
            int tpriority;
            String[] ttags;
            LocalDateTime tcreationDate;
            String t = "";
            if (args == null) {
                tlabel = System.console().readLine("Label: ");
                tdescription = System.console().readLine("Description: ");
                tpriority = Integer.parseInt(System.console().readLine("Priority: "));
                ttags = System.console().readLine("Tags (seperate with \', \'): ").split(", ");
                tcreationDate = LocalDateTime.now();
                new Todo(tlabel, tdescription, tpriority, ttags, tcreationDate);
            } else if (args.length == 1) {
                tlabel = args[0];
                tcreationDate = LocalDateTime.now();
                todos.put(id++, new Todo(tlabel, null, -1, null, tcreationDate));
            }
            if (tags != null) {
                for (String string : tags) {
                    t += string + ", ";
                }
                t.substring(0, t.length() - 2);
            }
            R.logger.LOG("New Todo created! [" + todoId + "]" + label
                    + (description == null ? "" : "  Description: " + description) + "\n"
                    + (priority == -1 ? "" : "  Priority: " + priority));
        });
        R.events.RegisterTriggerEvent("todo::showTodos", "Displays all TODOs", args -> {
            System.out.println("Todos: ");
            System.out.println("---------------------------------");
            System.out.println("ID:\tLabel:\tPriority");

            for (Todo todo : todos.values()) {
                System.out.println("[" + todo.todoId + "]\t" + todo.label + "\t" + todo.priority);
            }
        });
        R.events.RegisterTriggerEvent("todo::inspectTodo", "Allows to inspect specific TODO", todo -> {
            int x;
            if (todo == null) {
                R.events.TriggerEvent("todo::showTodos", null);
                x = Integer.parseInt(System.console().readLine("ID: "));
            } else {
                x = Integer.parseInt(todo[0]);
            }
            System.out.println(todos.get(x).toString());
        });
        R.events.RegisterTriggerEvent("todo::removeTodo", "Removes TODO", todo -> {
            // ? R.logger.LOG("Removing Todo");
            int x;
            if (todo == null) {
                R.events.TriggerEvent("todo::showTodos", null);
                x = Integer.parseInt(System.console().readLine("ID: "));
            } else {
                x = Integer.parseInt(todo[0]);
            }
            todos.remove(x);
            R.logger.WARNING("Removed Todo! [" + x + "]");
        });
    }

    /**
     * TODO-Constructor
     * 
     * @param label
     * @param description
     * @param priority
     * @param tags
     * @param creationDate
     */
    public Todo(String label, String description, int priority, String[] tags, LocalDateTime creationDate) {
        this.todoId = id;
        this.label = label;
        this.description = description;
        this.priority = priority;
        this.tags = tags;
        this.creationDate = creationDate;
    }

    /**
     * @return todoId
     */
    public int getTodoId() {
        return todoId;
    }

    /**
     * @return label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @return tags
     */
    public String[] getTags() {
        return tags;
    }

    /**
     * @return creationDate
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /** toString */
    public String toString() {
        String t = "";
        if (tags != null) {
            for (String string : tags) {
                t += string + ", ";
            }
            t.substring(0, t.length() - 2);
        }
        return "[" + todoId + "]" + label + "\n--------------------------------\n"
                + (description == null ? "" : "  Description: " + description) + "\n"
                + (priority == -1 ? "" : "  Priority: " + priority) + "\n" + t + "\n  Creation-Date: " + creationDate;
    }
}

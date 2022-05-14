package net.falscheridiot.luna;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @autho @falscherIdiot
 * @version 1.0
 */
public class Todo {

    private static int id = -1;
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
        R.events.RegisterTriggerEvent("todo::createTodo", args -> {
            // ? R.logger.LOG("Creating new Todo");
            String tlabel = "";
            String tdescription = "";
            int tpriority = -1;
            String[] ttags = null;
            LocalDateTime tcreationDate = null;
            String t = "";
            if (args == null) {
                tlabel = System.console().readLine("Label: ");
                tdescription = System.console().readLine("Description: ");
                try {
                    tpriority = Integer.parseInt(System.console().readLine("Priority: "));
                } catch (Exception e) {
                    tpriority = -1;
                }
                ttags = System.console().readLine("Tags (seperate with \', \'): ").split(", ");
                tcreationDate = LocalDateTime.now();
                todos.put(++id, new Todo(tlabel, tdescription, tpriority, ttags, tcreationDate));
            } else if (args.length == 1) {
                tlabel = args[0];
                tcreationDate = LocalDateTime.now();
                todos.put(++id, new Todo(tlabel, null, -1, null, tcreationDate));
            }
            if (tags != null) {
                for (String string : tags) {
                    t += string + ", ";
                }
                t.substring(0, t.length() - 2);
            }
            R.logger.LOG("New Todo created! [" + id + "] " + tlabel
                    + (tdescription == null ? "" : "\n  Description: " + tdescription) + "\n"
                    + (tpriority == -1 ? "" : "  Priority: " + tpriority));
        });
        R.events.RegisterTriggerEvent("todo::showTodos", args -> {
            System.out.println("Todos: ");
            System.out.println("---------------------------------");
            System.out.println("ID:\tLabel:\tPriority");

            for (Todo todo : todos.values()) {
                System.out.println("[" + todo.todoId + "]\t" + todo.label + "\t" + todo.priority);
            }
        });
        R.events.RegisterTriggerEvent("todo::inspectTodo", todo -> {
            int x;
            if (todo == null) {
                R.events.TriggerEvent("todo::showTodos", null);
                try {
                    x = Integer.parseInt(System.console().readLine("ID: "));
                } catch (Exception e) {
                    R.logger.ERROR("Invalid ID!");
                    return;
                }
            } else {
                try {
                    x = Integer.parseInt(todo[0]);
                } catch (Exception e) {
                    R.logger.ERROR("Invalid ID!");
                    return;
                }
            }
            try {
                System.out.println(todos.get(x).toString());
            } catch (Exception e) {
                R.logger.ERROR("Todo does not exists!");
                return;
            }
        });
        R.events.RegisterTriggerEvent("todo::removeTodo", todo -> {
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
        String t = "{";
        if (tags != null) {
            for (String string : tags) {
                t += string + ", ";
            }
            t.substring(0, t.length() - 2);
            t += "}";
        }
        return "[" + todoId + "]" + label + "\n--------------------------------\n"
                + "  Description: " + description + "\n"
                + "  Priority: " + priority + "\n" + "  Tags: " + t + "\n  Creation-Date: " + creationDate;
    }
}

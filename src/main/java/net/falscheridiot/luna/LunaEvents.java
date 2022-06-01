package net.falscheridiot.luna;

import java.util.HashMap;

/**
 * @author @falscherIdiot
 * @version 1.1
 */
public class LunaEvents {

    /**
     * @author @falscherIdiot
     * @version 1.0
     */
    public interface EventFunction {
        /**
         * @param args
         */
        public void run(String[] args);
    }

    private HashMap<String, String> eventDesc;
    private HashMap<String, EventFunction> events;

    /** Constructor */
    public LunaEvents() {
        events = new HashMap<String, EventFunction>();
        eventDesc = new HashMap<String, String>();
    }

    /**
     * Registers an Event
     * 
     * @param eventName
     * @param eventFunction
     */
    public void RegisterTriggerEvent(String eventName, String description, EventFunction eventFunction) {
        events.put(eventName, eventFunction);
        eventDesc.put(eventName, description);
    }

    /**
     * Triggers an Event
     * 
     * @param eventName
     * @param args
     * @return
     */
    public boolean TriggerEvent(String eventName, String[] args) {
        if (!events.containsKey(eventName)) {
            return false;
        }
        events.get(eventName).run(args);
        return true;
    }

    /**
     * Prints all registered Events
     */
    public void printEvents() {
        for (String event : eventDesc.keySet()) {
            System.out.println(event + "\t-    " + eventDesc.get(event));
        }
    }
}

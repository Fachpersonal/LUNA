package net.luna.util;

public class LSNObject {

    private String key;
    private String value;
    private LSNType type;

    public LSNObject(String key, String value, LSNType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public LSNType getType() {
        return type;
    }

    public String toString() {
        return "<" + key + ">" + value + "</" + type.toString() + ">";
    }
}

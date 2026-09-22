package ru.otus.animals;

public enum Color {
    WHITE ("белый"),
    BLACK ("чёрный");

    private final String value;

    Color(String value) {
        this.value = value;
    }
    public String getValue () {
        return value;
    }

    public static Color fromString(String text) {
        if (text == null) return null;
        for (Color c : Color.values()) {
            if (c.getValue().equalsIgnoreCase(text.trim())) {
                return c;
            }
        }
        return null; // не найдено
    }
}

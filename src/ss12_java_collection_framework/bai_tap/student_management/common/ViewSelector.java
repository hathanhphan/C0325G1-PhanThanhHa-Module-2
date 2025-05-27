package ss12_java_collection_framework.bai_tap.student_management.common;

public enum ViewSelector {
    DISPLAY(1),
    ADD(2),
    UPDATE(3),
    DELETE(4),
    SEARCH(5),
    SORT(6),
    EXIT(-1);

    private final int value;

    ViewSelector(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ViewSelector fromValue(int value) {
        for (ViewSelector v : values()) {
            if (v.value == value) return v;
        }
        return EXIT;
    }
}

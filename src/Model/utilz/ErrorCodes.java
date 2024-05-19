package Model.utilz;

public enum ErrorCodes {
    PATH_NOT_FOUND(-1), START_END_POINTS_NOT_CORRECT(-2), NOT_ALL_TOWNS_CONNECTED(-3), NO_TOWNS(-4);
    private final int value;
    private ErrorCodes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

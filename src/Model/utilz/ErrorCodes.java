package Model.utilz;

public enum ErrorCodes {
    PATH_NOT_FIND(-1), START_END_POINTS_NOT_CORRECT(-2);
    private final int value;
    private ErrorCodes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package View.utilz;

public enum Actions {
    TSP_COMPUTE(1);
    private int value;
    private Actions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

package View.utilz;

public enum Actions {
    TSP_COMPUTE(1), ADD_TOWN(2), DELETE_TOWN(3), ADD_RAY(4), CLEAR(5);
    private int value;
    private Actions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

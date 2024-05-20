package View.utilz;

public enum Actions {
    TSP_COMPUTE(1), ADD_TOWN(2), ADD_RAY(3), CLEAR(4), REAL_TIME_UPDATING(5);
    private int value;
    private Integer[][] params = new Integer[100][3];
    private Actions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Integer[][] getParams(int action) {
        if (action == Actions.TSP_COMPUTE.value) {
            return Actions.TSP_COMPUTE.params;
        }
        else if (action == Actions.ADD_TOWN.value) {
            return Actions.ADD_TOWN.params;
        }
        else if (action == Actions.ADD_RAY.value) {
            return Actions.ADD_RAY.params;
        }
        else if (action == Actions.CLEAR.value) {
            return Actions.CLEAR.params;
        }
        else if (action == Actions.REAL_TIME_UPDATING.getValue()) {
            return Actions.REAL_TIME_UPDATING.params;
        }

        return null;
    }

    public void setParams(Integer[][] params) {
        this.params = params;
    }

    public void resetParams() {
        this.params = new Integer[100][3];
    }
}

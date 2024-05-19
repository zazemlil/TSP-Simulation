package View.utilz;

import Model.utilz.ErrorCodes;

public enum Actions {
    TSP_COMPUTE(1), ADD_TOWN(2), ADD_RAY(3), CLEAR(4);
    private int value;
    private Integer[][] params = new Integer[100][3];
    private Actions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Integer[][] getParams(int errCode) {
        if (errCode == Actions.TSP_COMPUTE.value) {
            return Actions.TSP_COMPUTE.params;
        }
        if (errCode == Actions.ADD_TOWN.value) {
            return Actions.ADD_TOWN.params;
        }
        if (errCode == Actions.ADD_RAY.value) {
            return Actions.ADD_RAY.params;
        }
        if (errCode == Actions.CLEAR.value) {
            return Actions.CLEAR.params;
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

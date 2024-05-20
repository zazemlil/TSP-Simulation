package Model.utilz;

public enum ErrorCodes {
    PATH_NOT_FOUND(-1, "Error: can not find path"),
    NOT_ALL_TOWNS_CONNECTED(-2, "Error: not all towns are connected"),
    NO_TOWNS(-3, "Error: no towns");
    private final int value;
    private final String info;
    private ErrorCodes(int value, String info) {
        this.value = value;
        this.info = info;
    }

    public int getValue() {
        return value;
    }

    public static String getInfo(int errCode) {
        if (errCode == ErrorCodes.PATH_NOT_FOUND.getValue()) {
            return ErrorCodes.PATH_NOT_FOUND.info;
        }
        if (errCode == ErrorCodes.NOT_ALL_TOWNS_CONNECTED.getValue()) {
            return ErrorCodes.NOT_ALL_TOWNS_CONNECTED.info;
        }
        if (errCode == ErrorCodes.NO_TOWNS.getValue()) {
            return ErrorCodes.NO_TOWNS.info;
        }

        return "";
    }
}

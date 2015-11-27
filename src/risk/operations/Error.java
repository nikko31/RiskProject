package risk.operations;

/**
 * Created by DarkLinux on 27/11/15.
 */
public class Error implements Operation {
    String errorStr;

    public Error(String errorStr) {
        this.errorStr = errorStr;
    }

    public String getErrorStr() {
        return errorStr;
    }

    @Override
    public String operationString() {
        return "ERROR: " + this.errorStr;
    }
}

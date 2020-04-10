package common;

public class RequestResult {
    private boolean result;
    private String failMessage;

    public static RequestResult Complete(){
        return new RequestResult(true);
    }

    public static RequestResult Fail(String msg){
        return new RequestResult(false, msg);
    }

    private RequestResult(boolean complete) {
        this.result = complete;
    }

    private RequestResult(boolean complete, String failMessage) {
        this.result = complete;
        this.failMessage = failMessage;
    }

    public boolean isComplete() {
        return result;
    }

    public String getFailMessage() {
        return failMessage;
    }
}

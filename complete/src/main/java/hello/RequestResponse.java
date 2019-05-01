package hello;

public class RequestResponse {
    private boolean successfulRequest;
    private Long tempBalance;
    private String requestType;

    public RequestResponse() {
        super();
    }
    public RequestResponse(boolean successfulRequest, Long tempBalance, String requestType) {
        super();
        this.successfulRequest = successfulRequest;
        this.tempBalance = tempBalance;
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public Long getTempBalance() {
        return tempBalance;
    }
    public void setTempBalance(Long tempBalance) {
        this.tempBalance = tempBalance;
    }
    public boolean getSuccessfulRequest() {
        return successfulRequest;
    }
    public void setSuccessfulRequest(boolean successfulRequest) {
        this.successfulRequest = successfulRequest;
    }
    @Override
    public String toString() {
        return String.format("RequestResponse [successfulRequest=%s, tempBalance=%s, requestType=%s]", successfulRequest, tempBalance, requestType);
    }
}
package dtos.responses;

public class APIResponse {
    private boolean success;
    private Object data;

    public APIResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }
}

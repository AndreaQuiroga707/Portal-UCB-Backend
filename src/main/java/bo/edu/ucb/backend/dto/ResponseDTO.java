package bo.edu.ucb.backend.dto;

public class ResponseDTO {
    private Integer status;
    private String message;
    private String error;
    private Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(Integer status, String message, String error, Object data) {
        this.status = status;
        this.message = message;
        this.error = error;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" + "status=" + status + ", message=" + message + ", error=" + error + ", data=" + data + '}';
    }
}

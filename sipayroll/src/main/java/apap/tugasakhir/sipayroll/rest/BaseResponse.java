package apap.tugasakhir.sipayroll.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse{
    private int status;
    private String message;
    private PegawaiDTO result;

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @return the result
     */
    public PegawaiDTO getResult() {
        return result;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @param result the result to set
     */
    public void setResult(PegawaiDTO result) {
        this.result = result;
    }
}
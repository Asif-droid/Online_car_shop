package util;

import java.io.Serializable;

public class rqst_class implements Serializable {
    String rqst;
    String text;

    public void setRqst(String rqst) {
        this.rqst = rqst;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRqst() {
        return rqst;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "rqst_class{" +
                "rqst='" + rqst + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

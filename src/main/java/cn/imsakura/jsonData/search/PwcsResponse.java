package cn.imsakura.jsonData.search;
import java.util.List;

public class PwcsResponse {



        private int code;
        private String message;
        private List<PwcsUser> result;

        // Getter 和 Setter
        public int getCode() { return code; }
        public void setCode(int code) { this.code = code; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public List<PwcsUser> getResult() { return result; }
        public void setResult(List<PwcsUser> result) { this.result = result; }




}

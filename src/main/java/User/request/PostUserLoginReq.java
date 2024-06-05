package User.request;

public class PostUserLoginReq {
    String email;
    String pw;

    public String getEmail() {
        return email;
    }

    public String getPw() {
        return pw;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}

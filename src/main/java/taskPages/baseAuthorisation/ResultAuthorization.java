package taskPages.baseAuthorisation;

import java.io.Serializable;

public class ResultAuthorization implements Serializable {
    private String user;
    private boolean authenticated;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    @Override
    public String toString() {
        return "ResultAuthorization{" +
                "user='" + user + '\'' +
                ", authenticated=" + authenticated +
                '}';
    }
}

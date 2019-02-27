package mvc.entity;

public class UserInfo {
    private int userId;
    private String username;
    private String password;

    public int getKey() {
        return userId;
    }

    public void setKey(int key) {
        this.userId = key;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

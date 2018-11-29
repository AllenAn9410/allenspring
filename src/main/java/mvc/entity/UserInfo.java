package mvc.entity;

public class UserInfo {
    private int key;
    private String name;
    private String password;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "key=" + key +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

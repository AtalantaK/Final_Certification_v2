package UI.saucedemo.utils;

public enum Users {
    standard_user("standard_user"),
    locked_out_user("locked_out_user"),
    performance_glitch_user("performance_glitch_user");

    private String userName;

    Users(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

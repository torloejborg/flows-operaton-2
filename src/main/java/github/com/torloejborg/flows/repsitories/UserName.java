package github.com.torloejborg.flows.repsitories;

public enum UserName {
    USER_ADMIN("joe"),
    USER_SUPPLIER("john"),
    USER_CUSTOMER("jane");

    public final String name;

    UserName(String name) {
        this.name = name;
    }
}

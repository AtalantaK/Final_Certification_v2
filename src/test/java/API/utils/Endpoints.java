package API.utils;

public enum Endpoints {
    EMPLOYEE("employee"),
    NAME("name"),
    EMPLOYEES("employees"),
    AUTH("login");

    public static final String URI = "https://innopolispython.onrender.com/";

    private String endpointName;

    Endpoints(String endpointName) {
        this.endpointName = endpointName;
    }

    public String getEndpointName() {
        return endpointName;
    }
}

package API.utils;

import API.models.EmployeeRequest;

public class RequestFactory {

    public static EmployeeRequest createEmployeeRequestWOName(String city, String position, String surname) {
        return EmployeeRequest.builder().city(city).position(position).surname(surname).build();
    }

    public static EmployeeRequest createEmployeeRequestWOSurnamePosition(String city, String name) {
        return EmployeeRequest.builder().city(city).name(name).build();
    }

    public static EmployeeRequest createEmployeeRequestWOCity(String name, String position, String surname) {
        return EmployeeRequest.builder().name(name).position(position).surname(surname).build();
    }

    public static EmployeeRequest createEmployeeRequest(String city, String name, String position, String surname) {
        return EmployeeRequest.builder().city(city).name(name).position(position).surname(surname).build();
    }

    public static EmployeeRequest createEmployeeOnlySurname(String surname) {
        return EmployeeRequest.builder().surname(surname).build();
    }

    public static EmployeeRequest createEmployeeOnlyPosition(String position) {
        return EmployeeRequest.builder().position(position).build();
    }

    public static EmployeeRequest createEmployeeOnlyName(String name) {
        return EmployeeRequest.builder().name(name).build();
    }

    public static EmployeeRequest createEmployeeOnlyCity(String city) {
        return EmployeeRequest.builder().city(city).build();
    }
}

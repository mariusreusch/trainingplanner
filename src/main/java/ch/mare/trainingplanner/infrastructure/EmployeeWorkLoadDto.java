package ch.mare.trainingplanner.infrastructure;

import ch.mare.trainingplanner.common.OnlyForFramework;

public class EmployeeWorkLoadDto {
    private String employeeName;
    private double workloadInPercentage;

    @OnlyForFramework
    private EmployeeWorkLoadDto() {
    }

    public EmployeeWorkLoadDto(String employeeName, double workloadInPercentage) {
        this.employeeName = employeeName;
        this.workloadInPercentage = workloadInPercentage;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getWorkloadInPercentage() {
        return workloadInPercentage;
    }
}

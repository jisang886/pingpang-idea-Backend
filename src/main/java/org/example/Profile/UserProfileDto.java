package org.example.Profile;


public class UserProfileDto {
    private String name;
    private String gender;
    private Integer age;
    private Double height;
    private Double weight;
    private String trainingPlan;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getTrainingPlan() { return trainingPlan; }
    public void setTrainingPlan(String trainingPlan) { this.trainingPlan = trainingPlan; }
}

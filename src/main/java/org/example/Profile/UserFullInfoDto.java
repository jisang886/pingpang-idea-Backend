package org.example.Profile;

public class UserFullInfoDto {
    private Long userId;
    private String username;

    private String name;
    private String gender;
    private Integer age;
    private Double height;
    private Double weight;
    private String trainingPlan;

    // 构造器
    public UserFullInfoDto() {}

    // Getter & Setter
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

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

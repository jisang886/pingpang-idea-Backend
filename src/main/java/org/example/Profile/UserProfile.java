package org.example.Profile;


import jakarta.persistence.*;
import org.example.login.User;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String gender; // 男 / 女

    private Integer age;

    private Double height;

    private Double weight;

    @Column(name = "training_plan")
    private String trainingPlan;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

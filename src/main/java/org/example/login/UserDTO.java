package org.example.login;

public class UserDTO {
    private Long id;
    private String username;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}

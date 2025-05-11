package org.example.Profile;

import org.example.login.User;
import org.example.login.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository profileRepository;

    // 保存用户基础信息
    @PostMapping("/save")
    public String save(@RequestBody UserProfileDto dto, HttpSession session) {
        // 获取当前登录用户的ID
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "Not logged in";
        }

        // 根据 userId 获取用户
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return "User not found";

        // 获取当前用户的资料，如果没有则创建新的资料
        UserProfile profile = profileRepository.findByUserId(user.getId());
        if (profile == null) profile = new UserProfile();

        // 复制 DTO 数据到 UserProfile
        BeanUtils.copyProperties(dto, profile);
        profile.setUser(user);  // 设置用户与资料的关系
        profileRepository.save(profile);

        return "{\"code\":200,\"msg\":\"Profile saved successfully！\"}";

    }

    // 获取当前用户的资料
    @GetMapping("/me")
    public UserProfile getMyProfile(HttpSession session) {
        // 获取当前登录用户的ID
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return null;

        // 根据 userId 获取用户
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        // 获取用户的资料
        return profileRepository.findByUserId(user.getId());
    }

    // 获取当前登录用户的完整信息（包括用户名和资料）
    @GetMapping("/full")
    public Object getFullUserInfo(HttpSession session) {
        // 获取当前登录用户的ID
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "Not logged in";

        // 根据 userId 获取用户
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return "User not found";

        // 获取用户资料
        UserProfile profile = profileRepository.findByUserId(user.getId());

        UserFullInfoDto dto = new UserFullInfoDto();
        dto.setUserId(user.getId());
        dto.setUsername(user.getUsername());

        if (profile != null) {
            dto.setName(profile.getName());
            dto.setGender(profile.getGender());
            dto.setAge(profile.getAge());
            dto.setHeight(profile.getHeight());
            dto.setWeight(profile.getWeight());
            dto.setTrainingPlan(profile.getTrainingPlan());
        }

        return dto;
    }
}

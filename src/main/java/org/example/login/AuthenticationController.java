package org.example.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 注册
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        // 检查用户是否已经存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
//            return "Username already exists";
            return "{\"code\":400,\"msg\":\"该用户名已被占用！\"}";
        }
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
//        return "User registered successfully";
        return "{\"code\":201,\"msg\":\"用户注册成功！\"}";
    }

    // 登录
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        // 查找用户
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null || !passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
//            return "Invalid username or password";
            return "{\"code\":401,\"msg\":\"账号或密码错误！\"}";
        }



        // 登录成功，将用户信息保存到 HttpSession
        session.setAttribute("userId", foundUser.getId());
        session.setAttribute("username", foundUser.getUsername());

//        return "Login successful";
        return "{\"code\": 200, \"msg\": \"登录成功！\"}";
    }


    // 登出
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 清除所有 session 数据

        return "{\"code\": 200, \"msg\": \"退出登录成功！\"}";
       // return "Logout successful";
    }


//    @PutMapping("/update")
//    public Object updateUser(@RequestBody UpdateUserRequest1 request, HttpSession session) {
//        Long userId = (Long) session.getAttribute("userId");
//        if (userId == null) {
//            return "No user is logged in";
//        }
//
//        User user = userRepository.findById(userId).orElse(null);
//        if (user == null) {
//            return "User not found";
//        }
//
//        // 检查新用户名是否已被其他人使用
//        if (!user.getUsername().equals(request.getNewUsername()) &&
//                userRepository.findByUsername(request.getNewUsername()) != null) {
//            return "Username already taken";
//        }
//
//        // 更新用户名和加密后的密码
//        user.setUsername(request.getNewUsername());
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        userRepository.save(user);
//
//        // 更新 session 中的用户名
//        session.setAttribute("username", user.getUsername());
//
//        return new UserDTO(user.getId(), user.getUsername());
//    }






    @PutMapping("/update")
    public Object updateUser(@RequestBody UpdateUserRequest1 request, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "No user is logged in";
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found";
        }

        // 处理修改用户名
        String newUsername = request.getNewUsername();
        if (newUsername != null && !newUsername.equals(user.getUsername())) {
            if (userRepository.findByUsername(newUsername) != null) {
                return "Username already taken";
            }
            user.setUsername(newUsername);
            session.setAttribute("username", newUsername); // 同时更新 session
        }

        // 处理修改密码
        String newPassword = request.getNewPassword();
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepository.save(user);
        return new UserDTO(user.getId(), user.getUsername());
    }












    // 获取用户信息（前端通过传用户名来查询）
//    @GetMapping("/userinfo")
//    public Object getUserInfo(@RequestParam String username) {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return "User not found";
//        }
//        return new UserDTO(user.getId(), user.getUsername());
//    }

    // 获取当前登录用户的用户信息
    @GetMapping("/currentUser")
    public Object getCurrentUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "No user is logged in";
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return "User not found";
        }

        return new UserDTO(user.getId(), user.getUsername());
    }
}


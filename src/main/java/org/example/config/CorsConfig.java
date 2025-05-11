package org.example.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// 在Spring Boot配置类中添加
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/auth/**")  // 允许登录注册接口跨域
                .allowedOrigins("http://localhost:8000")  // 前端实际地址
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true);  // 允许携带凭证


//        registry.addMapping("/api/wenxin/chat")  // 允许文心对话接口跨域
//                .allowedOrigins("http://localhost:8000")  // 前端实际地址
//                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
//                .allowCredentials(true);  // 允许携带凭证
//
//
//        registry.addMapping("/api/video/upload")  // 允许视频保存接口跨域
//                .allowedOrigins("http://localhost:8000")  // 前端实际地址
//                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
//                .allowCredentials(true);  // 允许携带凭证

        registry.addMapping("/api/**")  // 允许视频保存接口跨域
                .allowedOrigins("http://localhost:8000")  // 前端实际地址
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true);  // 允许携带凭证

        registry.addMapping("/delete/{id}")  // 允许视频保存接口跨域
                .allowedOrigins("http://localhost:8000")  // 前端实际地址
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true);  // 允许携带凭证

        registry.addMapping("/api/training/update/{id}")  // 允许视频保存接口跨域
                .allowedOrigins("http://localhost:8000")  // 前端实际地址
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true);  // 允许携带凭证


        registry.addMapping("/profile/full")  // 允许视频保存接口跨域
                .allowedOrigins("http://localhost:8000")  // 前端实际地址
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true);  // 允许携带凭证


        registry.addMapping("/profile/save")  // 允许视频保存接口跨域
                .allowedOrigins("http://localhost:8000")  // 前端实际地址
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowCredentials(true);  // 允许携带凭证


    }
}

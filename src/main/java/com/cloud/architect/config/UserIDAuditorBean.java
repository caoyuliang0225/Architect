//package com.cloud.architect.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Optional;
//
///**
// * Created by Cao Yuliang on 2019-07-09.
// */
//@Configuration
//public class UserIDAuditorBean implements AuditorAware<Long> {
//
//    @Override
//    public Optional<Long> getCurrentAuditor() {
//
//        SecurityContext ctx = SecurityContextHolder.getContext();
//        if (ctx == null) {
//            return Optional.empty();
//        }
//        if (ctx.getAuthentication() == null) {
//            return Optional.empty();
//        }
//        if (ctx.getAuthentication().getPrincipal() == null) {
//            return Optional.empty();
//        }
//        Object principal = ctx.getAuthentication().getPrincipal();
//        if (principal.getClass().isAssignableFrom(Long.class)) {
//            return Optional.of((Long) principal);
//        } else {
//            return Optional.empty();
//        }
//    }
//}

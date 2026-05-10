package com.ebac.exerc_44_ebac_auth.controller;

import com.ebac.exerc_44_ebac_auth.service.SessionManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProtectedController {

    private final SessionManager sessionManager;

    public ProtectedController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/protected/message")
    public ResponseEntity<Map<String, String>> getProtectedMessage(@RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "Você não fez login"));
        }

        String token = authorization.replace("Bearer ", "");

        if (!sessionManager.isValid(token)) {
            return ResponseEntity.status(401).body(Map.of("error", "Você não fez login"));
        }

        return ResponseEntity.ok(Map.of("message", "Acesso garantido"));
    }
}

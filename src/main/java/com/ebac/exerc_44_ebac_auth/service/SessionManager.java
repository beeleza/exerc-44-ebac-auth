package com.ebac.exerc_44_ebac_auth.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    private final Map<String, LocalDateTime> sessions = new ConcurrentHashMap<>();

    public String createSession() {
        String token = UUID.randomUUID().toString();
        sessions.put(token, LocalDateTime.now().plusMinutes(1));
        return token;
    }

    public boolean isValid(String token) {
        LocalDateTime expiry = sessions.get(token);
        if (expiry == null) return false;
        if (LocalDateTime.now().isAfter(expiry)) {
            sessions.remove(token);
            return false;
        }
        return true;
    }

    public void removeSession(String token) {
        sessions.remove(token);
    }
}

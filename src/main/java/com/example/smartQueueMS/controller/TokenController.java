package com.example.smartQueueMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartQueueMS.entity.Token;
import com.example.smartQueueMS.service.TokenService;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/book")
    public ResponseEntity<Token> bookToken(@RequestParam Long userId,
                                           @RequestParam Long serviceId) {
        return ResponseEntity.ok(tokenService.bookToken(userId, serviceId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Token> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(tokenService.getTokenStatus(id));
    }

    @GetMapping("/queue/{serviceId}")
    public ResponseEntity<List<Token>> getQueue(@PathVariable Long serviceId) {
        return ResponseEntity.ok(tokenService.getLiveQueue(serviceId));
    }

    @PostMapping("/next/{serviceId}")
    public ResponseEntity<Token> next(@PathVariable Long serviceId) {
        return ResponseEntity.ok(tokenService.callNextToken(serviceId));
    }

    @PostMapping("/skip/{tokenId}")
    public ResponseEntity<String> skip(@PathVariable Long tokenId) {
        tokenService.skipToken(tokenId);
        return ResponseEntity.ok("Skipped");
    }
}
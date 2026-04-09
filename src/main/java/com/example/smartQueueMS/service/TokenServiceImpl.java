package com.example.smartQueueMS.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.smartQueueMS.entity.Token;
import com.example.smartQueueMS.entity.TokenStatus;
import com.example.smartQueueMS.repository.ServiceRepository;
import com.example.smartQueueMS.repository.TokenRepository;
import com.example.smartQueueMS.repository.UserRepository;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Token bookToken(Long userId, Long serviceId) {

        Integer nextTokenNumber = tokenRepository
                .findTopByServiceIdOrderByTokenNumberDesc(serviceId)
                .map(t -> t.getTokenNumber() + 1)
                .orElse(1);

        Token token = new Token();
        token.setTokenNumber(nextTokenNumber);
        token.setStatus(TokenStatus.WAITING);
        token.setCreatedTime(LocalDateTime.now());

        token.setUser(userRepository.findById(userId).orElseThrow());
        token.setService(serviceRepository.findById(serviceId).orElseThrow());

        return tokenRepository.save(token);
    }

    @Override
    public Token getTokenStatus(Long tokenId) {
        return tokenRepository.findById(tokenId).orElseThrow();
    }

    @Override
    public List<Token> getLiveQueue(Long serviceId) {
        return tokenRepository.findByServiceIdAndStatusOrderByTokenNumberAsc(serviceId, TokenStatus.WAITING);
    }

    @Override
    public Token callNextToken(Long serviceId) {

        List<Token> queue = getLiveQueue(serviceId);

        if (queue.isEmpty()) throw new RuntimeException("No tokens in queue");

        Token next = queue.get(0);
        next.setStatus(TokenStatus.SERVING);

        return tokenRepository.save(next);
    }

    @Override
    public void skipToken(Long tokenId) {
        Token token = tokenRepository.findById(tokenId).orElseThrow();
        token.setStatus(TokenStatus.SKIPPED);
        tokenRepository.save(token);
    }
}
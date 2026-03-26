package com.example.smartQueueMS.service;

import java.util.List;

import com.example.smartQueueMS.entity.Token;

public interface TokenService {

    Token bookToken(Long userId, Long serviceId);

    Token getTokenStatus(Long tokenId);

    List<Token> getLiveQueue(Long serviceId);

    Token callNextToken(Long serviceId);

    void skipToken(Long tokenId);
}
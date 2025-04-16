package com.seuprojeto.controller;

import com.seuprojeto.service.SocialMentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/social")
public class SocialMentionController {


    @Autowired
    private SocialMentionService socialMentionService;

    // Endpoint para buscar menções no Twitter
    @GetMapping("/twitter")
    public Mono<String> getTwitterMentions(@RequestParam String query) {
        return socialMentionService.fetchTwitterMentions(query);
    }

    // Endpoint para buscar menções no Instagram
    @GetMapping("/instagram")
    public Mono<String> getInstagramMentions(@RequestParam String hashtag) {
        return socialMentionService.fetchInstagramMentions(hashtag);
    }
}

package com.seuprojeto.repository;

import com.seuprojeto.model.SocialMention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMentionRepository extends JpaRepository<SocialMention, Long> {
}

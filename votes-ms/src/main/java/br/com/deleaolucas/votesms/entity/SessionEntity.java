package br.com.deleaolucas.votesms.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @OneToOne()
    @JoinColumn(name = "topic-voting-id")
    private TopicVotingEntity topicVotingEntity;

    private LocalDateTime startingVoting;

    private LocalDateTime finalVoting;

    private Boolean produceMessage;

    public SessionEntity() {
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public TopicVotingEntity getTopicVoting() {
        return topicVotingEntity;
    }

    public void setTopicVoting(TopicVotingEntity topicVotingEntity) {
        this.topicVotingEntity = topicVotingEntity;
    }

    public LocalDateTime getStartingVoting() {
        return startingVoting;
    }

    public void setStartingVoting(LocalDateTime startingVoting) {
        this.startingVoting = startingVoting;
    }

    public LocalDateTime getFinalVoting() {
        return finalVoting;
    }

    public void setFinalVoting(LocalDateTime finalVoting) {
        this.finalVoting = finalVoting;
    }

    public Boolean isProduceMessage() {
        return produceMessage;
    }

    public void setProduceMessage(Boolean produceMessage) {
        this.produceMessage = produceMessage;
    }

}
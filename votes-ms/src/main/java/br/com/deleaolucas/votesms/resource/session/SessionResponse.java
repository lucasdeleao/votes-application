package br.com.deleaolucas.votesms.resource.session;

import br.com.deleaolucas.votesms.entity.SessionEntity;
import br.com.deleaolucas.votesms.entity.TopicVotingEntity;

import java.time.LocalDateTime;

public class SessionResponse {

    private Long sessionId;
    private TopicVotingEntity topicVotingEntity;
    private LocalDateTime startingVoting;
    private LocalDateTime finalVoting;

    public SessionResponse(SessionEntity sessionEntity) {
        sessionId = sessionEntity.getSessionId();
        topicVotingEntity = sessionEntity.getTopicVoting();
        startingVoting = sessionEntity.getStartingVoting();
        finalVoting = sessionEntity.getFinalVoting();
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public TopicVotingEntity getTopicVotingEntity() {
        return topicVotingEntity;
    }

    public void setTopicVotingEntity(TopicVotingEntity topicVotingEntity) {
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
}

package br.com.deleaolucas.votesms.service;

import br.com.deleaolucas.votesms.entity.SessionEntity;
import br.com.deleaolucas.votesms.entity.TopicVotingEntity;
import br.com.deleaolucas.votesms.repository.SessionRepository;
import br.com.deleaolucas.votesms.repository.TopicVotingRepository;
import br.com.deleaolucas.votesms.resource.session.SessionRequest;
import br.com.deleaolucas.votesms.resource.session.SessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.web.servlet.function.ServerResponse.notFound;

@Service
public class SessionService {

    private TopicVotingRepository topicVotingRepository;
    private SessionRepository sessionRepository;

    @Autowired
    public SessionService(TopicVotingRepository topicVotingRepository, SessionRepository sessionRepository) {
        this.topicVotingRepository = topicVotingRepository;
        this.sessionRepository = sessionRepository;
    }

    public Mono<Mono<SessionResponse>> openSession(final Long topicId, final SessionRequest sessionRequest) {
        return topicVotingRepository.findById(topicId)
                .map(x-> create(x, LocalDateTime.now(), sessionRequest.getFinalVoting()))
                .map(x-> save(x)
                .map(SessionResponse::new)
                .switchIfEmpty((Mono<? extends SessionResponse>) notFound().build()));
    }

    public SessionEntity create(final TopicVotingEntity topicVotingEntity, final LocalDateTime startingVoting, final LocalDateTime finalVoting) {
        final SessionEntity sessionEntity = new SessionEntity();

        sessionEntity.setTopicVoting(topicVotingEntity);
        sessionEntity.setStartingVoting(startingVoting);
        sessionEntity.setFinalVoting(finalVoting);
        return sessionEntity;
    }

    public Mono<SessionEntity> save(SessionEntity sessionEntity) {
        return sessionRepository.save(sessionEntity);
    }

}

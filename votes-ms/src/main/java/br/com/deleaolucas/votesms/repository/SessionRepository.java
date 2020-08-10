package br.com.deleaolucas.votesms.repository;

import br.com.deleaolucas.votesms.entity.SessionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.List;

public interface SessionRepository extends ReactiveCrudRepository<SessionEntity, Long> {
    List<SessionEntity> findByProduceMessageFalseOrProduceMessage(Boolean produceMessage);
}

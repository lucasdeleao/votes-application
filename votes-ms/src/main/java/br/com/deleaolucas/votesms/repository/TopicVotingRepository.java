package br.com.deleaolucas.votesms.repository;

import br.com.deleaolucas.votesms.entity.TopicVotingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TopicVotingRepository extends ReactiveCrudRepository<TopicVotingEntity, Long> {
}

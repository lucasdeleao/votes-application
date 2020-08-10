package br.com.deleaolucas.votesms.repository;

import br.com.deleaolucas.votesms.entity.VotesEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VotesRepository extends ReactiveCrudRepository<VotesEntity, Long> {
}

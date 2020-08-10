package br.com.deleaolucas.votesms.service;

import br.com.deleaolucas.votesms.entity.TopicVotingEntity;
import br.com.deleaolucas.votesms.repository.TopicVotingRepository;
import br.com.deleaolucas.votesms.resource.topicvoting.TopicVotingRequest;
import br.com.deleaolucas.votesms.resource.topicvoting.TopicVotingResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class TopicVotingService {

    private TopicVotingRepository topicVotingRepository;

    @Autowired
    public TopicVotingService(TopicVotingRepository topicVotingRepository){
        this.topicVotingRepository = topicVotingRepository;
    }

    public TopicVotingEntity create(final TopicVotingRequest topicVotingRequest){
        final TopicVotingEntity topicVotingEntity = new TopicVotingEntity();
        topicVotingEntity.setDescription(topicVotingRequest.getDescription());
        return  topicVotingEntity;
    }

    public TopicVotingResponse save(final TopicVotingRequest topicVotingRequest){
        final TopicVotingEntity topicVotingEntity = create(topicVotingRequest);
        return topicVotingRepository.save(topicVotingEntity).map(TopicVotingResponse::new).block();
    }

}

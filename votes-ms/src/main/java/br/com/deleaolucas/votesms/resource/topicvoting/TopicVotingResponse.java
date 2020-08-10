package br.com.deleaolucas.votesms.resource.topicvoting;

import br.com.deleaolucas.votesms.entity.TopicVotingEntity;

public class TopicVotingResponse {

    private String description;

    public TopicVotingResponse(TopicVotingEntity topicVotingEntity) {
        description = topicVotingEntity.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

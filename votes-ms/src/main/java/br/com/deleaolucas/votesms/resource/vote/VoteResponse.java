package br.com.deleaolucas.votesms.resource.vote;

import br.com.deleaolucas.votesms.entity.TopicVotingEntity;

public class VoteResponse {

    private TopicVotingEntity topicVoting;

    private int resultYes;
    private int resultNo;

    public VoteResponse(TopicVotingEntity topicVoting, int resultYes, int resultNo) {
        this.topicVoting = topicVoting;
        this.resultYes = resultYes;
        this.resultNo = resultNo;
    }

    public TopicVotingEntity getTopicVoting() {
        return topicVoting;
    }

    public void setTopicVoting(TopicVotingEntity topicVoting) {
        this.topicVoting = topicVoting;
    }

    public int getResultYes() {
        return resultYes;
    }

    public void setResultYes(int resultYes) {
        this.resultYes = resultYes;
    }

    public int getResultNo() {
        return resultNo;
    }

    public void setResultNo(int resultNo) {
        this.resultNo = resultNo;
    }

}

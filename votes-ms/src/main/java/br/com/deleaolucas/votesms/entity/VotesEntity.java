package br.com.deleaolucas.votesms.entity;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class VotesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @OneToOne()
    @JoinColumn(name = "topic-voting-id")
    private TopicVotingEntity topicVotingEntity;

    private String vote;

    public VotesEntity() {
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public TopicVotingEntity getTopicVotingEntity() {
        return topicVotingEntity;
    }

    public void setTopicVotingEntity(TopicVotingEntity topicVotingEntity) {
        this.topicVotingEntity = topicVotingEntity;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

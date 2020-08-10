package br.com.deleaolucas.votesms.entity;

import javax.persistence.*;

@Entity
@Table(name = "topic-voting")
public class TopicVotingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic-voting-id")
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToOne(mappedBy = "topicVotingEntity")
    private SessionEntity session;

    public TopicVotingEntity() {
    }

    public TopicVotingEntity(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
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

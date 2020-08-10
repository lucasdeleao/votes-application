package br.com.deleaolucas.votesms.service;

import br.com.deleaolucas.votesms.client.RestClient;
import br.com.deleaolucas.votesms.entity.SessionEntity;
import br.com.deleaolucas.votesms.entity.VotesEntity;
import br.com.deleaolucas.votesms.repository.SessionRepository;
import br.com.deleaolucas.votesms.repository.TopicVotingRepository;
import br.com.deleaolucas.votesms.repository.VotesRepository;
import br.com.deleaolucas.votesms.resource.vote.VoteRequest;
import br.com.deleaolucas.votesms.resource.vote.VoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Streamable;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VoteService {

    @Value("${THE_SESSION_NOT_EXISTS:${the.session.not.exist}}")
    private String THE_SESSION_NOT_EXISTS;

    private VotesRepository votesRepository;
    private TopicVotingRepository topicVotingRepository;
    private SessionRepository sessionRepository;
    private VoteResponse voteResponse;
    private RestClient restClient;

    @Autowired
    public VoteService(VotesRepository votesRepository, TopicVotingRepository topicVotingRepository,
                       SessionRepository sessionRepository, VoteResponse voteResponse, RestClient restClient) {
        this.votesRepository = votesRepository;
        this.topicVotingRepository = topicVotingRepository;
        this.sessionRepository = sessionRepository;
        this.voteResponse = voteResponse;
        this.restClient = restClient;
    }

    public VotesEntity create(final VoteRequest voteRequest) {
        final VotesEntity votesEntity = new VotesEntity();
        votesEntity.setVote(voteRequest.getVote());
        return votesEntity;
    }

    public VoteResponse executeVote(long topicId, VoteRequest votesRequest) throws Exception {
        final VotesEntity votesEntity = create(votesRequest);

        if (restClient.validateDocument(votesRequest.getVote())) {

            topicVotingRepository.findById(topicId)
                    .map(x -> {
                        if (isSessionOpenOfTopicVoting(x.getId())) {
                            voteResponse = save(votesEntity);
                        }

                        return voteResponse;
                    });
            return voteResponse;
        }
        return voteResponse;
    }

    public VoteResponse save(VotesEntity votesEntity) {
        final ArrayList<String> listVotes = new ArrayList<>();
        final int[] votes = calculatingVotes(listVotes);

        votesRepository.findById(votesEntity.getVoteId())
                .map(x -> listVotes.add(x.getVote()));

        return votesRepository.save(votesEntity).map(
                x -> {
                    VoteResponse voteResponse = new VoteResponse(votesEntity.getTopicVotingEntity(), votes[0], votes[1]);
                    return voteResponse;
                }
        ).block();
    }

    public Boolean isSessionOpenOfTopicVoting(long topicVotingId) {
        Mono<SessionEntity> session = sessionRepository.findById(topicVotingId);
        return session.map(x -> LocalDateTime.now().isBefore(x.getFinalVoting()))
                .switchIfEmpty(IllegalArgumentException(THE_SESSION_NOT_EXISTS));
    }

    public int[] calculatingVotes(final ArrayList<String> listVotes) {
        final int[] votes;
        final Streamable<String> streamable = Streamable.of(listVotes);

        votes = new int[2];
        votes[0] = (int) streamable.filter(c -> c.equals("Sim")).get().count();
        votes[1] = (int) streamable.filter(c -> c.equals("Não")).get().count();
        return votes;
    }

    public VoteResponse finalResult(long topicId) {
        final ArrayList<String> listVotes = new ArrayList<>();
        final int[] votes = calculatingVotes(listVotes);

        votesRepository.findById(topicId)
                .map(x -> {
                    listVotes.add(x.getVote());
                    return voteResponse = new VoteResponse(x.getTopicVotingEntity(), votes[0], votes[1])

                });
        return voteResponse;
    }

    public List<String> doHaveAnOpenSessionThatCanBeClosed() {
        List<SessionEntity> sessionsOpen = sessionRepository.findByProduceMessageFalseOrProduceMessage(null);
        return sessionsOpen.stream()
                .filter(session -> session.getFinalVoting().isBefore(LocalDateTime.now()))
                .map(session -> saveAndReturnTopicVotingDescription(session))
                .collect(Collectors.toList());
    }

    private String saveAndReturnTopicVotingDescription(SessionEntity session) {
        session.setProduceMessage(Boolean.TRUE);
        Mono<SessionEntity> sessionSaved = sessionRepository.save(session);
        return sessionSaved.map(x->x.getTopicVoting().getDescription()).block();
    }

}

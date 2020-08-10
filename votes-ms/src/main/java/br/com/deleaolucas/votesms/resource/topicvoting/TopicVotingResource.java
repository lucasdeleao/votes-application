package br.com.deleaolucas.votesms.resource.topicvoting;

import br.com.deleaolucas.votesms.service.TopicVotingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/${api.version}/topic-voting")
public class TopicVotingResource {

    private TopicVotingService topicVotingService;

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    public TopicVotingResource(TopicVotingService topicVotingService) {
        this.topicVotingService = topicVotingService;
    }

    @PostMapping(value = "", consumes = JSON, produces = JSON)
    public ResponseEntity<TopicVotingResponse> create(@RequestBody TopicVotingRequest request) {
        final TopicVotingResponse response = topicVotingService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}

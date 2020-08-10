package br.com.deleaolucas.votesms.resource.vote;


import br.com.deleaolucas.votesms.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/${api.version}/vote")
public class VoteResource {

    private VoteService voteService;

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    public VoteResource(VoteService voteService) {
        this.voteService = voteService;
    }

    @RequestMapping(value = "/{topicVotingId}/vote", method = RequestMethod.POST)
    public ResponseEntity<VoteResponse> vote(@PathVariable Long topicVotingId, @RequestBody VoteRequest voteRequest) throws Exception {
        return ResponseEntity.ok(voteService.executeVote(topicVotingId, voteRequest));
    }

    @GetMapping(value = "/finalresult/{pautaId}")
    @ResponseBody
    public ResponseEntity<VoteResponse> GetFinalResult(@PathVariable("pautaId") int id) {
        VoteResponse response = voteService.finalResult(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}



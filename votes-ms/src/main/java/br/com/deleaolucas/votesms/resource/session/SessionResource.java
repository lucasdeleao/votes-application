package br.com.deleaolucas.votesms.resource.session;

import br.com.deleaolucas.votesms.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/${api.version}/open-session")
public class SessionResource {

    private SessionService sessionService;

    private static final String JSON = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    public SessionResource(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/{topic-voting-id}", consumes = JSON, produces = JSON)
    public ResponseEntity<Mono<SessionResponse>> open(@PathVariable Long id, @RequestBody SessionRequest sessionRequest) {
        final Mono<Mono<SessionResponse>> mono = sessionService.openSession(id, sessionRequest);
        return mono.map(x -> ResponseEntity
                .status(HttpStatus.OK)
                .body(x)
        ).defaultIfEmpty(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build()
        )
                .block();
    }

}

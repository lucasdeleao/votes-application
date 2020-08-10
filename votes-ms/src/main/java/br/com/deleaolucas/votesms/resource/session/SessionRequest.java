package br.com.deleaolucas.votesms.resource.session;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class SessionRequest {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime finalVoting;

    public SessionRequest() {
    }

    public SessionRequest(LocalDateTime finalVoting) {
        this.finalVoting = finalVoting;
    }

    public LocalDateTime getFinalVoting() {
        return finalVoting;
    }

    public void setFinalVoting(LocalDateTime finalVoting) {
        this.finalVoting = finalVoting;
    }

}

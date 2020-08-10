package br.com.deleaolucas.votesms.resource.vote;

public class VoteRequest {

    private String vote;
    private String cpf;

    public VoteRequest() {
    }

    public VoteRequest(String vote) {
        this.vote = vote;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
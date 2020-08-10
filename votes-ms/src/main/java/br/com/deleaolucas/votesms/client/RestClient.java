package br.com.deleaolucas.votesms.client;

import br.com.deleaolucas.votesms.config.VoteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class RestClient {
    private RestTemplate restTemplate;
    private VoteConfig voteConfig;

    @Autowired
    public RestClient(RestTemplate restTemplate, VoteConfig voteConfig) {
        this.restTemplate = restTemplate;
        this.voteConfig = voteConfig;
    }

    public Boolean validateDocument(String document) throws Exception {
        try {
            URI uri = URI.create(String.format(voteConfig.getUrl(), document));
            UserInfoResponse userInfoResponse = restTemplate.getForObject(uri, UserInfoResponse.class);
            return userInfoResponse.isAbleToVote();
        } catch (HttpClientErrorException httpClientErrorException) {
            throw new Exception(httpClientErrorException);
        }
    }


}

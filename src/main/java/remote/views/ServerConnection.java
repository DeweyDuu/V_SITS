package remote.views;

import org.springframework.web.client.RestTemplate;
import remote.dto.RoundResultDTO;
import remote.server.NetworkedTournament;

import java.util.Arrays;
import java.util.List;

public class ServerConnection {

    private String serverUrl;
    private RestTemplate restTemplate;

    public ServerConnection(String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
    }
    
    
    public List<NetworkedTournament> fetchTournaments() {
        NetworkedTournament[] all = restTemplate.getForObject(
            serverUrl + "/tournaments/all", NetworkedTournament[].class);
        if (all != null) {
            return Arrays.asList(all);
        } else {
            return Arrays.asList();
        }
    }
    
    public List<RoundResultDTO> getMoves(String tournamentId) {
        RoundResultDTO[] moves = restTemplate.getForObject(
            serverUrl + "/tournaments/" + tournamentId + "/moves", RoundResultDTO[].class);
        if (moves != null) {
            return Arrays.asList(moves);
        } else {
            return Arrays.asList();
        }
    }
    
    public void startTournament(String tournamentId) {
        restTemplate.postForObject(
            serverUrl + "/tournaments/" + tournamentId + "/start", null, String.class);
    }
}

package remote.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import remote.dto.RegistrationRequest;
import remote.dto.RoundResultDTO;
import remote.server.NetworkedTournament;

import java.util.Arrays;
import java.util.List;

@Service
public class TournamentServerClient {

    private String serverUrl;
    private RestTemplate restTemplate;
    @Autowired
    public TournamentServerClient(
            @Value("${tournament.server.url:http://localhost:8080}") String serverUrl) {
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
    }
    
    public TournamentServerClient(String serverUrl, RestTemplate restTemplate) {
        this.serverUrl = serverUrl;
        this.restTemplate = restTemplate;
    }
    
    public List<NetworkedTournament> listTournaments() {
        String targetUrl = serverUrl + "/tournaments";
        NetworkedTournament[] activeTournaments = restTemplate.getForObject(targetUrl, NetworkedTournament[].class);
        if (activeTournaments != null) {
            return Arrays.asList(activeTournaments);
        } else {
            return Arrays.asList();
        }
    }
    //sprint3 addition~
    public List<NetworkedTournament> listAllTournaments() {
        String targetUrl = serverUrl + "/tournaments/all";
        NetworkedTournament[] all = restTemplate.getForObject(targetUrl, NetworkedTournament[].class);
        if (all != null) {
            return Arrays.asList(all);
        } else {
            return Arrays.asList();
        }
    }
    
    public List<RoundResultDTO> getMoves(String tournamentId) {
        String targetUrl = serverUrl + "/tournaments/" + tournamentId + "/moves";
        RoundResultDTO[] moves = restTemplate.getForObject(targetUrl, RoundResultDTO[].class);
        if (moves != null) {
            return Arrays.asList(moves);
        } else {
            return Arrays.asList();
        }
    }
    
    public void register(String tournamentId, String name, String ip, int port) {
        RegistrationRequest request = new RegistrationRequest(name, ip, port);
        String targetUrl = serverUrl + "/tournaments/" + tournamentId + "/register";
        restTemplate.postForObject(targetUrl, request, String.class);
        System.out.println("Tournament registered");
    }
}

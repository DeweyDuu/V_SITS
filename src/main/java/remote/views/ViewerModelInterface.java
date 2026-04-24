package remote.views;

import java.util.List;
import remote.dto.RoundResultDTO;
import remote.server.NetworkedTournament;

public interface ViewerModelInterface {
    void connect(String ip, int port);
    void showConnect();
    void showTournamentList();
    void showMoves(String tournamentId);
    List<NetworkedTournament> fetchTournaments();
    List<RoundResultDTO> getMoves(String tournamentId);
    void startTournament(String tournamentId);
}

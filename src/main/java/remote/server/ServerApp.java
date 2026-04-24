package remote.server;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import game.IteratedPrisonersDilemma;
import game.PrisonerAction;
import participants.AlwaysCooperate;
import participants.AlwaysDefect;
import participants.TitForTat;
import tournament.RoundRobin;

@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        SpringApplication.run(ServerApp.class, args);
    }
    
    
    @Bean
    ApplicationRunner initTournaments(TournamentRegistry registry) {
        return args -> {
            NetworkedTournament ipd1 = new NetworkedTournament(
                "ipd1",
                "Cooperate vs TitForTat",
                new RoundRobin(),
                new IteratedPrisonersDilemma(20),
                PrisonerAction::valueOf
            );
            ipd1.addParticipant(new AlwaysCooperate());
            ipd1.addParticipant(new TitForTat());
            registry.add(ipd1);
            System.out.println("Tournament 'ipd1' is open for registration.");
            
            NetworkedTournament ipd2 = new NetworkedTournament(
                "ipd2",
                "Defect vs Cooperate",
                new RoundRobin(),
                new IteratedPrisonersDilemma(20),
                PrisonerAction::valueOf
            );
            ipd2.addParticipant(new AlwaysDefect());
            ipd2.addParticipant(new AlwaysCooperate());
            registry.add(ipd2);
            System.out.println("Tournament 'ipd2' is open for registration.");

            NetworkedTournament ipd3 = new NetworkedTournament(
                "ipd3",
                "Live: All Three Bots",
                new RoundRobin(),
                new IteratedPrisonersDilemma(60),
                PrisonerAction::valueOf
            );
            ipd3.addParticipant(new AlwaysCooperate());
            ipd3.addParticipant(new AlwaysDefect());
            ipd3.addParticipant(new TitForTat());
            registry.add(ipd3);
            
            new Thread(() -> ipd3.start()).start();
            System.out.println("Tournament 'ipd3' is running.");
        };
    }
}

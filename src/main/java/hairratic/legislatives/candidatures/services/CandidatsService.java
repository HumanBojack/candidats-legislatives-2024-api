package hairratic.legislatives.candidatures.services;

import hairratic.legislatives.candidatures.data.Candidat;
import hairratic.legislatives.candidatures.repositories.CandidatsListRepository;
import hairratic.legislatives.candidatures.repositories.CandidatsRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CandidatsService {
    private  final CandidatsRepository candidatsRepository;

    public CandidatsService(CandidatsListRepository candidatsRepository) {
        this.candidatsRepository = candidatsRepository;
    }

    public List<Candidat> getAll(){
        return candidatsRepository.getAll();
    }

    public List<Candidat> getCandidatsForDepartement(String departement){
        return candidatsRepository.getCandidatsForDepartement(departement);
    }

    public List<Candidat> getCandidatsForDepartementAndCirconscription(
            String departement, String circonscription
    ){
        String normalizedCirconscription = this.normalizeCirconscription(departement, circonscription);
        return candidatsRepository.getCandidatsForDepartementAndCirconscription(departement, normalizedCirconscription);
    }

    private String normalizeCirconscription(String departement, String circonscription){
        if(circonscription.length() == 1){
            circonscription = "0" + circonscription;
        }

        if(!circonscription.startsWith(departement) || circonscription.length() < 4){
            circonscription = departement+circonscription;
        }

        return circonscription;
    }
}

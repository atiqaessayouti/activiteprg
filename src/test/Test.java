package test;

import beans.Activite;
import beans.Etudiant;
import beans.ParticipationActivite;
import java.util.Date;
import java.util.List;
import services.ActiviteService;
import services.EtudiantService;
import services.ParticipationActiviteService;

public class Test {
    public static void main(String[] args) {
        ActiviteService activiteService = new ActiviteService();
        EtudiantService etudiantService = new EtudiantService();
        ParticipationActiviteService participationService = new ParticipationActiviteService();

        // Ajouter des activités
        Activite activite1 = new Activite(0, "Séminaire de Gestion", new Date(), "Un séminaire sur la gestion moderne.");
        Activite activite2 = new Activite(0, "Atelier de Programmation", new Date(), "Un atelier pratique sur la programmation.");
        
        activiteService.create(activite1);
        activiteService.create(activite2);

        // Ajouter des étudiants
        Etudiant etudiant1 = new Etudiant(0, "Rami", "Amal", "ra.amal@example.com");
        Etudiant etudiant2 = new Etudiant(0, "Sophie", "Dupont", "sophie    .dupont@example.com");

        if (etudiantService.findByEmail(etudiant1.getEmail()) == null) {
            etudiantService.create(etudiant1);
        }
        
        if (etudiantService.findByEmail(etudiant2.getEmail()) == null) {
            etudiantService.create(etudiant2);
        }

        // Enregistrer des participations
        ParticipationActivite participation1 = new ParticipationActivite(0, activite1, etudiant1);
        ParticipationActivite participation2 = new ParticipationActivite(0, activite2, etudiant2);
        
        participationService.create(participation1);
        participationService.create(participation2);

        // Filtrer les participants par activité
        System.out.println("Participants pour l'activité : " + activite1.getIntitule());
        List<ParticipationActivite> participations = participationService.findAll();
        for (ParticipationActivite participation : participations) {
            if (participation.getActivite().getId() == activite1.getId()) {
                System.out.println("- " + participation.getEtudiant().getNom());
            }
        }

        // Rechercher une activité
        int rechercheId = 1; // ID de l'activité à rechercher
        Activite activiteTrouvee = activiteService.findById(rechercheId);
        if (activiteTrouvee != null) {
            System.out.println("Activité trouvée : " + activiteTrouvee.getIntitule());
        } else {
            System.out.println("Aucune activité trouvée avec l'ID : " + rechercheId);
        }
    }
}
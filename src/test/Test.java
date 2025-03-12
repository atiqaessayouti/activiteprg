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
    private static ActiviteService activiteService = new ActiviteService();
    private static EtudiantService etudiantService = new EtudiantService();
    private static ParticipationActiviteService participationService = new ParticipationActiviteService();

    public static void main(String[] args) {
        // Ajouter une activité
        Activite activite1 = new Activite( "Séminaire de Gestion", new Date(), "Un séminaire sur la gestion moderne.");
        Activite activite2 = new Activite("Atelier de Programmation", new Date(), "Un atelier pratique sur la programmation.");
        ajouterActivite(activite1);
        ajouterActivite(activite2);

        // Ajouter des étudiants
        Etudiant etudiant1 = new Etudiant("Essayouti", "Atiqa", "Essayouti.atiqa@example.com");
        Etudiant etudiant2 = new Etudiant("bidas", "Zineb", "bidas.zineb@example.com");
        ajouterEtudiant(etudiant1);
        ajouterEtudiant(etudiant2);

        // Enregistrer des participations
        enregistrerParticipation(activite1, etudiant1);
        enregistrerParticipation(activite2, etudiant2);

        // Filtrer participants par activité
        filtrerParticipantsParActivite(activite1);

        // Rechercher activité
        rechercherActivite(1); 
    }

    private static void ajouterActivite(Activite activite) {
        activiteService.create(activite);
        System.out.println("Activité ajoutée : " + activite.getIntitule());
    }

    private static void ajouterEtudiant(Etudiant etudiant) {
        if (etudiantService.findByEmail(etudiant.getEmail()) == null) {
            etudiantService.create(etudiant);
            System.out.println("Étudiant ajouté : " + etudiant.getNom());
        } else {
            System.out.println("L'étudiant avec l'email " + etudiant.getEmail() + " existe déjà.");
        }
    }

    private static void enregistrerParticipation(Activite activite, Etudiant etudiant) {
        ParticipationActivite participation = new ParticipationActivite(activite, etudiant);
        if (participationService.create(participation)) {
            System.out.println("Participation enregistrée pour : " + etudiant.getNom() + " à l'activité " + activite.getIntitule());
        } else {
            System.out.println("Erreur lors de l'enregistrement de la participation pour : " + etudiant.getNom());
        }
    }

    private static void filtrerParticipantsParActivite(Activite activite) {
        System.out.println("Participants pour l'activité : " + activite.getIntitule());
        List<ParticipationActivite> participations = participationService.findAll();
        for (ParticipationActivite participation : participations) {
            if (participation.getActivite().getId() == activite.getId()) {
                System.out.println("- " + participation.getEtudiant().getNom());
            }
        }
    }

    private static void rechercherActivite(int rechercheId) {
        Activite activiteTrouvee = activiteService.findById(rechercheId);
        if (activiteTrouvee != null) {
            System.out.println("Activité trouvée : " + activiteTrouvee.getIntitule());
        } else {
            System.out.println("Aucune activité trouvée avec l'ID : " + rechercheId);
        }
    }
}
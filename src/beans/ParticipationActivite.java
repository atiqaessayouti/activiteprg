package beans;

/**
 *
 * @author HP
 */
public class ParticipationActivite {

    private Activite activite;
    private Etudiant etudiant;

    public ParticipationActivite(Activite activite, Etudiant etudiant) {
        this.activite = activite;
        this.etudiant = etudiant;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
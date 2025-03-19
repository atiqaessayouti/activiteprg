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

    public ParticipationActivite(int id_activite, int id_etudiant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
package services;

import beans.ParticipationActivite;
import connexion.Connexion;
import beans.Activite;
import beans.Etudiant;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service pour la gestion des participations aux activités.
 */
public class ParticipationActiviteService implements IDao<ParticipationActivite> {

    private Connexion connexion;
    private ActiviteService activiteService;
    private EtudiantService etudiantService;

    public ParticipationActiviteService() {
        connexion = Connexion.getInstance();
        activiteService = new ActiviteService();
        etudiantService = new EtudiantService();
    }

    @Override
    public boolean create(ParticipationActivite participation) {
        String req = "INSERT INTO participationactivite (activite_id, etudiant_id) VALUES (?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, participation.getActivite().getId());
            ps.setInt(2, participation.getEtudiant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la création de la participation : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(ParticipationActivite participation) {
        // Note: This method assumes participation has a unique identifier to delete.
        String req = "DELETE FROM participationactivite WHERE activite_id = ? AND etudiant_id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, participation.getActivite().getId());
            ps.setInt(2, participation.getEtudiant().getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de la participation : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public ParticipationActivite findById(int id) {
        // This method may need to be redefined or removed since there's no unique ID now.
        return null; // Adjust as necessary based on your application's logic.
    }

    @Override
    public List<ParticipationActivite> findAll() {
        List<ParticipationActivite> participations = new ArrayList<>();
        String req = "SELECT * FROM participationactivite";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Activite activite = activiteService.findById(rs.getInt("activite_id"));
                Etudiant etudiant = etudiantService.findById(rs.getInt("etudiant_id"));
                participations.add(new ParticipationActivite(activite, etudiant));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des participations : " + ex.getMessage());
        }
        return participations;
    }

    @Override
    public boolean update(ParticipationActivite o) {
        throw new UnsupportedOperationException("Not supported yet."); // To be implemented if needed
    }
}
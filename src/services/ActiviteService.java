package services;

import beans.Activite;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service pour la gestion des activités.
 */
public class ActiviteService implements IDao<Activite> {
    private final Connexion connexion;

    public ActiviteService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Activite o) {
        String req = "INSERT INTO Activité (id, intitule, date, description) VALUES (null, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getIntitule());
            ps.setDate(2, new java.sql.Date(o.getDate().getTime()));
            ps.setString(3, o.getDescription());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Activite o) {
        String req = "DELETE FROM Activité WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Activite o) {
        String req = "UPDATE Activité SET intitule = ?, date = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getIntitule());
            ps.setDate(2, new java.sql.Date(o.getDate().getTime()));
            ps.setString(3, o.getDescription());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Activite findById(int id) {
        String req = "SELECT * FROM Activité WHERE id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Activite(rs.getInt("id"), rs.getString("intitule"), rs.getDate("date"), rs.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Activite> findAll() {
        List<Activite> activites = new ArrayList<>();
        String req = "SELECT * FROM Activité";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                activites.add(new Activite(rs.getInt("id"), rs.getString("intitule"), rs.getDate("date"), rs.getString("description")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return activites;
    }

    // New method to search activities by criteria
   public List<Activite> findByCriteria(String criteria) {
    List<Activite> activites = new ArrayList<>();
    String req = "SELECT * FROM Activité WHERE intitule LIKE ? OR description LIKE ?";
    try {
        PreparedStatement ps = connexion.getCn().prepareStatement(req);
        ps.setString(1, "%" + criteria + "%");
        ps.setString(2, "%" + criteria + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            activites.add(new Activite(rs.getInt("id"), rs.getString("intitule"), rs.getDate("date"), rs.getString("description")));
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la recherche des activités : " + ex.getMessage());
    }
    return activites;
}

    public List<Activite> findByCriteria() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
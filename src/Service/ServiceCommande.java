package Service;

import Entity.command;
import Helpers.DbConnect;
import IService.IService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommande implements IService <command>{

    Connection cnx;

    public ServiceCommande() {
        cnx = DbConnect.getConnect();
    }
    @Override
    public void add(command t) throws SQLException {

        Statement st = cnx.createStatement();
        String query = "insert into commande (id,nom_c,prenom_c,email,ville,code_postale,adresse,telephone)values(NULL, '" + t.getNom_c() + "', '" + t.getPrenom_c() + "', '" +t.getEmail()+ "', '" + t.getVille() +"','"+t.getCode_postale()+ "','" + t.getAdresse() + "','" + t.getTelephone() + "')";
        st.executeUpdate(query);
    }

    @Override
    public List<command> read() throws SQLException {
        List<command> ls = new ArrayList<command>();
        Statement st = cnx.createStatement();
        String req = "select * from commande order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Integer idoffre = rs.getInt("id");
            String titleoffre = rs.getString("nom_c");
            String priceoffre = rs.getString("prenom_c");
            String nombreplace = rs.getString("email");
            String description = rs.getString("ville");
            String laocalisation = rs.getString("code_postale");
            String style = rs.getString("adresse");
            String image = rs.getString("telephone");
            command p = new command(idoffre,titleoffre,priceoffre,nombreplace,description,laocalisation,style,image);
            ls.add(p);
        }

        return ls;
    }

    @Override
    public void update(command t) throws SQLException {

        Statement st = cnx.createStatement();


        String query = "UPDATE `commande` SET `nom_c` = '" + t.getNom_c() + "',`prenom_c` = '" + t.getPrenom_c() + "', `email` = '" + t.getEmail() + "', `ville` = '" + t.getVille() + "', `code_postale` = '" + t.getCode_postale() + "', `adresse` = '"
                + t.getAdresse() + "', `telephone` = '" + t.getTelephone() + "' WHERE `commande`.`id` = " + t.getIdcommande() + " ;";
        st.executeUpdate(query);

    }

    @Override
    public void delete(Long id) throws SQLException {
        Statement st = cnx.createStatement();
        String query = "DELETE FROM `commande` WHERE `commande`.`id` = '" + id + "'";
        st.executeUpdate(query);

    }



    public List<command> readrecommended() throws SQLException {
        List<command> ls = new ArrayList<command>();
        Statement st = cnx.createStatement();
        String req = "select * from commande  where nom_c='testme' order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Integer idoffre = rs.getInt("idcommande");
            String titleoffre = rs.getString("nom_c");
            String priceoffre = rs.getString("prenom_c");
            String nombreplace = rs.getString("email");
            String description = rs.getString("ville");
            String laocalisation = rs.getString("code_postale");
            String style = rs.getString("adresse");
            String image = rs.getString("telephone");
            command p = new command(idoffre,titleoffre,priceoffre,nombreplace,description,laocalisation,style,image);
            ls.add(p);
        }

        return ls;
    }



}

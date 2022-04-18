package Service;

import Entity.Product;
import Helpers.DbConnect;
import IService.IService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceProduct implements IService<Product> {

    Connection cnx;
    public  ServiceProduct() {
        cnx = DbConnect.getConnect();
    }



    /*
     * CRUD
     *  */

    /**
     *
     * @param product
     * @throws SQLException
     */
    @Override
    public void add(Product product) throws SQLException {

        Statement st=cnx.createStatement();
        String query = "insert into products (id,title,image,price,category_id)values(NULL, '" + product.getTitle() + "', '" + product.getImage() + "', '" +product.getPrice()+"', '"+product.getCategory_id()+  "')";
        st.executeUpdate(query);
    }




    @Override
    public List<Product> read() throws SQLException {

        List<Product> ls = new ArrayList<Product>();
        Statement st = cnx.createStatement();
        String req = "select * from products order by id";
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String image = rs.getString("image");

            //   LocalDate born = rs.getDate("born").toLocalDate();
            double price = rs.getDouble("price");
            int category_id = rs.getInt("category_id");
            Product p = new Product(id,title,image,price,category_id);
            ls.add(p);
        }


        return ls;

    }

    @Override
    public void update(Product t) throws SQLException {

        Statement st = cnx.createStatement();


        String query = "UPDATE `products` SET `title` = '" + t.getTitle() + "',`image` = '" + t.getImage()
                + "', `price` = '" + t.getPrice() + "', `category_id` = '" + t.getCategory_id()
                + "' WHERE `products`.`id` = " + t.getId() + " ;";
        st.executeUpdate(query);
    }

    @Override
    public void delete(Long id) throws SQLException {

        Statement st =cnx.createStatement();
        String query = "DELETE FROM `products` WHERE `products`.`id` = '" + id + "'";
        st.executeUpdate(query);

    }


}

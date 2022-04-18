package Service;

import Entity.category;
import IService.IService;

import java.sql.SQLException;
import java.util.List;

public class ServiceCategory implements IService<category> {
    @Override
    public void add(category category) throws SQLException {


    }

    @Override
    public List<category> read() throws SQLException {
        return null;
    }

    @Override
    public void update(category category) throws SQLException {

    }

    @Override
    public void delete(Long id) throws SQLException {

    }
}

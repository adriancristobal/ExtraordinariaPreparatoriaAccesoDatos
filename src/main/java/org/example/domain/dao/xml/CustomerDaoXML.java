package org.example.domain.dao.xml;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.example.domain.dao.common.SQLQueries;
import org.example.domain.dao.xml.connectionsJDBC.DBConnection;
import org.example.model.xml.CustomerXML;
import org.springframework.core.NestedRuntimeException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class CustomerDaoXML {

    private DBConnection dbConnection;

    @Inject
    public CustomerDaoXML(DBConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    @Override
    public Either<Integer, List<CustomerXML>> getAll(){
        List<Reader> listReader = new ArrayList();
        int result = -1;
        try {

            con = db.getConnection();
            stmt = con.prepareStatement(QueryStrings.GET_ALL_READERS);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                listReader.add(Reader.builder()
                        .id(resultSet.getInt(1))
                        .name_reader(resultSet.getString(2))
                        .birth_reader(resultSet.getDate(3))
                        .build());
            }
            return Either.right(listReader);
        } catch (Exception ex) {
            Logger.getLogger(DaoReaderJDBCImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Either.left(result);
        } finally {

            db.closeConnection(con);
        }
    }
}

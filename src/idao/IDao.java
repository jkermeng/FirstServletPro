package idao;

import java.sql.SQLException;
import java.util.Set;

public interface IDao<T> {
    public Set<T> select(T t) throws SQLException;

    public T selectById(int id) throws SQLException;

    public void insert(T t) throws SQLException;

    ;

    public void delete(T t) throws SQLException;

    public void isExist();

    void update(T t) throws SQLException;

}

package jdbcutil;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IPreparedStatement {
	void setPreparedStatement(PreparedStatement ps) throws SQLException;
}

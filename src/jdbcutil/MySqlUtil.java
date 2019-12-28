package jdbcutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlUtil {
	private static Connection conn;
	private static ResultSet rs;
	private static PreparedStatement ps;

	public MySqlUtil(Connection conn) {
		this.conn = conn;
	}

	public void QueryWithParam(String sql, IPreparedStatement ips, IResultSet irs) throws SQLException {
		ps = conn.prepareStatement(sql);
		ips.setPreparedStatement(ps);
		rs = ps.executeQuery();
		irs.setIResultSet(rs);
	}

	public void QueryNoParam(String sql, IResultSet irs) throws SQLException {
		ps = conn.prepareStatement(sql);
		rs = ps.executeQuery();
		irs.setIResultSet(rs);
	}

	public void UpdateOrInsert(String sql, IPreparedStatement ips) throws SQLException {
		ps = conn.prepareStatement(sql);
		ips.setPreparedStatement(ps);
		ps.execute();
	}
}

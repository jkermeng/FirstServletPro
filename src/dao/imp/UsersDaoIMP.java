package dao.imp;


import com.mysql.cj.MysqlConnection;
import jdbcutil.IPreparedStatement;
import jdbcutil.IResultSet;
import jdbcutil.MySqlConnection;
import jdbcutil.MySqlUtil;
import onetooneentity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class UsersDaoIMP {
    private Connection conn;
    private PreparedStatement ps;
    private Connection connect = MySqlConnection.getConnect();

    public void insertUserKeyWord(String keyword, Integer uid) {
        try {
            new MySqlUtil(connect).UpdateOrInsert("update users set uhotword = ? where uid = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, keyword);
                    ps.setInt(2, uid);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public void modifyUserByUnameAndUemail(Users users) {
        try {
            new MySqlUtil(connect).UpdateOrInsert("update users set upassword = ? where uname=? and uemail = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, users.getUpassword());
                    ps.setString(2, users.getUname());
                    ps.setString(3, users.getUemail());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public Set<Users> selectAllUsers() throws SQLException {
        Set<Users> setusers = new HashSet<>();
        conn = MySqlConnection.getConnect();
        ps = conn.prepareStatement("select uid from users");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int uid = rs.getInt("uid");
            Users u = new Users(uid);
            setusers.add(u);
        }
        return setusers;
    }


    public Users Login(String number, String password) {
        Users user = new Users();
        Connection connect = MySqlConnection.getConnect();
        MySqlUtil mySqlUtil = new MySqlUtil(this.connect);
        try {
            mySqlUtil.QueryWithParam("SELECT * FROM users WHERE uemail=? AND upassword = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, number);
                    ps.setString(2, password);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int uid = rs.getInt("uid");
                        String uname = rs.getString("uname");
                        String uphone = rs.getString("uphone");
                        String ugender = rs.getString("ugender");
                        String uemail = rs.getString("uemail");
                        String upassword = rs.getString("upassword");
                        user.setUid(uid);
                        user.setUname(uname);
                        user.setUphone(uphone);
                        user.setUgender(ugender);
                        user.setUemail(uemail);
                        user.setUpassword(upassword);
                    }

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return user;
    }

    public void Register(Users users) {
        MySqlUtil mySqlUtil = new MySqlUtil(this.connect);
        try {
            mySqlUtil.UpdateOrInsert("insert into users(uname,urname,uphone,ugender,uemail,upassword) values(?,?,?,?,?,?)", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, users.getUname());
                    ps.setString(2, users.getrName());
                    ps.setString(3, users.getUphone());
                    ps.setString(4, users.getUgender());
                    ps.setString(5, users.getUemail());
                    ps.setString(6, users.getUpassword());
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
    }

    public Boolean existPhone(String uphone) {
        Set<Users> users = selectUphone();
        boolean yes = false;
        for (Users us :
                users) {
            if (us.getUphone() != null && us.getUphone().equals(uphone)) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Boolean existEmail(String email) {
        Set<Users> users = selectUemail();
        boolean yes = false;
        for (Users us :
                users) {
            if (us.getUemail() != null && us.getUemail().equals(email)) {
                yes = true;
                break;
            }
        }
        return yes;
    }

    public Set<Users> selectUphone() {
        Set<Users> users = new LinkedHashSet<>();
        try {
            new MySqlUtil(connect).QueryNoParam("select uphone from users", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        Users users1 = new Users();
                        users1.setUphone(rs.getString("uphone"));
                        users.add(users1);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return users;
    }

    public Set<Users> selectUemail() {
        Set<Users> users = new LinkedHashSet<>();
        try {
            new MySqlUtil(connect).QueryNoParam("select uemail from users", new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        Users user = new Users();
                        user.setUemail(rs.getString("uemail"));
                        users.add(user);
                    }

                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }
        return users;
    }

    public Users selectUsersByUname(String Uname) {
        Users user = new Users();
        try {
            new MySqlUtil(connect).QueryWithParam("select uid from users where uname = ?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setString(1, Uname);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int uid = rs.getInt("uid");
                        user.setUid(uid);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlConnection.closePart();
        }

        return user;
    }

    public Users selectUsersById(int useid) {

        MySqlUtil mySqlUtil = new MySqlUtil(connect);
        Users user = new Users();
        try {
            mySqlUtil.QueryWithParam("select * from users where uid =?", new IPreparedStatement() {
                @Override
                public void setPreparedStatement(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, useid);
                }
            }, new IResultSet() {
                @Override
                public void setIResultSet(ResultSet rs) throws SQLException {
                    while (rs.next()) {
                        int uid = rs.getInt("uid");
                        String uname = rs.getString("uname");
                        String urname = rs.getString("urname");
                        String uphone = rs.getString("uphone");
                        String ugender = rs.getString("ugender");
                        String uemail = rs.getString("uemail");
                        String upassword = rs.getString("upassword");
                        String uhotword = rs.getString("uhotword");
                        user.setUid(uid);
                        user.setUname(uname);
                        user.setrName(urname);
                        user.setUphone(uphone);
                        user.setUgender(ugender);
                        user.setUemail(uemail);
                        user.setUpassword(upassword);
                        user.setUhotword(uhotword);

                    }
                }

            });
        } catch (SQLException e) {
            MySqlConnection.closePart();
        } finally {
        }
        return user;

    }


}
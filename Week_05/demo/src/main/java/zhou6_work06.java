import java.sql.*;

public class zhou6_work06 {


    static  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();

        }
    }


    public static void main(String[] args) throws SQLException {
        Connection conn=getConn();
        String sql = "select * from test";

        Statement statement = getStat(conn);
        ResultSet resultSet = myexecuteQuery(statement,sql);
        while(resultSet.next()){
            String id = resultSet.getString(1) ;
            String code = resultSet.getString("code");
            String name = resultSet.getString(3);
            System.out.print("id: " + id + "  ");
            System.out.print("name: " + name + "  ");
            System.out.println("code: " + code);
        }
        closeRs(resultSet);
        closeStmt(statement);
        closeConn(conn);
        
    }

    private static void closeConn(Connection conn) {
        try {
            if(conn != null ){
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeStmt(Statement stmt) {
        try {
            if(stmt != null ){
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void closeRs(ResultSet rs) {
        try {
            if(rs != null ){
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet myexecuteQuery(Statement statement, String sql) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private static Statement getStat(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    private static Connection getConn() {
        Connection conn = null;
        try {
            //连接数据库
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root","123456");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}

package vn.its.dao;

import vn.its.beans.Emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDao {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TUAN", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static List<Emp> getRecords(int start, int total) {
        List<Emp> list = new ArrayList<Emp>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "select * from ( select a.*, rownum rnum from ( SELECT * FROM EMP order by ID ) a where rownum <= " + total + " ) where rnum >= " + start);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Emp e = new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getFloat(3));
                list.add(e);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}

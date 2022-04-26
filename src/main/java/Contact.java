import java.sql.*;

public class Contact {
    private int id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    Contact(int id,String firstname,String lastname,String email,String password)
    {
        this.id=id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.password=password;
        this.email=email;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample","root","Udhaya2k1*");
            String query="insert into sample.person values(?,?,?,?,?)";
            PreparedStatement ps= con.prepareStatement(query);
            ps.setInt(1,id);
            ps.setString(2,firstname);
            ps.setString(3,lastname);
            ps.setString(4,email);
            ps.setString(5,password);
            ps.executeUpdate();
            System.out.println("contact added in db");
        }
        catch(ClassNotFoundException ce)
        {
            System.out.println(ce.getMessage().toString());
        }
        catch(SQLException se)
        {
            System.out.println(se.getMessage().toString());
        }
        System.out.println("contact added succesfully");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

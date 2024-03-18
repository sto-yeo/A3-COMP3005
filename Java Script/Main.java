import java.sql.*;


public class Main {
    public static void main(String[] args) {
        //addStudent("abc", "xyz", "abc.xyz@email.com", new Date( 2030, 12, 22));
        //updateStudentEmail(4, "xyz.abcd@email.com");
        deleteStudent(4);
        getAllStudent();

    }

    //This is the getAllStudent function
    public static void getAllStudent() {
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "9re3nTree&";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.print(resultSet.getString("student_id") + " \t");
                System.out.print(resultSet.getString("first_name") + " \t");
                System.out.print(resultSet.getString("last_name") + " \t");
                System.out.print(resultSet.getString("email") + " \t");
                System.out.println(resultSet.getString("enrollment_date"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //This is addStudent function
    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "9re3nTree&";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertSQL);
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            enrollment_date.setYear(enrollment_date.getYear() - 1900); ;
            enrollment_date.setMonth(enrollment_date.getMonth() -1);
            statement.setDate(4, enrollment_date);
            statement.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //This is updateStudentEmail function
    public static  void updateStudentEmail(Integer student_id, String new_email){
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "9re3nTree&";
        try{
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String updateSQL = "UPDATE students SET email = ? WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(updateSQL);
            statement.setString(1, new_email);
            statement.setInt(2, student_id);
            statement.executeUpdate();
            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    //This is deleteStudent function
    public static void deleteStudent(Integer student_id){
        String url = "jdbc:postgresql://localhost:5432/University";
        String user = "postgres";
        String password = "9re3nTree&";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String deleteSQL = "DELETE from students where student_id = ?";
            PreparedStatement statement = connection.prepareStatement(deleteSQL);
            statement.setInt(1, student_id);
            statement.executeUpdate();
            connection.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }


}
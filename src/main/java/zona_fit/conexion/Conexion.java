package zona_fit.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    //método estático para realizar la conexión a una BD
    public static Connection getConexion(){
        //Objeto conexión
        Connection conexion = null;
        //infromación par acceder a la BD
        var baseDatos = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "root1234";
        //Manejo de excepciones
        try{
            //método para hacer la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e){
            System.out.println("Error al conectar a la BD: " + e.getMessage());
        }
        return conexion;
    }

//    public static void main(String[] args) {
//        var conexion = Conexion.getConexion();
//        if (conexion != null)
//            System.out.println("conexion exitosa mi perro parado " + conexion);
//         else
//            System.out.println("todo pendejo, no le salio");
//
//    }
}

package ubicamovil.accesoadatos;

import java.sql.*; // Que es java.sql   https://es.wikipedia.org/wiki/Java_Database_Connectivity

public class ComunDB { 

    // Documentacion de clases-anidadas --> https://javadesdecero.es/poo/clases-anidadas/
    class TipoDB { // La clase TipoDB se utilizara para saber que tipo de gestor de base de datos estamos accediendo 

        static final int SQLSERVER = 1; // Propiedad que tendra valor 1 para saber que es SQL SERVER
        static final int MYSQL = 2; // Propiedad que tendra valor 2 para saber que es MYSQL
    }
    static int TIPODB = TipoDB.SQLSERVER; //Propiedad para el tipo de gestor de base de datos que estamos utilizando
    //La propiedad "connectionUrl" es para almacenar el string de conexion a la base de datos actual 
    static String connectionUrl = "jdbc:sqlserver://localhost:1433;database=UbicaMovil;user=root;password=12345;loginTimeout=30;encrypt=false;trustServerCertificate=false";    
//    static String connectionUrl = "workstation id=ubicamovilDb.mssql.somee.com;packet size=4096;user id=UbicaMovilProject;pwd=Ubicamovil;data source=ubicamovilDb.mssql.somee.com;persist security info=False;initial catalog=ubicamovilDb; encrypt=false;trustServerCertificate=false";
    
    // Documentacion para entender por que se esta utilizando throws SQLException
    // en los siguientes metodos --> http://dis.um.es/~bmoros/Tutorial/parte9/cap9-3.html
        
    /* El metodo obtenerConexion() nos va a devolver una conexion abierta al gestor de base de datos que 
     estemos utilizando*/
    public static Connection obtenerConexion() throws SQLException {
        // Registrar el Driver de la conexion a la base de datos SQL server
        // para que lo reconozca el servidor web
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        Connection connection = DriverManager.getConnection(connectionUrl); // abrir la conexion a la base de datos
        return connection; // retornar la conexion a la base de datos
    }

    /* El metodo createStatement lo utilizaremos para devolver un Statement  el cual permite 
      ejecutar una consulta de INSERT, UPDATE, DELETE , SELECT en la base de datos
      aprender mas sobre la clase Statement -->  https://www.ibm.com/docs/es/i/7.3?topic=types-statement-objects*/
    public static Statement createStatement(Connection pConn) throws SQLException {
        Statement statement = pConn.createStatement();
        return statement;
    }

    /* El metodo createPreparedStatement lo utilizaremos para devolver un PreparedStatement  el cual permite 
    * ejecutar una consulta de INSERT, UPDATE, DELETE , SELECT en la base de datos y 
    nos permite utilizar parametros en la consultas para evitar la inyección sql 
      aprender mas sobre la clase PreparedStatement -->  http://www.chuidiang.org/java/mysql/PreparedStatement-java-mysql.php */
    public static PreparedStatement createPreparedStatement(Connection pConn, String pSql) throws SQLException {
        PreparedStatement statement = pConn.prepareStatement(pSql);
        return statement;
    }

    /*  El metodo obtenerResultSet lo utilizaremos para devolver un ResultSet el cual
    sirver para almacenar el resultado de la consulta SELECT --> https://es.wikipedia.org/wiki/Result_Set */
    public static ResultSet obtenerResultSet(Statement pStatement, String pSql) throws SQLException {
        ResultSet resultSet = pStatement.executeQuery(pSql);
        return resultSet;
    }

    /*  El metodo obtenerResultSet es una sobre carga del metodo anterior --> https://es.wikipedia.org/wiki/Sobrecarga_(inform%C3%A1tica) */
    public static ResultSet obtenerResultSet(PreparedStatement pPreparedStatement) throws SQLException {
        ResultSet resultSet = pPreparedStatement.executeQuery();
        return resultSet;
    }

    /*  El metodo ejecutarSQL permite ejecutar una consulta INSERT, UPDATE, DELETE directamente a la base de datos*/
    public static int ejecutarSQL(String pSql) throws SQLException {
        int result;
        try (Connection connection = obtenerConexion();) { // Obtener la conexion y encerrarla en try para cierre automatico 
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(pSql); // Ejecutar la consulta SQL y devolvera un valor entero con la cantidad de filas afectadas
        } catch (SQLException ex) {
            throw ex; // Enviar el error al proximo metodo 
        }
        return result; // Retornar el valor de fila afectadas.
    }

    /* La clase UtilQuery la utilizaremos para concantenar mejor los filtros de la consultas SELECT
     a la base de datos  */
    class UtilQuery {

        private String SQL; // Propiedad para almacenar la consulta SELECT
        private PreparedStatement statement; // Propiedad para almacenar PreparedStatement al cual se le agregaran los parametros ?
        private int numWhere; // Propiedad para almacenar la cantidad de campos incluidos en el WHERE de una consulta SELECT

        public UtilQuery() { // Constructor vacio
        }

        public UtilQuery(String SQL, PreparedStatement statement, int numWhere) { // Constructor lleno
            this.SQL = SQL;
            this.statement = statement;
            this.numWhere = numWhere;
        }

        // Encapsulamiento de la propiedades SQL,statement, numWhere
        public String getSQL() {
            return SQL;
        }

        public void setSQL(String SQL) {
            this.SQL = SQL;
        }

        public PreparedStatement getStatement() {
            return statement;
        }

        public void setStatement(PreparedStatement statement) {
            this.statement = statement;
        }

        public int getNumWhere() {
            return numWhere;
        }

        public void setNumWhere(int numWhere) {
            this.numWhere = numWhere;
        }
        // Fin de los metodos del encapsulamiento de las propiedades SQL,statement, numWhere

        /*El metodo AgregarWhereAnd lo utilizaremos para cuando agreguemos un nuevo campo en la consulta SELECT y automaticamente
        agregar el WHERE o AND segun la cantidad de campos agregados en la consulta, tambien incrementara en 1 la cantidad de campos 
        en el WHERE */
        public void AgregarWhereAnd(String pSql) {
            if (this.SQL != null) {
                if (this.numWhere == 0) { 
                    // Si el numWhere es cero significa que es el primer campo agregado en la consulta SELECT entonces agregamos el WHERE
                    this.SQL += " WHERE ";
                } else {
                    // Si el numWhere es diferente a cero significa que agregaremos el AND a la consulta SELECT 
                    this.SQL += " AND ";
                }
                this.SQL += pSql; // Agregar el valor extra a la consulta
            }
            this.numWhere++; // Incrementar en 1 la cantidad de campos actuales en el WHERE de la consulta SELECT
        }
    }
}

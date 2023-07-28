package ubicamovil.accesoadatos;

import ubicamovil.entidadesdenegocio.Categoria;
import java.util.*;
import java.sql.*;

public class CategoriaDAL {
    
    static String getFields() {
        return "r.Id, r.Nombre";
    }
    
    private static String getSelect(Categoria pDoc){
        String sql = "SELECT ";
        if (pDoc.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pDoc.getTop_aux() + " ";
        }
        sql += (getFields() + " FROM Categorias r");
        return sql;
    }

    private static String addOrderBy(Categoria pDoc) {
        String sql = " ORDER BY r.Id DESC";
        if (pDoc.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pDoc.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int create(Categoria pDoc) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO Categorias(Nombre) VALUES(?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pDoc.getNombre());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int update(Categoria pDoc) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Categorias SET Nombre=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pDoc.getNombre());
                ps.setInt(2, pDoc.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    public static int delete(Categoria pDoc) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Categorias WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pDoc.getId());
                result = ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }    
    
    static int asignarDatosResultSet(Categoria pDoc, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pDoc.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pDoc.setNombre(pResultSet.getString(pIndex)); // index 2
        return pIndex;
    }
    
    private static void getData(PreparedStatement pPS, ArrayList<Categoria> pDoc) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) { 
                Categoria doc = new Categoria(); 
                asignarDatosResultSet(doc, resultSet, 0);
                pDoc.add(doc);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static Categoria getById(Categoria pDoc) throws Exception {
        Categoria doc = new Categoria();
        ArrayList<Categoria> docs = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(pDoc);
            sql += " WHERE r.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pDoc.getId());
                getData(ps, docs);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (docs.size() > 0) { // Verificar si el ArrayList de Rol trae mas de un registro en tal caso solo debe de traer uno
            doc = docs.get(0); // Si el ArrayList de Rol trae un registro o mas obtenemos solo el primero 
        }
        return doc;
    }

    public static ArrayList<Categoria> getAll() throws Exception {
        ArrayList<Categoria> docs = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(new Categoria());
            sql += addOrderBy(new Categoria());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                getData(ps, docs);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } 
        catch (SQLException ex) {
            throw ex;
        }
        return docs;
    }
    
    static void querySelect(Categoria pDoc, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pDoc.getId() > 0) {
            pUtilQuery.AgregarWhereAnd(" r.Id=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pDoc.getId()); 
            }
        } if (pDoc.getNombre() != null && pDoc.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" r.Nombre LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pDoc.getNombre() + "%"); 
            }
        }
    }

    public static ArrayList<Categoria> Search(Categoria pDoc) throws Exception {
        ArrayList<Categoria> docs = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(pDoc);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0); 
            querySelect(pDoc, utilQuery);
            sql = utilQuery.getSQL(); 
            sql += addOrderBy(pDoc);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0); 
                querySelect(pDoc, utilQuery);
                getData(ps, docs);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        }
        catch (SQLException ex) {
            throw ex;
        }
        return docs;
    }
}

package ubicamovil.accesoadatos;

import ubicamovil.entidadesdenegocio.Anuncio;
import java.util.*;
import java.sql.*;

public class AnuncioDAL {
    
    static String getFields() {
        return "r.Id, r.Nombre";
    }
    
    private static String getSelect(Anuncio pDoc){
        String sql = "SELECT ";
        if (pDoc.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pDoc.getTop_aux() + " ";
        }
        sql += (getFields() + " FROM Anuncio r");
        return sql;
    }

    private static String addOrderBy(Anuncio pDoc) {
        String sql = " ORDER BY r.Id DESC";
        if (pDoc.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pDoc.getTop_aux() + " ";
        }
        return sql;
    }
    
    public static int create(Anuncio pDoc) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO Anuncios VALUES(?,?,?,?,?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pDoc.getNombre());
                ps.setString(2, pDoc.getDescripocion());
                ps.setString(3, pDoc.getFechaInicio());
                ps.setString(4, pDoc.getFechaFin());
                ps.setInt(5, pDoc.getIdEmpresa());
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

    public static int update(Anuncio pDoc) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Anuncios SET Nombre=?, Descripcion=?, FechaInicio=?, FechaFin=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pDoc.getNombre());
                ps.setString(2, pDoc.getDescripocion());
                ps.setString(3, pDoc.getFechaFin());
                ps.setString(4, pDoc.getFechaFin());
                ps.setInt(5,pDoc.getId());
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

    public static int delete(Anuncio pDoc) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Anuncios WHERE Id=?";
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
    
    static int asignarDatosResultSet(Anuncio pDoc, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pDoc.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pDoc.setNombre(pResultSet.getString(pIndex)); // index 2
        return pIndex;
    }
    
    private static void getData(PreparedStatement pPS, ArrayList<Anuncio> pDoc) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) { 
                Anuncio doc = new Anuncio(); 
                asignarDatosResultSet(doc, resultSet, 0);
                pDoc.add(doc);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static Anuncio getById(Anuncio pDoc) throws Exception {
        Anuncio doc = new Anuncio();
        ArrayList<Anuncio> docs = new ArrayList();
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

    public static ArrayList<Anuncio> getAll() throws Exception {
        ArrayList<Anuncio> docs = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(new Anuncio());
            sql += addOrderBy(new Anuncio());
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
    
    static void querySelect(Anuncio pDoc, ComunDB.UtilQuery pUtilQuery) throws SQLException {
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

    public static ArrayList<Anuncio> Search(Anuncio pDoc) throws Exception {
        ArrayList<Anuncio> docs = new ArrayList();
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

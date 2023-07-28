package ubicamovil.accesoadatos;

import ubicamovil.entidadesdenegocio.Categoria;
import ubicamovil.entidadesdenegocio.Empresa;
import java.util.*;
import java.sql.*;

public class EmpresaDAL {
    static String getFields() {
        return "x.Id, x.Nombre, x.Direccion, x.Telefono, x.HorarioEntrada, x.HorarioSalida, x.Latitud, x.Longitud, x.IdCategoria";
    }

    private static String getSelect(Empresa pRoute) {
        String sql = "SELECT ";
        if (pRoute.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
            sql += "TOP " + pRoute.getTop_aux() + " ";
        }
        sql += (getFields() + " FROM Empresas x");
        return sql;
    }

    private static String addOrderBy(Empresa pRoute) {
        String sql = " ORDER BY x.Id DESC";
        if (pRoute.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.MYSQL) {
            sql += " LIMIT " + pRoute.getTop_aux() + " ";
        }
        return sql;
    }

    public static int create(Empresa pRoute) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "INSERT INTO Empresas(Nombre, Direccion, Telefono, HorarioEntrada, HorarioSalida, Latitud, Longitud, IdCategoria) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pRoute.getNombre());                
                ps.setString(2, pRoute.getDireccion());
                ps.setString(3, pRoute.getTelefono());
                ps.setString(4, pRoute.getHorarioEntrada());
                ps.setString(5, pRoute.getHorarioSalida());                
                ps.setString(6, pRoute.getLatitud());
                ps.setString(7, pRoute.getLongitud());
                ps.setInt(8, pRoute.getIdCategoria());
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

    public static int update(Empresa pRoute) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "UPDATE Empresas SET Nombre=?, Direccion=?, Telefono=?, HorarioEntrada=?, HorarioSalida=?, Latitud=?, Longitud=?, IdCategoria=? WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setString(1, pRoute.getNombre());                
                ps.setString(2, pRoute.getDireccion());
                ps.setString(3, pRoute.getTelefono());
                ps.setString(4, pRoute.getHorarioEntrada());
                ps.setString(5, pRoute.getHorarioSalida());                
                ps.setString(6, pRoute.getLatitud());
                ps.setString(7, pRoute.getLongitud());
                ps.setInt(8, pRoute.getIdCategoria());
                ps.setInt(9, pRoute.getId());
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

    public static int delete(Empresa pRoute) throws Exception {
        int result;
        String sql;
        try (Connection conn = ComunDB.obtenerConexion();) {
            sql = "DELETE FROM Empresas WHERE Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pRoute.getId());
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

    static int asignarDatosResultSet(Empresa pRoute, ResultSet pResultSet, int pIndex) throws Exception {
        pIndex++;
        pRoute.setId(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pRoute.setNombre(pResultSet.getString(pIndex)); // index 2
        pIndex++;
        pRoute.setDireccion(pResultSet.getString(pIndex)); // index 3
        pIndex++;
        pRoute.setTelefono(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pRoute.setHorarioEntrada(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pRoute.setHorarioSalida(pResultSet.getString(pIndex)); // index 6
        pIndex++;
        pRoute.setLatitud(pResultSet.getString(pIndex)); // index 7
        pIndex++;
        pRoute.setLongitud(pResultSet.getString(pIndex)); // index 8
        pIndex++;
        pRoute.setIdCategoria(pResultSet.getInt(pIndex)); // index 9
        
        return pIndex;
    }

    private static void getData(PreparedStatement pPS, ArrayList<Empresa> pRoute) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            while (resultSet.next()) {
                Empresa route = new Empresa();
                asignarDatosResultSet(route, resultSet, 0);
                pRoute.add(route);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    private static void getDataCategoria(PreparedStatement pPS, ArrayList<Empresa> pRoute) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResultSet(pPS);) {
            HashMap<Integer, Categoria> documentMap = new HashMap();
            while (resultSet.next()) {
                Empresa route = new Empresa();
                int index = asignarDatosResultSet(route, resultSet, 0);
                if (documentMap.containsKey(route.getIdCategoria()) == false) {
                    Categoria document = new Categoria();
                    CategoriaDAL.asignarDatosResultSet(document, resultSet, index);
                    documentMap.put(document.getId(), document);
                    route.setCategoria(document);
                } else {
                    route.setCategoria(documentMap.get(route.getIdCategoria())); 
                }
                pRoute.add(route);
            }
            resultSet.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    public static Empresa getById(Empresa pRoute) throws Exception {
        Empresa route = new Empresa();
        ArrayList<Empresa> routes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(pRoute);
            sql += " WHERE x.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, pRoute.getId());
                getData(ps, routes);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        if (routes.size() > 0) { // Verificar si el ArrayList de Rol trae mas de un registro en tal caso solo debe de traer uno
            route = routes.get(0); // Si el ArrayList de Rol trae un registro o mas obtenemos solo el primero 
        }
        return route;
    }

    public static ArrayList<Empresa> getAll() throws Exception {
        ArrayList<Empresa> routes = new ArrayList<>();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(new Empresa());
            sql += addOrderBy(new Empresa());
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                getData(ps, routes);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return routes;
    }

    static void querySelect(Empresa pRoute, ComunDB.UtilQuery pUtilQuery) throws SQLException {
        PreparedStatement statement = pUtilQuery.getStatement();
        if (pRoute.getId() > 0) {
            pUtilQuery.AgregarWhereAnd(" x.Id=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pRoute.getId());
            }
        }
        if (pRoute.getNombre()!= null && pRoute.getNombre().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" x.Nombre LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRoute.getNombre() + "%");
            }
        }
        if (pRoute.getDireccion()!= null && pRoute.getDireccion().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" x.DireccionLIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRoute.getDireccion() + "%");
            }
        }
        if (pRoute.getTelefono()!= null && pRoute.getTelefono().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" x.Telefono LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRoute.getTelefono() + "%");
            }
        }
        if (pRoute.getHorarioEntrada()!= null && pRoute.getHorarioEntrada().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" x.HorarioEntrada LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRoute.getHorarioEntrada() + "%");
            }
        }
        if (pRoute.getHorarioSalida()!= null && pRoute.getHorarioSalida().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" x.HorarioSalida LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRoute.getHorarioSalida() + "%");
            }
        }
        if (pRoute.getLatitud()!= null && pRoute.getLatitud().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" x.Latitud LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRoute.getLatitud() + "%");
            }
        }
        if (pRoute.getLongitud()!= null && pRoute.getLongitud().trim().isEmpty() == false) {
            pUtilQuery.AgregarWhereAnd(" x.Longitud LIKE ? ");
            if (statement != null) {
                statement.setString(pUtilQuery.getNumWhere(), "%" + pRoute.getLongitud() + "%");
            }
        }
        if (pRoute.getIdCategoria() > 0) {
            pUtilQuery.AgregarWhereAnd(" x.IdCategoria=? ");
            if (statement != null) {
                statement.setInt(pUtilQuery.getNumWhere(), pRoute.getIdCategoria());
            }
        }
    }

    public static ArrayList<Empresa> Search(Empresa pRoute) throws Exception {
        ArrayList<Empresa> routes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = getSelect(pRoute);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pRoute, utilQuery);
            sql = utilQuery.getSQL();
            sql += addOrderBy(pRoute);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pRoute, utilQuery);
                getData(ps, routes);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return routes;
    }
    
    public static ArrayList<Empresa> searchCategoria(Empresa pRoute) throws Exception {
        ArrayList<Empresa> routes = new ArrayList();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = "SELECT ";
            if (pRoute.getTop_aux() > 0 && ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER) {
                sql += "TOP " + pRoute.getTop_aux() + " ";
            }
            sql += getFields();
            sql += ",";
            sql += CategoriaDAL.getFields();
            sql += " FROM Empresas x";
            sql += " JOIN Categorias r on (x.IdCategoria=r.Id)";
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = comundb.new UtilQuery(sql, null, 0);
            querySelect(pRoute, utilQuery);
            sql = utilQuery.getSQL();
            sql += addOrderBy(pRoute);
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pRoute, utilQuery);
                getDataCategoria(ps, routes);
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } catch (SQLException ex) {
            throw ex;
        }
        return routes;
    }
}

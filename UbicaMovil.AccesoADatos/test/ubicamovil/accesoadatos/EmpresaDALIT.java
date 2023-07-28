package ubicamovil.accesoadatos;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ubicamovil.entidadesdenegocio.Empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ubicamovil.entidadesdenegocio.Categoria;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpresaDALIT {
    
    private static Empresa empActual;
    
    public EmpresaDALIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getFields method, of class EmpresaDAL.
     */
    @Test
    public void test8GetFields() {
        System.out.println("getFields");
        String expResult = "";
        String result = EmpresaDAL.getFields();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of create method, of class EmpresaDAL.
     */
    @Test
    public void test1Create() throws Exception {
        System.out.println("create");
        Empresa pEmpresa =  new Empresa();
        pEmpresa.setNombre("Nombre UNIT TEST");
        pEmpresa.setDireccion("Direccion UNIT TEST");
        pEmpresa.setTelefono("6767-6767");
        pEmpresa.setHorarioEntrada("8:00");
        pEmpresa.setHorarioSalida("22:00");
        pEmpresa.setLatitud("0");
        pEmpresa.setLongitud("0");
        Categoria catB = new Categoria();
        catB.setTop_aux(1);
        pEmpresa.setIdCategoria(CategoriaDAL.Search(catB).get(0).getId());
        int expResult = 0;
        int result = EmpresaDAL.create(pEmpresa);
        assertNotEquals(expResult, result);
    }

    public int testIndividualQuerySelect(Empresa pEmpresa) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("", null, 0);
        EmpresaDAL.querySelect(pEmpresa, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }

    /**
     * Test of update method, of class EmpresaDAL.
     */
    @Test
    public void test5Update() throws Exception {
        System.out.println("update");
        Empresa pEmpresa = new Empresa();
        pEmpresa.setId(empActual.getId());
        pEmpresa.setNombre("Nombre UNIT TEST M");
        pEmpresa.setDireccion("Direccion UNIT TEST M");
        pEmpresa.setTelefono("9999-9999");
        pEmpresa.setHorarioEntrada("6:00");
        pEmpresa.setHorarioSalida("20:00");
        pEmpresa.setLatitud("0");
        pEmpresa.setLongitud("0");
        Categoria catB = new Categoria();
        catB.setTop_aux(2);
        pEmpresa.setIdCategoria(CategoriaDAL.Search(catB).get(1).getId());
        int expResult = 0;
        int result = EmpresaDAL.update(pEmpresa);
        assertNotEquals(expResult, result);
    }

    /**
     * Test of delete method, of class EmpresaDAL.
     */
    @Test
    public void test9Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = EmpresaDAL.delete(empActual);
        assertNotEquals(expResult, result);
        Empresa empDelete = EmpresaDAL.getById(empActual);
        assertTrue(empDelete.getId() == 0);
    }

    /**
     * Test of asignarDatosResultSet method, of class EmpresaDAL.
     */
    @Test
    public void test91AsignarDatosResultSet() throws Exception {
        System.out.println("asignarDatosResultSet");
        Empresa pEmpresa = new Empresa();
        try (Connection conn = ComunDB.obtenerConexion();) {
            String sql = "SELECT " + EmpresaDAL.getFields()+ " FROM Empresas x";
            sql += " WHERE x.Id=?";
            try (PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);) {
                ps.setInt(1, empActual.getId());
                try (ResultSet resultSet = ComunDB.obtenerResultSet(ps);) {
                    while (resultSet.next()) {
                        EmpresaDAL.asignarDatosResultSet(pEmpresa, resultSet, 0);
                    }
                    resultSet.close();
                } catch (SQLException ex) {
                    throw ex;
                }
                ps.close();
            } catch (SQLException ex) {
                throw ex;
            }
            conn.close();
        } // Handle any errors that may have occurred.
        catch (SQLException ex) {
            throw ex;
        }
        assertTrue(pEmpresa.getId() == empActual.getId());
    }

    /**
     * Test of getById method, of class EmpresaDAL.
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        Empresa result = EmpresaDAL.getById(empActual);
        assertEquals(empActual.getId(), result.getId());
    }

    /**
     * Test of getAll method, of class EmpresaDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<Empresa> result = EmpresaDAL.getAll();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of querySelect method, of class EmpresaDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        int index = 0;
        Empresa pEmpresa = new Empresa();
        pEmpresa.setId(1);
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setNombre("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setDireccion("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setTelefono("9999-9999");
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setHorarioEntrada("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setHorarioSalida("TEST");
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setLatitud("0");
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setLongitud("0");
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
        pEmpresa.setIdCategoria(4);
        index++;
        assertTrue(testIndividualQuerySelect(pEmpresa) == index);
    }

    /**
     * Test of Search method, of class EmpresaDAL.
     */
    @Test
    public void test3Search() throws Exception {
        System.out.println("Search");
        Empresa pEmpresa = new Empresa();
        pEmpresa.setNombre("Nombre UNIT TEST");
        pEmpresa.setTop_aux(10);
        ArrayList<Empresa> result = EmpresaDAL.Search(pEmpresa);
        assertTrue(result.size() > 0);
        empActual = result.get(0);
    }

    /**
     * Test of searchCategoria method, of class EmpresaDAL.
     */
    @Test
    public void test7SearchCategoria() throws Exception {
        System.out.println("searchCategoria");
        Empresa pEmpresa = new Empresa();
        pEmpresa.setTop_aux(10);
        ArrayList<Empresa> result = EmpresaDAL.searchCategoria(pEmpresa);
        assertTrue(result.size()>0);
        Empresa empresaConCategoria = result.get(0);
        assertTrue(empresaConCategoria.getIdCategoria() == empresaConCategoria.getCategoria().getId());
    }
    
}

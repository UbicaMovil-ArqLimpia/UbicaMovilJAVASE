package ubicamovil.accesoadatos;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ubicamovil.entidadesdenegocio.Categoria;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoriaDALIT {
    
    private static Categoria catActual;
    
    public CategoriaDALIT() {
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
     * Test of create method, of class CategoriaDAL.
     */
    @Test
    public void test1Crear() throws Exception {
        System.out.println("create");
        Categoria pCategoria = new Categoria(0, "TEST UNIT");
        int expResult = 0;
        int result = CategoriaDAL.create(pCategoria);
        assertNotEquals(expResult, result);
    }

    public int testIndividualQuerySelect(Categoria pCategoria) throws Exception {
        ComunDB comundb = new ComunDB();
        ComunDB.UtilQuery pUtilQuery = comundb.new UtilQuery("",null, 0);
        CategoriaDAL.querySelect(pCategoria, pUtilQuery);
        return pUtilQuery.getNumWhere();
    }

    /**
     * Test of update method, of class CategoriaDAL.
     */
    @Test
    public void test5Update() throws Exception {
        System.out.println("update");
        Categoria pCategoria = new Categoria();
        pCategoria.setId(catActual.getId());
        pCategoria.setNombre("TEST UNIT M");
        int expResult = 0;
        int result = CategoriaDAL.update(pCategoria);
        assertNotEquals(expResult, result);
        Categoria rolUpdate = CategoriaDAL.getById(catActual);
        assertTrue(rolUpdate.getNombre().equals(pCategoria.getNombre()));
    }

    /**
     * Test of delete method, of class CategoriaDAL.
     */
    @Test
    public void test7Delete() throws Exception {
        System.out.println("delete");
        int expResult = 0;
        int result = CategoriaDAL.delete(catActual);
        assertNotEquals(expResult, result);
        Categoria catDelete = CategoriaDAL.getById(catActual);
        assertTrue(catDelete.getId() == 0);
    }

    /**
     * Test of getById method, of class CategoriaDAL.
     */
    @Test
    public void test4GetById() throws Exception {
        System.out.println("getById");
        Categoria result = CategoriaDAL.getById(catActual);
        assertEquals(catActual.getId(), result.getId());
    }

    /**
     * Test of getAll method, of class CategoriaDAL.
     */
    @Test
    public void test6GetAll() throws Exception {
        System.out.println("getAll");
        ArrayList<Categoria> result = CategoriaDAL.getAll();
        assertTrue(result.size() > 0);
    }

    /**
     * Test of querySelect method, of class CategoriaDAL.
     */
    @Test
    public void test2QuerySelect() throws Exception {
        System.out.println("querySelect");
        Categoria pCategoria = new Categoria();
        pCategoria.setId(1);
        assertTrue(testIndividualQuerySelect(pCategoria) == 1);
        pCategoria.setNombre("TEST");
        assertTrue(testIndividualQuerySelect(pCategoria) == 2);
    }

    /**
     * Test of Search method, of class CategoriaDAL.
     */
    @Test
    public void test3Search() throws Exception {
        System.out.println("Search");
        Categoria pCategoria = new Categoria(0, "TEST UNIT");
        ArrayList<Categoria> result = CategoriaDAL.Search(pCategoria);
        assertTrue(result.size() > 0);
        catActual = result.get(0);
    }
    
}

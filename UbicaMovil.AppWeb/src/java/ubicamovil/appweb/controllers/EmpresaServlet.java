package ubicamovil.appweb.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList; // Importar la clase ArrayList
import ubicamovil.accesoadatos.CategoriaDAL; // Importar la clase CategoriaDAL de la capa de acceso a datos
import ubicamovil.accesoadatos.EmpresaDAL; // Importar la clase EmpresaDAL de la capa de acceso a datos
import ubicamovil.appweb.utils.*; // Importar las clases SessionUser, Utilidad del paquete de utils
import ubicamovil.entidadesdenegocio.Categoria; // Importar la clase Categoria de la capa de entidades de negocio
import ubicamovil.entidadesdenegocio.Empresa; // Importar la clase Empresa de la capa de entidades de negocio

@WebServlet(name = "EmpresaServlet", urlPatterns = {"/Empresa"})
public class EmpresaServlet extends HttpServlet {

    private Empresa obtenerEmpresa(HttpServletRequest request) {
        // Obtener el parámetro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        Empresa empresa = new Empresa();
        if (accion.equals("create") == false) { // Si la accion no es create.
            // Obtener el parámetro id del request  y asignar ese valor a la propiedad Id de Categoria.
            empresa.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        // Obtener el parámetro nombre del request y asignar ese valor a la propiedad Nombre de Empresa.
        empresa.setNombre(Utilidad.getParameter(request, "nombre", ""));
        // Obtener el parámetro direccion del request y asignar ese valor a la propiedad Direccion de Empresa.
        empresa.setDireccion(Utilidad.getParameter(request, "direccion", ""));
        // Obtener el parámetro telefono del request y asignar ese valor a la propiedad Telefono de Empresa.
        empresa.setTelefono(Utilidad.getParameter(request, "telefono", ""));
        // Obtener el parámetro horarioEntrada del request y asignar ese valor a la propiedad HorarioEntrada de Empresa.
        empresa.setHorarioEntrada(Utilidad.getParameter(request, "horarioEntrada", ""));
        // Obtener el parámetro horarioSalida del request y asignar ese valor a la propiedad HorarioSalida de Empresa.
        empresa.setHorarioSalida(Utilidad.getParameter(request, "horarioSalida", ""));
        // Obtener el parámetro latitud del request y asignar ese valor a la propiedad Latitud de Empresa.
        empresa.setLatitud(Utilidad.getParameter(request, "latitud", ""));
        // Obtener el parámetro longitud del request y asignar ese valor a la propiedad Longitud de Empresa.
        empresa.setLongitud(Utilidad.getParameter(request, "longitud", ""));
        // Obtener el parámetro idCategoria del request  y asignar ese valor a la propiedad IdCategoria de Empresa.
        empresa.setIdCategoria(Integer.parseInt(Utilidad.getParameter(request, "idCategoria", "0")));
        if (accion.equals("index")) {
            // Obtener el parámetro top_aux del request  y asignar ese valor a la propiedad Top_aux de Empresa.
            empresa.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "5")));
            empresa.setTop_aux(empresa.getTop_aux() == 0 ? Integer.MAX_VALUE : empresa.getTop_aux());
        }
        // Devolver la instancia de la entidad Empresa con los valores obtenidos del request.
        return empresa;
    }
    
    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Empresa, y el parametro accion sea igual index. Este método se encargara
     * de enviar los datos de los empresas al jsp de index de Empresa.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Empresa
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Empresa que utlizaremos para enviar el
     * jsp
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empresa empresa = new Empresa(); // Crear una instancia  de la entidad de Empresa.
            empresa.setTop_aux(10); // Agregar el Top_aux con el valor de 10 a la propiedad Top_aux de Empresa.
            // Ir a la capa de acceso a datos y buscar los registros de Empresa y asociar Categoria.
            ArrayList<Empresa> empresas = EmpresaDAL.searchCategoria(empresa);
            // Enviar los empresas al jsp utilizando el request.setAttribute con el nombre del atributo empresa.
            request.setAttribute("empresas", empresas);
            // Enviar el Top_aux de Empresa al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", empresa.getTop_aux());
            request.getRequestDispatcher("Views/Empresa/index.jsp").forward(request, response); // Direccionar al jsp index de Empresa.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post, al servlet
     * Empresa , y el parámetro accion sea igual index. Este método se encargara
     * de enviar los datos de los empresas al jsp de index de Empresa
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empresa empresa = obtenerEmpresa(request); // Llenar la instancia de Empresa con los parámetros enviados en el request.
            // Ir a la capa de acceso a datos y buscar los registros de Empresa y asociar Categoria.
            ArrayList<Empresa> empresas = EmpresaDAL.searchCategoria(empresa);
            // Enviar los empresas al jsp utilizando el request.setAttribute con el nombre del atributo empresa.
            request.setAttribute("empresas", empresas);
            // Enviar el Top_aux de Empresa al jsp utilizando el request.setAttribute con el nombre del atributo top_aux.
            request.setAttribute("top_aux", empresa.getTop_aux());
            request.getRequestDispatcher("Views/Empresa/index.jsp").forward(request, response); // Direccionar al jsp index de Empresa.
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response); // Enviar al jsp de error si hay un Exception.
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Empresa, y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // direccionar al jsp create de Empresa
        request.getRequestDispatcher("Views/Empresa/create.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Empresa , y el parámetro accion sea igual create.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empresa empresa = obtenerEmpresa(request); // Llenar la instancia de Empresa con los parámetros enviados en el request
            // Enviar los datos de Empresa a la capa de accesoa a datos para que lo almacene en la base de datos el registro.
            int result = EmpresaDAL.create(empresa);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron ingresados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro registrar un nuevo registro
                Utilidad.enviarError("No se logro registrar un nuevo registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }

    }

    /**
     * En este método obtiene por Id un Empresa desde la capa de acceso a datos
     * el Id se captura del request que se envio al servlet de Empresa
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get o post enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void requestGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empresa empresa = obtenerEmpresa(request); // Llenar la instancia de Empresa con los parámetros enviados en el request.
            Empresa empresa_result = EmpresaDAL.getById(empresa); // Obtener desde la capa de acceso a datos el empresa por Id.
            if (empresa_result.getId() > 0) { // Si el Id es mayor a cero.
                Categoria categoria = new Categoria();
                categoria.setId(empresa_result.getIdCategoria());
                // Obtener desde la capa de acceso a datos el categoria por Id y asignarlo al empresa.
               empresa_result.setCategoria(CategoriaDAL.getById(categoria));
                // Enviar el atributo empresa con el valor de los datos del empresa de nuestra base de datos a un jsp
                request.setAttribute("empresa", empresa_result);
            } else {
                // Enviar al jsp de error el siguiente mensaje. El Id: ? no existe en la tabla de Empresa
                Utilidad.enviarError("El Id: " + empresa_result.getId() + " no existe en la tabla de Empresa", request, response);
            }
        } catch (Exception ex) {
            // enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Empresa , y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el empresa al jsp de edit que se obtiene por Id
        requestGetById(request, response);
        // Direccionar al jsp edit de Empresa
        request.getRequestDispatcher("Views/Empresa/edit.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Empresa , y el parámetro accion sea igual edit.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empresa empresa = obtenerEmpresa(request); // Llenar la instancia de Empresa con los parámetros enviados en el request.
            // Enviar los datos de Empresa a la capa de accesoa a datos para modificar el registro.
            int result = EmpresaDAL.update(empresa);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron modificado correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex para que nos direcciones al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro actualizar el registro.
                Utilidad.enviarError("No se logro actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Empresa , y el parámetro accion sea igual details.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el empresa al jsp de details que se obtiene por Id.
        requestGetById(request, response);
        // Direccionar al jsp details de Empresa.
        request.getRequestDispatcher("Views/Empresa/details.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion get al servlet
     * Empresa , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Enviar el empresa al jsp de delete que se obtiene por Id.
        requestGetById(request, response);
        // Direccionar al jsp delete de Empresa.
        request.getRequestDispatcher("Views/Empresa/delete.jsp").forward(request, response);
    }

    /**
     * En este método se ejecutara cuando se envie una peticion post al servlet
     * Empresa , y el parámetro accion sea igual delete.
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Empresa
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Empresa empresa = obtenerEmpresa(request); // Llenar la instancia de Empresa con los parámetros enviados en el request.
            // Enviar los datos de Empresa a la capa de accesoa a datos para que elimine el registro.
            int result = EmpresaDAL.delete(empresa);
            if (result != 0) { // Si el result es diferente a cero significa que los datos fueron eliminados correctamente.
                // Enviar el atributo accion con el valor index al jsp de index.
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);  // Ir al método doGetRequestIndex para que nos direccione al jsp index.
            } else {
                // Enviar al jsp de error el siguiente mensaje. No se logro eliminar el registro.
                Utilidad.enviarError("No se logro eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            // Enviar al jsp de error si hay un Exception.
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones get que se realice al Servlet Empresa
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion get enviada al servlet Empresa
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Empresa que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro accion del request
        String accion = Utilidad.getParameter(request, "accion", "index");
        // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
        switch (accion) {
            case "index":
                // Enviar el atributo accion al jsp de index.
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response); // Ir al método doGetRequestIndex.
                break;
            case "create":
                // Enviar el atributo accion al jsp de create.
                request.setAttribute("accion", accion);
                doGetRequestCreate(request, response); // Ir al metodo doGetRequestCreate.
                break;
            case "edit":
                // Enviar el atributo accion al jsp de edit.
                request.setAttribute("accion", accion);
                doGetRequestEdit(request, response);// Ir al metodo doGetRequestEdit.
                break;
            case "delete":
                // Enviar el atributo accion al jsp de delete.
                request.setAttribute("accion", accion);
                doGetRequestDelete(request, response); // Ir al metodo doGetRequestDelete.
                break;
            case "details":
                // Enviar el atributo accion al jsp de details.
                request.setAttribute("accion", accion);
                doGetRequestDetails(request, response); // Ir al metodo doGetRequestDetails.
                break;
            default:
                // Enviar el atributo accion al jsp de index.
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
        }
    }

    /**
     * Este método es un override al método de la clase HttpServlet para recibir
     * todas las peticiones post que se realice al Servlet Empresa
     *
     * @param request en este parámetro vamos a recibir el request de la
     * peticion post enviada al servlet Empresa
     * @param response en este parámetro vamos a recibir el response de la
     * peticion get enviada al servlet Empresa que utlizaremos para enviar el jsp al
     * navegador web
     * @throws ServletException devolver una exception de un servlet
     * @throws IOException devolver una exception al leer o escribir un archivo
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el parámetro accion del request.
        String accion = Utilidad.getParameter(request, "accion", "index");
        // Hacer un switch para decidir a cual metodo ir segun el valor que venga en el parámetro de accion.
        switch (accion) {
            case "index":
                // Enviar el atributo accion al jsp de index.
                request.setAttribute("accion", accion);
                doPostRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
                break;
            case "create":
                // Enviar el atributo accion al jsp de create.
                request.setAttribute("accion", accion);
                doPostRequestCreate(request, response); // Ir al metodo doPostRequestCreate.
                break;
            case "edit":
                // Enviar el atributo accion al jsp de edit.
                request.setAttribute("accion", accion);
                doPostRequestEdit(request, response); // Ir al metodo doPostRequestEdit.
                break;
            case "delete":
                // Enviar el atributo accion al jsp de delete.
                request.setAttribute("accion", accion);
                doPostRequestDelete(request, response); // Ir al metodo doPostRequestDelete.
                break;
            default:
                // Enviar el atributo accion al jsp de index.
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response); // Ir al metodo doGetRequestIndex.
        }
    }
    // </editor-fold>
}

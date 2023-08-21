package ubicamovil.appweb.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import ubicamovil.accesoadatos.AnuncioDAL;
import ubicamovil.entidadesdenegocio.Anuncio;
import ubicamovil.appweb.utils.Utilidad;

@WebServlet(name = "AnuncioServlet", urlPatterns = {"/Anuncio"})
public class AnuncioServlet extends HttpServlet {

    private Anuncio obtenerAnuncio(HttpServletRequest request) {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Anuncio anuncio = new Anuncio();
        if (!accion.equals("create")) {
            anuncio.setId(Integer.parseInt(Utilidad.getParameter(request, "id", "0")));
        }
        anuncio.setNombre(Utilidad.getParameter(request, "titulo", ""));
        if (accion.equals("index")) {
            anuncio.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, "top_aux", "10")));
            anuncio.setTop_aux(anuncio.getTop_aux() == 0 ? Integer.MAX_VALUE : anuncio.getTop_aux());
        }
        return anuncio;
    }

    private void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Anuncio anuncio = new Anuncio();
            anuncio.setTop_aux(10);
            ArrayList<Anuncio> anuncios = AnuncioDAL.Search(anuncio);
            request.setAttribute("anuncios", anuncios);
            request.setAttribute("top_aux", anuncio.getTop_aux());
            request.getRequestDispatcher("Views/Anuncio/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Anuncio anuncio = obtenerAnuncio(request);
            ArrayList<Anuncio> anuncios = AnuncioDAL.Search(anuncio);
            request.setAttribute("anuncios", anuncios);
            request.setAttribute("top_aux", anuncio.getTop_aux());
            request.getRequestDispatcher("Views/Anuncio/index.jsp").forward(request, response);
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Views/Anuncio/create.jsp").forward(request, response);
    }

    private void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Anuncio anuncio = obtenerAnuncio(request);
            int result = AnuncioDAL.create(anuncio);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logró registrar un nuevo anuncio", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void requestGetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Anuncio anuncio = obtenerAnuncio(request);
            Anuncio anuncio_result = AnuncioDAL.getById(anuncio);
            if (anuncio_result.getId() > 0) {
                request.setAttribute("anuncio", anuncio_result);
            } else {
                Utilidad.enviarError("El Id: " + anuncio.getId() + " no existe en la tabla de Anuncio", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Anuncio/edit.jsp").forward(request, response);
    }

    private void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Anuncio anuncio = obtenerAnuncio(request);
            int result = AnuncioDAL.update(anuncio);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logró actualizar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    private void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Anuncio/details.jsp").forward(request, response);
    }

    private void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        requestGetById(request, response);
        request.getRequestDispatcher("Views/Anuncio/delete.jsp").forward(request, response);
    }

    private void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Anuncio anuncio = obtenerAnuncio(request);
            int result = AnuncioDAL.delete(anuncio);
            if (result != 0) {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            } else {
                Utilidad.enviarError("No se logró eliminar el registro", request, response);
            }
        } catch (Exception ex) {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");
        switch (accion) {
            case "index":
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
                break;
            case "create":
                request.setAttribute("accion", accion);
                doGetRequestCreate(request, response);
                break;
            case "edit":
                request.setAttribute("accion", accion);
                doGetRequestEdit(request, response);
                break;
            case "delete":
                request.setAttribute("accion", accion);
                doGetRequestDelete(request, response);
                break;
            case "details":
                request.setAttribute("accion", accion);
                doGetRequestDetails(request, response);
                break;
            default:
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = Utilidad.getParameter(request, "accion", "index");
        switch (accion) {
            case "index":
                request.setAttribute("accion", accion);
                doPostRequestIndex(request, response);
                break;
            case "create":
                request.setAttribute("accion", accion);
                doPostRequestCreate(request, response);
                break;
            case "edit":
                request.setAttribute("accion", accion);
                doPostRequestEdit(request, response);
                break;
            case "delete":
                request.setAttribute("accion", accion);
                doPostRequestDelete(request, response);
                break;
            default:
                request.setAttribute("accion", accion);
                doGetRequestIndex(request, response);
        }
    }
}

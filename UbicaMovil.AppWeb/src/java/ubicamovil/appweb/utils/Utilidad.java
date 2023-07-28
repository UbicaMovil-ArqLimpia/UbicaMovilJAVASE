package ubicamovil.appweb.utils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utilidad {
    /**
     * En este método vamos obtener el valor de un parámetro, que es enviado en
     * la peticion get o post que recibe un servlet, desde un formulario o url
     * del navegador web. La utilidad de crear este metodo, es no repetir una y
     * otra ves la validacion, de que si existe o no el parámetro enviado desde
     * el formulario o url del navegador web.
     *
     * @param request en este parámetro vamos a recibir el request de un servlet
     * @param pKey en este parámetro vamos a recibir el nombre del parámetro,
     * que se envio desde un formulario o url del navegador web
     * @param pDefault en este parámetro vamos a recibir el valor por defecto,
     * que se va a retornar en el caso que no exista un parámetro, en el request
     * @return String devolver el valor del parámetro que se envio desde un
     * formulario o url del navegador web
     */
    public static String getParameter(HttpServletRequest request, String pKey, String pDefault) {
        String result = "";
        // request.getParameter es para obtener el valor de un parámetro que se envio desde un formulario o url del navegador web 
        String value = request.getParameter(pKey); // obtener el valor del parámetro a partir del nombre que se envio pKey 
        if (value != null && value.trim().length() > 0) { // verificar que valor del parámetro sea un valor correcto  
            result = value; // asigar el valor del parámetro a la variable de result
        } else {
            result = pDefault; // en el caso que el valor del parámetro sea un valor incorrecto devolver el valor por defecto 
        }
        return result; // retornar el valor del parámetro o valor por defecto
    }

    /**
     * En este método vamos a direccionar al jsp error en el caso que suceda una
     * exception en los servlet
     *
     * @param pError en este parámetro vamos a recibir el error que sucedio en
     * un servlet
     * @param request en este parámetro vamos a recibir el request de un servlet
     * @param response en este parámetro vamos a recibir el response de un
     * servlet o jsp para direccionar al jsp error
     * @throws javax.servlet.ServletException devolver una exception de un
     * servlet
     * @throws java.io.IOException devolver una exception al leer o escribir un
     * archivo
     */
    public static void enviarError(String pError, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.setAttribute es para crear un atributo y asignarle un valor el cual puede ser recibido en un jsp
        request.setAttribute("error", pError); // crear el atributo error  y asignarle el valor de la variable pError
        // request.getRequestDispatcher nos permite direccionar a un jsp desde un servlet 
        request.getRequestDispatcher("Views/Shared/error.jsp").forward(request, response); // direccionar al jsp error 
    }

    /**
     * Este método se utilizara para obtener la ruta de nuestra aplicacion web
     * al momento de obtener rutas de imagenes, css, js
     *
     * @param request en este parámetro vamos a recibir el request de un servlet
     * o jsp
     * @param pStrRuta en este parámetro vamos a recibir la ruta que se va
     * concatenar a la ruta raiz de la aplicacion web
     * @return String va a devolver la ruta completa del archivo css, js, imagen
     * que se desea enviar a un nevegador web
     */
    public static String obtenerRuta(HttpServletRequest request, String pStrRuta) {
        // request.getContextPath() devuelve el nombre de la aplicación (o directorio raíz del proyecto) en Java EE.
        return request.getContextPath() + pStrRuta; // concatenar la ruta raiz de la aplicacion, mas la ruta del archivo css, js o imagen 
    }
}

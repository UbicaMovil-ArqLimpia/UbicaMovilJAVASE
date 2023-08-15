package ubicamovil.entidadesdenegocio;

import java.util.ArrayList;

public class Empresa {
    private int id;
    private String nombre;    
    private String direccion;
    private String telefono;
    private String horarioEntrada;    
    private String horarioSalida;
    private String latitud;
    private String longitud;
    private int idCategoria;
    private int top_aux;
    
    private Categoria categoria;
    private ArrayList<Anuncio> anuncio;

    public Empresa() {
    }

    public Empresa(int id, String nombre, String direccion, String telefono, String horarioEntrada, String horarioSalida, String latitud, String longitud, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horarioEntrada = horarioEntrada;
        this.horarioSalida = horarioSalida;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }

    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Anuncio> getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(ArrayList<Anuncio> anuncio) {
        this.anuncio = anuncio;
    }
}

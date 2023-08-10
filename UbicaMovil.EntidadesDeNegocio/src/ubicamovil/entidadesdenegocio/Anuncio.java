package ubicamovil.entidadesdenegocio;

public class Anuncio {
    
    private int id;
    private String nombre;
    private String descripocion;
    private String fechaInicio;
    private String fechaFin;
    private Empresa empresa;

    public Anuncio() {
    }

    public Anuncio(int id, String nombre, String descripocion, String fechaInicio, String fechaFin, Empresa empresa) {
        this.id = id;
        this.nombre = nombre;
        this.descripocion = descripocion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.empresa = empresa;
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

    public String getDescripocion() {
        return descripocion;
    }

    public void setDescripocion(String descripocion) {
        this.descripocion = descripocion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
}


package ubicamovil.entidadesdenegocio;

public class Anuncio {
    
    private int id;
    private String nombre;
    private String descripocion;
    private String fechaInicio;
    private String fechaFin;
    private int idEmpresa;
    private int top_aux;
    private Empresa empresa;

    public Anuncio() {
    }

    public Anuncio(int id, String nombre, String descripocion, String fechaInicio, String fechaFin, int idEmpresa, int top_aux, Empresa empresa) {
        this.id = id;
        this.nombre = nombre;
        this.descripocion = descripocion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEmpresa = idEmpresa;
        this.top_aux = top_aux;
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

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getTop_aux() {
        return top_aux;
    }

    public void setTop_aux(int top_aux) {
        this.top_aux = top_aux;
    }
}


public class Estudiante extends Persona {
    String materia;
    Double calificacion;

    public Estudiante(String nombre, int edad, String ciudad, String materia, Double calificacion) {
        super(nombre, edad, ciudad);
        this.materia = materia;
        this.calificacion = calificacion;
        // TODO Auto-generated constructor stub
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

}

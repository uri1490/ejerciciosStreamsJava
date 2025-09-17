import java.util.Optional;

public class Persona {
    String nombre;
    int edad;
    String ciudad;
    Optional<String> codigoPostal;

    public Persona(String nombre, int edad, String ciudad, Optional<String> codigoPostal) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Optional<String> getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Optional<String> codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal
                + "]";
    }

}

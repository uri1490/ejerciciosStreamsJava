import java.util.Optional;

public class Empleado extends Persona {
    double salario;

    public Empleado(String nombre, int edad, String ciudad, Optional<String> codigoPostal, double salario) {
        super(nombre, edad, ciudad, codigoPostal);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

}

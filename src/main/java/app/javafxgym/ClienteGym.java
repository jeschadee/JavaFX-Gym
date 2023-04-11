package app.javafxgym;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ClienteGym {
    private Integer Id;
    private String Nombre;
    private String Apellido;
    private Long Dni;
    private Long Telefono;
    private Long TelefonoAux;
    private String ObraSocial;
    private String Domicilio;
    private Date FechaNacimiento;
    private Integer Edad;
    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        Edad = edad;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public Long getDni() {
        return Dni;
    }
    public void setDni(Long dni) {
        Dni = dni;
    }

    public Long getTelefono() {
        return Telefono;
    }

    public void setTelefono(Long telefono) {
        Telefono = telefono;
    }

    public Long getTelefonoAux() {
        return TelefonoAux;
    }

    public void setTelefonoAux(Long telefonoAux) {
        TelefonoAux = telefonoAux;
    }

    public String getObraSocial() {
        return ObraSocial;
    }

    public void setObraSocial(String obraSocial) {
        ObraSocial = obraSocial;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public LocalDate getFechaNacimiento() {
        LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(FechaNacimiento) );
        return localDate;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    public ClienteGym(Integer id, String nombre, String apellido, Long dni, Long telefono, Long telefonoAux, String obraSocial, String domicilio, Date fechaNacimiento, Integer edad) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Dni = dni;
        Telefono = telefono;
        TelefonoAux = telefonoAux;
        ObraSocial = obraSocial;
        Domicilio = domicilio;
        FechaNacimiento = fechaNacimiento;
        Edad = edad;
    }
}

package app.javafxgym;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class PagosGym {
    public Integer IdUsuario;
    public String ApeYNom;
    public Integer Cantidad;
    public Date FechaPago;
    public String YaPago;
    public Integer DiasRestantes;

    public PagosGym(Integer idUsuario, String apeYNom, int cantidad, Date fechaPago, Boolean yaPago, Integer diasRestantes) {
        ApeYNom = apeYNom;
        Cantidad = cantidad;
        FechaPago = fechaPago;
        YaPago = yaPago ? "Si":"No";
        DiasRestantes = diasRestantes;
        IdUsuario = idUsuario;
    }

    public Integer getDiasRestantes() {
        return DiasRestantes;
    }
    public void setDiasRestantes(Integer diasRestantes) {
        DiasRestantes = diasRestantes;
    }
    public Integer getIdUsuario() {
        return IdUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }
    public String getApeYNom() {
        return ApeYNom;
    }
    public void setApeYNom(String apeYNom) {
        ApeYNom = apeYNom;
    }
    public Integer getCantidad() {
        return Cantidad;
    }
    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }
    public void setFechaPago(Date fechaPago) {
        FechaPago = fechaPago;
    }
    public String getYaPago() {
        return YaPago;
    }
    public LocalDate getFechaPago() {
        if(FechaPago == null){
            return null;
        }
        else{
            LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(FechaPago));
            return localDate;
        }
    }
}

package app.javafxgym;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class PagosGym {
    private Integer IdUsuario;
    private String ApeYNom;
    private Integer Cantidad;
    private Date FechaPago;
    private String YaPago;
    private Integer DiasRestantes;
    private String Img;
    private String MontoLocal;

    public PagosGym(Integer idUsuario, String apeYNom, int cantidad, Date fechaPago, Boolean yaPago, Integer diasRestantes, String img) {
        ApeYNom = apeYNom;
        Cantidad = cantidad;
        FechaPago = fechaPago;
        YaPago = yaPago ? "Pagado":"No pagó";
        DiasRestantes = diasRestantes;
        IdUsuario = idUsuario;
        Img = img;
        MontoLocal = getMontoLocal();
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
    public String getImg() {
        return Img;
    }

    public String getMontoLocal(){
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        currencyFormatter.setMaximumFractionDigits(0);
        return currencyFormatter.format(Cantidad);
    }
    public void setImg(String img) {
        Img = img;
    }
}

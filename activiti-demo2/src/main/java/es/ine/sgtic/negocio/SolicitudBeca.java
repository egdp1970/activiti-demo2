package es.ine.sgtic.negocio;

import java.io.Serializable;

public class SolicitudBeca implements Serializable {

	private static final long serialVersionUID = 5079921065975310553L;
	private String nif;
	private String nombre;
	private String ape1;
	private String ape2;
	private String email;
	private int importe;
	
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApe1() {
		return ape1;
	}
	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}
	public String getApe2() {
		return ape2;
	}
	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getImporte() {
		return importe;
	}
	public void setImporte(int importe) {
		this.importe = importe;
	}
}

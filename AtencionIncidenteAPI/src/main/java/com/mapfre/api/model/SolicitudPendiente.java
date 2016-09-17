package com.mapfre.api.model;
public class SolicitudPendiente {
	
	private Integer id;
	
    private String numeroCti;
    private String proceso;
    private String tipoSolicitud;

    private String matricula;
    private String sistema;
    private String subProceso;
    private String asunto;
    private String descripcion;
    private String datosAModificar;

    public SolicitudPendiente() {
		// TODO Auto-generated constructor stub
	}
    
    public SolicitudPendiente(String numeroCti, String proceso, String tipoSolicitud) {
        this.numeroCti = numeroCti;
        this.proceso = proceso;
        this.tipoSolicitud = tipoSolicitud;
    }

    public SolicitudPendiente(String numeroCti, String proceso, String tipoSolicitud, String matricula, String sistema, String subProceso, String asunto, String descripcion, String datosAModificar) {
        this.numeroCti = numeroCti;
        this.proceso = proceso;
        this.tipoSolicitud = tipoSolicitud;
        this.matricula = matricula;
        this.sistema = sistema;
        this.subProceso = subProceso;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.datosAModificar = datosAModificar;
    }

    public String getNumeroCti() {
        return numeroCti;
    }

    public void setNumeroCti(String numeroCti) {
        this.numeroCti = numeroCti;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public String getSubProceso() {
        return subProceso;
    }

    public void setSubProceso(String subProceso) {
        this.subProceso = subProceso;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String ddescripcion) {
        this.descripcion = ddescripcion;
    }

    public String getDatosAModificar() {
        return datosAModificar;
    }

    public void setDatosAModificar(String datosAModificar) {
        this.datosAModificar = datosAModificar;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    
}
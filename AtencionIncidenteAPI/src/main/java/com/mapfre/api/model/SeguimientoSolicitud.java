package com.mapfre.api.model;

import java.sql.Date;

public class SeguimientoSolicitud {
	
	private Integer solicitudId;
	private String detalle;
	
	public SeguimientoSolicitud() {
		// TODO Auto-generated constructor stub
	}


	public Integer getSolicitudId() {
		return solicitudId;
	}


	public void setSolicitudId(Integer solicitudId) {
		this.solicitudId = solicitudId;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	

}

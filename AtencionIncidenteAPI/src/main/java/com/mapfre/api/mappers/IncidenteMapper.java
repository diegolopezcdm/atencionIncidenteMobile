package com.mapfre.api.mappers;

import java.util.List;

import com.mapfre.api.model.SolicitudPendiente;

public interface IncidenteMapper {
	
	List<SolicitudPendiente> list();
	
	SolicitudPendiente get(Integer id);
	
	void aprobar(Integer id);

}

package com.mapfre.api.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mapfre.api.model.SolicitudPendiente;

public interface IncidenteMapper {

	List<SolicitudPendiente> list();

	SolicitudPendiente get(Integer id);

	void aprobar(Integer id);

	void observar(Integer id);

	String getRegla(@Param("idSolicitud") String idSolicitud);

	String asignarAnalista(@Param("idSolicitud") String idSolicitud,
			@Param("FLG_AFECTA_IDI") String FLG_AFECTA_IDI,
			@Param("FLG_REGULATORIO") String FLG_REGULATORIO);

	Integer getAfecta(@Param("idSolicitud") Integer idSolicitud);

	Integer getRegulatorio(@Param("idSolicitud") Integer idSolicitud);

}

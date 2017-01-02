package com.mapfre.api.mappers;

import org.apache.ibatis.annotations.Param;

public interface SeguimientoIncidenteMapper {

	void registrar(@Param("idSolicitud") Integer idSolicitud,
			@Param("detalle") String detalle, @Param("estado") String estado);

}

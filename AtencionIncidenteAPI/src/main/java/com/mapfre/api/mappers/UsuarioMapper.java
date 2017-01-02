package com.mapfre.api.mappers;

import org.apache.ibatis.annotations.Param;


public interface UsuarioMapper {
	String getEmail(@Param("solicitudId")Integer solicitudId);

}

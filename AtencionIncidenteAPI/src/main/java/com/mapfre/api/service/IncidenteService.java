package com.mapfre.api.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mapfre.api.mail.SendMailTLS;
import com.mapfre.api.mappers.IncidenteMapper;
import com.mapfre.api.mappers.SeguimientoIncidenteMapper;
import com.mapfre.api.mappers.UsuarioMapper;
import com.mapfre.api.model.SeguimientoSolicitud;
import com.mapfre.api.model.SolicitudPendiente;
import com.mapfre.api.util.MyBatisUtil;

public class IncidenteService {

	private static final String COO = "COO";
	private static final String APR = "APR";

	public SolicitudPendiente get(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			IncidenteMapper userMapper = sqlSession
					.getMapper(IncidenteMapper.class);
			return userMapper.get(id);
		} finally {
			sqlSession.close();
		}
	}

	public List<SolicitudPendiente> list() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			IncidenteMapper userMapper = sqlSession
					.getMapper(IncidenteMapper.class);
			return userMapper.list();
		} finally {
			sqlSession.close();
		}
	}

	public void aprobar(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			IncidenteMapper userMapper = sqlSession
					.getMapper(IncidenteMapper.class);
			userMapper.aprobar(id);

			SeguimientoIncidenteMapper seguimientoIncidenteMapper = sqlSession
					.getMapper(SeguimientoIncidenteMapper.class);
			seguimientoIncidenteMapper.registrar(id, "SOLICITUD APROBADA", APR);

			sqlSession.commit();

			String toEmail = getEmail(sqlSession, id);
			String regla = userMapper.getRegla(id.toString());
			
			SendMailTLS.send(toEmail,
					"APROBACIÓN - Solicitud de Atención de Incidente",
					regla);
			
			Integer afecta = userMapper.getAfecta(id);
			Integer regulatorio = userMapper.getAfecta(id);
			String idAnalista = userMapper.asignarAnalista(id.toString(), afecta.toString(), regulatorio.toString());
			
			if(idAnalista!=null && idAnalista.trim().length()>0){
				String toEmailAnalista = getEmail(sqlSession, Integer.valueOf(idAnalista));
				SendMailTLS.send(toEmailAnalista,
						"ASIGNACIÓN - Solicitud de Atención de Incidente",
						regla);
			}
			
		} finally {
			sqlSession.close();
		}

	}

	public void observar(SeguimientoSolicitud seguimientoSolicitud) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			IncidenteMapper userMapper = sqlSession
					.getMapper(IncidenteMapper.class);
			SeguimientoIncidenteMapper seguimientoIncidenteMapper = sqlSession
					.getMapper(SeguimientoIncidenteMapper.class);
			userMapper.observar(seguimientoSolicitud.getSolicitudId());
			seguimientoIncidenteMapper.registrar(
					seguimientoSolicitud.getSolicitudId(),
					seguimientoSolicitud.getDetalle(), COO);
			sqlSession.commit();

			String toEmail = getEmail(sqlSession,
					seguimientoSolicitud.getSolicitudId());
			String regla = userMapper.getRegla(seguimientoSolicitud.getSolicitudId().toString());
			
			SendMailTLS.send(toEmail,
					"OBSERVADO - Solicitud de Atención de Incidente",
					regla);

		} finally {
			sqlSession.close();
		}

	}

	public String getEmail(SqlSession sqlSession, int solicitudId) {
		UsuarioMapper userMapper = sqlSession.getMapper(UsuarioMapper.class);
		return userMapper.getEmail(solicitudId);
	}

}

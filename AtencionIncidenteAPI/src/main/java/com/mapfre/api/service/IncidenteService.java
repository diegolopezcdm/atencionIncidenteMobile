package com.mapfre.api.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mapfre.api.mappers.IncidenteMapper;
import com.mapfre.api.model.SolicitudPendiente;
import com.mapfre.api.util.MyBatisUtil;

public class IncidenteService {

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
			IncidenteMapper userMapper = sqlSession.getMapper(IncidenteMapper.class);
			return userMapper.list();
		} finally {
			sqlSession.close();
		}
	}

	public void aprobar(Integer id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			IncidenteMapper userMapper = sqlSession.getMapper(IncidenteMapper.class);
			userMapper.aprobar(id);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

}

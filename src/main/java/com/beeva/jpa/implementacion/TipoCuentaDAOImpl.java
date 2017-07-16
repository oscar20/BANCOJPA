package com.beeva.jpa.implementacion;

import org.springframework.stereotype.Repository;

import com.beeva.jpa.DAO.TipoCuentaDAO;
import com.beeva.jpa.models.Cuenta;

@Repository
public class TipoCuentaDAOImpl extends TipoCuentaDAO{
	

	@Override
	public int getTipoCuenta(Cuenta c) {
		int id_tipo_cuenta = c.getTipocuenta().getIdtipocuenta();
		return id_tipo_cuenta;
	}

}

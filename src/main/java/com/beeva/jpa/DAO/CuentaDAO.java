package com.beeva.jpa.DAO;

import java.util.List;

import com.beeva.jpa.models.Cliente;
import com.beeva.jpa.models.Cuenta;

public abstract class CuentaDAO {
	
	public abstract Cuenta saveCuenta(Cuenta c);
	public abstract List<Cuenta> getCuenta(Cliente c);
	public abstract void retiro(Cuenta tipo,double cantidad);

}

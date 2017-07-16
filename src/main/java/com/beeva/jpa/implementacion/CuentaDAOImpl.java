package com.beeva.jpa.implementacion;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beeva.jpa.DAO.CuentaDAO;
import com.beeva.jpa.models.Cliente;
import com.beeva.jpa.models.Cuenta;

@Repository
public class CuentaDAOImpl extends CuentaDAO{
	
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public Cuenta saveCuenta(Cuenta c) {
		try{
			
			em.persist(c);
			System.out.println("Transaccion de cuenta realizada con exito..!");
		}catch(Exception e){
			System.out.println("Transaccion guardar cuenta fallida :(");
		}
		return c;
		
	}

	@Override
	public List<Cuenta> getCuenta(Cliente c) {
		return em.createQuery("SELECT c FROM Cuenta c WHERE idcliente=:id_cliente").setParameter("id_cliente", c.getIdcliente()).getResultList();
		
	}
	
	public void retiro(Cuenta c, double cantidad){
		
		int tipo = c.getTipocuenta().getIdtipocuenta();
		if(tipo == 1){
			if(c.getBalance() > 5000){
				c.setBalance((c.getBalance() - cantidad));
				System.out.println( c.getCliente().getNombre() + " realizo un retiro, balance actual: " + c.getBalance());
			}else
				System.out.println("No puedes retirar de una cuenta de ahorro si tienes menos de 5000");
		}else{
			Calendar fecha = new  GregorianCalendar();
			int dia = fecha.get(Calendar.DAY_OF_WEEK);
			if((dia !=7 && dia !=1)){
				c.setBalance((c.getBalance() - cantidad));
				System.out.println("Transaccion exitosa");
			}else
				System.out.println("No puedes retirar de una cuenta de cheques en fin de semana");
				
		}
			
	}
}

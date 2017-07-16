package com.beeva.jpa.BANCOJPA;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beeva.jpa.DAO.BancoDAO;
import com.beeva.jpa.DAO.BancoclienteDAO;
import com.beeva.jpa.DAO.ClienteDAO;
import com.beeva.jpa.DAO.CuentaDAO;
import com.beeva.jpa.DAO.TipoCuentaDAO;
import com.beeva.jpa.implementacion.BancoDAOImpl;
import com.beeva.jpa.implementacion.BancoclienteDAOImpl;
import com.beeva.jpa.implementacion.ClienteDAOImpl;
import com.beeva.jpa.implementacion.CuentaDAOImpl;
import com.beeva.jpa.implementacion.TipoCuentaDAOImpl;
import com.beeva.jpa.models.Banco;
import com.beeva.jpa.models.Bancocliente;
import com.beeva.jpa.models.Cliente;
import com.beeva.jpa.models.Cuenta;
import com.beeva.jpa.models.Tipocuenta;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	// ================ LLAMADA A CONTEXT Y CLASE ====
    	ApplicationContext context = new ClassPathXmlApplicationContext("core-context.xml");
    	BancoDAO banco_dao = (BancoDAO)context.getBean(BancoDAOImpl.class);
    	ClienteDAO cliente_dao = (ClienteDAO)context.getBean(ClienteDAOImpl.class);
    	BancoclienteDAO banco_cliente = (BancoclienteDAO)context.getBean(BancoclienteDAOImpl.class);
    	CuentaDAO cuenta_dao = (CuentaDAO)context.getBean(CuentaDAOImpl.class);
    	
        System.out.println( "Ejecutando Proyecto Banco JPA......!" );
        
        // ================= BANCO =======================
        Banco banco1 = new Banco();
        banco1.setNombre("Elektra");
        Banco banco_recuperado = banco_dao.saveBanco(banco1);
        
        // ================= CLIENTES ====================
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Oscar");
        cliente1.setApellido("Almazan");
        Cliente cliente_recuperado = cliente_dao.saveCliente(cliente1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Sandra");
        cliente2.setApellido("Argueta");
        Cliente cliente_recuperado2 = cliente_dao.saveCliente(cliente2);
        
        // ================= BANCO CLIENTES ==============
        Bancocliente ban_cli = new Bancocliente();
        ban_cli.setCliente(cliente_recuperado);
        ban_cli.setBanco(banco_recuperado);
        banco_cliente.saveBancocliente(ban_cli);
        
        Bancocliente ban_cli2 = new Bancocliente();
        ban_cli.setCliente(cliente_recuperado2);
        ban_cli.setBanco(banco_recuperado);
        banco_cliente.saveBancocliente(ban_cli2);
        
        // ================ TIPO CUENTA ==================
        Tipocuenta tipo_cuenta_ahorro = new Tipocuenta();
        tipo_cuenta_ahorro.setIdtipocuenta(1); // Tipo cuenta Ahorro
        Tipocuenta tipo_cuenta_cheques = new Tipocuenta();
        tipo_cuenta_cheques.setIdtipocuenta(2);
              
        // ================ CUENTAS ======================
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setBalance(7000.0);
        cuenta1.setCliente(cliente_recuperado);
        cuenta1.setTipocuenta(tipo_cuenta_ahorro);
        
        Cuenta cuenta2 = new Cuenta();
        cuenta2.setBalance(15000);
        cuenta2.setCliente(cliente_recuperado);
        cuenta2.setTipocuenta(tipo_cuenta_ahorro);
        
        cuenta_dao.saveCuenta(cuenta1);
        cuenta_dao.saveCuenta(cuenta2);
        
        // ================ OBTENCION DE DATOS ===========
        //Obtenemos los parametros de los clientes de la base de datos de cierto cliente
        List<Cliente> lista_clientes = cliente_dao.getCliente(cliente_recuperado.getIdcliente());
        for(Cliente c : lista_clientes)
        	System.out.println(c.getIdcliente() + " " + c.getNombre() + " " + c.getApellido());
        
        //Obtenemos las cuentas que tiene cierto cliente desde la base de datos
        List<Cuenta> account_list = cuenta_dao.getCuenta(cliente_recuperado);
        for(Cuenta c : account_list)
        	System.out.println(c.getCliente().getNombre() + " tiene la cuenta " + c.getTipocuenta().getNombre()
        					 + " con un balance de: " + c.getBalance() + "\n");
        
        // =============== OPERACIONES ===================
        cuenta_dao.retiro(cuenta1, 200);
        
        
    }
}

package com.ufv.dis.practica2;

import org.junit.Before;
import org.junit.Test;

import com.ufv.dis.practica2.agenda.Agenda;
import com.ufv.dis.practica2.agenda.Contacto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.UUID;

public class AppTest {
	
	Agenda A = new Agenda();
	ArrayList<Contacto> contactos = new ArrayList<Contacto>();
	
	@Before 
    public void initialize() {
		Contacto c = new Contacto("Francisco", "Lopez", "UFV","618470569","Francisco@gmail.com","Calle Rosales 35", UUID.randomUUID().toString());
		A.addContacto(c);
		Contacto c1 = new Contacto("Raul", "Fernandez", "UFV","618470578","Raul@gmail.com","Calle Alcala 58", UUID.randomUUID().toString());
		A.addContacto(c1);
		Contacto c2 = new Contacto("Laura", "Hernandez", "UFV","618470487","Laura@gmail.com","Calle Brujula 58", UUID.randomUUID().toString());
		A.addContacto(c2);
		Contacto c3 = new Contacto("Raul", "Jimenez", "UFV","618470578","Raul@gmail.com","Calle Alcala 58", UUID.randomUUID().toString());
		A.addContacto(c3);
    }
	
	@Test	//Buscamos un contacto que no existe, debe ser False
	public void TestExisteContacto_NoEncuentra() {	
		Contacto c = new Contacto("Ramon", "Lopez", "UFV","618470569","Francisco@gmail.com","Calle Rosales 35", UUID.randomUUID().toString());
		assertFalse(A.existeContacto(c));
	}
	
	@Test	//Buscamos un contacto que si existe, debe ser True
	public void TestExisteContacto_SiEncuentra() {	
		Contacto c = new Contacto("Francisco", "Lopez", "UFV","618470569","Francisco@gmail.com","Calle Rosales 35", UUID.randomUUID().toString());
		assertTrue(A.existeContacto(c));
	}
	
	@Test	//Probamos getContactos que nos devuelve el ListArray, vemos si está vacia debe ser False
	public void TestgetContacts_IsNotEmpty() {	
		contactos=A.getContactos();
		assertFalse(contactos.isEmpty());
	}
	
	@Test	//Otra vez con getContactos, vemos si el tamaño del ListArray es el adecuado, debe ser Igual a 4
	public void TestgetContacts_Size() {	
		contactos=A.getContactos();
		assertEquals(4, contactos.size());
	}
	
	@Test	//Buscamos el objeto<Contacto> de un individuo en especifico, debe ser True
	public void TestBuscarContacto() {			//Si comparamos dos objetos con equals() o assertEquals, aun siendo iguales da falso. Hay que comparar los valores campo a campo
		Contacto c1 = new Contacto("Ramon", "Fernandez", "UFV","618470578","Raul@gmail.com","Calle Alcala 58", UUID.randomUUID().toString());
		A.addContacto(c1);
		Contacto c = A.buscarContacto(c1.getId());
		assertTrue((c1.getNombre()==c.getNombre() && c1.getApellidos()==c.getApellidos() && c1.getEmpresa()==c.getEmpresa() && c1.getTelefono()==c.getTelefono() && c1.getEmail()==c.getEmail()  && c1.getDireccion()==c.getDireccion()));													
	}
	
	@Test	//Buscamos el objeto<Contacto> de un individuo que no está en la agenda, deber ser Null
	public void TestBuscarContacto_NoExiste() {	
		Contacto c1 = new Contacto("Ramon", "Fernandez", "UFV","618470578","Raul@gmail.com","Calle Alcala 58", UUID.randomUUID().toString());
		assertNull(A.buscarContacto(c1.getId()));
	}
	
	@Test	//Borramos un contacto de la agenda y lo buscamos para ver que ya no está. Debe ser Null
	public void TestBorrarContacto() {
		Contacto c = new Contacto("Raul", "Garabán", "UFV","618470345","Raul@gmail.com","Calle Brujula 58", UUID.randomUUID().toString());
		A.addContacto(c);
		A.eliminarContacto(c.getId());
		assertNull(A.buscarContacto(c.getId()));
	}
	
	@Test	//Intentamos borrar un contacto que no existe, debe ser Null.
	public void TestBorrarContacto_NoExiste() {	
		Contacto c = new Contacto("Ramon", "Lopez", "UFV","618470569","Francisco@gmail.com","Calle Rosales 35", UUID.randomUUID().toString());
		assertFalse(A.eliminarContacto(c.getId()));
	}
	
	@Test	//Editamos un contacto de la agenda, y comparamos el nuevo objeto<Contacto> para ver si se ha editado bien. Debe ser True
	public void TestEditarContacto() {
		Contacto c1 = new Contacto("Mario", "Hermoso", "UFV","6184753543","Mario@gmail.com","Calle Alcala 58", UUID.randomUUID().toString());
		A.addContacto(c1);
		Contacto c = new Contacto("Mario", "Hermoso", "MIT","217470472","Mario@gmail.com","77 Massachusetts Ave", UUID.randomUUID().toString());
		assertTrue(A.editarContacto(c1, c));
	}
	
	@Test	//Intentamos editar un contacto que no existe en la agenda, debe ser Null
	public void TestEditarContacto_NoExiste() {
		Contacto c = new Contacto("Martin", "Fernandez", "UFV","515151816","Raul@gmail.com","Calle sextante 85", UUID.randomUUID().toString());
		Contacto c1 = new Contacto("Martin", "Fernandez", "MIT","217470472","Raul@gmail.com","77 Massachusetts Ave", UUID.randomUUID().toString());
		assertFalse(A.editarContacto(c, c1));
	}
	
	
	@Test	//Intentamos añadir un Contacto que ya existe ( nombre + apellido ), debe ser Null
	public void TestaddContacto_YaExiste() {	
		Contacto c = new Contacto("Raul", "Jimenez", "UFV","618470578","Raul@gmail.com","Calle Alcala 58", UUID.randomUUID().toString());
		assertFalse(A.addContacto(c));
	}
	
	@Test	//Intentamos guardar los contactos a un fichero JSON y no se puede acceder debe ser una excepcion
	public void TestGuardarJson_Error(){	
		assertNotNull(A.guardarJson("json/contactos.json"));
	}
	
	@Test	//Intentamos guardar los contactos a un fichero JSON debe ser null
	public void TestGuardarJson(){	
		assertNull(A.guardarJson("src/main/resources/contactos.json"));
	}
	
	@Test	//Intentamos importar los contactos desde un fichero JSON que no está creado
	public void TestImportarJson_NoExiste(){	
		assertNotNull(A.importarJson("json/contactos.json"));
	}
	
	@Test	//Intentamos importar los contactos desde un fichero JSON que está creado
	public void TestImportarJson(){	
		assertNull(A.importarJson("src/main/resources/contactos.json"));
	}
	
}

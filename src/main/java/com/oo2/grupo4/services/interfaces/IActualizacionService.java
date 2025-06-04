package com.oo2.grupo4.services.interfaces;

import com.oo2.grupo4.entities.Actualizacion;

public interface IActualizacionService {

	Actualizacion create (Actualizacion actualizacion);
	
	Actualizacion update (Actualizacion actualizacion);
	
	Actualizacion find (int idActualizacion, int idTicket);
	
	Actualizacion delete (int idActualizacion, int idTicket );
	
}

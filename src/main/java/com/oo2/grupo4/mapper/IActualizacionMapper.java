package com.oo2.grupo4.mapper;

import org.mapstruct.Mapper;

import com.oo2.grupo4.dto.ActualizacionCreateDTO;
import com.oo2.grupo4.dto.ActualizacionResponseDTO;
import com.oo2.grupo4.entities.Actualizacion;

@Mapper(componentModel = "string")
public  interface IActualizacionMapper {
		
	ActualizacionResponseDTO toDTO ( Actualizacion actualizacion);
	
	Actualizacion toEntity (ActualizacionCreateDTO actualizacionCreateDTO);
	
}

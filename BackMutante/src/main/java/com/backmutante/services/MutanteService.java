package com.backmutante.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.backmutante.dtos.PersonaDTO;
import com.backmutante.entities.PersonaEntity;
import com.backmutante.repositories.MutanteRepository;

@Service
public class MutanteService {
	
	public MutanteRepository repository;
	
	//--- Constructor ---
	public MutanteService(MutanteRepository repository) {
		this.repository = repository;
	}
	
	//--------- Find ALL ---------
	@Transactional
	public List<PersonaDTO> findAll() throws Exception {
		
		List<PersonaEntity> personas = repository.findAll();
		List<PersonaDTO> listaDTOs = new ArrayList<PersonaDTO>();
		
		try {
			for (PersonaEntity persona : personas) {
				
				PersonaDTO dto = new PersonaDTO();
				
				dto.setId(persona.getId());
				dto.setNombre(persona.getNombre());
				dto.setApellido(persona.getApellido());
				dto.setEdad(persona.getEdad());
				dto.setAdn(persona.getAdn());
				dto.setEstado(persona.getEstado());
				
				listaDTOs.add(dto);
			}
			
			return listaDTOs;
			
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	
	//--------- Find By ID ---------	
	@Transactional
	public PersonaDTO findById (Long id) throws Exception {
		
		Optional<PersonaEntity> personaOptional = repository.findById(id);
		
		try {
			
			PersonaEntity persona = personaOptional.get();
			
			PersonaDTO dto = new PersonaDTO();
			
			dto.setId(persona.getId());
			dto.setNombre(persona.getNombre());
			dto.setApellido(persona.getApellido());
			dto.setEdad(persona.getEdad());
			dto.setAdn(persona.getAdn());
			dto.setEstado(persona.getEstado());
			
			return dto;
			
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	
	//--------- Save ---------
	public PersonaDTO save (PersonaDTO dto) throws Exception {
		
		PersonaEntity persona = new PersonaEntity();
		
		persona.setId(dto.getId());
		persona.setNombre(dto.getNombre());
		persona.setApellido(dto.getApellido());
		persona.setEdad(dto.getEdad());
		persona.setAdn(dto.getAdn());
		persona.setEstado(dto.getEstado());
		
		try {
			
			persona = (PersonaEntity) repository.save(persona);
			dto.setId(persona.getId());
			
			return dto;
			
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	
	//--------- Update ---------
	@Transactional
	public PersonaDTO update(Long id, PersonaDTO dto) throws Exception {
		
		Optional<PersonaEntity> personaOptional = repository.findById(id);
		
		try {
			
			PersonaEntity persona = personaOptional.get();
			
			persona.setId(id);
			persona.setNombre(dto.getNombre());
			persona.setApellido(dto.getApellido());
			persona.setEdad(dto.getEdad());
			persona.setAdn(dto.getAdn());
			persona.setEstado(dto.getEstado());
			
			repository.save(persona);
			dto.setId(persona.getId());
			
			return dto;
			
		} catch (Exception e) {
			throw new Exception();
		}		
	}
	
	
	//--------- Delete ---------
	@Transactional
	public boolean delete(Long id) throws Exception {
		
		try {
			
			if(repository.existsById(id)){
				repository.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	
	//--------- Is Mutant ---------
	@Transactional
	public boolean isMutant(Long id) throws Exception {
		
		Optional<PersonaEntity> personaOptional = repository.findById(id);
		
		try {
			
			PersonaEntity persona = personaOptional.get();
			
			//--- Variables ---
			int size = persona.getAdn().length;
	        char matriz[][]= new char[size][size];
	        int horizontal = 0, vertical = 0, oblicua = 0;
	        int cont = 0;
	        char aux = ' ';
			
			//--- Carga de la Matriz con el ADN ---
	        for(int i=0; i<size; i++){
	            for(int j=0; j<size; j++){
	                matriz[i][j] = persona.getAdn()[i].charAt(j);
	            }
	        }
			
			//--- Busqueda HORIZONTAL ---			
	        for (int i = 0; i < size; i++){
	            for (int j= 0; j < size; j++){
	                if (aux == ' ' || j == 0){
	                    aux = matriz[i][j];
	                }
	                if (matriz[i][j] == aux && j != 0){
	                    cont++;
	                    if (cont == 4){
	                        horizontal++;
	                    }
	                } else {
	                    aux = matriz[i][j];
	                    cont = 1;
	                }
	            }
	        }
	        
			//--- Busqueda VERTICAL ---
	        for (int i = 0; i < size; i++){
	            for (int j= 0; j < size; j++){
	                if (aux == ' '|| i == 0){
	                    aux = matriz[j][i];
	                }
	                if (matriz[j][i] == aux && i != 0){
	                    cont++;
	                    if (cont == 4){
	                        vertical++;
	                    }
	                } else {
	                    aux = matriz[j][i];
	                    cont = 1;
	                }
	            }
	        }
	        
			//--- Busqueda OBLICUA (izq->der) ---
	        for (int i = 0; i < size; i++) {
	            for (int j = 0; j < size; j++) {

	                int columna = j;
	                int acc = 0;
	                String diagonal = "";

	                while (true) {
	                    if (columna >= size - i) {
	                        break;
	                    }
	                    diagonal += persona.getAdn()[i + acc].charAt(columna);

	                    acc++;
	                    columna++;
	                }

	                for (int d = 0; d < diagonal.length(); d++) {
	                    if (aux == ' ' || d == 0) {
	                        aux = diagonal.charAt(d);
	                    }
	                    if (diagonal.charAt(d) == aux && d != 0) {
	                        cont++;
	                        if (cont == 4) {
	                            oblicua++;
	                        }
	                    } else {
	                        aux = diagonal.charAt(d);
	                        cont = 1;
	                    }
	                }
	            }
	        }
	        
	      //--- Resultado ---
	        if((horizontal+vertical+oblicua) > 1){
	        	persona.setEstado(true);;
	            return persona.getEstado();
	        } else {
	        	persona.setEstado(false);
	            return persona.getEstado();
	        }
	        
			
		} catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	
	
	
	
	
	
	
}

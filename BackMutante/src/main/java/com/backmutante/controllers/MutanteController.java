package com.backmutante.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.backmutante.dtos.PersonaDTO;
import com.backmutante.services.MutanteService;

@RestController
@RequestMapping("api/v1/datosmutante")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MutanteController {

	public MutanteService service;
	
	//--- Controller ---
	public MutanteController(MutanteService service) {
		this.service = service;
	}
	
	//--------- Get ALL ---------
	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());  //200 = HttpStatus.OK
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error");
		}
	}
	
	//--------- Get ONE ---------
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	//--------- Post ---------
	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody PersonaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	//--------- Put ---------
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable Long id, @RequestBody PersonaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	//--------- Delete ---------
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete (@PathVariable Long id) {
		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Registro eliminado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	//--------- Is Mutant ---------
	@GetMapping("/mutant/{id}")
	@Transactional
	public ResponseEntity isMutant (@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.isMutant(id));
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	
	
	
	
	
	
}

package com.mingeso.backend.uploadingfiles;

import java.io.IOException;

import com.mingeso.backend.models.Formulario;
import com.mingeso.backend.repositories.FormularioRepository;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingeso.backend.uploadingfiles.storage.StorageFileNotFoundException;
import com.mingeso.backend.uploadingfiles.storage.StorageService;


@CrossOrigin
@Controller
public class FileUploadController {

	private final StorageService storageService;
	private final FormularioRepository formularioRepository;

	@Autowired
	public FileUploadController(StorageService storageService, FormularioRepository formularioRepository) {
		this.storageService = storageService;
		this.formularioRepository = formularioRepository;
	}

	@GetMapping("/postulacion")
	public String listUploadedFiles(Model model) throws IOException {
		return "uploadForm";
	}

	@GetMapping("/files/{carpeta:.+}/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename, @PathVariable String carpeta) {

		Resource file = storageService.loadAsResource(carpeta + "/" + filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@PostMapping("/files")
	public String handleFileUpload(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3, @RequestParam("file4") MultipartFile file4,
			@RequestParam("file5") MultipartFile file5, @RequestParam("nombre") String nombre,
			@RequestParam("correo") String correo, @RequestParam("diplomado") Integer diplomado,
			RedirectAttributes redirectAttributes) {

		// Se crea objeto formulario.
		Formulario formulario = new Formulario();
		formulario.setNombre(nombre);
		formulario.setCorreo(correo);
		formulario.setStatus(1);
		formulario.setIdDiplomado(diplomado);
		Formulario result = formularioRepository.createFormulario(formulario);

		Integer id = result.getId();
			
		storageService.store(file1,id,"Titulo_Profesional.pdf");
		storageService.store(file2,id,"Certificado_Nacimiento.pdf");
		storageService.store(file3,id,"Copia_cedula_Identidad.pdf");
		storageService.store(file4,id,"Curriculum_Vitae.pdf");
		storageService.store(file5,id,"Ficha_de_inscripcion.pdf");

		return "redirect:http://localhost:3000/PostulacionEnviada";
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
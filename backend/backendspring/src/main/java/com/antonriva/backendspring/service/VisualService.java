package com.antonriva.backendspring.service;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.antonriva.backendspring.model.Partido;
import com.antonriva.backendspring.model.RecursoVigente;
import com.antonriva.backendspring.model.TipoDeVisual;
import com.antonriva.backendspring.model.Visual;
import com.antonriva.backendspring.repository.PartidoRepository;
import com.antonriva.backendspring.repository.RecursoVigenteRepository;
import com.antonriva.backendspring.repository.TipoDeVisualRepository;
import com.antonriva.backendspring.repository.VisualRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VisualService {

    private final VisualRepository visualRepository;
    private final PartidoRepository partidoRepository;
    private final TipoDeVisualRepository tipoDeVisualRepository;
    private final RecursoVigenteRepository recursoVigenteRepository;

    private static final String FILE_DIRECTORY = "/home/mrschopen/Documents/proyectoBDII/eVoto/frontend/evoto/public/visuales"; // Cambia esta ruta según tu estructura

    public VisualService(VisualRepository visualRepository, 
                         PartidoRepository partidoRepository, 
                         TipoDeVisualRepository tipoDeVisualRepository,
                         RecursoVigenteRepository recursoVigenteRepository) {
        this.visualRepository = visualRepository;
        this.partidoRepository = partidoRepository;
        this.tipoDeVisualRepository = tipoDeVisualRepository;
        this.recursoVigenteRepository = recursoVigenteRepository;
    }

    @Transactional
    public Visual registrarLogo(MultipartFile logoFile, Long partidoId, Long tipoDeVisualId, Long recursoVigenteId) {
        try {
            // Imprimir los valores recibidos al inicio del método
            System.out.println("Iniciando registro de logo con los siguientes parámetros:");
            System.out.println("Archivo: " + (logoFile != null ? logoFile.getOriginalFilename() : "null"));
            System.out.println("Partido ID: " + partidoId);
            System.out.println("Tipo de Visual ID: " + tipoDeVisualId);
            System.out.println("Recurso Vigente ID: " + recursoVigenteId);

            // Validar si el archivo no está vacío
            if (logoFile == null || logoFile.isEmpty()) {
                throw new IllegalArgumentException("El archivo está vacío o no se proporcionó.");
            }

            // 1. Guardar el archivo en el sistema de archivos local
            String fileName = UUID.randomUUID().toString() + "_" + logoFile.getOriginalFilename();
            File file = new File(FILE_DIRECTORY, fileName);
            logoFile.transferTo(file);
            String filePath = file.getAbsolutePath();
            System.out.println("Archivo guardado localmente en: " + filePath);

            // 2. Buscar el partido asociado (si se proporciona)
            Partido partido = null;
            if (partidoId != null) {
                System.out.println("Buscando partido con ID: " + partidoId);
                partido = partidoRepository.findById(partidoId)
                        .orElseThrow(() -> new EntityNotFoundException("Partido no encontrado con ID: " + partidoId));
            } else {
                throw new IllegalArgumentException("El ID del partido no puede ser nulo.");
            }

            // 3. Buscar el tipo de visual
            System.out.println("Buscando tipo de visual con ID: " + tipoDeVisualId);
            TipoDeVisual tipoDeVisual = tipoDeVisualRepository.findById(tipoDeVisualId)
                    .orElseThrow(() -> new EntityNotFoundException("Tipo de visual no encontrado con ID: " + tipoDeVisualId));

            // 4. Verificar si ya existe un recurso vigente (recursoVigenteId = 1) para este partido
            Optional<Visual> visualExistente = visualRepository.findByPartidoAndRecursoVigenteId(partido, 1L);
            if (visualExistente.isPresent()) {
                // Cambiar el recurso vigente del registro existente a 2 (no vigente)
                Visual visualPrevio = visualExistente.get();
                RecursoVigente recursoNoVigente = recursoVigenteRepository.findById(2L)
                        .orElseThrow(() -> new EntityNotFoundException("Recurso vigente con ID 2 no encontrado."));
                visualPrevio.setRecursoVigente(recursoNoVigente);
                visualRepository.save(visualPrevio);
                System.out.println("El recurso existente con ID " + visualPrevio.getId() + " fue actualizado a recurso no vigente (ID 2).");
            }

            // 5. Registrar el nuevo logo con recursoVigenteId = 1
            RecursoVigente recursoVigente = recursoVigenteRepository.findById(recursoVigenteId)
                    .orElseThrow(() -> new EntityNotFoundException("Recurso vigente no encontrado con ID: " + recursoVigenteId));
            
            // Ruta relativa desde el directorio público
            String relativePath = "/visuales/" + fileName;
            System.out.println("Archivo guardado en: " + relativePath);


            Visual nuevoVisual = new Visual();
            nuevoVisual.setContenido(relativePath); // Guardar la ruta absoluta del archivo
            nuevoVisual.setPartido(partido);
            nuevoVisual.setTipoDeVisual(tipoDeVisual);
            nuevoVisual.setRecursoVigente(recursoVigente);

            visualRepository.save(nuevoVisual);

            System.out.println("Nuevo logo registrado exitosamente en la tabla Visual con los siguientes datos:");
            System.out.println("ID de Visual: " + nuevoVisual.getId());
            System.out.println("Contenido: " + nuevoVisual.getContenido());
            System.out.println("Partido: " + (nuevoVisual.getPartido() != null ? nuevoVisual.getPartido().getId() : "null"));
            System.out.println("Tipo de Visual: " + nuevoVisual.getTipoDeVisual().getId());
            System.out.println("Recurso Vigente: " + nuevoVisual.getRecursoVigente().getId());

            return nuevoVisual;
        } catch (Exception e) {
            System.out.println("Error en registrarLogo: " + e.getMessage());
            throw new RuntimeException("Error al registrar el logo: " + e.getMessage(), e);
        }
    }


}

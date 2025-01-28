package bo.edu.ucb.backend.bl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bo.edu.ucb.backend.dao.ProgramaAcademicoDAO;
import bo.edu.ucb.backend.dto.ProgramaAcademicoDTO;
import org.springframework.validation.BindingResult;

@Service
public class ProgramaAcademicoBL {
    @Autowired
    private ProgramaAcademicoDAO programaAcademicoDAO;

    public ProgramaAcademicoDTO save(ProgramaAcademicoDTO programaAcademicoDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        return programaAcademicoDAO.save(programaAcademicoDTO);
    }

    public ProgramaAcademicoDTO findProgramaAcademicoById(Integer programaId) {
        return programaAcademicoDAO.findById(programaId).orElseThrow(() -> new RuntimeException("Programa académico no encontrado"));
    }

    public Iterable<ProgramaAcademicoDTO> findAllProgramasAcademicos() {
        try {
            return programaAcademicoDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los programas académicos");
        }
    }

    public void deleteProgramaAcademicoById(Integer programaId) {
        if (programaAcademicoDAO.findById(programaId).isPresent()) {
            programaAcademicoDAO.deleteById(programaId);
        } else {
            throw new RuntimeException("Programa académico no encontrado");
        }
    }

    public ProgramaAcademicoDTO updateProgramaAcademico(ProgramaAcademicoDTO programaAcademicoDTO, BindingResult result){
        if (result.hasErrors()) {
            String errorMessage = result.getFieldErrors().get(0).getDefaultMessage();
            throw new RuntimeException(errorMessage);
        }
        if (programaAcademicoDAO.findById(programaAcademicoDTO.getProgramaId()).isPresent()) {
            return programaAcademicoDAO.save(programaAcademicoDTO);
        } else {
            throw new RuntimeException("Programa académico no encontrado");
        }
    }
}

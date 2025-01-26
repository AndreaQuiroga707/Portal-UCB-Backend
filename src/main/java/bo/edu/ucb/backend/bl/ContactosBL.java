package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.ContactosDAO;
import bo.edu.ucb.backend.dto.ContactosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ContactosBL {
    @Autowired
    private ContactosDAO contactosDAO;

    public List<ContactosDTO> findAllContactos() {
        try {
            return contactosDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los contactos");
        }
    }

    public ContactosDTO findContactosById(Integer id) {
        try {
            Optional<ContactosDTO> contactosDTO = contactosDAO.findById(id);
            if (contactosDTO.isPresent()) {
                return contactosDTO.get();
            } else {
                throw new RuntimeException("Contacto no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ContactosDTO save(ContactosDTO contactosDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return contactosDAO.save(contactosDTO);
    }

    public ContactosDTO deleteContactosById(Integer id) {
        ContactosDTO contactosDTO = findContactosById(id);
        if (contactosDTO != null) {
            contactosDAO.deleteById(id);
        }
        return contactosDTO;
    }

    public ContactosDTO updateContactos(ContactosDTO contactosDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return contactosDAO.save(contactosDTO);
    }
}

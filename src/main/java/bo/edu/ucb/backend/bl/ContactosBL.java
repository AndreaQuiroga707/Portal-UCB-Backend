package bo.edu.ucb.backend.bl;

import bo.edu.ucb.backend.dao.ContactosDAO;
import bo.edu.ucb.backend.entity.Contactos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ContactosBL {
    @Autowired
    private ContactosDAO contactosDAO;


    public List<Contactos> findAllContactos() {
        try {
            return contactosDAO.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los contactos");
        }
    }

    public Contactos findContactosById(Integer id) {
        try {
            Optional<Contactos> contactosDTO = contactosDAO.findById(id);
            if (contactosDTO.isPresent()) {
                return contactosDTO.get();
            } else {
                throw new RuntimeException("Contacto no encontrado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Contactos save(Contactos contactos, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return contactosDAO.save(contactos);
    }

    public Contactos deleteContactosById(Integer id) {
        Contactos contactos = findContactosById(id);
        if (contactos != null) {
            contactosDAO.deleteById(id);
        }
        return contactos;
    }

    public Contactos updateContactos(Contactos contactos, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException(result.getFieldErrors().get(0).getDefaultMessage());
        }
        return contactosDAO.save(contactos);
    }
}

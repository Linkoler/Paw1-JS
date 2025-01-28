package com.jsfcourse.person;

import java.io.IOException;
import java.io.Serializable;

import org.primefaces.event.CellEditEvent;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.UserDAO;
import com.jsf.dao.RoleDAO;
import com.jsf.dao.UsRoDAO;
import com.jsf.entities.User;
import com.jsf.entities.Role;
import com.jsf.entities.UsRo;

@Named
@ViewScoped
public class UserEditBB implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_PERSON_LIST = "pages/admin/adminPg?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private User user = new User();
    private UsRo usro = new UsRo();
    private User loaded = null;

    @EJB
    UserDAO userDAO;

    @EJB
    RoleDAO roleDAO;

    @EJB
    UsRoDAO usroDAO;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    public User getUser() {
        return user;
    }

    public void onLoad() throws IOException {
        loaded = (User) flash.get("user");

        if (loaded != null) {
            user = loaded;
        } else {
           // context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
        }
    }

    public String saveData() {
        try {
            if (user.getIdUser() == 0) {
                userDAO.create(user);
                usro.setUser(user);
                Role role = roleDAO.find(4);
                if (role == null) {
                    throw new IllegalStateException("Wystąpił błąd przy rejestracji");
                }
                usro.setRole(role);
                usroDAO.create(usro);

                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Rejestracja zakończona mozna się zalogować", null));
            } else {
                userDAO.merge(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd podczas zapisu.", null));
            return PAGE_STAY_AT_THE_SAME;
        }

        return PAGE_PERSON_LIST;
    }
    
    
    public void onCellEdit(CellEditEvent event) {
        try {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();

            if (newValue != null && !newValue.equals(oldValue)) {
                int rowIndex = event.getRowIndex();
                UsRo editedUsRo = user.getUsRos().get(rowIndex); 

                
                Role newRole = roleDAO.findByName(newValue.toString());
                if (newRole != null) {
                    editedUsRo.setRole(newRole);
                    usroDAO.merge(editedUsRo);

                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Rola została zmieniona z " + oldValue + " na " + newValue, null));
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Nie znaleziono roli o nazwie: " + newValue, null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas edycji roli.", null));
        }
    }

}

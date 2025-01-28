
package com.jsfcourse.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import com.jsf.dao.RoleDAO;
import com.jsf.entities.Role;

@Named("roleListBB")
@RequestScoped
public class RoleListBB {
    private static final String PAGE_PERSON_EDIT = "/pages/admin/userEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String lastName;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    RoleDAO roleDAO;

    

    public List<Role> getFullList() {
        return roleDAO.getFullList();
    }

    public List<Role> getList() {
        Map<String, Object> searchParams = new HashMap<>();
        
        return roleDAO.getList(searchParams);
    }

    
}

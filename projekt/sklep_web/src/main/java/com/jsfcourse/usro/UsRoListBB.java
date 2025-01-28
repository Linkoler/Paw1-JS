
package com.jsfcourse.usro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import com.jsf.dao.UsRoDAO;
import com.jsf.entities.UsRo;

@Named("usroListBB")
@RequestScoped
public class UsRoListBB {
    private static final String PAGE_PERSON_EDIT = "/pages/admin/userEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    UsRoDAO usroDAO;

    

    public List<UsRo> getFullList() {
        return usroDAO.getFullList();
    }

    public List<UsRo> getList() {
        Map<String, Object> searchParams = new HashMap<>();
        
        return usroDAO.getList(searchParams);
    }

    
}

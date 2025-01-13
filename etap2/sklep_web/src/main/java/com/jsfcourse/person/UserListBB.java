//package com.jsfcourse.person;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import jakarta.inject.Inject;
//import jakarta.inject.Named;
//import jakarta.enterprise.context.RequestScoped;
//import jakarta.ejb.EJB;
//import jakarta.faces.context.ExternalContext;
//import jakarta.faces.context.Flash;
//import com.jsf.dao.UserDAO;
//import com.jsf.entities.User;
//
//@Named("userListBB")
//@RequestScoped
//public class UserListBB {
//    private static final String PAGE_PERSON_EDIT = "personEdit?faces-redirect=true";
//    private static final String PAGE_STAY_AT_THE_SAME = null;
//
//    private String lastName;
//
//    @Inject
//    ExternalContext extcontext;
//
//    @Inject
//    Flash flash;
//
//    @EJB
//    UserDAO userDAO;
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public List<User> getFullList() {
//        return userDAO.getFullList();
//    }
//
//    public List<User> getList() {
//        Map<String, Object> searchParams = new HashMap<>();
//        if (lastName != null && !lastName.isEmpty()) {
//            searchParams.put("lastName", lastName);
//        }
//        return userDAO.getList(searchParams);
//    }
//
//    public String newUser() {
//        User user = new User();
//        flash.put("user", user);
//        return PAGE_PERSON_EDIT;
//    }
//
//    public String editUser(User user) {
//        flash.put("user", user);
//        return PAGE_PERSON_EDIT;
//    }
//
//    public String deleteUser(User user) {
//        userDAO.remove(user);
//        return PAGE_STAY_AT_THE_SAME;
//    }
//}
package com.jsfcourse.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.Flash;
import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

@Named("userListBB")
@RequestScoped
public class UserListBB {
    private static final String PAGE_PERSON_EDIT = "personEdit?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private String lastName;

    @Inject
    ExternalContext extcontext;

    @Inject
    Flash flash;

    @EJB
    UserDAO userDAO;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<User> getFullList() {
        return userDAO.getFullList();
    }

    public List<User> getList() {
        Map<String, Object> searchParams = new HashMap<>();
        if (lastName != null && !lastName.isEmpty()) {
            searchParams.put("lastName", lastName);
        }
        return userDAO.getList(searchParams);
    }

    public String newUser() {
        User user = new User();
        flash.put("user", user);
        return PAGE_PERSON_EDIT;
    }

    public String editUser(User user) {
        flash.put("user", user);
        return PAGE_PERSON_EDIT;
    }

    public String deleteUser(User user) {
        userDAO.remove(user);
        return PAGE_STAY_AT_THE_SAME;
    }
}

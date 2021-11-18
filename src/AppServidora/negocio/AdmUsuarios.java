/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServidora.negocio;

import general.IConstantes;

/**
 *
 * @author ersolano
 */
public class AdmUsuarios {

    public AdmUsuarios() {
    }
    
    public boolean validarAdm(String login, String pwd){
        return login.equals(IConstantes.userAdm) && pwd.equals(IConstantes.pwdAdm);
    }
    
}

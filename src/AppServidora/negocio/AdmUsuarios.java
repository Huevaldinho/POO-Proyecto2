/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppServidora.negocio;

import general.IConstantes;

/**
 * Clase especializada en manejar usuarios
 */
public class AdmUsuarios {

    public AdmUsuarios() {
    }

    /**
     * Metodo par validar el administrador en la app
     * @param pwd: codigo ingresado
     * @return Retorna booleano para dar acceso
     */
    public boolean validarAdm(String pwd){
        return pwd.equals(IConstantes.pwdAdm);
    }
    
}

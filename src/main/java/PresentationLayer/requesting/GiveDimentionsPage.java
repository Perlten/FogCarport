/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.requesting;

import FunctionLayer.DAOException;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adamlass
 */
public class GiveDimentionsPage extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        return "index";
    }

}

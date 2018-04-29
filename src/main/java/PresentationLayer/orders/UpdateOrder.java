/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer.orders;

import FunctionLayer.DAOException;
import PresentationLayer.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Perlt
 */
public class UpdateOrder extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DAOException {
        return null;
    }
    
}

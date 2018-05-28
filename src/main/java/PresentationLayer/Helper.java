/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PresentationLayer;

import javax.servlet.http.HttpServletRequest;

/**
 * Used to hold static helper methods for the presentationlayer.
 *
 * @author adamlass
 */
public class Helper {

    public static double safeDouble(HttpServletRequest request, String parameter) {
        double res = 0;
        try {
            res = Double.parseDouble(request.getParameter(parameter));
        } catch (Exception e) {
        }
        return res;
    }

    public static int safeInt(HttpServletRequest request, String parameter) {
        int res = 0;
        try {
            res = Integer.parseInt(request.getParameter(parameter));
        } catch (Exception e) {
        }
        return res;
    }
}

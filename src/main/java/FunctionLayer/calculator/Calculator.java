/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculator;

import FunctionLayer.entities.Customization;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class Calculator {

    private Customization cust;
    private List<Product> products;
    private final int rafterWoodLength = 600; //TODO make db

    public Calculator(Customization cust) {
        this.products = new ArrayList<>();
        this.cust = cust;
    }

    public void calculate() {

    }

    public List<Product> getProducts() {
        return products;
    }

    private void calculateRafter() {
        int amountOfRafters = (int) (cust.getLength() / 50);
        int width = cust.getWidth();
        double remainder = width / rafterWoodLength; //TODO make db
        if (remainder % 1 != 0) {
            remainder++;
        }

        int pitstops = (int) (remainder - 1);

        if (pitstops > 0) {
            polesAndBeams(pitstops);
        }

        amountOfRafters *= remainder;

        int lengthOfRafters = (int) (width / remainder);

        if (amountOfRafters > 0) {
            products.add(new Product("Rafter", "Used on beam", "pcs", amountOfRafters, lengthOfRafters, 0)); //TODO make db
        }
    }

    private void polesAndBeams(int pitstops) {
        int lanes = 2 + pitstops;
        int placingLength = cust.getLength() - Customization.padding;

        double amountOfBeams = placingLength / rafterWoodLength;

        

        if (amountOfBeams % 1 != 0) {
            int lastBeam = placingLength % rafterWoodLength;

            products.add(new Product("Beam", "Used together with the poles to support the rafters. This is the end piece!", "pcs", lanes, lastBeam, 0)); //TODO make db
        }

        products.add(new Product("Beam", "Used together with the poles to support the rafters.", "pcs", ((int) amountOfBeams) * lanes, rafterWoodLength, 0)); //TODO make db

        //TODO poles
        
    }

}

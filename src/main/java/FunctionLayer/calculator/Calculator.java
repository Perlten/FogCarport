/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculator;

import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Shed;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class Calculator {

    private Customization cust;
    private Shed shed;

    private List<Product> products;
    private final int rafterWoodLength = 600; //TODO make db
    private final int maxHeight = 480;
    private double maxShedWidth;
    private double poleDistanceWidth;

    public Calculator(Customization cust) {
        this.products = new ArrayList<>();
        this.cust = cust;
        this.maxShedWidth = cust.getWidth() - (2*Customization.padding);
        this.shed = cust.getShed();
    }

    public void calculate() {
        calculateRafter();
        if(shed != null){
          shedPoles();
          calculateCladding();
          
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    private void calculateRafter() {
        int amountOfRafters = (int) (cust.getLength() / 50);
        double width = (double) cust.getWidth();
        double remainder =  width / rafterWoodLength; //TODO make db
        if (remainder % 1 != 0) {
            remainder++;
        }

        int pitstops = (int) (remainder - 1);
        
        poleDistanceWidth = maxShedWidth / (pitstops + 1);

        polesAndBeams(pitstops);

        amountOfRafters *= remainder;

        double lengthOfRafters = (width / remainder);

        if (amountOfRafters > 0) {
            products.add(new Product("Rafter", "Used on beam", "pcs", amountOfRafters, lengthOfRafters, 0)); //TODO make db
        }
    }

    private void polesAndBeams(int pitstops) {
        int lanes = 2 + pitstops;
        int placingLength = cust.getLength() - Customization.padding;

        double amountOfBeams = ((double) placingLength) / rafterWoodLength;

        if (amountOfBeams < 1) {
            amountOfBeams = 1;
        }

        if (amountOfBeams % 1 != 0) {
            int lastBeam = placingLength % rafterWoodLength;

            products.add(new Product("Beam", "Used together with the poles to support the rafters. This is the end piece!", "pcs", lanes, lastBeam, 0)); //TODO make db
        }

        int beamsOnLane = (int) amountOfBeams;

        products.add(new Product("Beam", "Used together with the poles to support the rafters.", "pcs", beamsOnLane * lanes, rafterWoodLength, 0)); //TODO make db

        int poles = 1;

        poles += placingLength / (rafterWoodLength / 2);

        if ((placingLength / ((double) (rafterWoodLength)) / 2) % 1 != 0) {
            poles++;
        }

        products.add(new Product("Pole", "Used to support the beams", "pcs", poles * lanes, cust.getHeight() + 90, 0)); //TODO make db
    }

    private void calculateCladding() {
        int circumsference = shed.getLength() + shed.getWidth() * 2;
        int claddingNeeded = (int) Math.ceil(circumsference * 0.125);
        int claddingHeight = cust.getHeight() - 20; //20 cm air from floor
        products.add(new Product("Cladding", "Cladding used og the shed", "pcs", claddingNeeded, claddingHeight, 0)); //TODO makedb
        
    }
    
    private void shedPoles(){
        double lanesPasses = shed.getWidth() / poleDistanceWidth;
        int numPoles = (int) (lanesPasses + 1);
        if(lanesPasses % 1 != 0){
            numPoles += 2;
        }
        products.add(new Product("Pole", "Purposed for shed.", "pcs", numPoles, cust.getHeight() + 90, 0)); //make db
    }
    
    

    public static void main(String[] args) {
        Shed shed = new Shed(200, 640);
        shed = null;
        Customization cust = new Customization(700, 700, 200, 0, shed, null, null);
        Calculator cal = new Calculator(cust);
        cal.calculate();
        System.out.println(cal.getProducts());
    }

}

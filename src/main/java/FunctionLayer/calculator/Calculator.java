/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.calculator;

import FunctionLayer.FOGException;
import FunctionLayer.LogicFacade;
import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Shed;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author adamlass
 */
public class Calculator {

    private Order order;
    private Customization cust;
    private Shed shed;

    private List<Product> products;
    private final int rafterWoodLength = 600; //TODO make db
    private double maxShedWidth;
    private double poleDistanceWidth;

    public Calculator(Order order) {
        this.order = order;
        this.cust = order.getCustomization();
        this.products = new ArrayList<>();
        this.maxShedWidth = cust.getWidth() - (2 * Customization.padding);
        this.shed = cust.getShed();
    }

    public void calculate() throws FOGException {
        calculateRafter();
        if (shed != null) {
            shedPoles();
            calculateCladding();

        }
    }

    public List<Product> getProducts() {
        return products;
    }

    private void calculateRafter() throws FOGException {
        int amountOfRafters = (int) (cust.getLength() / 50);
        double width = (double) cust.getWidth();
        double remainder = width / rafterWoodLength; //TODO make db
        if (remainder % 1 != 0) {
            remainder++;
        }

        int pitstops = (int) (remainder - 1);

        poleDistanceWidth = maxShedWidth / (pitstops + 1);

        polesAndBeams(pitstops);

        amountOfRafters *= remainder;

        double lengthOfRafters = (width / remainder);

        if (amountOfRafters > 0) {
            LogicFacade.writeLine(new Product(1, amountOfRafters, lengthOfRafters), order.getOrderid());
        }
    }

    private void polesAndBeams(int pitstops) throws FOGException {
        int lanes = 2 + pitstops;
        int placingLength = cust.getLength() - Customization.padding;

        double amountOfBeams = ((double) placingLength) / rafterWoodLength;

        if (amountOfBeams < 1) {
            amountOfBeams = 1;
        }

        if (amountOfBeams % 1 != 0) {
            int lastBeam = placingLength % rafterWoodLength;

            LogicFacade.writeLine(new Product(2, lanes, lastBeam), order.getOrderid());
        }

        int beamsOnLane = (int) amountOfBeams;

        LogicFacade.writeLine(new Product(3, beamsOnLane * lanes, rafterWoodLength), order.getOrderid());
        int poles = 1;

        poles += placingLength / (rafterWoodLength / 2);

        if ((placingLength / ((double) (rafterWoodLength)) / 2) % 1 != 0) {
            poles++;
        }

        LogicFacade.writeLine(new Product(4, poles * lanes, cust.getHeight() + 90), order.getOrderid());
    }

    private void calculateCladding() throws FOGException {
        int circumsference = shed.getLength() + shed.getWidth() * 2;
        int claddingNeeded = (int) Math.ceil(circumsference * 0.125);
        int claddingHeight = cust.getHeight() - 20; //20 cm air from floor
        LogicFacade.writeLine(new Product(5, claddingNeeded, claddingHeight), order.getOrderid());

    }

    private void shedPoles() throws FOGException {
        double lanesPasses = shed.getWidth() / poleDistanceWidth;
        int numPoles = (int) (lanesPasses + 1);
        if (lanesPasses % 1 != 0) {
            numPoles += 2;
        }
        LogicFacade.writeLine(new Product(6, numPoles, cust.getHeight() + 90), order.getOrderid());
    }

}

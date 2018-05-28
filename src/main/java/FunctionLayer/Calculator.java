/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

import FunctionLayer.entities.Customization;
import FunctionLayer.entities.Order;
import FunctionLayer.entities.Product;
import FunctionLayer.entities.Shed;
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
    private final int RAFTERWOODLENGTH;
    private final int LATHLENGTH;
    private final int TILEWIDTH;

    private final double WIDTH;
    private final double LENGTH;
    private double maxShedWidth;
    private double poleDistanceWidth;

    public Calculator(Order order) throws FOGException {
        this.RAFTERWOODLENGTH = (int) LogicFacade.getProduct(1).getLength();
        this.LATHLENGTH = (int) LogicFacade.getProduct(7).getLength();
        this.TILEWIDTH = (int) LogicFacade.getProduct(8).getLength();

        this.order = order;
        this.cust = order.getCustomization();
        this.products = new ArrayList<>();
        this.WIDTH = cust.getWidth();
        this.LENGTH = cust.getLength();
        this.maxShedWidth = WIDTH - (2 * Customization.padding);
        this.shed = cust.getShed();
    }

    public void calculate() throws FOGException {

        calculateRafter();
        laths();
        roof();
        if (shed != null) {
            shedPoles();
            calculateCladding();

        }
    }

    public double totalPrice() throws FOGException {
        double estPrice = 0.0;
        for (Product prod : products) {
            Product product = LogicFacade.getProduct(prod.getId());
            product.setAmount(prod.getAmount());
            estPrice += product.totalPrice();

            switch (product.getTitle()) {
                case "Cladding":
                    if (cust.getCladding() != null) {
                        double cmPrice = cust.getCladding().getPrice() / 100;
                        estPrice += (product.getLength() * prod.getAmount()) * cmPrice;
                    }
                    break;
                case "Tile":
                    if (cust.getTile() != null) {
                        estPrice += prod.getAmount() * cust.getTile().getPrice();
                    }
                    break;
            }
        }

        return estPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    private void calculateRafter() throws FOGException {
        int amountOfRafters = (int) (cust.getLength() / 50);
        double remainder = WIDTH / RAFTERWOODLENGTH;
        if (remainder % 1 != 0) {
            remainder++;
        }

        int pitstops = (int) (remainder - 1);

        poleDistanceWidth = maxShedWidth / (pitstops + 1);

        polesAndBeams(pitstops);

        amountOfRafters *= remainder;

        double lengthOfRafters = (WIDTH / remainder);

        if (amountOfRafters > 0) {
            products.add(new Product(1, amountOfRafters, lengthOfRafters));
        }
    }

    private void polesAndBeams(int pitstops) throws FOGException {
        int lanes = 2 + pitstops;
        int placingLength = cust.getLength() - Customization.padding;

        double amountOfBeams = ((double) placingLength) / RAFTERWOODLENGTH;

        if (amountOfBeams < 1) {
            amountOfBeams = 1;
        }

        if (amountOfBeams % 1 != 0) {
            int lastBeam = placingLength % RAFTERWOODLENGTH;

            products.add(new Product(2, lanes, lastBeam));
        }

        int beamsOnLane = (int) amountOfBeams;

        products.add(new Product(3, beamsOnLane * lanes, RAFTERWOODLENGTH));
        int poles = 1;

        poles += placingLength / (RAFTERWOODLENGTH / 2);

        if ((placingLength / ((double) (RAFTERWOODLENGTH)) / 2) % 1 != 0) {
            poles++;
        }

        products.add(new Product(4, poles * lanes, cust.getHeight() + 90));
    }

    private void calculateCladding() throws FOGException {
        int circumsference = shed.getLength() + shed.getWidth() * 2;
        int claddingNeeded = (int) Math.ceil(circumsference * 0.125);
        int claddingHeight = cust.getHeight() - 20; //20 cm air from floor
        products.add(new Product(5, claddingNeeded, claddingHeight));

    }

    private void shedPoles() throws FOGException {
        double lanesPasses = shed.getWidth() / poleDistanceWidth;
        int numPoles = (int) (lanesPasses + 1);
        if (lanesPasses % 1 != 0) {
            numPoles += 2;
        }
        products.add(new Product(6, numPoles, cust.getHeight() + 90));
    }

    private void laths() throws FOGException {
        int lathDistance = 30;
        int lanes = (int) (WIDTH / lathDistance) + 1;

        int lathsOnLane = (int) (cust.getLength() / LATHLENGTH);

        double remainder = cust.getLength() % LATHLENGTH;

        if (lathsOnLane != 0) {
            products.add(new Product(7, lanes * lathsOnLane, LATHLENGTH));
        }

        if (remainder != 0) {
            products.add(new Product(7, lanes, remainder));
        }
    }

    private void roof() {
        double currentLength = 0.0;
        int lengthAmount = 0;
        double lengthRemainder = 0;

        while (currentLength != LENGTH) {

            if ((currentLength + TILEWIDTH) <= LENGTH) {
                lengthAmount++;
                currentLength += TILEWIDTH;
            } else {
                lengthRemainder = LENGTH - currentLength;
                currentLength += lengthRemainder;
            }
        }

        double currentWidth = 0.0;
        int widthAmount = 0;

        if (cust.getRoofangle() == 0) {
            //flat roof
            while (currentWidth < WIDTH) {
                widthAmount++;
                currentWidth += TILEWIDTH;
            }

            //add products
            products.add(new Product(8, lengthAmount * widthAmount, TILEWIDTH));
            if (lengthRemainder != 0) {
                products.add(new Product(8, widthAmount, lengthRemainder));

            }

        } else {
            //angled roof
            double roofAngle = cust.getRoofangle();

            double lastAngle = 180 - (roofAngle * 2);
            double sideWidth = (Math.sin(Math.toRadians(roofAngle)) * WIDTH)
                    / Math.sin(Math.toRadians(lastAngle));

            while (currentWidth < sideWidth) {
                widthAmount++;
                currentWidth += TILEWIDTH;
            }

            //add products
            products.add(new Product(8, 2 * lengthAmount * widthAmount, TILEWIDTH));
            if (lengthRemainder != 0) {
                products.add(new Product(8, 2 * widthAmount, lengthRemainder));
            }
        }

    }

}

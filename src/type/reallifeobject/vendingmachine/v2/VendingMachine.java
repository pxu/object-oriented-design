package type.reallifeobject.vendingmachine.v2;

import type.reallifeobject.vendingmachine.v2.products.Inventory;
import type.reallifeobject.vendingmachine.v2.products.Product;
import type.reallifeobject.vendingmachine.v2.states.AfterPaymentState;
import type.reallifeobject.vendingmachine.v2.states.AfterSelectionState;
import type.reallifeobject.vendingmachine.v2.states.IVendingMachineState;
import type.reallifeobject.vendingmachine.v2.states.InitialState;
import type.reservation.hotel.v2.exceptions.InvalidRequestException;

public class VendingMachine {
    private Inventory inventory;
    private Class<? extends Product> selectProduct;
    private double payment;

    private IVendingMachineState currentState;
    private InitialState initialState;
    private AfterSelectionState afterSelectionState;
    private AfterPaymentState afterPaymentState;

    public VendingMachine(Class<? extends Product> ...productClassList) {
        this.inventory = new Inventory(productClassList);
        this.initialState = new InitialState(this);
        this.afterSelectionState = new AfterSelectionState(this);
        this.afterPaymentState = new AfterPaymentState(this);

        this.setState(InitialState.class);
    }

    public void select(Class<? extends Product> productClass) throws Exception {
        this.currentState.select(productClass);
    }

    public void pay(Double amount) throws Exception {
        this.currentState.pay(amount);
    }

    public Product get() throws Exception {
        return this.currentState.get();
    }

    public void cancel() {
        this.currentState.cancel();
    }

    public void setState(Class<? extends IVendingMachineState> state) {
        if (state.equals(InitialState.class)) {
            this.currentState = this.initialState;
        } else if (state.equals(AfterSelectionState.class)) {
            this.currentState = this.afterSelectionState;
        } else if (state.equals(AfterPaymentState.class)) {
            this.currentState = this.afterPaymentState;
        } else {
            throw new InvalidRequestException("input state is not valid!");
        }
    }

    public void refill(Class<? extends Product> productClass, int amount) {
        this.inventory.refill(productClass, amount);
    }

    public void reduce(Class<? extends Product> productClass) {
        this.inventory.reduce(productClass);
    }

    public void clearRequest() {
        if (this.getPayment() != 0) {
            System.out.println("$" + this.getPayment() + " will be refunded.");
        }
        this.setPayment(0);
        this.setSelectProduct(null);
    }

    public boolean contains(Class<? extends Product> productClass) {
        return this.inventory.contains(productClass);
    }

    public Class<? extends Product> getSelectProduct() {
        return selectProduct;
    }

    public double getPayment() {
        return payment;
    }

    public void setSelectProduct(Class<? extends Product> selectProduct) {
        this.selectProduct = selectProduct;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void printInfo() throws Exception {
        System.out.println("Current State: " + this.currentState);
        if (this.selectProduct != null) {
            System.out.println("Selected product: " + this.selectProduct.getDeclaredConstructor().newInstance().getName());
        }
        System.out.println("Inserted Balance: " + this.getPayment());
        System.out.println("Inventory: " + this.inventory.toString());
    }
}

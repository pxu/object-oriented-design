package type.reallifeobject.vendingmachine.v2.states;

import type.reallifeobject.vendingmachine.v2.exceptions.CannotSelectItemException;
import type.reallifeobject.vendingmachine.v2.VendingMachine;
import type.reallifeobject.vendingmachine.v2.exceptions.InvalidRequestException;
import type.reallifeobject.vendingmachine.v2.products.Product;
import type.reallifeobject.vendingmachine.v2.products.ProductType;

public class AfterPaymentState extends AbstractVendingMachineState{
    public AfterPaymentState(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    /**
     * 无效
     *
     * @param productType 产品的type
     */
    @Override
    public void select(ProductType productType) {
        throw new CannotSelectItemException("Payment has been gone through. Please cancel the transaction first.");
    }

    /**
     * 无效
     *
     * @param amount 输入的金额
     */
    @Override
    public void pay(Double amount) {
        throw new InvalidRequestException("Payment has already filled.");
    }

    /**
     * 取出选中商品，找出余额。将售货机的当前信息清空，并将其切换到InitialState并将其切换到InitialState
     *
     * @return 选中的商品
     */
    @Override
    public Product get() {
        ProductType productType = this.vendingMachine.getSelectedProductType();
        Product product = productType.getProduct();
        this.vendingMachine.setPayment(this.vendingMachine.getPayment() - product.getPrice());

        this.vendingMachine.clearRequest();
        this.vendingMachine.reduce(productType);
        this.vendingMachine.setState(InitialState.class);
        return product;
    }

    /**
     * 取消交易，将售货机的当前信息清空，并将其切换到InitialState
     */
    @Override
    public void cancel() {
        this.vendingMachine.clearRequest();

        System.out.println("Transaction is cancelled.");
        this.vendingMachine.setState(InitialState.class);
    }

    @Override
    public String toString() {
        return "After Payment State";
    }
}

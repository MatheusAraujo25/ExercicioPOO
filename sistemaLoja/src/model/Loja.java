package model;

public class Loja {

    private int productCode;
    private String productName;
    private int productAmount;
    private double productPrice;



//    get's and set's

    public int getProductCode () {
     return productCode;
    }

    public void setProductCode(int productCode){
        this.productCode = productCode;
    }

    public String getProductName (){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public int getProductAmount(){
        return productAmount;
    }

    public void setProductAmount(int productAmount){
        this.productAmount = productAmount;
    }

    public double getProductPrice(){
        return productPrice;
    }

    public void setProductPrice (double productPrice){
        this.productPrice = productPrice;
    }

    public double getStoreBalance(){
        return storeBalance;
    }

    public void setStoreBalance(double storeBalance){
        this.storeBalance = storeBalance;
    }

//    private int productCode;
//    private String productName;
//    private int productAmount;
//    private double productPrice;
//    private double storeBalance;


    public void IncrementeBalance(double valueBalance)
    {
         storeBalance +=  valueBalance;
    }


     static double showBalance()
    {
        return storeBalance;
    }

//    productListAllRegisters
    @Override
    public String toString() {
        return  "Product code:" + productCode + "\n" +
                "Name: " + productName + "\n" +
                "Amount: " + productAmount + "\n" +
                "Unit price: " + productPrice + "\n";
    }

}
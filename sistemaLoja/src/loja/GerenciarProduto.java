package loja;

import model.Loja;
import org.w3c.dom.ls.LSOutput;

import java.sql.ClientInfoStatus;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciarProduto {

public  List<Loja> productsStorage = new ArrayList<>();


    public static void main(String[] args) {
        GerenciarProduto gcp = new GerenciarProduto();
        Scanner sc = new Scanner(System.in);
        //gcp  = GerenciarProduto;
        //sc = Scanner;
        //Utilei a forma ambreviada para economizar tempo na escrita de codigo.
        int option = 0;
        do {
            option = buildingMenu(sc, gcp);
        }while (option!=9);

    }

    public static int buildingMenu (Scanner sc, GerenciarProduto gcp)
    {
        int option;
        System.out.println("MENU");
        System.out.println("1. Register prodcut");
        System.out.println("2. Find Product");
        System.out.println("3. Show inventory");
        System.out.println("4. Show balance");
        System.out.println("5. Sale product");
        System.out.println("6. Buy product");
        System.out.println("9. Exit");
        System.out.println("Insert a option: ");
        option = Integer.parseInt(sc.nextLine());
        System.out.println("\n");
        switch (option){
            case 1:
                gcp.execRegisterProduct(sc);
                break;
            case 2:
                gcp.execShowProduct(sc);
                break;
            case 3:
                gcp.execInventoryStorage(sc);
                break;
            case 4:
                break;
            case 5:
                gcp.execProductSale(sc);
                break;
            case 6:
                break;
            case 9:
                System.out.println("FIM");
                break;
            default:
                System.out.println("Opcao invalida");
        }
        return option;
    }

    public void execRegisterProduct (Scanner sc)
    {
//      Insere o novo produto na lista: productsStorage
        Loja product = new  Loja();
        System.out.println("Insert a product code:");
        product.setProductCode(Integer.parseInt(sc.nextLine()));
        System.out.println("Insert a name:");
        product.setProductName(sc.nextLine());
        System.out.println("Insert a amount:");
        product.setProductAmount(Integer.parseInt(sc.nextLine()));
        System.out.println("Insert a price");
        product.setProductPrice(Double.parseDouble(sc.nextLine()));
        productsStorage.add(product);
        System.out.println("Product register!");

    }

    public void  execInventoryStorage (Scanner sc)
    {
        System.out.println("Show current storage:");
        for (Loja l : productsStorage)
        {
            System.out.println(l.toString());
        }
        if (productsStorage == null)
        {
            System.out.println("Nothing to show, not exist any product register!");
        }
    }

    public void execShowProduct (Scanner sc)
    {
        System.out.println("Insert a code of product you need to find:");
        int valueCode = Integer.parseInt(sc.nextLine());
        Loja product = null;
        for (Loja l: productsStorage)
        {
            if(valueCode == l.getProductCode())
            {
                System.out.println(l.toString());
                break;
            }
            else
            {
                System.out.println("Don't exist any product to this code!");
            }
        }
    }


    public static void execShowStoreBalance(Scanner sc)
    {
//            quero exibir o saldo da loja aqui! nessa budega
    }

    public double execCalcValueItemsBuy(double unitPrice, int itemsAmount)
    {
        double spentMoneyInThisItem;
        spentMoneyInThisItem = unitPrice * itemsAmount;
        return spentMoneyInThisItem;
    }


    public int execVerifyBalance(double spentMoneyInThisItem)
    {
//            quero usar o valor do saldo = balance, aqui para poder fazer uma operacao logica

    }

    public void execBuyProductToInventory(Scanner sc)
    {
        System.out.println("Insert a number product to you need buy:");
        int  valueCode = Integer.parseInt(sc.nextLine());

        for (Loja l : productsStorage)
        {
            if (valueCode == l.getProductCode())
            {
                String nameItem;
                int unitsOfItem;
                System.out.println("Insert  number of  items  you to need buy:");
                int itemsAmount = Integer.parseInt(sc.nextLine());
                System.out.println("Insert  unit price of this item:");
                int unitPrice = Integer.parseInt(sc.nextLine());

                unitsOfItem = l.getProductAmount();
                nameItem = l.getProductName();

                double spentMoneyInThisItem;
                spentMoneyInThisItem = execCalcValueItemsBuy(unitPrice, itemsAmount);

                execVerifyBalance(spentMoneyInThisItem);

            }
            else
            {
                System.out.println("\n ATTENTION!!");
                System.out.println("The code value doesn't exist! Check the product code, before you try! \n");
            }
        }
    }



    public boolean execCheckInventoryUnitProduct(int allUnitInInventory, int soldUnits)
    {
        if (allUnitInInventory > soldUnits)
        {
            return true;
        }
        return false;
    }

    public int DecrementItemOfInventory (int allUnitInInventory, int soldUnits)
    {
        int decrementItem;

        decrementItem = allUnitInInventory - soldUnits;

        return decrementItem;
    }

    public double execCalcValueProduct(int soldUnits, double price)
    {
        double soldValue;
        soldValue = soldUnits * price;
        return soldValue;
    }


    public void execProductSale (Scanner sc)
    {
        System.out.println("Insert a code for the product you sold:");
        int  valueCode = Integer.parseInt(sc.nextLine());
        for (Loja l: productsStorage)
        {
            if (valueCode == l.getProductCode())
            {
                System.out.println("Insert number of items amount 's is be sold:");
                int soldUnits = Integer.parseInt(sc.nextLine());

//              Total de unidade em esoque
                int allUnitInInventory;
                double price;
//              Valor total da venda
                double valueSold;
//              Verifica se a quantidade de itens eh a suficiente para a demanda.
                boolean checkAmountsInventoryItems;
                int decrementItems;
                String nameItem;

//              pega o total de unidades do invetario
                allUnitInInventory = l.getProductAmount();
                nameItem = l.getProductName();
//              pega o preco dos item
                price = l.getProductPrice();

                checkAmountsInventoryItems = execCheckInventoryUnitProduct(allUnitInInventory, soldUnits);
                valueSold = execCalcValueProduct(soldUnits, price);

                if (checkAmountsInventoryItems)
                {
                    decrementItems = DecrementItemOfInventory(allUnitInInventory, soldUnits);
                    System.out.println("-------------Sale-----------------");
                    System.out.println("Items sale: " + nameItem + "\n" +
                                        "Value fo sale: " + valueSold + "\n");
                    l.setStoreBalance(valueSold);
                    l.setProductAmount(decrementItems);
                }
                else
                {
                    System.out.println("You dont have this total of units!");
                    System.out.println("Available units: " + allUnitInInventory + "\n");
                }
                break;
            }
            else
            {
                System.out.println("\n ATTENTION!!");
                System.out.println("The code value doesn't exist! Check the product code, before you try! \n");
            }
        }
//        execCalcValueProduct();
    }





}

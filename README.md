# Final-Walters-Chepkwony-Vaal-Wang

## Description

This is a cashier system. The application will keep track of charges, 
accept a cash amount, and display the change needed. 
Possible additions include support for validating credit card checksums, 
and support for discounts/coupons.

## Build

With IntelliJ and gradle installed and configured, run the Main class to build the project.

    src > main > java/edu/bsu/cs222/pos > Main

## Suppressions

In AdminPanelUI.java on line 105, itemList.getColumns().addAll(nameColumn, priceColumn);
is suppressed because of an "unchecked generics array creation for varargs parameter."
Checking for the argument types is unnecessary when we are sure of the types that will be passed in.

In CashierUI on line 185 and 186, the function is suppressed for the same reason.

In ReceiptUI on line 91, the function is suppressed for the same reason.

## Authors

Owen Walters\
Dan Chepkwony\
Bradley Vaal\
Youxuan Wang

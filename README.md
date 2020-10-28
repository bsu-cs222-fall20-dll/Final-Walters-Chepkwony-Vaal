# Final-Walters-Chepkwony-Vaal-Wang

## Description

This is a cashier system. The application will keep track of charges, 
accept a cash amount, and display the change needed. 
Possible additions include support for validating credit card checksums, 
and support for discounts/coupons.

## Build

To build the project, with IntelliJ and gradle installed and configured run the Main class.

    src > main > java/edu/bsu/cs222/pos > Main

## Suppresions

In AdminPanelUI.java on line 102, itemList.getColumns().addAll(nameColumn, priceColumn);
is suppressed because of an "unchecked generics array creation for varargs parameter."
Checking for the argument types is unnecessary when we are sure of the types that will be passed in.

## Authors

Owen Walters [ogwalters@bsu.edu], Dan Chepkwony [dkchepkwony@bsu.edu], Bradley Vaal [bavaal@bsu.edu], and Youxuan Wang [ywang5@bsu.edu].

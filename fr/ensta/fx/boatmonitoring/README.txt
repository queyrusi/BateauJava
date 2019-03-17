*****************
/!\ IMPORTANT /!\
*****************

All of this and more can be found in the javadoc comments attached to the packages,
classes, methods and fields provided. First step is to find a convenient way to access and
navigate those comments (either directly in the IDE or by first generating a html version).

In particular, the comments attached to the boatmonitoring package act as an entry point.


***********
* GENERAL *
***********

The present package provides Java FX UI classes to be used for the boat monitoring system:

FXBoatUI            - For an user on a monitored boat
FXHomeUI            - For an user at home
FXControlCenterUI   - For users at the control center of this system


Additionally, to be able to use the first two:

BoatMonitorTab      - Used by FXBoatUI and FXHomeUI to display the actual monitoring
                      Those UIs provide methods to access/create such tabs
                      Its constructor shouldn't be used directly


Various demos are provided to get started (in the demo subpackage).

TotoHomeDemo        - Starting point to be able to use the various UIs
PauseDemo           - Pause feature showcase
CustomHelloWorld    - How to build your own UI with FXGenericUI (from the generic
                      subpackage) as a base


Other classes in this package are used internally and one shouldn't need to look at them
to implement a boat monitoring system using those UIs.




************
* ADVANCED *
************

If, however, one wants to customize the views:

generic subpackage  - Provides a FXGenericUI featuring pause/resume, warnings, menus
                      and tabs including one for logs.
                      It can be used as a starting point or as an illustration
user subpackage     - Provides classes for FXBoatUI and FXHomeUI
                      It can be used as an illustration to get started with Java FX
util subpackage     - Provides a FXHelper class with some static utilities
                      (easy layout for GridPane and property bindings)


**********
* AUTHOR *
**********

Luka Le Roux - Research engineer
L107 - luka.le_roux@ensta-bretagne.fr

Do contact me if you want to report anything or submit an idea to improve code quality.
# LaserTag
Laser Tag for Software

You can run the player entry screen through the batch script. However, doing so without referencing the .jar file will prevent the entry screen from being connected to the database. 

In VSCode, you can reference the .jar file by navigating to the EXPLORER and drop down the JAVA PROJECTS menu. Then, inside the LaserTag dropdown menu, there will be a Referenced Libraries tab. Click the + button on this tab. This should take you to the folder with the .jar file in it. Add the postgresql-42.5.4.jar to the project. The code should now be connected to the database. 

For any other IDEs, you will have to research how to reference a .jar file. In the future, we will have this fixed. 


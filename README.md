# LaserTag

You can run the player entry screen through the batch script. However, the .jar file is not referenced in the build script which means it will not be connected to the database. We will fix this in the future.

# For Current Testing Purposes
In VSCode, you can reference the .jar file by navigating to the EXPLORER and drop down the JAVA PROJECTS menu. Then, inside the LaserTag dropdown menu, there will be a Referenced Libraries tab. Click the + button on this tab. This should take you to the folder with the .jar file in it. Add the postgresql-42.5.4.jar to the project. The code should now be connected to the database when running from VSCode. 

For any other IDEs, you will have to research how to reference a .jar file. 

# Test IDs
The following IDs are already in the database, you may use them for testing purposes:
* 777
* 17
* 72

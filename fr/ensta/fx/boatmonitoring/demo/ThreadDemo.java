package fr.ensta.fx.boatmonitoring.demo;

import java.util.Scanner;

public class ThreadDemo extends Thread {
	
    private TotoHomeDemo userInterface;
    private TotoHomeModel dataModel;


	public ThreadDemo(TotoHomeDemo userInterface) {
        this.userInterface = userInterface;
        
        this.dataModel = new TotoHomeModel();
        
    }

	
	public TotoHomeDemo getUserInterface() {
		return userInterface;
	}

	public void setUserInterface(TotoHomeDemo userInterface) {
		this.userInterface = userInterface;
	}

	public TotoHomeModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(TotoHomeModel dataModel) {
		this.dataModel = dataModel;
	}


    @Override
    public void run() {
        super.run();
        
        Scanner reader = new Scanner(System.in);
       
        
        System.out.println("Enter the name of the boat or 'quit':");
        String boatName = reader.nextLine();
            
        if ("quit".equals(boatName)) return;
            
        dataModel.setBoatName(boatName);
            
            //userInterface.login( dataModel.getAccountName(), dataModel.getPassword() ); // doesn't work with older jres
            
        reader.close();
    }


}

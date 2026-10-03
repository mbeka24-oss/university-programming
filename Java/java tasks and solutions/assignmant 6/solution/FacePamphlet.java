import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
import java.util.ArrayList;

public class FacePamphlet extends Program
					implements FacePamphletConstants {


	private String newPerson;
	private String delatedPerson;
	private String searchPerson;
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		addNorthField();
		addLeftField();
		
		add(canvas);
		addActionListeners();

		
		
    }
	//adds everithing that are north of canvas 
	private void addNorthField(){
		add(new JLabel("Name"), NORTH);
		
		nameField = new JTextField(TEXT_FIELD_SIZE);
		nameField.addActionListener(this);
		add(nameField, NORTH);
		
		Add = new JButton("Add");
		add(Add, NORTH);
		
		Delete = new JButton("Delete");
		add(Delete, NORTH);
		
		Lookup = new JButton("Lookup");
		add(Lookup, NORTH);
	}
	//adds everithing that are west of canvas 

	private void addLeftField(){
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.addActionListener(this);
		add(statusField, WEST);
		ChangeStatus = new JButton("Change Status");
		add(ChangeStatus, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		picture = new JLabel("No Image", SwingConstants.CENTER);
		add(picture, WEST);
		ChangePicture = new JButton("Change Picture");
		add(ChangePicture, WEST); //
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		
		
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.addActionListener(this);
		add(friendField, WEST);
		AddFriend = new JButton("Add Friend");
		add(AddFriend, WEST);
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	Object source = e.getSource();
    	String TextStatus = statusField.getText();
    	String TextFriend = friendField.getText();
    	String TextName = nameField.getText();
    	if(source == ChangeStatus && !TextStatus.equals("")){//works if u click on ChangeStatus
    		status = TextStatus;
    		canvas.add(new GLabel("your status is:" + status, 10, position));
    		position += 15;
    	}
    	if(source == AddFriend && !TextFriend.equals("")){//works if u click on add frinend
    		friend = TextFriend;
    		canvas.add(new GLabel("your friend is:" + friend, 10, position));  
    		position += 15;
    	}
    	if(source == Add && !TextName.equals("")){//works if u click on add profile

    			
    		newPerson = TextName;
    		canvas.add(new GLabel("new profile: " + newPerson, 10, position));  
    		position += 15;
    	}
    	if(source == Delete && !TextName.equals("")){//works if u click on delete
    		delatedPerson = TextName;
    		canvas.add(new GLabel(" friend is deleted: " + delatedPerson, 10, position));  
    		position += 15;
    	}
    	if(source == Lookup && !TextName.equals("")){//works if u click on looke up
    		searchPerson = TextName;
    		canvas.add(new GLabel("searchPerson: " + searchPerson, 10, position));  
    		position += 15;
    	}
    }
    
    private	ArrayList<String> name = new ArrayList<String>();
    private ArrayList<String> Status = new ArrayList<String>();
    private ArrayList<String> friends = new ArrayList<String>();
    private ArrayList<GImage> Picture = new ArrayList<GImage>();
    private int position = 10;
	private String friend;
    private String  status;
    private String  frinds;
    
	private JTextField nameField;
	private JButton Add;
	private JButton Delete;
	private JButton Lookup;
	
	private JTextField statusField;	
	private JButton ChangePicture;
	private JButton AddFriend;
	private JButton ChangeStatus;
	
	private JLabel picture;
	private JTextField friendField;

	
	private FacePamphletCanvas canvas = new FacePamphletCanvas();;
	private FacePamphletDatabase database;
	private FacePamphletProfile currentProfile;
	
	private String shortFileName;
	private File file;
}

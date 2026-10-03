/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	GLabel status;
	GLabel message;
	GLabel friendsList;
	GLabel Name;
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {//

		message = new GLabel("Welcome to Face Pamphlet");
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() - message.getWidth() / 2), (getHeight() - (BOTTOM_MESSAGE_MARGIN * 2)));
		}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * passed in.
	 */
	public void showMessage(String msg) {//showMessage
		message.setLabel(msg);
		message.setLocation( (getWidth()- message.getWidth()) / 2, (getHeight() - (BOTTOM_MESSAGE_MARGIN * 2))); 
		} 
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		message.setLabel("");
		add(message);
		Name = new GLabel(profile.getName(), LEFT_MARGIN, TOP_MARGIN); //
		Name.setFont(PROFILE_NAME_FONT);
		Name.setColor(Color.BLUE);
		Name.move(0, Name.getAscent() / 2);
		add(Name);
		
		if(profile.getStatus().equals("")){// Display Profile Status

			status = new GLabel("has no status");
			status.setFont( PROFILE_STATUS_FONT);
			add(status,  LEFT_MARGIN, TOP_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		}else{
			status = new GLabel(profile.getName() + "'s staus: " + profile.getStatus() );
			status.setFont(PROFILE_STATUS_FONT);
			add(status, LEFT_MARGIN, TOP_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
		}
		// Display Friends List Title

		
		GLabel friendsNamesOnScreen = new GLabel("Friends: ", getWidth() / 2 , TOP_MARGIN+IMAGE_MARGIN);
		friendsNamesOnScreen.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendsNamesOnScreen);
		
		// Iterate Through Friends List
		Iterator<String> friends = profile.getFriends();

	}
	public void removeProfile(){
		
	}

}

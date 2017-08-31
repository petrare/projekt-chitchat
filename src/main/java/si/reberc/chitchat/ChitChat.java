package si.reberc.chitchat;

public class ChitChat {

	public static void main(String[] args) {
		ChatFrame chatFrame = new ChatFrame();
		DrugiRobot robotek = new DrugiRobot(chatFrame);
		robotek.activate();
		chatFrame.pack();
		chatFrame.setVisible(true);
		chatFrame.setTitle("Klepetalnica");
	}

}

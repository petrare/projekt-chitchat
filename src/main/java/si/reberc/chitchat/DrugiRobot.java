package si.reberc.chitchat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.ClientProtocolException;

import si.reberc.chitchat.*;

public class DrugiRobot extends TimerTask {
	private ChatFrame chat;

	public DrugiRobot(ChatFrame chat) {
		this.chat = chat;
	}

	public void SporociloJson(List<Message> seznam) {
		for (Message x : seznam) {
			System.out.println("sem dobil");
			System.out.println(x.isGlobal());
			System.out.println(x.getText());
			System.out.println(x.getRecipient());
			if (x.isGlobal()) {
				chat.receiveMessage(x.getSender(), x.getText());
			}

			else {
				chat.receiveMessage(x.getSender() + "@", x.getText());
			}
		}
	}

	/**
	 * Activate the robot!
	 */
	public void activate() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(this, 5000, 1000);
	}

	@Override
	public void run() {
		if (chat.prijavljen == true) {
			try {
				String uporabnik = chat.getUporabnik();
				SporociloJson(Chat.getMessages(uporabnik));
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

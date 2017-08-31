package si.reberc.chitchat;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Insets;

public class ChatFrame extends JFrame implements ActionListener, KeyListener {

	private JTextArea output;
	private JTextField input;
	private JTextField vzdevek;
	private JTextField prejemnik;
	public String uporabnik;
	public boolean prijavljen;

	public String getUporabnik() {
		return uporabnik;
	}

	private JButton gumbPrijava;
	private JButton gumbOdjava;

	public ChatFrame() {
		super();
		this.prijavljen = false;
		Container pane = this.getContentPane();
		this.uporabnik = System.getProperty("user.name");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 262, 35, 65, 67, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 212, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		JPanel VnosVzdevka = new JPanel();
		VnosVzdevka.setLayout(new FlowLayout());
		GridBagConstraints gbc_VnosVzdevka = new GridBagConstraints();
		gbc_VnosVzdevka.insets = new Insets(0, 0, 5, 5);
		gbc_VnosVzdevka.gridx = 0;
		gbc_VnosVzdevka.gridy = 0;
		pane.add(VnosVzdevka, gbc_VnosVzdevka);
		JLabel oznaka = new JLabel("Vzdevek:");
		GridBagConstraints gbc_oznaka = new GridBagConstraints();
		gbc_oznaka.insets = new Insets(0, 0, 5, 5);
		gbc_oznaka.gridx = 2;
		gbc_oznaka.gridy = 0;
		getContentPane().add(oznaka, gbc_oznaka);

		this.vzdevek = new JTextField(10);
		GridBagConstraints gbc_vzdevek = new GridBagConstraints();
		gbc_vzdevek.insets = new Insets(0, 0, 5, 0);
		gbc_vzdevek.gridx = 3;
		gbc_vzdevek.gridy = 0;
		pane.add(vzdevek, gbc_vzdevek);
		vzdevek.addKeyListener(this);

		JLabel prejemnik_1 = new JLabel("Prejemnik:");
		GridBagConstraints gbc_prejemnik_1 = new GridBagConstraints();
		gbc_prejemnik_1.insets = new Insets(0, 0, 0, 5);
		gbc_prejemnik_1.gridx = 2;
		gbc_prejemnik_1.gridy = 3;
		getContentPane().add(prejemnik_1, gbc_prejemnik_1);

		this.prejemnik = new JTextField(10);
		GridBagConstraints gbc_clovek = new GridBagConstraints();
		gbc_clovek.gridx = 3;
		gbc_clovek.gridy = 3;
		pane.add(prejemnik, gbc_clovek);

		this.gumbPrijava = new JButton("Prijava");
		GridBagConstraints gbc_gumbPrijava = new GridBagConstraints();
		gbc_gumbPrijava.insets = new Insets(0, 0, 5, 5);
		gbc_gumbPrijava.gridx = 2;
		gbc_gumbPrijava.gridy = 1;
		pane.add(gumbPrijava, gbc_gumbPrijava);
		gumbPrijava.addActionListener(this);

		this.gumbOdjava = new JButton("Odjava");
		GridBagConstraints gbc_gumbOdjava = new GridBagConstraints();
		gbc_gumbOdjava.insets = new Insets(0, 0, 5, 0);
		gbc_gumbOdjava.gridx = 3;
		gbc_gumbOdjava.gridy = 1;
		pane.add(gumbOdjava, gbc_gumbOdjava);
		gumbOdjava.addActionListener(this);

		this.output = new JTextArea(20, 40);
		this.output.setEditable(false);
		JScrollPane scrollpane = new JScrollPane(output);

		GridBagConstraints gbc_scrollpane = new GridBagConstraints();
		gbc_scrollpane.fill = GridBagConstraints.BOTH;
		gbc_scrollpane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollpane.gridx = 0;
		gbc_scrollpane.gridy = 2;
		pane.add(scrollpane, gbc_scrollpane);

		this.input = new JTextField(40);
		GridBagConstraints gbc_input = new GridBagConstraints();
		gbc_input.fill = GridBagConstraints.BOTH;
		gbc_input.insets = new Insets(0, 0, 0, 5);
		gbc_input.gridx = 0;
		gbc_input.gridy = 3;
		pane.add(input, gbc_input);
		input.addKeyListener(this);
	}

	/**
	 * @param person
	 *            - the person sending the message
	 * @param message
	 *            - the message content
	 */
	public void addMessage(boolean global, String person, String message) {
		String chat = this.output.getText();
		Chat.SendMessage(global, person, this.prejemnik.getText(), message);
		this.output.setText(chat + person + ": " + message + "\n");
	}

	public void receiveMessage(String person, String message) {
		String chat = this.output.getText();
		this.output.setText(chat + person + ": " + message + "\n");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.gumbPrijava && this.vzdevek.getText().equals("")) {
			Chat.prijava(this.uporabnik);
			this.prijavljen = true;
		} else if (e.getSource() == this.gumbPrijava) {
			this.uporabnik = this.vzdevek.getText();
			Chat.prijava(this.uporabnik);
			this.prijavljen = true;
		} else if (e.getSource() == this.gumbOdjava) {
			Chat.odjava(this.uporabnik);
			this.prijavljen = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		if (e.getSource() == this.vzdevek) {
			if (e.getKeyChar() == '\n') {
				this.uporabnik = this.vzdevek.getText();
			}
		} else if (e.getSource() == this.input) {
			if (e.getKeyChar() == '\n') {
				if (this.prejemnik.getText().equals("")) {
					this.addMessage(true, this.uporabnik, this.input.getText());
					this.input.setText("");
				} else {
					this.addMessage(false, this.uporabnik, this.input.getText());
					this.input.setText("");
				}

			}

		}

	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
// addWindowsListener(new WindowAdapter(){
// public void windowOpened(WindowEvent e){
// input.requestFocusInWindow();

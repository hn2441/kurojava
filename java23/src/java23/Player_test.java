package java23;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.media.*;
import javax.media.protocol.*;
import java.io.*;

class player extends JFrame implements ActionListener, ControllerListener {
	private Container con;
	private JPanel contentPanel; // ������ ������ �� �г�
	private Component controlComponent; // ȭ�� ������Ʈ
	private Component visualComponent; // ��� ��Ʈ�� ������Ʈ
	private Player player;
	private JButton bt = new JButton("START");

	public player(String title) {
		super(title);
		init();
		start();
		this.setSize(600, 400);
		this.setVisible(true);

	}

	public void init() {
		con = this.getContentPane();
		con.setLayout(new BorderLayout());

		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(bt, BorderLayout.EAST);

		con.add(southPanel, BorderLayout.SOUTH);

	}

	/* Player�� �����, �����ʸ� ����Ѵ�. */
	public void makePlayer(String filePath) {
		MediaLocator locator = new MediaLocator(filePath);
		System.out.println(filePath);
		try {
			/* DataSource ���� */
			DataSource dataSource = Manager.createDataSource(locator);

			/* ���� �������� Player�� �ִٸ� �ݴ´�. */
			if (player != null) {
				player.close();
			}

			/* Player ���� */
			player = Manager.createPlayer(dataSource);

			/* ������ ��� */
			player.addControllerListener(this);
		} catch (IOException e) {
			System.out.println(e);
		} catch (NoDataSourceException e) {
			System.out.println("DataSource�� ���� �� �����ϴ�.");
		} catch (NoPlayerException e) {
			System.out.println("Player�� ���� �� �����ϴ�.");
		}
	}

	/* Player�� ���º��� ���� */
	public synchronized void controllerUpdate(ControllerEvent e) {

		/* Player�� Realized ������ �� ������Ʈ�� �����ӿ� �߰��Ѵ�. */
		if (e instanceof RealizeCompleteEvent) {
			contentPanel = new JPanel(new BorderLayout());

			/* �⺻ ���� ������Ʈ */
			controlComponent = player.getControlPanelComponent();
			if (controlComponent != null) {
				contentPanel.add(controlComponent, BorderLayout.SOUTH);
			}

			/* Visual ������Ʈ */
			visualComponent = player.getVisualComponent();
			if (visualComponent != null) {
				contentPanel.add(visualComponent, BorderLayout.CENTER);
			}

			/* �����ӿ� ������Ʈ �߰� */
			con.add(contentPanel);
			// setContentPane(contentPanel);
			pack();
		}

	}

	public void start() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		bt.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		/* ���� ���� �̺�Ʈ ó�� */
		if (e.getSource() == bt) {
			File f = new File("1.mp4");
			String filePath = "file:/" + f.getAbsolutePath();
			/* Player�� �����, �����Ű��. */
			makePlayer(filePath);
			try {
				player.start();
			} catch (NullPointerException ee) {
			}

		}

	}

};

class Player_test {
	public static void main(String[] args) {
		player ppp = new player("test");
	}
}

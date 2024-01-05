import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;

public class Homepage extends JFrame {

	private JPanel contentPane;

	PanelHome panelHome;
	PanelBooking panelBooking;
	PanelManageBooking panelManageBooking;
	PanelCustomer panelCustomer;
	PanelAddCustomer panelAddCustomer;
	PanelPayment panelPayment;
	PanelEditCustomer panelEditCustomer;
	PanelStaff panelStaff;
	PanelEditBooking panelEditBooking;
	//Declaring ArrayList with type of CustomerRecord,StaffRecord and BookingDetails
	private ArrayList<CustomerRecord> customerRecordList = new ArrayList<CustomerRecord>();
	private ArrayList<StaffRecord> staffRecordList = new ArrayList<StaffRecord>();
	private ArrayList<BookingDetails> bookingDetailsList = new ArrayList<BookingDetails>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	JPanel menuStaff = new JPanel();

	// Create the frame.
	public Homepage() {
		//Instantiating objects of the Panel Class
		panelHome = new PanelHome(this);
		panelBooking = new PanelBooking(this);
		panelManageBooking = new PanelManageBooking(this);
		panelCustomer = new PanelCustomer(this);
		panelAddCustomer = new PanelAddCustomer(this);
		panelPayment = new PanelPayment(this);
		panelEditCustomer = new PanelEditCustomer(this);
		panelStaff = new PanelStaff(this);
		panelEditBooking = new PanelEditBooking(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1313, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 204, 255));
		panel.setBounds(0, 0, 194, 691);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel menuBooking = new JPanel();
		//Switch to booking panel
		menuBooking.addMouseListener(new PanelButtonMouseAdapter(menuBooking) {
			@Override
			public void mousePressed(MouseEvent e) {
				menuClicked(panelBooking);
				panelPayment.receiptArea.setText("");
				panelPayment.firstNameField.setText("");
				panelPayment.lastNameField.setText("");
				panelPayment.icField.setText("");
				panelPayment.contactField.setText("");
				panelPayment.emailField.setText("");
				panelBooking.model.setRowCount(0);
			}
		});
		menuBooking.setBackground(new Color(102, 204, 255));
		menuBooking.setBounds(0, 362, 194, 51);
		panel.add(menuBooking);
		menuBooking.setLayout(null);

		JLabel lblNewLabel = new JLabel("Add Booking");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(46, 11, 101, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		menuBooking.add(lblNewLabel);

		JPanel menuHome = new JPanel();
		//Switch to home panel
		menuHome.addMouseListener(new PanelButtonMouseAdapter(menuHome) {
			@Override
			public void mousePressed(MouseEvent e) {
				menuClicked(panelHome);
			}
		});
		menuHome.setBackground(new Color(102, 204, 255));
		menuHome.setLayout(null);
		menuHome.setBounds(0, 217, 194, 51);
		panel.add(menuHome);

		JLabel lblHome = new JLabel("Home");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHome.setForeground(Color.WHITE);
		lblHome.setBounds(62, 11, 64, 22);
		menuHome.add(lblHome);

		JPanel menuManageBooking = new JPanel();
		//Switch to manage booking panel
		menuManageBooking.addMouseListener(new PanelButtonMouseAdapter(menuManageBooking) {
			@Override
			public void mousePressed(MouseEvent e) {
				menuClicked(panelManageBooking);
			}
		});
		menuManageBooking.addMouseListener(new PanelButtonMouseAdapter(menuManageBooking));
		menuManageBooking.setBackground(new Color(102, 204, 255));
		menuManageBooking.setLayout(null);
		menuManageBooking.setBounds(0, 436, 194, 51);
		panel.add(menuManageBooking);

		JLabel lblManageBookingRecords = new JLabel("Manage Booking ");
		lblManageBookingRecords.setForeground(Color.WHITE);
		lblManageBookingRecords.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageBookingRecords.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblManageBookingRecords.setBounds(10, 11, 174, 22);
		menuManageBooking.add(lblManageBookingRecords);
		//Switch to staff panel
		menuStaff.addMouseListener(new PanelButtonMouseAdapter(menuStaff) {
			@Override
			public void mousePressed(MouseEvent e) {
				menuClicked(panelStaff);
			}
		});
		menuStaff.setBackground(new Color(102, 204, 255));
		menuStaff.setLayout(null);
		menuStaff.setBounds(0, 509, 194, 51);
		panel.add(menuStaff);

		JLabel lblStaff = new JLabel("Manage Staff");
		lblStaff.setForeground(Color.WHITE);
		lblStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblStaff.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStaff.setBounds(10, 11, 174, 22);
		menuStaff.add(lblStaff);

		JPanel panelMainContent = new JPanel();
		panelMainContent.setBounds(193, 0, 1104, 691);
		contentPane.add(panelMainContent);
		panelMainContent.setLayout(null);

		panelMainContent.add(panelHome);
		panelMainContent.add(panelBooking);
		panelMainContent.add(panelManageBooking);
		panelMainContent.add(panelCustomer);
		panelMainContent.add(panelAddCustomer);
		panelMainContent.add(panelPayment);
		panelMainContent.add(panelEditCustomer);
		panelMainContent.add(panelStaff);
		panelMainContent.add(panelEditBooking);

		menuClicked(panelHome);

		JPanel menuManageCustomer = new JPanel();
		//Switch to manage customer panel
		menuManageCustomer.addMouseListener(new PanelButtonMouseAdapter(menuManageCustomer) {
			@Override
			public void mousePressed(MouseEvent e) {
				menuClicked(panelCustomer);
			}
		});
		menuManageCustomer.setLayout(null);
		menuManageCustomer.setBackground(new Color(102, 204, 255));
		menuManageCustomer.setBounds(0, 287, 194, 51);
		panel.add(menuManageCustomer);

		JLabel lblManageCustomer = new JLabel("Manage Customer");
		lblManageCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageCustomer.setForeground(Color.WHITE);
		lblManageCustomer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblManageCustomer.setBounds(10, 11, 174, 22);
		menuManageCustomer.add(lblManageCustomer);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		Image img_logo = new ImageIcon(this.getClass().getResource("/logo.png")).getImage().getScaledInstance(120, 120,
				Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(img_logo));
		lblLogo.setBounds(43, 18, 110, 120);
		panel.add(lblLogo);

		JLabel lblTitle = new JLabel("De' Luna Hotel");
		lblTitle.setForeground(new Color(255, 215, 0));
		lblTitle.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 24));
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setBounds(10, 149, 174, 37);
		panel.add(lblTitle);
		
		//Run the methods to setup ArrayList records and constructing the JTables
		Staff.SetupStaffRecordList(staffRecordList);
		Staff.ConstructStaffTable(this, staffRecordList);
		Customer.SetupCustomerRecordList(customerRecordList);
		Customer.ConstructCustomerTable(this, customerRecordList);
		Booking.SetupBookingDetailsList(bookingDetailsList);
		Booking.ConstructBookingTable(this, bookingDetailsList);
	
	}

	//Getter methods for each ArrayList
	public ArrayList<CustomerRecord> getCustomerRecordList() {
		return customerRecordList;
	}

	public ArrayList<StaffRecord> getStaffRecordList() {
		return staffRecordList;
	}

	public ArrayList<BookingDetails> getBookingDetailsList() {
		return bookingDetailsList;
	}

	public void menuClicked(JPanel panel) {
		panelHome.setVisible(false);
		panelBooking.setVisible(false);
		panelManageBooking.setVisible(false);
		panelCustomer.setVisible(false);
		panelAddCustomer.setVisible(false);
		panelPayment.setVisible(false);
		panelEditCustomer.setVisible(false);
		panelStaff.setVisible(false);
		panelEditBooking.setVisible(false);

		panel.setVisible(true);
	}
	
	//Change panel colors depending on mouse event
	private class PanelButtonMouseAdapter extends MouseAdapter {

		JPanel mousePanel;

		public PanelButtonMouseAdapter(JPanel panel) {
			this.mousePanel = panel;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			mousePanel.setBackground(new Color(245, 224, 130));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			mousePanel.setBackground(new Color(102, 204, 255));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			mousePanel.setBackground((Color.gray));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			mousePanel.setBackground((Color.gray));
		}

	}
}

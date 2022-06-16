package com.example.cafemanagementsystem;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

public class cafeteriaGUI extends JFrame{
	JMenu mnuCustomer,mnuStaff,mnuMenu,mnuBill;
	JMenuItem micInsert,micDelete,micModify,micDisplay,
			  misInsert,misDelete,misModify,misDisplay,
			  mimInsert,mimDelete,mimModify,mimDisplay,
			  mibInsert,mibDelete,mibModify,mibDisplay;
	JTextArea micDisplayContent,misDisplayContent,mimDisplayContent,mibDisplayContent;
	JLabel lblcustId,lblcustfn,lblcustln,lblcusthn,lblcuststreet,lblcustcity,lblcuststate,lblcustpin,lbl,
		   lblstaffName,lblstaffId,lblstaffSal,lblstaffAge,
		   lblcategoryId,lblcategoryName,lblfoodName,lblfoodQ,lblcost,
		   lblitemName,lblquantity,lblprice,lblbid,lblcustomerId,
		   lblmisDeleteStaffId,lblmicDeleteCustId,lblmimDeleteCategoryId,lblmibDeletebillId;
	JTextField tfcustId,tfcustfn,tfcustln,tfcusthn,tfcuststreet,tfcustcity,tfcuststate,tfcustpin,
			   tfstaffName,tfstaffId,tfstaffSal,tfstaffAge,
			   tfcategoryId,tfcategoryName,tffoodName,tffoodQ,tfcost,
			   tfitemName,tfquantity,tfprice,tfbid,tfcustomerId;
	JButton	btnstaffDelete,btnstaffShow,btnstaffModify,btnstaffSubmit,
			btnCustDelete,btnCustShow,btnCustModify,btncustSubmit,
			btnMenuShow, btnmenuDelete,btnmenuModify,btnmenuSubmit,
			btnBillShow,btnBillDelete,btnBillModify,btnbillSubmit;
	JComboBox cstaffId,cCustId,cCategoryId,cBillId;
		
	public cafeteriaGUI(){
		getContentPane().setBackground(Color.lightGray);
		setSize(1200,800);
		//menu
		JMenuBar mb=new JMenuBar();
		mnuCustomer = new JMenu("Customer");
		mnuStaff = new JMenu("Staff");
		mnuMenu = new JMenu("Food Menu");
		mnuBill = new JMenu("Bill");
		micInsert = new JMenuItem("Insert");
		micDelete = new JMenuItem("Delete");
		micModify = new JMenuItem("Modify");
		micDisplay= new JMenuItem("Display");
		misInsert = new JMenuItem("Insert");
		misDelete = new JMenuItem("Delete");
		misModify = new JMenuItem("Modify");
		misDisplay= new JMenuItem("Display");
		mimInsert = new JMenuItem("Insert");
		mimDelete = new JMenuItem("Delete");
		mimModify = new JMenuItem("Modify");
		mimDisplay= new JMenuItem("Display");
		mibInsert = new JMenuItem("Insert");
		mibDelete = new JMenuItem("Delete");
		mibModify = new JMenuItem("Modify");
		mibDisplay= new JMenuItem("Display");
		mnuCustomer.add(micInsert);	
		mnuCustomer.add(micDelete);
		mnuCustomer.add(micModify);
		mnuCustomer.add(micDisplay);
		mnuStaff.add(misInsert);
		mnuStaff.add(misDelete);
		mnuStaff.add(misModify);
		mnuStaff.add(misDisplay);
		mnuMenu.add(mimInsert);
		mnuMenu.add(mimDelete);
		mnuMenu.add(mimModify);
		mnuMenu.add(mimDisplay);
		mnuBill.add(mibInsert);
		mnuBill.add(mibDelete);
		mnuBill.add(mibModify);
		mnuBill.add(mibDisplay);
		mb.add(mnuCustomer);
		mb.add(mnuStaff);
		mb.add(mnuMenu);
		mb.add(mnuBill);
		setJMenuBar(mb);
		
		getContentPane().removeAll();
		repaint();
		JLabel welcome=new JLabel("                WELCOME TO CAFETERIA MANAGEMENT SYSTEM");
		welcome.setFont(new Font("Verdana",Font.BOLD,28));
		add(welcome);
		
		//Add actionListener for display button in customer
		micDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				repaint();
				getContentPane().setBackground(Color.GREEN);
				lbl= new JLabel("Display Customer table ");
				lbl.setFont(new Font("Verdana",Font.BOLD,20));
				lbl.setBounds(30,20,340,50);
				add(lbl);
				try {
					//to display all rows in customers table
					JTextArea micDisplayContent=new JTextArea(40,70);
					micDisplayContent.setBounds(100,100,800,400);
					micDisplayContent.setFont(new Font("Verdana",Font.BOLD,12));
					add(micDisplayContent);
					micDisplayContent.setEditable(false);
					micDisplayContent.setText("ID \tFIRSTNAME \tLASTNAME  \tHOUSENO  \tSTREET  \tCITY  \tSTATE \tPINCODE\n\n");
					String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
					String username = "it19737043";
					String password = "vasavi";
					Connection connection = DriverManager.getConnection(dbURL,username,password);
					Statement st=connection.createStatement();
					ResultSet rs=st.executeQuery("select * from customer");
					while(rs.next()) {
						micDisplayContent.append(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\t"+rs.getInt(8)+"\n");
					}
					connection.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		  });
		//Add actionListener to insert button in customer
		micInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				getContentPane().removeAll();
				repaint();
				getContentPane().setBackground(Color.yellow);
				lbl= new JLabel("Insert into Customer table ");
				lbl.setFont(new Font("Verdana",Font.BOLD,20));
				lbl.setBounds(30,20,340,50);
				add(lbl);
				try {
					//label and textfield for customer ID
					lblcustId=new JLabel("Enter customer ID:");
					lblcustId.setBounds(50,80,110,30);
					add(lblcustId);
					tfcustId=new JTextField(10);
					tfcustId.setBounds(220,80,190,30);
					add(tfcustId);
					//label and textfield for customer first name
					lblcustfn=new JLabel("Enter customer's FirstName:");
					lblcustfn.setBounds(50,130,190,30);
					add(lblcustfn);
					tfcustfn=new JTextField(10);
					tfcustfn.setBounds(220,130,190,30);
					add(tfcustfn);
					//label and textfield for customer last name
					lblcustln=new JLabel("Enter customer's LastName:");
					lblcustln.setBounds(50,180,190,30);
					add(lblcustln);
					tfcustln=new JTextField(10);
					tfcustln.setBounds(220,180,190,30);
					add(tfcustln);
					//label and textfield for customer HouseNo
					lblcusthn=new JLabel("Enter customer's HouseNo:");
					lblcusthn.setBounds(50,230,190,30);
					add(lblcusthn);
					tfcusthn=new JTextField(10);
					tfcusthn.setBounds(220,230,190,30);
					add(tfcusthn);
					//label and textfield for customer streetname
					lblcuststreet=new JLabel("Enter customer's streetName:");
					lblcuststreet.setBounds(50,280,190,30);
					add(lblcuststreet);
					tfcuststreet=new JTextField(10);
					tfcuststreet.setBounds(220,280,190,30);
					add(tfcuststreet);
					//label and textfield for customer city
					lblcustcity=new JLabel("Enter customer's city:");
					lblcustcity.setBounds(50,330,190,30);
					add(lblcustcity);
					tfcustcity=new JTextField(10);
					tfcustcity.setBounds(220,330,190,30);
					add(tfcustcity);
					//label and textfield for customer state
					lblcuststate=new JLabel("Enter customer's state:");
					lblcuststate.setBounds(50,380,190,30);
					add(lblcuststate);
					tfcuststate=new JTextField(10);
					tfcuststate.setBounds(220,380,190,30);
					add(tfcuststate);	
					//label and textfield for customer pincode
					lblcustpin=new JLabel("Enter customer's pincode:");
					lblcustpin.setBounds(50,430,190,30);
					add(lblcustpin);
					tfcustpin=new JTextField(10);
					tfcustpin.setBounds(220,430,190,30);
					add(tfcustpin);
					//button for inserting a customer
				    btncustSubmit=new JButton("Submit");
					btncustSubmit.setBounds(120,500,100,30);
					btncustSubmit.setBackground(Color.green);
					add(btncustSubmit);
					
					btncustSubmit.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							try {
								String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
								String username = "it19737043";
								String password = "vasavi";
								Connection connection = DriverManager.getConnection(dbURL,username,password);
								String id=tfcustId.getText();
								String fn=tfcustfn.getText();
								String ln=tfcustln.getText();
								String hn=tfcusthn.getText();
								String street=tfcuststreet.getText();
								String city=tfcustcity.getText();
								String state=tfcuststate.getText();
								String pin=tfcustpin.getText();
								String sql="INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?,?)";
								PreparedStatement stmt=connection.prepareStatement(sql);
								stmt.setString(1,id);
								stmt.setString(2,fn);
								stmt.setString(3,ln);
								stmt.setString(4,hn);
								stmt.setString(5,street);
								stmt.setString(6,city);
								stmt.setString(7,state);
								stmt.setString(8,pin);
								int rows=stmt.executeUpdate();
								stmt.executeUpdate("commit");
								if(rows>0) {
									JOptionPane.showMessageDialog(cafeteriaGUI.this,"Inserted succesfully.");
							    }
								connection.close();
							}catch(SQLException se) {
								JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry,couldn't insert, "+se);
								System.out.println("could not insert"+se);
							}
						}
					});
			                
				}catch(Exception se) {
					se.printStackTrace();
				}
			}
		  });	
		//add actionListener to delete button in customer
		micDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().setBackground(Color.cyan);
				repaint();
				lbl= new JLabel("Delete from Customer table ");
				lbl.setFont(new Font("Verdana",Font.BOLD,20));
				lbl.setBounds(30,15,340,50);
				add(lbl);
				try {
					String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
					String username = "it19737043";
					String password = "vasavi";
					Connection connection = DriverManager.getConnection(dbURL,username,password);
					String sql="select id from customer";
					PreparedStatement stmt=connection.prepareStatement(sql);
					ResultSet rs= stmt.executeQuery(sql);
			
					String ids[] =new String[20];
					int i=0;
					while(rs.next())
					{
						 ids[i]=rs.getString("id");
						 i++;
					}
					int j=0,count=0;
					while(ids[j]!=null)
					{
						count++;j++;
					}
					String id[]=new String[count];
					for(j=0;j<count;j++) id[j]=ids[j];
					
		            //label and jcombobox for selecting an id to be deleted
					lblmicDeleteCustId=new JLabel("Select Customer Id:");
					lblmicDeleteCustId.setBounds(50,70,170,30);
					lblmicDeleteCustId.setFont(new Font("Verdana",Font.BOLD,12));
					add(lblmicDeleteCustId);
					cCustId=new JComboBox(id);
					cCustId.setBounds(220,70,180,30);
					cCustId.setFont(new Font("Verdana",Font.BOLD,12));
					add(cCustId);
					
					//button for show details a Customer
				    btnCustShow=new JButton("Show Customer Details");
					btnCustShow.setBounds(120,110,170,30);
					btnCustShow.setBackground(Color.orange);
					add(btnCustShow);
					
					//label and textfield for customer ID
					lblcustId=new JLabel("Enter customer ID:");
					lblcustId.setBounds(50,150,110,30);
					add(lblcustId);
					tfcustId=new JTextField(10);
					tfcustId.setBounds(220,150,190,30);
					add(tfcustId);
					//label and textfield for customer first name
					lblcustfn=new JLabel("Enter customer's FirstName:");
					lblcustfn.setBounds(50,200,190,30);
					add(lblcustfn);
					tfcustfn=new JTextField(10);
					tfcustfn.setBounds(220,200,190,30);
					add(tfcustfn);
					//label and textfield for customer last name
					lblcustln=new JLabel("Enter customer's LastName:");
					lblcustln.setBounds(50,250,190,30);
					add(lblcustln);
					tfcustln=new JTextField(10);
					tfcustln.setBounds(220,250,190,30);
					add(tfcustln);
					//label and textfield for customer HouseNo
					lblcusthn=new JLabel("Enter customer's HouseNo:");
					lblcusthn.setBounds(50,300,190,30);
					add(lblcusthn);
					tfcusthn=new JTextField(10);
					tfcusthn.setBounds(220,300,190,30);
					add(tfcusthn);
					//label and textfield for customer streetname
					lblcuststreet=new JLabel("Enter customer's streetName:");
					lblcuststreet.setBounds(50,350,190,30);
					add(lblcuststreet);
					tfcuststreet=new JTextField(10);
					tfcuststreet.setBounds(220,350,190,30);
					add(tfcuststreet);
					//label and textfield for customer city
					lblcustcity=new JLabel("Enter customer's city:");
					lblcustcity.setBounds(50,400,190,30);
					add(lblcustcity);
					tfcustcity=new JTextField(10);
					tfcustcity.setBounds(220,400,190,30);
					add(tfcustcity);
					//label and textfield for customer state
					lblcuststate=new JLabel("Enter customer's state:");
					lblcuststate.setBounds(50,450,190,30);
					add(lblcuststate);
					tfcuststate=new JTextField(10);
					tfcuststate.setBounds(220,450,190,30);
					add(tfcuststate);	
					//label and textfield for customer pincode
					lblcustpin=new JLabel("Enter customer's pincode:");
					lblcustpin.setBounds(50,500,190,30);
					add(lblcustpin);
					tfcustpin=new JTextField(10);
					tfcustpin.setBounds(220,500,190,30);
					add(tfcustpin);
					//button for inserting a customer
				    btnCustDelete=new JButton("Delete");
					btnCustDelete.setBounds(120,550,100,30);
					btnCustDelete.setBackground(Color.green);
					add(btnCustDelete);
					
					btnCustShow.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String sid1=(String) cCustId.getSelectedItem();
								String sql2="select * from customer where ID="+sid1;
								PreparedStatement stmt2=connection.prepareStatement(sql2);
								stmt2.executeUpdate();
								ResultSet rs2= stmt2.executeQuery(sql2);
								while(rs2.next()) {
									tfcustId.setText(rs2.getString(1));
								    tfcustfn.setText(rs2.getString(2));
									tfcustln.setText(rs2.getString(3));
									tfcusthn.setText(rs2.getString(4));
									tfcuststreet.setText(rs2.getString(5));
								    tfcustcity.setText(rs2.getString(6));
									tfcuststate.setText(rs2.getString(7));
									tfcustpin.setText(rs2.getString(8));
								}										
						    }catch(Exception ae) {
								System.out.println("error");
							}
							
						}
					
					});
					btnCustDelete.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								String sid=(String) cCustId.getSelectedItem();
								String sqlDelete="delete from customer S where S.id=?";
								PreparedStatement stmt3=connection.prepareStatement(sqlDelete);
								stmt3.setString(1,sid);
								int rows=stmt3.executeUpdate();
								stmt.executeUpdate("commit");
								if(rows>0) {
									tfcustId.setText("");
								    tfcustfn.setText("");
									tfcustln.setText("");
									tfcusthn.setText("");
									tfcuststreet.setText("");
								    tfcustcity.setText("");
									tfcuststate.setText("");
									tfcustpin.setText("");
									JOptionPane.showMessageDialog(cafeteriaGUI.this,"Deleted succesfully.");
							    }
								connection.close();
							}catch(SQLException se) {
								JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't delete");
								System.out.println("could not delete"+se);
							}
						}
						
					});
				//connection.close();	
				}catch(SQLException se) {
					se.printStackTrace();
				}
				
			}
		});
		//add actionListener to modify button in customer
				micModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.pink);
						lbl= new JLabel("Modify Customer table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,15,340,50);
						add(lbl);
						try {
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							String sql="select id from customer";
							PreparedStatement stmt=connection.prepareStatement(sql);
							ResultSet rs= stmt.executeQuery(sql);
					
							String ids[] =new String[20];
							int i=0;
							while(rs.next())
							{
								 ids[i]=rs.getString("id");
								 i++;
							}
							int j=0,count=0;
							while(ids[j]!=null)
							{
								count++;j++;
							}
							String id[]=new String[count];
							for(j=0;j<count;j++) id[j]=ids[j];
							
				            //label and jcombobox for selecting an id to be deleted
							lblmicDeleteCustId=new JLabel("Select Customer Id:");
							lblmicDeleteCustId.setBounds(50,100,170,30);
							lblmicDeleteCustId.setFont(new Font("Verdana",Font.BOLD,12));
							add(lblmicDeleteCustId);
							cCustId=new JComboBox(id);
							cCustId.setBounds(220,100,170,30);
							cCustId.setFont(new Font("Verdana",Font.BOLD,12));
							add(cCustId);
							
							//button for show details a Customer
						    btnCustShow=new JButton("Show Customer Details");
							btnCustShow.setBounds(120,150,170,30);
							btnCustShow.setBackground(Color.orange);
							add(btnCustShow);
							
							//label and textfield for customer ID
							lblcustId=new JLabel("You cannot modify customer ID");
							lblcustId.setBounds(50,200,190,30);
							add(lblcustId);
							/*tfcustId=new JTextField(10);
							tfcustId.setBounds(220,130,190,30);
							add(tfcustId);*/
							//label and textfield for customer first name
							lblcustfn=new JLabel("Enter customer's FirstName:");
							lblcustfn.setBounds(50,250,190,30);
							add(lblcustfn);
							tfcustfn=new JTextField(10);
							tfcustfn.setBounds(220,250,190,30);
							add(tfcustfn);
							//label and textfield for customer last name
							lblcustln=new JLabel("Enter customer's LastName:");
							lblcustln.setBounds(50,300,190,30);
							add(lblcustln);
							tfcustln=new JTextField(10);
							tfcustln.setBounds(220,300,190,30);
							add(tfcustln);
							//label and textfield for customer HouseNo
							lblcusthn=new JLabel("Enter customer's HouseNo:");
							lblcusthn.setBounds(50,350,190,30);
							add(lblcusthn);
							tfcusthn=new JTextField(10);
							tfcusthn.setBounds(220,350,190,30);
							add(tfcusthn);
							//label and textfield for customer streetname
							lblcuststreet=new JLabel("Enter customer's streetName:");
							lblcuststreet.setBounds(50,400,190,30);
							add(lblcuststreet);
							tfcuststreet=new JTextField(10);
							tfcuststreet.setBounds(220,400,190,30);
							add(tfcuststreet);
							//label and textfield for customer city
							lblcustcity=new JLabel("Enter customer's city:");
							lblcustcity.setBounds(50,450,190,30);
							add(lblcustcity);
							tfcustcity=new JTextField(10);
							tfcustcity.setBounds(220,450,190,30);
							add(tfcustcity);
							//label and textfield for customer state
							lblcuststate=new JLabel("Enter customer's state:");
							lblcuststate.setBounds(50,500,190,30);
							add(lblcuststate);
							tfcuststate=new JTextField(10);
							tfcuststate.setBounds(220,500,190,30);
							add(tfcuststate);	
							//label and textfield for customer pincode
							lblcustpin=new JLabel("Enter customer's pincode:");
							lblcustpin.setBounds(50,550,190,30);
							add(lblcustpin);
							tfcustpin=new JTextField(10);
							tfcustpin.setBounds(220,550,190,30);
							add(tfcustpin);
							//button for inserting a customer
						    btnCustModify=new JButton("Modify");
							btnCustModify.setBounds(120,600,100,30);
							btnCustModify.setBackground(Color.green);
							add(btnCustModify);
							
							btnCustShow.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cCustId.getSelectedItem();
										String sql2="select firstname,lastname,houseno,street,city,state,pincode from customer where ID="+sid1;
										PreparedStatement stmt2=connection.prepareStatement(sql2);
										stmt2.executeUpdate();
										ResultSet rs2= stmt2.executeQuery(sql2);
										while(rs2.next()) {
											tfcustfn.setText(rs2.getString(1));
											tfcustln.setText(rs2.getString(2));
											tfcusthn.setText(rs2.getString(3));
											tfcuststreet.setText(rs2.getString(4));
										    tfcustcity.setText(rs2.getString(5));
											tfcuststate.setText(rs2.getString(6));
											tfcustpin.setText(rs2.getString(7));
										}										
								    }catch(Exception ae) {
										System.out.println("error");
									}
									
								}
							
							});
							btnCustModify.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid=(String) cCustId.getSelectedItem();
										//String id=tfcustId.getText();
										String fn=tfcustfn.getText();
										String ln=tfcustln.getText();
										String hn=tfcusthn.getText();
										String street=tfcuststreet.getText();
										String city=tfcustcity.getText();
										String state=tfcuststate.getText();
										String pin=tfcustpin.getText();
										String sqlUpdate="update customer set firstname=" + "\'" + fn + "\', "+
														"lastname=" + "\'" + ln + "\', "+
														"houseno=" + "\'" + hn + "\', "+
														"street=" + "\'"+ street+"\',"+
														"city=" + "\'"+ city+"\',"+
														"state=" + "\'"+ state+"\',"+
														"pincode=" + pin+
														" where id="+sid;
										PreparedStatement stmt3=connection.prepareStatement(sqlUpdate);
										int rows=stmt3.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Modified succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't modify");
										System.out.println("could not modify"+se);
									}
								}
								
							});
						//connection.close();	
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
				});
		//Add actionListener for display button in staff
				misDisplay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae)
					{
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.green);
						lbl= new JLabel("Display Staff table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,20,340,50);
						add(lbl);
						try {
							//to display all rows in staff table
							JTextArea misDisplayContent=new JTextArea(70,70);
							misDisplayContent.setBounds(80,90,800,400);
							misDisplayContent.setFont(new Font("Verdana",Font.BOLD,12));
							add(misDisplayContent);
							misDisplayContent.setEditable(false);
							misDisplayContent.setText("NAME \tID \tSALARY \tAGE\n\n");
							
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							Statement st=connection.createStatement();
							ResultSet rs=st.executeQuery("select * from staff");
							while(rs.next()) {
								misDisplayContent.append(rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getInt(3)+"\t"+rs.getInt(4)+"\n");
							}
							connection.close();
						}catch(SQLException se) {
							se.printStackTrace();
						}
					}
				  });
				//Add actionListener to insert button in staff
				misInsert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.yellow);
						lbl= new JLabel("Insert into staff table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,20,340,50);
						add(lbl);
						try {
							//label and textfield for staff Name
							lblstaffName=new JLabel("Enter Staff Name:");
							lblstaffName.setBounds(50,80,110,30);
							add(lblstaffName);
							tfstaffName=new JTextField(10);
							tfstaffName.setBounds(220,80,190,30);
							add(tfstaffName);
							//label and textfield for staff Id
							lblstaffId=new JLabel("Enter Staff ID:");
							lblstaffId.setBounds(50,130,190,30);
							add(lblstaffId);
							tfstaffId=new JTextField(10);
							tfstaffId.setBounds(220,130,190,30);
							add(tfstaffId);
							//label and textfield for staff salary
							lblstaffSal=new JLabel("Enter staff salary:");
							lblstaffSal.setBounds(50,180,190,30);
							add(lblstaffSal);
							tfstaffSal=new JTextField(10);
							tfstaffSal.setBounds(220,180,190,30);
							add(tfstaffSal);
							//label and textfield for staff age
							lblstaffAge=new JLabel("Enter staff age:");
							lblstaffAge.setBounds(50,230,190,30);
							add(lblstaffAge);
							tfstaffAge=new JTextField(10);
							tfstaffAge.setBounds(220,230,190,30);
							add(tfstaffAge);
							//button for inserting a staff
						    btnstaffSubmit=new JButton("Submit");
							btnstaffSubmit.setBounds(120,300,100,30);
							btnstaffSubmit.setBackground(Color.green);
							add(btnstaffSubmit);
							
							btnstaffSubmit.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e){
									try {
										String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
										String username = "it19737043";
										String password = "vasavi";
										Connection connection = DriverManager.getConnection(dbURL,username,password);
										String name=tfstaffName.getText();
										String id=tfstaffId.getText();
										String sal=tfstaffSal.getText();
										String age=tfstaffAge.getText();
										String sql="INSERT INTO STAFF VALUES(?,?,?,?)";
										PreparedStatement stmt=connection.prepareStatement(sql);
										stmt.setString(1,name);
										stmt.setString(2,id);
										stmt.setString(3,sal);
										stmt.setString(4,age);
										int rows=stmt.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Inserted succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry,couldn't insert, "+se);
										System.out.println("could not insert"+se);
									}
								}
							});
					                
						}catch(Exception se) {
							se.printStackTrace();
						}
					}
				  });	
				//add actionListener to delete button in staff
				misDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						getContentPane().setBackground(Color.cyan);
						repaint();
						lbl= new JLabel("Delete from Staff table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,15,340,50);
						add(lbl);
						try {
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							String sql="select id from staff";
							PreparedStatement stmt=connection.prepareStatement(sql);
							ResultSet rs= stmt.executeQuery(sql);
					
							String ids[] =new String[20];
							int i=0;
							while(rs.next())
							{
								 ids[i]=rs.getString("id");
								 i++;
							}
							int j=0,count=0;
							while(ids[j]!=null)
							{
								count++;j++;
							}
							String id[]=new String[count];
							for(j=0;j<count;j++) id[j]=ids[j];
							
				            //label and jcombobox for selecting an id to be deleted
							lblmisDeleteStaffId=new JLabel("Select StaffId:");
							lblmisDeleteStaffId.setBounds(50,100,170,30);
							lblmisDeleteStaffId.setFont(new Font("Verdana",Font.BOLD,12));
							add(lblmisDeleteStaffId);
							cstaffId=new JComboBox(id);
							cstaffId.setBounds(220,100,170,30);
							cstaffId.setFont(new Font("Verdana",Font.BOLD,12));
							add(cstaffId);
							
							//button for show details a staff
						    btnstaffShow=new JButton("Show Staff Details");
							btnstaffShow.setBounds(120,150,170,30);
							btnstaffShow.setBackground(Color.orange);
							add(btnstaffShow);
							
							//label and textfield for staff Name
							lblstaffName=new JLabel("Enter Staff Name:");
							lblstaffName.setBounds(50,200,110,30);
							add(lblstaffName);
							tfstaffName=new JTextField(10);
							tfstaffName.setBounds(220,200,190,30);
							add(tfstaffName);
							//label and textfield for staff Id
							lblstaffId=new JLabel("Enter Staff ID:");
							lblstaffId.setBounds(50,250,190,30);
							add(lblstaffId);
							tfstaffId=new JTextField(10);
							tfstaffId.setBounds(220,250,190,30);
							add(tfstaffId);
							//label and textfield for staff salary
							lblstaffSal=new JLabel("Enter staff salary:");
							lblstaffSal.setBounds(50,300,190,30);
							add(lblstaffSal);
							tfstaffSal=new JTextField(10);
							tfstaffSal.setBounds(220,300,190,30);
							add(tfstaffSal);
							//label and textfield for staff age
							lblstaffAge=new JLabel("Enter staff age:");
							lblstaffAge.setBounds(50,350,190,30);
							add(lblstaffAge);
							tfstaffAge=new JTextField(10);
							tfstaffAge.setBounds(220,350,190,30);
							add(tfstaffAge);

							//button for deleting a staff
						    btnstaffDelete=new JButton("Delete");
							btnstaffDelete.setBounds(120,400,100,30);
							btnstaffDelete.setBackground(Color.green);
							add(btnstaffDelete);
							
							btnstaffShow.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cstaffId.getSelectedItem();
										String sql2="select NAME,ID,SALARY,AGE from staff where ID="+sid1;
										PreparedStatement stmt2=connection.prepareStatement(sql2);
										stmt2.executeUpdate();
										ResultSet rs2= stmt2.executeQuery(sql2);
										while(rs2.next()) {
											tfstaffName.setText(rs2.getString(1));
										    tfstaffId.setText(String.valueOf(rs2.getInt(2)));
											tfstaffSal.setText(String.valueOf(rs2.getInt(3)));
											tfstaffAge.setText(String.valueOf(rs2.getInt(4)));
										}										
								    }catch(Exception ae) {
										System.out.println("error");
									}
									
								}
							
							});
							btnstaffDelete.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid=(String) cstaffId.getSelectedItem();
										String sqlDelete="delete from staff S where S.id=?";
										PreparedStatement stmt3=connection.prepareStatement(sqlDelete);
										stmt3.setString(1,sid);
										int rows=stmt3.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											tfstaffName.setText("");
										    tfstaffId.setText("");
											tfstaffSal.setText("");
											tfstaffAge.setText("");
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Deleted succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't delete");
										System.out.println("could not delete"+se);
									}
								}
								
							});
						//connection.close();	
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
				});
				//add actionListener to delete button in staff
				misModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.pink);
						lbl= new JLabel("Modify Staff table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,15,340,50);
						add(lbl);
						try {
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							String sql="select id from staff";
							PreparedStatement stmt=connection.prepareStatement(sql);
							ResultSet rs= stmt.executeQuery(sql);
					
							String ids[] =new String[20];
							int i=0;
							while(rs.next())
							{
								 ids[i]=rs.getString("id");
								 i++;
							}
							int j=0,count=0;
							while(ids[j]!=null)
							{
								count++;j++;
							}
							String id[]=new String[count];
							for(j=0;j<count;j++) id[j]=ids[j];
							
				            //label and jcombobox for selecting an id to be deleted
							lblmisDeleteStaffId=new JLabel("Select StaffId:");
							lblmisDeleteStaffId.setBounds(50,100,170,30);
							lblmisDeleteStaffId.setFont(new Font("Verdana",Font.BOLD,12));
							add(lblmisDeleteStaffId);
							cstaffId=new JComboBox(id);
							cstaffId.setBounds(220,100,170,30);
							cstaffId.setFont(new Font("Verdana",Font.BOLD,12));
							add(cstaffId);
							
							//button for show details a staff
						    btnstaffShow=new JButton("Show Staff Details");
							btnstaffShow.setBounds(120,150,170,30);
							btnstaffShow.setBackground(Color.orange);
							add(btnstaffShow);
							//label and textfield for staff Id
							lblstaffId=new JLabel("   You cannot modify Staff ID");
							lblstaffId.setBounds(50,200,190,30);
							add(lblstaffId);
							//label and textfield for staff Name
							lblstaffName=new JLabel("Enter Staff Name:");
							lblstaffName.setBounds(50,250,110,30);
							add(lblstaffName);
							tfstaffName=new JTextField(10);
							tfstaffName.setBounds(220,250,190,30);
							add(tfstaffName);
							//label and textfield for staff salary
							lblstaffSal=new JLabel("Enter staff salary:");
							lblstaffSal.setBounds(50,300,190,30);
							add(lblstaffSal);
							tfstaffSal=new JTextField(10);
							tfstaffSal.setBounds(220,300,190,30);
							add(tfstaffSal);
							//label and textfield for staff age
							lblstaffAge=new JLabel("Enter staff age:");
							lblstaffAge.setBounds(50,350,190,30);
							add(lblstaffAge);
							tfstaffAge=new JTextField(10);
							tfstaffAge.setBounds(220,350,190,30);
							add(tfstaffAge);

							//button for updating a staff
						    btnstaffModify=new JButton("Modify");
							btnstaffModify.setBounds(120,400,100,30);
							btnstaffModify.setBackground(Color.green);
							add(btnstaffModify);
							
							btnstaffShow.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cstaffId.getSelectedItem();
										String sql2="select NAME,ID,SALARY,AGE from staff where ID="+sid1;
										PreparedStatement stmt2=connection.prepareStatement(sql2);
										stmt2.executeUpdate();
										ResultSet rs2= stmt2.executeQuery(sql2);
										while(rs2.next()) {
											tfstaffName.setText(rs2.getString(1));
											tfstaffSal.setText(String.valueOf(rs2.getInt(3)));
											tfstaffAge.setText(String.valueOf(rs2.getInt(4)));
										}										
								    }catch(Exception ae) {
										System.out.println("error");
									}
									
								}
							
							});
							btnstaffModify.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid=(String) cstaffId.getSelectedItem();
										String name=tfstaffName.getText();
										String sal=tfstaffSal.getText();
										String age=tfstaffAge.getText();
										String sqlUpdate="update staff set name=" + "\'" + name + "\', "+ "salary= "+sal+ " ,age="+age+" where id="+sid;
										PreparedStatement stmt3=connection.prepareStatement(sqlUpdate);
										int rows=stmt3.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Modified succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't modify");
										System.out.println("could not update"+se);
									}
								}								
							});
						//connection.close();	
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
				});
				//Add actionListener for display button in menu
				mimDisplay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.green);
						lbl= new JLabel("Display Food Menu table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,20,340,50);
						add(lbl);
						try {
							JTextArea mimDisplayContent=new JTextArea(40,70);
							mimDisplayContent.setBounds(100,100,800,600);
							mimDisplayContent.setFont(new Font("Verdana",Font.BOLD,12));
							add(mimDisplayContent);
							mimDisplayContent.setEditable(false);
							mimDisplayContent.setText("CATEGORY_NAME\tCATEGORY_ID\tFOOD_NAME\tFOOD_QUANTITY\tCOST\n\n");
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							Statement st=connection.createStatement();
							ResultSet rs=st.executeQuery("select * from menu");
							while(rs.next()) {
								mimDisplayContent.append(rs.getString(1)+"\t\t"+rs.getInt(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t\t"+rs.getDouble(5)+"\n");
							}
							connection.close();
						}catch(SQLException se) {
							se.printStackTrace();
						}
					}
				  });
				//Add actionListener to insert button in menu
				mimInsert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						getContentPane().removeAll();
						getContentPane().setBackground(Color.yellow);
						repaint();
						lbl= new JLabel("Insert into Food Menu table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,20,340,50);
						add(lbl);
						try {
							//label and textfield for category name
							lblcategoryName=new JLabel("Enter category Name:");
							lblcategoryName.setBounds(50,80,190,30);
							add(lblcategoryName);
							tfcategoryName=new JTextField(10);
							tfcategoryName.setBounds(220,80,190,30);
							add(tfcategoryName);
							//label and textfield for category ID
							lblcategoryId=new JLabel("Enter category ID:");
							lblcategoryId.setBounds(50,130,110,30);
							add(lblcategoryId);
							tfcategoryId=new JTextField(10);
							tfcategoryId.setBounds(220,130,190,30);
							add(tfcategoryId);
							//label and textfield for food name
							lblfoodName=new JLabel("Enter Food Name:");
							lblfoodName.setBounds(50,180,190,30);
							add(lblfoodName);
							tffoodName=new JTextField(10);
							tffoodName.setBounds(220,180,190,30);
							add(tffoodName);
							//label and textfield for food quantity
							lblfoodQ=new JLabel("Enter Food quantity:");
							lblfoodQ.setBounds(50,230,190,30);
							add(lblfoodQ);
							tffoodQ=new JTextField(10);
							tffoodQ.setBounds(220,230,190,30);
							add(tffoodQ);
							//label and textfield for cost
							lblcost=new JLabel("Enter cost:");
							lblcost.setBounds(50,280,190,30);
							add(lblcost);
							tfcost=new JTextField(10);
							tfcost.setBounds(220,280,190,30);
							add(tfcost);
							//button for inserting a menu item
						    btnmenuSubmit=new JButton("Submit");
							btnmenuSubmit.setBounds(120,330,100,30);
							btnmenuSubmit.setBackground(Color.green);
							add(btnmenuSubmit);
							
							btnmenuSubmit.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e){
									try {
										String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
										String username = "it19737043";
										String password = "vasavi";
										Connection connection = DriverManager.getConnection(dbURL,username,password);
										String cn=tfcategoryName.getText();
										String cid=tfcategoryId.getText();
										String fn=tffoodName.getText();
										String q=tffoodQ.getText();
										String c=tfcost.getText();
										String sql="INSERT INTO MENU VALUES(?,?,?,?,?)";
										PreparedStatement stmt=connection.prepareStatement(sql);
										stmt.setString(1,cn);
										stmt.setString(2,cid);
										stmt.setString(3,fn);
										stmt.setString(4,q);
										stmt.setString(5,c);
										int rows=stmt.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Inserted succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry,couldn't insert, "+se);
										System.out.println("could not insert"+se);
									}
								}
							});
					                
						}catch(Exception se) {
							se.printStackTrace();
						}
					}
				  });	
				//add actionListener to delete button in food menu
				mimDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.cyan);
						lbl= new JLabel("Delete from Food Menu table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,15,340,50);
						add(lbl);
						try {
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							String sql="select category_id from menu";
							PreparedStatement stmt=connection.prepareStatement(sql);
							ResultSet rs= stmt.executeQuery(sql);
					
							String ids[] =new String[20];
							int i=0;
							while(rs.next())
							{
								 ids[i]=rs.getString("category_id");
								 i++;
							}
							int j=0,count=0;
							while(ids[j]!=null)
							{
								count++;j++;
							}
							String id[]=new String[count];
							for(j=0;j<count;j++) id[j]=ids[j];
							
				            //label and jcombobox for selecting an id to be deleted
							lblmimDeleteCategoryId=new JLabel("Select Category Id:");
							lblmimDeleteCategoryId.setBounds(50,100,170,30);
							lblmimDeleteCategoryId.setFont(new Font("Verdana",Font.BOLD,12));
							add(lblmimDeleteCategoryId);
							cCategoryId=new JComboBox(id);
							cCategoryId.setBounds(220,100,170,30);
							cCategoryId.setFont(new Font("Verdana",Font.BOLD,12));
							add(cCategoryId);
							
							//button for show details a menu
						    btnMenuShow=new JButton("Show FoodMenu Details");
							btnMenuShow.setBounds(120,150,200,30);
							btnMenuShow.setBackground(Color.orange);
							add(btnMenuShow);
							//label and textfield for category name
							lblcategoryName=new JLabel("Enter category Name:");
							lblcategoryName.setBounds(50,200,190,30);
							add(lblcategoryName);
							tfcategoryName=new JTextField(10);
							tfcategoryName.setBounds(220,200,190,30);
							add(tfcategoryName);
							//label and textfield for category ID
							lblcategoryId=new JLabel("Enter category ID:");
							lblcategoryId.setBounds(50,250,110,30);
							add(lblcategoryId);
							tfcategoryId=new JTextField(10);
							tfcategoryId.setBounds(220,250,190,30);
							add(tfcategoryId);
							//label and textfield for food name
							lblfoodName=new JLabel("Enter Food Name:");
							lblfoodName.setBounds(50,300,190,30);
							add(lblfoodName);
							tffoodName=new JTextField(10);
							tffoodName.setBounds(220,300,190,30);
							add(tffoodName);
							//label and textfield for food quantity
							lblfoodQ=new JLabel("Enter Food quantity:");
							lblfoodQ.setBounds(50,350,190,30);
							add(lblfoodQ);
							tffoodQ=new JTextField(10);
							tffoodQ.setBounds(220,350,190,30);
							add(tffoodQ);
							//label and textfield for cost
							lblcost=new JLabel("Enter cost:");
							lblcost.setBounds(50,400,190,30);
							add(lblcost);
							tfcost=new JTextField(10);
							tfcost.setBounds(220,400,190,30);
							add(tfcost);
							//button for inserting a menu item
						    btnmenuDelete=new JButton("Delete");
							btnmenuDelete.setBounds(120,450,100,30);
							btnmenuDelete.setBackground(Color.green);
							add(btnmenuDelete);
																				
							btnMenuShow.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cCategoryId.getSelectedItem();
										String sql2="select category_name,category_id,food_name,food_quantity,cost from menu where CATEGORY_ID="+sid1;
										PreparedStatement stmt2=connection.prepareStatement(sql2);
										stmt2.executeUpdate();
										ResultSet rs2= stmt2.executeQuery(sql2);
										while(rs2.next()) {
											tfcategoryName.setText(rs2.getString(1));
										    tfcategoryId.setText(rs2.getString(2));
											tffoodName.setText(rs2.getString(3));
											tffoodQ.setText(rs2.getString(4));
											tfcost.setText(rs2.getString(5));
										}										
								    }catch(Exception ae) {
										System.out.println("error");
									}									
								}							
							});
							btnmenuDelete.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid=(String) cCategoryId.getSelectedItem();
										String sqlDelete="delete from menu where category_id=?";
										PreparedStatement stmt3=connection.prepareStatement(sqlDelete);
										stmt3.setString(1,sid);
										int rows=stmt3.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											tfcategoryName.setText("");
										    tfcategoryId.setText("");
											tffoodName.setText("");
											tffoodQ.setText("");
											tfcost.setText("");
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Deleted succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't delete");
										System.out.println("could not delete"+se);
									}
								}
								
							});
						//connection.close();	
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
				});
				//add actionListener to modify button in food menu
				mimModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.pink);
						lbl= new JLabel("Modify Food Menu table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,15,340,50);
						add(lbl);
						try {
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							String sql="select category_id from menu";
							PreparedStatement stmt=connection.prepareStatement(sql);
							ResultSet rs= stmt.executeQuery(sql);
					
							String ids[] =new String[20];
							int i=0;
							while(rs.next())
							{
								 ids[i]=rs.getString("category_id");
								 i++;
							}
							int j=0,count=0;
							while(ids[j]!=null)
							{
								count++;j++;
							}
							String id[]=new String[count];
							for(j=0;j<count;j++) id[j]=ids[j];
							
				            //label and jcombobox for selecting an id to be deleted
							lblmimDeleteCategoryId=new JLabel("Select Category Id:");
							lblmimDeleteCategoryId.setBounds(50,100,170,30);
							lblmimDeleteCategoryId.setFont(new Font("Verdana",Font.BOLD,12));
							add(lblmimDeleteCategoryId);
							cCategoryId=new JComboBox(id);
							cCategoryId.setBounds(220,100,170,30);
							cCategoryId.setFont(new Font("Verdana",Font.BOLD,12));
							add(cCategoryId);
							
							//button for show details a menu
						    btnMenuShow=new JButton("Show FoodMenu Details");
							btnMenuShow.setBounds(120,150,200,30);
							btnMenuShow.setBackground(Color.orange);
							add(btnMenuShow);
							//label and textfield for category name
							lblcategoryName=new JLabel("Enter category Name:");
							lblcategoryName.setBounds(50,200,190,30);
							add(lblcategoryName);
							tfcategoryName=new JTextField(10);
							tfcategoryName.setBounds(220,200,190,30);
							add(tfcategoryName);
							//label and textfield for category ID
							lblcategoryId=new JLabel("You cannot modify category ID");
							lblcategoryId.setBounds(50,250,190,30);
							add(lblcategoryId);
							
							//label and textfield for food name
							lblfoodName=new JLabel("Enter Food Name:");
							lblfoodName.setBounds(50,300,190,30);
							add(lblfoodName);
							tffoodName=new JTextField(10);
							tffoodName.setBounds(220,300,190,30);
							add(tffoodName);
							//label and textfield for food quantity
							lblfoodQ=new JLabel("Enter Food quantity:");
							lblfoodQ.setBounds(50,350,190,30);
							add(lblfoodQ);
							tffoodQ=new JTextField(10);
							tffoodQ.setBounds(220,350,190,30);
							add(tffoodQ);
							//label and textfield for cost
							lblcost=new JLabel("Enter cost:");
							lblcost.setBounds(50,400,190,30);
							add(lblcost);
							tfcost=new JTextField(10);
							tfcost.setBounds(220,400,190,30);
							add(tfcost);
							//button for modifying a menu item
						    btnmenuModify=new JButton("Modify");
							btnmenuModify.setBounds(120,450,100,30);
							btnmenuModify.setBackground(Color.green);
							add(btnmenuModify);
																				
							btnMenuShow.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cCategoryId.getSelectedItem();
										String sql2="select category_name,food_name,food_quantity,cost from menu where CATEGORY_ID="+sid1;
										PreparedStatement stmt2=connection.prepareStatement(sql2);
										stmt2.executeUpdate();
										ResultSet rs2= stmt2.executeQuery(sql2);
										while(rs2.next()) {
											tfcategoryName.setText(rs2.getString(1));
											tffoodName.setText(rs2.getString(2));
											tffoodQ.setText(rs2.getString(3));
											tfcost.setText(rs2.getString(4));
										}										
								    }catch(Exception ae) {
										System.out.println("error");
									}									
								}							
							});
							btnmenuModify.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cCategoryId.getSelectedItem();
										String cn=tfcategoryName.getText();
										String fn=tffoodName.getText();
										String q=tffoodQ.getText();
										String c=tfcost.getText();
										String sqlUpdate="update menu set category_name=" + "\'" + cn + "\', "+
												"food_name=" + "\'" + fn + "\', "+
												"food_quantity="  + q + ", "+
												"cost=" + c+
												" where category_id="+sid1;
										PreparedStatement stmt3=connection.prepareStatement(sqlUpdate);
										int rows=stmt3.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Modified succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't modify");
										System.out.println("could not modify"+se);
									}
								}
								
							});
						//connection.close();	
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
				});
				//to display all rows in bill table
				mibDisplay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.GREEN);
						lbl= new JLabel("Display Bill table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,20,340,50);
						add(lbl);
						try {
							JTextArea mibDisplayContent=new JTextArea(40,70);
							mibDisplayContent.setBounds(100,100,800,400);
							mibDisplayContent.setFont(new Font("Verdana",Font.BOLD,12));
							add(mibDisplayContent);
							mibDisplayContent.setEditable(false);
							mibDisplayContent.setText("ITEMNAME \tPRICE \tQUANTITY  \tBILL_ID  \tCUSTOMER_ID\n\n");
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							Statement st=connection.createStatement();
							ResultSet rs=st.executeQuery("select * from bill");
							while(rs.next()) {
								mibDisplayContent.append(rs.getString(1).trim()+"\t"+rs.getDouble(2)+"\t"+rs.getInt(3)+"\t"+rs.getInt(4)+"\t"+rs.getInt(5)+"\n");
							}
							connection.close();
						}catch(SQLException se) {
							se.printStackTrace();
						}
					}
				  });
				//Add actionListener to insert button in bill
				mibInsert.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.yellow);
						lbl= new JLabel("Insert into Bill table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,20,340,50);
						add(lbl);
						try {
							//label and textfield for item name
							lblitemName=new JLabel("Enter item Name:");
							lblitemName.setBounds(50,80,190,30);
							add(lblitemName);
							tfitemName=new JTextField(10);
							tfitemName.setBounds(220,80,190,30);
							add(tfitemName);
							//label and textfield for price
							lblprice=new JLabel("Enter price:");
							lblprice.setBounds(50,130,110,30);
							add(lblprice);
							tfprice=new JTextField(10);
							tfprice.setBounds(220,130,190,30);
							add(tfprice);
							//label and textfield for food name
							lblquantity=new JLabel("Enter Quantity:");
							lblquantity.setBounds(50,180,190,30);
							add(lblquantity);
							tfquantity=new JTextField(10);
							tfquantity.setBounds(220,180,190,30);
							add(tfquantity);
							//label and textfield for billid
							lblbid=new JLabel("Enter Bill Id:");
							lblbid.setBounds(50,230,190,30);
							add(lblbid);
							tfbid=new JTextField(10);
							tfbid.setBounds(220,230,190,30);
							add(tfbid);
							//label and textfield for customer id
							lblcustomerId=new JLabel("Enter customer ID:");
							lblcustomerId.setBounds(50,280,190,30);
							add(lblcustomerId);
							tfcustomerId=new JTextField(10);
							tfcustomerId.setBounds(220,280,190,30);
							add(tfcustomerId);
							//button for inserting a menu item
						    btnbillSubmit=new JButton("Submit");
							btnbillSubmit.setBounds(120,330,100,30);
							btnbillSubmit.setBackground(Color.green);
							add(btnbillSubmit);
							
							btnbillSubmit.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e){
									try {
										String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
										String username = "it19737043";
										String password = "vasavi";
										Connection connection = DriverManager.getConnection(dbURL,username,password);
										String in=tfitemName.getText();
										String p=tfprice.getText();
										String q=tfquantity.getText();
										String bid=tfbid.getText();
										String cid=tfcustomerId.getText();
										String sql="INSERT INTO BILL VALUES(?,?,?,?,?)";
										PreparedStatement stmt=connection.prepareStatement(sql);
										stmt.setString(1,in);
										stmt.setString(2,p);
										stmt.setString(3,q);
										stmt.setString(4,bid);
										stmt.setString(5,cid);
										int rows=stmt.executeUpdate();
										stmt.executeUpdate("commit");
										if(rows>0) {
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Inserted succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry,couldn't insert, "+se);
										System.out.println("could not insert"+se);
									}
								}
							});
					                
						}catch(Exception se) {
							se.printStackTrace();
						}
					}
				  });	
				//Add actionListener to delete button in bill
				mibDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.cyan);
						lbl= new JLabel("Delete from Bill table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,15,340,50);
						add(lbl);
						try {
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							String sql="select bill_id from bill";
							PreparedStatement stmt=connection.prepareStatement(sql);
							ResultSet rs= stmt.executeQuery(sql);
					
							String ids[] =new String[20];
							int i=0;
							while(rs.next())
							{
								 ids[i]=rs.getString("bill_id");
								 i++;
							}
							int j=0,count=0;
							while(ids[j]!=null)
							{
								count++;j++;
							}
							String id[]=new String[count];
							for(j=0;j<count;j++) id[j]=ids[j];
							
				            //label and jcombobox for selecting an id to be deleted
							lblmibDeletebillId=new JLabel("Select Bill Id:");
							lblmibDeletebillId.setBounds(50,100,170,30);
							lblmibDeletebillId.setFont(new Font("Verdana",Font.BOLD,12));
							add(lblmibDeletebillId);
							cBillId=new JComboBox(id);
							cBillId.setBounds(220,100,170,30);
							cBillId.setFont(new Font("Verdana",Font.BOLD,12));
							add(cBillId);
							//button for show details a menu
						    btnBillShow=new JButton("Show Bill Details");
							btnBillShow.setBounds(120,150,170,30);
							btnBillShow.setBackground(Color.orange);
							add(btnBillShow);
							//label and textfield for item name
							lblitemName=new JLabel("Enter item Name:");
							lblitemName.setBounds(50,200,190,30);
							add(lblitemName);
							tfitemName=new JTextField(10);
							tfitemName.setBounds(220,200,190,30);
							add(tfitemName);
							//label and textfield for price
							lblprice=new JLabel("Enter price:");
							lblprice.setBounds(50,250,110,30);
							add(lblprice);
							tfprice=new JTextField(10);
							tfprice.setBounds(220,250,190,30);
							add(tfprice);
							//label and textfield for food name
							lblquantity=new JLabel("Enter Quantity:");
							lblquantity.setBounds(50,300,190,30);
							add(lblquantity);
							tfquantity=new JTextField(10);
							tfquantity.setBounds(220,300,190,30);
							add(tfquantity);
							//label and textfield for billid
							lblbid=new JLabel("Enter Bill Id:");
							lblbid.setBounds(50,350,190,30);
							add(lblbid);
							tfbid=new JTextField(10);
							tfbid.setBounds(220,350,190,30);
							add(tfbid);
							//label and textfield for customer id
							lblcustomerId=new JLabel("Enter customer ID:");
							lblcustomerId.setBounds(50,400,190,30);
							add(lblcustomerId);
							tfcustomerId=new JTextField(10);
							tfcustomerId.setBounds(220,400,190,30);
							add(tfcustomerId);
							//button for deleting a bill item
						    btnBillDelete=new JButton("Delete");
							btnBillDelete.setBounds(120,450,100,30);
							btnBillDelete.setBackground(Color.green);
							add(btnBillDelete);
																				
							btnBillShow.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cBillId.getSelectedItem();
										String sql2="select itemname,price,quantity,bill_id,customer_id from bill where BILL_ID="+sid1;
										PreparedStatement stmt2=connection.prepareStatement(sql2);
										stmt2.executeUpdate();
										ResultSet rs2= stmt2.executeQuery(sql2);
										while(rs2.next()) {
											tfitemName.setText(rs2.getString(1));
											tfprice.setText(rs2.getString(2));
											tfquantity.setText(rs2.getString(3));
											tfbid.setText(rs2.getString(4));
											tfcustomerId.setText(rs2.getString(5));
										}										
								    }catch(Exception ae) {
										System.out.println("error");
									}									
								}							
							});
							btnBillDelete.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid=(String) cBillId.getSelectedItem();
										String sqlDelete="delete from bill where bill_id=?";
										PreparedStatement stmt3=connection.prepareStatement(sqlDelete);
										stmt3.setString(1,sid);
										int rows=stmt3.executeUpdate();
										stmt3.executeUpdate("commit");
										if(rows>0) {
											tfitemName.setText("");
										    tfprice.setText("");
											tfquantity.setText("");
											tfbid.setText("");
											tfcustomerId.setText("");
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Deleted succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't delete");
										System.out.println("could not delete"+se);
									}
								}
								
							});
						//connection.close();	
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
				});
				//Add actionListener to modify button in bill
				mibModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getContentPane().removeAll();
						repaint();
						getContentPane().setBackground(Color.pink);
						lbl= new JLabel("Modify Bill table ");
						lbl.setFont(new Font("Verdana",Font.BOLD,20));
						lbl.setBounds(30,15,340,50);
						add(lbl);
						try {
							String dbURL = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
							String username = "it19737043";
							String password = "vasavi";
							Connection connection = DriverManager.getConnection(dbURL,username,password);
							String sql="select bill_id from bill";
							PreparedStatement stmt=connection.prepareStatement(sql);
							ResultSet rs= stmt.executeQuery(sql);
					
							String ids[] =new String[20];
							int i=0;
							while(rs.next())
							{
								 ids[i]=rs.getString("bill_id");
								 i++;
							}
							int j=0,count=0;
							while(ids[j]!=null)
							{
								count++;j++;
							}
							String id[]=new String[count];
							for(j=0;j<count;j++) id[j]=ids[j];
							
				            //label and jcombobox for selecting an id to be deleted
							lblmibDeletebillId=new JLabel("Select Bill Id:");
							lblmibDeletebillId.setBounds(50,100,170,30);
							lblmibDeletebillId.setFont(new Font("Verdana",Font.BOLD,12));
							add(lblmibDeletebillId);
							cBillId=new JComboBox(id);
							cBillId.setBounds(220,100,170,30);
							cBillId.setFont(new Font("Verdana",Font.BOLD,12));
							add(cBillId);
							//button for show details a menu
						    btnBillShow=new JButton("Show Bill Details");
							btnBillShow.setBounds(120,150,170,30);
							btnBillShow.setBackground(Color.orange);
							add(btnBillShow);
							//label and textfield for item name
							lblitemName=new JLabel("Enter item Name:");
							lblitemName.setBounds(50,200,190,30);
							add(lblitemName);
							tfitemName=new JTextField(10);
							tfitemName.setBounds(220,200,190,30);
							add(tfitemName);
							//label and textfield for price
							lblprice=new JLabel("Enter price:");
							lblprice.setBounds(50,250,110,30);
							add(lblprice);
							tfprice=new JTextField(10);
							tfprice.setBounds(220,250,190,30);
							add(tfprice);
							//label and textfield for food name
							lblquantity=new JLabel("Enter Quantity:");
							lblquantity.setBounds(50,300,190,30);
							add(lblquantity);
							tfquantity=new JTextField(10);
							tfquantity.setBounds(220,300,190,30);
							add(tfquantity);
							//label and textfield for billid
							lblbid=new JLabel("You cannot modify Bill Id");
							lblbid.setBounds(50,350,200,30);
							add(lblbid);
							
							//label and textfield for customer id
							lblcustomerId=new JLabel("Enter customer ID:");
							lblcustomerId.setBounds(50,400,190,30);
							add(lblcustomerId);
							tfcustomerId=new JTextField(10);
							tfcustomerId.setBounds(220,400,190,30);
							add(tfcustomerId);
							//button for updating a bill item
						    btnBillModify=new JButton("Modify");
							btnBillModify.setBounds(120,450,100,30);
							btnBillModify.setBackground(Color.green);
							add(btnBillModify);
																				
							btnBillShow.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid1=(String) cBillId.getSelectedItem();
										String sql2="select itemname,price,quantity,customer_id from bill where BILL_ID="+sid1;
										PreparedStatement stmt2=connection.prepareStatement(sql2);
										stmt2.executeUpdate();
										ResultSet rs2= stmt2.executeQuery(sql2);
										while(rs2.next()) {
											tfitemName.setText(rs2.getString(1));
											tfprice.setText(rs2.getString(2));
											tfquantity.setText(rs2.getString(3));
											tfcustomerId.setText(rs2.getString(4));
										}										
								    }catch(Exception ae) {
										System.out.println("error");
									}									
								}							
							});
							btnBillModify.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										String sid=(String) cBillId.getSelectedItem();
										String in=tfitemName.getText();
										String p=tfprice.getText();
										String q=tfquantity.getText();
										//String bid=tfbid.getText();
										String cid=tfcustomerId.getText();
										String sqlModify="update bill set itemname=" + "\'" + in + "\', "+
												"price=" + p + ", "+
												"quantity="  + q + ", "+
												"customer_id=" + cid+
												" where bill_id="+sid;
										PreparedStatement stmt3=connection.prepareStatement(sqlModify);
										int rows=stmt3.executeUpdate();
										stmt3.executeUpdate("commit");
										if(rows>0) {
											JOptionPane.showMessageDialog(cafeteriaGUI.this,"Modified succesfully.");
									    }
										connection.close();
									}catch(SQLException se) {
										JOptionPane.showMessageDialog(cafeteriaGUI.this,"Sorry, something went wrong couldn't modify");
										System.out.println("could not modify"+se);
									}
								}
								
							});
						//connection.close();	
						}catch(SQLException se) {
							se.printStackTrace();
						}
						
					}
				});
		setVisible(true);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}

	public static void main(String[] args) {
		
		cafeteriaGUI h=new cafeteriaGUI();
		h.setTitle("Cafeteria Management System");
	}

}
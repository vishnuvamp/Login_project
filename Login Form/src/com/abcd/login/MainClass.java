package com.abcd.login;

import java.sql.*;
import java.util.*;

public class MainClass {

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		boolean b = true;
		while (b) {
			System.out.println("Welcome To Application");
			System.out.println("1.Signup\n2.Login\n3.Exit\n");
			int input = s.nextInt();

			switch (input) {
			case 1: {
				MainClass.insertRecords();
				break;
			}
			case 2: {
				MainClass.accountLogin();
				break;
			}
			case 3: {
				System.out.println("Thank You");
				b = false;
				break;
			}
			default: {
				System.out.println("Invalid Operation");
				break;
			}
			}
		}

	}

	public static void insertRecords() throws Exception {

		String url = "jdbc:mysql://localhost:3306/login_form";
		String uname = "root";
		String pass = "root";

		System.out.println("Enter The Id");
		int id = s.nextInt();
		System.out.println("Enter The Username");
		String un = s.next();
		System.out.println("Enter The Password");
		String pw = s.next();

		String query = "insert into login values(?,?,?)";

		Class.forName("com.mysql.cj.jdbc.Driver");// load the driver
		Connection con = DriverManager.getConnection(url, uname, pass);// get the connection

		PreparedStatement pst = con.prepareStatement(query);// create statement
		pst.setInt(1, id);
		pst.setString(2, un);
		pst.setString(3, pw);

		pst.executeUpdate();// execute query
		System.out.println("SignUp Successfull");

		con.close();// close the connection
	}

	public static void accountLogin() throws Exception {
		String url = "jdbc:mysql://localhost:3306/login_form";
		String uname = "root";
		String pass = "root";

		System.out.println("Enter The Username");
		String un = s.next();
		System.out.println("Enter The Password");
		String pw = s.next();

		String query = "select * from login";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);

		while (rs.next()) {
			if (un.contains(rs.getString(2)) && pw.contains(rs.getString(3))) {
				System.out.println("Welcome To The Application : " + rs.getString(2));
				break;
			} else if (rs.isLast()) {
				int unl = un.length();
				int pwl = pw.length();
				for (int index = 0; index < unl; index++) {
					if (un.charAt(index) != rs.getString(2).charAt(index)) {
						System.out.println("Entered User Name is incorrect or Not Exist");
						break;
					}
				}
				for (int index = 0; index < pwl; index++) {
					if (pw.charAt(index) != rs.getString(3).charAt(index)) {
						System.out.println("Entered Password Is Incorrect");
						break;
					}
				}
			}

		}
		con.close();

	}

}

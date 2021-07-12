package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import Main.MainFrame;

public class DBConnection {
	
	//MySQL DBMS
	private static final String URL = "jdbc:mysql://localhost/Company";	//�����ͺ��̽� �̸� : Company(3306)
	private static final String USERNAME = "root";              	    //����ڸ� root
	private static final String PASSWORD = "2001";                      //��й�ȣ 2001

	//�����ͺ��̽� ������ �����ϴ� Connection ���� ����
	static Connection connection = null;

	//������
	public DBConnection(){
		this.findDriver();				//Driver ã�� �Լ� ȣ��
		this.createConnectionObject();	//Connection ��ü ���� �Լ� ȣ��
	} //������ ��

	//Driver ã��
	private void findDriver(){
		//����̹� Ŭ������ �˻�
		try{ Class.forName("org.gjt.mm.mysql.Driver"); }
		catch(ClassNotFoundException e){
			//����̹� ã�� ���� �޽��� �ڽ� ����
			this.showMessageBox("Driver Not Found", "Driver Not Found");
		}
	} //Driver ã�� �Լ� ��
	
	//Connection ��ü ����
	private void createConnectionObject(){
		//DB������ �����ϴ� ��ü ����
		try{ 
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); } 
		catch(SQLException sqlException){
			//DB���� ���� �޽��� �ڽ� ����
			this.showMessageBox("Database Connect Error", "Database Connect Error");
		}
	} //Connection ��ü ���� �Լ� ��

	//�޽��� �ڽ� ����
	private void showMessageBox(String title, String message){
		MessageBox searchDriverErrorMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_ERROR);
		searchDriverErrorMessageBox.setText(title);
		searchDriverErrorMessageBox.setMessage(message);
		searchDriverErrorMessageBox.open();	//���� �޽��� �ڽ� ǥ��
	} //�޽��� �ڽ� ���� �Լ� ��
}
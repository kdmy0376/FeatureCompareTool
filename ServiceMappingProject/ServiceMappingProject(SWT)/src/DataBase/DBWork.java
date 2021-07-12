package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import Main.MainFrame;

public class DBWork {
	//���ο� ���� ���
	public static void addService(String companyName, String parentServiceName, String childServiceName,		//����� ���� ��ġ �Ӽ�
			String name, String definition, String domainTask, String sType, String synonymousNames,		  	//Identifying �Ӽ�
			double priority, String condition, String interfaceName, String businessProcess, String component,
			String mandatoryFunction, String secondaryFunction, 											  	//Functional �Ӽ�
			String servicePropertyString,																	  	//Selective �Ӽ�
			String andProperty, String orProperty)															  	//Relational �Ӽ�
	{
		//���̺� ����
		new DBTable(companyName);

		//���� ���� ������
		String insertNewServiceQuery = "INSERT INTO " + companyName + 
				" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,?, ?, ?, ?)";
		try{
			//�θ� ���񽺴� ����, �ڽ� ���� ����
			if(!(parentServiceName.equals("No_Parent")) && childServiceName.equals("No_Child")){			
				processParentService(name, companyName, parentServiceName);
			}
			//�ڽ� ���񽺴� ����, �θ� ���� ����
			else if(parentServiceName.equals("No_Parent") && !(childServiceName.equals("No_Child"))){		
				processChildService(name, companyName, childServiceName);
			}
			//�θ�, �ڽ� ���� �Ѵ� ���Խ�
			else if(!(parentServiceName.equals("No_Parent")) && !(childServiceName.equals("No_Child"))){	
				processParentService(name, companyName, parentServiceName);
				processChildService(name, companyName, childServiceName);
			}

			//Identifying Attributes
			PreparedStatement insertNewService = DBConnection.connection.prepareStatement(insertNewServiceQuery);
			insertNewService.setString(1, name);		//1,2,3, ~ 17�� �ʵ�
			insertNewService.setString(2, definition);
			insertNewService.setString(3, domainTask);
			insertNewService.setString(4, sType);
			insertNewService.setString(5, synonymousNames);
			insertNewService.setDouble(6, priority);
			insertNewService.setString(7, condition);
			insertNewService.setString(8, interfaceName);
			insertNewService.setString(9, businessProcess);
			insertNewService.setString(10, component);

			//Functional Attributes
			insertNewService.setString(11, mandatoryFunction);
			insertNewService.setString(12, secondaryFunction);

			//Selective Attributes
			insertNewService.setString(13, servicePropertyString);

			//Relational Attributes
			insertNewService.setString(14, andProperty);
			insertNewService.setString(15, orProperty);

			//����� ���� ��ġ �Ӽ�
			insertNewService.setString(16, parentServiceName);
			insertNewService.setString(17, childServiceName);
			insertNewService.executeUpdate();	

			//��� ���� �޽��� �����ֱ�
			MessageBox createTableMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			createTableMessageBox.setText("Success");
			createTableMessageBox.setMessage("Registration Success");
			createTableMessageBox.open();	//���� �޽��� �ڽ� ǥ��
		}
		catch(SQLException e){
			e.printStackTrace();
			//��� ���� �޽��� �����ֱ�
			MessageBox errorMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_ERROR);
			errorMessageBox.setText("Failed");
			errorMessageBox.setMessage("Registration Failed");
			errorMessageBox.open();	//���̺� ���� ���� �޽��� �ڽ� ǥ��
		}				
	} //���ο� ���� ��� �Լ� ��

	//��Ͻ� �θ� ���� ���� ó��
	private static void processParentService(String name, String companyName, String parentServiceName) throws SQLException{
		String searchServiceQuery = "SELECT ChildService from "+companyName+" where Name = '"+parentServiceName+"'";
		Statement searchStatement = DBConnection.connection.createStatement();
		ResultSet searchResultSet = searchStatement.executeQuery(searchServiceQuery);

		String childService = null;
		while(searchResultSet.next()){ childService = searchResultSet.getString(1); }

		if(!(childService.equals("No_Child"))){	childService += ","+name; } //�ڽ� ���񽺰� �ִٸ�
		else{ childService = name; } //�ڽ� ���񽺰� ���� �����̸�

		String updateChildServiceQuery = "UPDATE "+companyName+" SET ChildService = ? where Name = ?";
		PreparedStatement updateChildStatement = DBConnection.connection.prepareStatement(updateChildServiceQuery);
		updateChildStatement.setString(1, childService);
		updateChildStatement.setString(2, parentServiceName);
		updateChildStatement.executeUpdate();
	} //��Ͻ� �θ� ���� ���� ó�� �Լ� ��

	//��Ͻ� �ڽ� ���� ���� ó��
	private static void processChildService(String name, String companyName, String childServiceName) throws SQLException{
		String searchServiceQuery = "SELECT ParentService from "+companyName+" where Name = '"+childServiceName+"'";
		Statement searchStatement = DBConnection.connection.createStatement();
		ResultSet searchResultSet = searchStatement.executeQuery(searchServiceQuery);

		String parentService = null;
		while(searchResultSet.next()){ parentService = searchResultSet.getString(1); }

		if(!(parentService.equals("No_Parent"))){ parentService += ","+name; } //�θ� ���񽺰� �ִٸ�
		else{ parentService = name;	} //�θ� ���񽺰� ���� �����̸�

		String updateParentQuery = "UPDATE "+companyName+" SET ParentService = ? where Name = ?";
		PreparedStatement updateParentStatement = DBConnection.connection.prepareStatement(updateParentQuery);
		updateParentStatement.setString(1, parentService);
		updateParentStatement.setString(2, childServiceName);
		updateParentStatement.executeUpdate();
	} //��Ͻ� �ڽ� ���� ���� ó�� �Լ� ��

	//���� ��� ����
	public static void deleteAllService(){
		String[] companyList = setCompanyNameComboList();
		for(int i=0; i<companyList.length; i++){
			try{
				PreparedStatement deleteCompanyStatement = DBConnection.connection.prepareStatement("drop table "+companyList[i]);	//ȸ��� ����
				deleteCompanyStatement.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		MessageBox deleteMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
		deleteMessageBox.setText("Delete All");
		deleteMessageBox.setMessage("Delete All Success");
		deleteMessageBox.open();
	} //���� ��� ���� �Լ� ��
	
	//���õ� ���� ����
	public static void deleteService(String companyName, String serviceName){

		String deleteQuery = "delete from " + companyName + " where Name = ?";
		try{
			PreparedStatement deleteServiceStatement = DBConnection.connection.prepareStatement(deleteQuery);
			deleteServiceStatement.setString(1, serviceName);
			deleteServiceStatement.executeUpdate();

			Statement numberOfServiceStatement = DBConnection.connection.createStatement();
			ResultSet numberOfServiceResultSet = numberOfServiceStatement.executeQuery("select count(*) from "+companyName);
			int numberOfService = 0;
			while(numberOfServiceResultSet.next()){
				numberOfService = numberOfServiceResultSet.getInt(1);
			}
			if(numberOfService == 0){	//���񽺰� ���ٸ�
				PreparedStatement deleteCompanyStatement = DBConnection.connection.prepareStatement("drop table "+companyName);	//ȸ��� ����
				deleteCompanyStatement.executeUpdate();
			}

			MessageBox deleteMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			deleteMessageBox.setText("Delete");
			deleteMessageBox.setMessage("Delete Success");
			deleteMessageBox.open();	
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
			MessageBox errorMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_ERROR);
			errorMessageBox.setText("Failed");
			errorMessageBox.setMessage("Delete Failed");
			errorMessageBox.open();	
		}
	} //���õ� ���� ���� �Լ� ��

	//���� �Ӽ��� ����
	public static void updateService(String companyName, String parentServiceName, String childServiceName,	//����� ���� ��ġ �Ӽ�
			String name, String definition, String domainTask, String sType, String synonymousNames,	//Identifying �Ӽ�
			double priority, String condition, String interfaceName, String businessProcess, 
			String component,   
			String mandatoryFunction, String secondaryFunction, 									  	//Functional �Ӽ�
			String servicePropertyString,														  		//Selective �Ӽ�
			String andProperty, String orProperty){												  		//Relational �Ӽ�

		//Update ������
		String updateQuery = "update " + companyName + " set Name = ?, Definition = ?, DomainTask = ?, SType = ?, SynonymousNames = ?," +
				" Priority = ?, ConditionProperty = ?, Interface = ?, businessProcess = ?, Component = ?," +
				" MandatoryFunction = ?, SecondaryFunction = ?, " +
				" Property = ?, andProperty = ?, orProperty = ?," +	
				" ParentService = ?, ChildService = ?" +
				" where Name = ?";
		try{
			PreparedStatement updateServiceStatement = DBConnection.connection.prepareStatement(updateQuery);
			//Identifying Attributes
			updateServiceStatement.setString(1, name);		
			updateServiceStatement.setString(2, definition);
			updateServiceStatement.setString(3, domainTask);
			updateServiceStatement.setString(4, sType);
			updateServiceStatement.setString(5, synonymousNames);
			updateServiceStatement.setDouble(6, priority);
			updateServiceStatement.setString(7, condition);
			updateServiceStatement.setString(8, interfaceName);
			updateServiceStatement.setString(9, businessProcess);
			updateServiceStatement.setString(10, component);

			//Functional Attributes
			updateServiceStatement.setString(11, mandatoryFunction);
			updateServiceStatement.setString(12, secondaryFunction);

			//Selective Attributes
			updateServiceStatement.setString(13, servicePropertyString);

			//Relational Attributes
			updateServiceStatement.setString(14, andProperty);
			updateServiceStatement.setString(15, orProperty);

			//����� ���� ��ġ �Ӽ�
			updateServiceStatement.setString(16, parentServiceName);
			updateServiceStatement.setString(17, childServiceName);

			//Where��
			updateServiceStatement.setString(18, name);
			updateServiceStatement.executeUpdate(); 

			MessageBox updateMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			updateMessageBox.setText("Update");
			updateMessageBox.setMessage("Update Success");
			updateMessageBox.open();	
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
			MessageBox errorMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_ERROR);
			errorMessageBox.setText("Failed");
			errorMessageBox.setMessage("Update Failed");
			errorMessageBox.open();	
		}
	} //���� �Ӽ��� ���� �Լ� ��

	//���̺� �� Ʃ��(��)�� ���� �˻�
	public static int getTableRowNumber(String companyName){
		Statement countRowNumberStatement = null;
		ResultSet countRowNumberResultSet = null;
		
		//���̺��� Ʃ��(��) ���� �˻�
		String countRowNumberQuery = "select count(*) from " + companyName;
		int tableRowNumber = -1;

		try{
			countRowNumberStatement = DBConnection.connection.createStatement();
			countRowNumberResultSet = countRowNumberStatement.executeQuery(countRowNumberQuery);

			while(countRowNumberResultSet.next()){
				tableRowNumber = countRowNumberResultSet.getInt(1);
			}
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return tableRowNumber;
	} //���̺� �� Ʃ��(��)�� ���� �˻� �Լ� ��

	//���̺� ������ �Ӽ� �˻�(Name, DomainTask, Stype)
	public static String[] getTableProperty(String companyName, String serviceName){
		Statement findTablePropertyStatement = null;
		ResultSet findTablePropertyResultSet = null;
		
		//Service Name, DomainTask, SType �˻�
		String findTablePropertyQuery = "select Name, DomainTask, SType from " + companyName + " where Name= '" + serviceName + "'";
		String[] tablePropertyList = null;
		try{
			//�ش� ������ Name, DomainTask, SType �ʵ�
			findTablePropertyStatement = DBConnection.connection.createStatement();
			findTablePropertyResultSet = findTablePropertyStatement.executeQuery(findTablePropertyQuery);

			//�ش� ������ Name, DomainTask, SType �Ӽ� ���� ����
			tablePropertyList = new String[4];
			while(findTablePropertyResultSet.next()){

				tablePropertyList[0] = companyName;
				tablePropertyList[1] = findTablePropertyResultSet.getString(1);	//Name
				tablePropertyList[2] = findTablePropertyResultSet.getString(2);	//DomainTask
				tablePropertyList[3] = findTablePropertyResultSet.getString(3);	//Stype
			}
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		return tablePropertyList;
	} //���̺� ������ �Ӽ� �˻�(Name, DomainTask, Stype) �Լ� ��

	//�˻��� �ʵ��� �Ӽ� �˻�
	public static String getSelectedServicePropertyByServiceName(String companyName, String serviceName, String selectedField){
		Statement findSelectedServiceByServiceNameStatement = null;
		ResultSet selectedServiceByServiceNameResultSet = null;
		
		//�ش� ���񽺿� �ش��ϴ� ParentService �ʵ� �˻�
		String findSelectedServiceByServiceNameQuery = "select " + selectedField + " from " + companyName + " where Name= '" + serviceName + "'";
		String selectedServiceName = null;

		try{

			//�ش� ������ ParentService �ʵ�
			findSelectedServiceByServiceNameStatement = DBConnection.connection.createStatement();
			selectedServiceByServiceNameResultSet = findSelectedServiceByServiceNameStatement.executeQuery(findSelectedServiceByServiceNameQuery);

			//�ش� ������ ParentService �Ӽ� ���� ����
			while(selectedServiceByServiceNameResultSet.next()){

				selectedServiceName = selectedServiceByServiceNameResultSet.getString(1);
			}
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}

		return selectedServiceName;
	} //�˻��� �ʵ��� �Ӽ� �˻� �Լ� ��

	//���� �̸����� �Ӽ� �˻�
	public static String[] getPropertyByServiceName(String companyName, String serviceName){
		Statement findPropertyByServiceNameStatement = null;
		ResultSet propertyByServiceNameResultSet = null;
		
		//�ش� ���񽺿� �ش��ϴ� ��� �ʵ� �˻�
		String findPropertyByServiceNameQuery = "select * from " + companyName + " where Name= '" + serviceName + "'";
		String[] servicePropertyList = null;

		try{

			//�ش� ������ �Ӽ� ���
			findPropertyByServiceNameStatement = DBConnection.connection.createStatement();
			propertyByServiceNameResultSet = findPropertyByServiceNameStatement.executeQuery(findPropertyByServiceNameQuery);

			//�ش� ������ �Ӽ� ����� ����			
			servicePropertyList = new String[17];	//15�� �Ӽ� + 2��(���Ƿ� ���� �Ӽ� - �θ�, �ڽļ���)			
			while(propertyByServiceNameResultSet.next()){ //ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�

				for(int index=0; index<servicePropertyList.length; index++){

					//�Ӽ��� Priority �̶��
					if(index == 5){
						double priorityProperty = propertyByServiceNameResultSet.getDouble(index + 1);
						servicePropertyList[index] = Double.toString(priorityProperty);
					}
					//������ �Ӽ� �̶��
					else{
						servicePropertyList[index] = propertyByServiceNameResultSet.getString(index + 1);
					}
				}
			}		
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}

		return servicePropertyList;
	} //���� �̸����� �Ӽ� �˻� �Լ� ��

	//�θ� ���� �޺� ��� ����
	public static String[] setParentServiceNameComboList(String selectedItem){	//���ڷ� ȸ�� �̸�
		Statement findParentServiceStatement = null;
		Statement parentServiceNumberStatement = null;
		ResultSet parentServiceResultSet = null;
		ResultSet parentServiceNumberResultSet = null;

		//���õ� ȸ���� ���� �̸� ������ �˻�
		String findParentServiceQuery = "select Name from " + selectedItem;

		//���õ� ȸ���� ���� ������ �˻�
		String parentServiceNumberQuery = "select count(*) from " + selectedItem;

		String[] serviceNameList = null;

		try{
			//���� ���
			findParentServiceStatement = DBConnection.connection.createStatement();
			parentServiceResultSet = findParentServiceStatement.executeQuery(findParentServiceQuery);

			//���� ����
			parentServiceNumberStatement = DBConnection.connection.createStatement();
			parentServiceNumberResultSet = parentServiceNumberStatement.executeQuery(parentServiceNumberQuery);

			//���� ������ ����
			int parentServiceNumber = 0;
			while(parentServiceNumberResultSet.next()){				

				parentServiceNumber = parentServiceNumberResultSet.getInt(1);
			}

			//���� ����� ����
			serviceNameList = new String[parentServiceNumber];							
			int serviceCount = 0;
			while(parentServiceResultSet.next()){

				serviceNameList[serviceCount++] = parentServiceResultSet.getString(1);			
			}	
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}

		return serviceNameList;		
	} //�θ� ���� �޺� ��� ���� �Լ� ��

	//�ڽ� ���� �޺� ��� ����
	public static String[] setChildServiceNameComboList(String selectedItem){	//���ڷ� ȸ�� �̸�
		Statement findChildServiceStatement = null;
		Statement childServiceNumberStatement = null;	
		ResultSet childServiceResultSet = null;
		ResultSet childServiceNumberResultSet = null;
		
		//���õ� ȸ���� ���� �̸� ������ �˻�
		String findChildServiceQuery = "select Name from " + selectedItem;

		//���õ� ȸ���� ���� ������ �˻�
		String childServiceNumberQuery = "select count(*) from " + selectedItem;

		String[] serviceNameList = null;

		try{

			//���� ���
			findChildServiceStatement = DBConnection.connection.createStatement();
			childServiceResultSet = findChildServiceStatement.executeQuery(findChildServiceQuery);

			//���� ����
			childServiceNumberStatement = DBConnection.connection.createStatement();
			childServiceNumberResultSet = childServiceNumberStatement.executeQuery(childServiceNumberQuery);

			//���� ������ ����
			int childServiceNumber = 0;
			while(childServiceNumberResultSet.next()){				
				childServiceNumber = childServiceNumberResultSet.getInt(1);
			}

			//���� ����� ����
			serviceNameList = new String[childServiceNumber];							
			int serviceCount = 0;
			while(childServiceResultSet.next()){

				serviceNameList[serviceCount++] = childServiceResultSet.getString(1);			
			}	
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}

		return serviceNameList;	
	} //�ڽ� ���� �޺� ��� ���� �Լ� ��

	//ȸ���̸� �޺� ��� ����
	public static String[] setCompanyNameComboList(){
		Statement showTableListStatement = null;
		Statement showTableNumberStatement = null;	
		ResultSet tableNumberResultSet = null;
		ResultSet tableListResultSet = null;
		
		//��� ���̺� ���		
		String showTableListQuery = "show tables";

		//��� ���̺��� ���� ���
		String showTableNumberQuery = "select count(*) from information_schema.tables where table_schema = 'Company' and TABLE_TYPE='BASE TABLE'";	

		String[] tableList = null;		

		try{

			//���̺� ����
			showTableNumberStatement = DBConnection.connection.createStatement();
			tableNumberResultSet = showTableNumberStatement.executeQuery(showTableNumberQuery);

			//���̺� ���
			showTableListStatement = DBConnection.connection.createStatement();
			tableListResultSet = showTableListStatement.executeQuery(showTableListQuery);

			//���̺� ������ ����
			int tableNumber = 0;
			while(tableNumberResultSet.next()){				
				tableNumber = tableNumberResultSet.getInt(1);
			}

			//���̺� ����� ����
			tableList = new String[tableNumber];							
			int tableCount = 0;
			while(tableListResultSet.next()){

				tableList[tableCount++] = tableListResultSet.getString(1);			
			}	
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}				

		return tableList;	//���̺� ����� ��ȯ
	} //ȸ���̸� �޺� ��� ���� �Լ� ��
}
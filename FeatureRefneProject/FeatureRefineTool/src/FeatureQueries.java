import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

public class FeatureQueries {
	private static final String URL = "jdbc:mysql://localhost:3306/featureRefine";    	//DB SystemService
	private static final String USERNAME = "root";              	                	//����ڸ� root
	private static final String PASSWORD = "2001";                          	    	//��й�ȣ 2001

	private Connection connection = null; 	
	private Statement createTableStatement = null;
	private Statement findPropertyByFeatureNameStatement = null;
	private ResultSet propertyByFeatureNameResultSet = null;
	
	private Statement findNamePropertyStatement = null;
	private ResultSet findNamePropertyResultSet = null;
	
	private Statement countRowNumberStatement = null;
	private ResultSet countRowNumberResultSet = null;
	
	private Statement findRelationshipByFeatureNameStatement = null;
	private ResultSet relationshipByFeatureNameResultSet = null;
	
	private Statement findSimanticsByFeatureNameStatement = null;
	private ResultSet simanticsByFeatureNameResultSet = null;
	
	private Statement findFeatureStatement = null;
	private ResultSet featureResultSet = null;
	private Statement featureNumberStatement = null;
	private ResultSet featureNumberResultSet = null;

	//������
	public FeatureQueries(){
		//����̹� Ŭ������ �˻�
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
		}catch(ClassNotFoundException e){
			//����̹� ã�� ���� �޽��� �ڽ� ����
			MessageBox searchDriverErrorMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			searchDriverErrorMessageBox.setText("Driver Not Found");
			searchDriverErrorMessageBox.setMessage("Driver Not Found");
			searchDriverErrorMessageBox.open();
		}
		//Connection ��ü ����
		try{
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);  //DB������ �����ϴ� ��ü ����
		}catch(SQLException sqlException){
			//DB���� ���� �޽��� �ڽ� ����
			MessageBox connectDatabaseErrorMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			connectDatabaseErrorMessageBox.setText("Database Connect Error");
			connectDatabaseErrorMessageBox.setMessage("Database Connect Error");
			connectDatabaseErrorMessageBox.open();
		}	
	} //������ ��
	
	//��ó�� �̸� �Ӽ� �˻�
	public String[] getFeatureList(){
		String findFeatureNameQuery = "select Name from feature";
		String featureNumberQuery = "select count(*) from feature";
		String[] featureNameList = null;

		try{
			//���
			findFeatureStatement = connection.createStatement();
			featureResultSet = findFeatureStatement.executeQuery(findFeatureNameQuery);

			//����
			featureNumberStatement = connection.createStatement();
			featureNumberResultSet = featureNumberStatement.executeQuery(featureNumberQuery);

			int featureNumber = 0;
			while(featureNumberResultSet.next()){				
				featureNumber = featureNumberResultSet.getInt(1);
			}

			featureNameList = new String[featureNumber];							
			int index = 0;
			while(featureResultSet.next()){
				featureNameList[index++] = featureResultSet.getString(1);			
			}	
		}catch(SQLException sqlException){}
		return featureNameList;	
	} //��ó�� �̸� �Ӽ� �˻� �Լ� ��
	
	//Relationship �Ӽ� �˻�
	public String[] getRelationshipByFeatureName(String featureName){
		String findRelationshipByFeatureNameQuery = "select Property, ConditionProperty from feature where Name= '" + featureName + "'";
		String[] featurePropertyList = null;
		try{
			//�ش� ��ó�� �Ӽ� ���
			findRelationshipByFeatureNameStatement = connection.createStatement(); 
			relationshipByFeatureNameResultSet = findRelationshipByFeatureNameStatement.executeQuery(findRelationshipByFeatureNameQuery);

			//�ش� ��ó�� �Ӽ� ����� ����			
			featurePropertyList = new String[2];	//4�� �Ӽ�			
			while(relationshipByFeatureNameResultSet.next()){ //ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
				featurePropertyList[0] = relationshipByFeatureNameResultSet.getString("Property");
				featurePropertyList[1] = relationshipByFeatureNameResultSet.getString("ConditionProperty");
			}		
		}
		catch(SQLException sqlException){}
		return featurePropertyList;
	} //Relationship �Ӽ� �˻� �Լ� ��
	
	//Simantics �Ӽ� �˻�
	public String[] getSemanticsByFeatureName(String featureName){
		String findSimanticsByFeatureNameQuery = "select Classification, FType, Priority, Definition from feature where Name= '" + featureName + "'";
		String[] featurePropertyList = null;
		try{
			//�ش� ��ó�� �Ӽ� ���
			findSimanticsByFeatureNameStatement = connection.createStatement(); 
			simanticsByFeatureNameResultSet = findSimanticsByFeatureNameStatement.executeQuery(findSimanticsByFeatureNameQuery);

			//�ش� ��ó�� �Ӽ� ����� ����			
			featurePropertyList = new String[4];	//4�� �Ӽ�			
			while(simanticsByFeatureNameResultSet.next()){ //ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
				featurePropertyList[0] = simanticsByFeatureNameResultSet.getString("Classification");
				featurePropertyList[1] = simanticsByFeatureNameResultSet.getString("FType");
				double priorityProperty = simanticsByFeatureNameResultSet.getDouble("Priority");
				featurePropertyList[2] = Double.toString(priorityProperty);
				featurePropertyList[3] = simanticsByFeatureNameResultSet.getString("Definition");
			}		
		}
		catch(SQLException sqlException){}
		return featurePropertyList;
	} //Simantics �Ӽ� �˻� �Լ� ��
	
	//���õ� ������ ��ü �Ӽ� �˻�
	public String[] getPropertyByFeatureName(String featureName){

		//�ش� ��ó�� �ش��ϴ� ��� �ʵ� �˻�
		String findPropertyByFeatureNameQuery = "select * from Feature where Name= '" + featureName + "'";
		String[] featurePropertyList = null;
		
		try{
			//�ش� ��ó�� �Ӽ� ���
			findPropertyByFeatureNameStatement = connection.createStatement();
			propertyByFeatureNameResultSet = findPropertyByFeatureNameStatement.executeQuery(findPropertyByFeatureNameQuery);

			//�ش� ��ó�� �Ӽ� ����� ����			
			featurePropertyList = new String[11];	//11�� �Ӽ�			
			while(propertyByFeatureNameResultSet.next()){ //ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
				for(int index=0; index<featurePropertyList.length; index++){
					//�Ӽ��� Priority �̶��
					if(index == 4){
						double priorityProperty = propertyByFeatureNameResultSet.getDouble(index + 1);
						featurePropertyList[index] = Double.toString(priorityProperty);
					}
					//������ �Ӽ� �̶��
					else{
						featurePropertyList[index] = propertyByFeatureNameResultSet.getString(index + 1);
					}
				}
			}		
		}
		catch(SQLException sqlException){
		}
		return featurePropertyList;
	} //���õ� ������ ��ü �Ӽ� �˻� �Լ� ��
	
	//���̺� �� Ʃ��(��)�� ���� �˻�
	public int getTableRowNumber(){

		//���̺��� Ʃ��(��) ���� �˻�
		String countRowNumberQuery = "select count(*) from feature";
		int tableRowNumber = -1;

		try{
			countRowNumberStatement = connection.createStatement();
			countRowNumberResultSet = countRowNumberStatement.executeQuery(countRowNumberQuery);

			while(countRowNumberResultSet.next()){
				tableRowNumber = countRowNumberResultSet.getInt(1);
			}
		}
		catch(SQLException sqlException){
		}
		return tableRowNumber;
	} //���̺� �� Ʃ��(��)�� ���� �˻� �Լ� ��
	
	//��ó ���̺� �� ��� Ʃ�� �˻�
	public String[] getNameProperty(){

		//�ش� ��ó�� �ش��ϴ� ��� �ʵ� �˻�
		String findNamePropertyQuery = "select Name from Feature";
		String[] featurePropertyList = null;
		
		try{
			//�ش� ��ó�� �̸� �Ӽ� ���
			findNamePropertyStatement = connection.createStatement();
			findNamePropertyResultSet = findNamePropertyStatement.executeQuery(findNamePropertyQuery);

			featurePropertyList = new String[getTableRowNumber()];					
			while(findNamePropertyResultSet.next()){ //ResultSet�� Ʃ��(����) �ϳ��� ��� ResultSet�� next()�޼��带 �̿��� �˻�
				for(int index=0; index<featurePropertyList.length; index++){
						featurePropertyList[index] = findNamePropertyResultSet.getString(index + 1);
				}
			}		
		}
		catch(SQLException sqlException){
		}
		return featurePropertyList;
	} //��ó ���̺� �� ��� Ʃ�� �˻� �Լ� ��
	
	//���ο� ���� ���
	public void addService(String name, String classification, String fType, String synonymousNames, double priority, 
							String condition, String definition, String serviceProperty, String relationalProperty,
							String identifier, String registrationAuthority){
		//���̺� ����		
		String createTableStatementQuery = "create table if not exists Feature";	//Feature ���̺� �������� �ʴٸ� ����        						 
		String tableFieldQuery = createTableStatementField();						//���̺� �ʵ� ����

		//���� ���� ������
		String insertNewFeatureQuery = "INSERT INTO Feature VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
		try{
			//���̺� ���� ó��
			createTableStatement = connection.createStatement();
			createTableStatement.executeUpdate(createTableStatementQuery + tableFieldQuery);   

			//���̺� ���� ���� �޽��� �ڽ� ����
			MessageBox createTableMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			createTableMessageBox.setText("Create Table Success");
			createTableMessageBox.setMessage("Create Table Success");
			createTableMessageBox.open();	

			//Identifying Attributes
			PreparedStatement insertNewFeature = connection.prepareStatement(insertNewFeatureQuery);
			insertNewFeature.setString(1, name);		//1,2,3... ����
			insertNewFeature.setString(2, classification);
			insertNewFeature.setString(3, fType);
			insertNewFeature.setString(4, synonymousNames);
			insertNewFeature.setDouble(5, priority);
			insertNewFeature.setString(6, condition);
			insertNewFeature.setString(7, definition);

			//Selective Attributes
			insertNewFeature.setString(8, serviceProperty);

			//Relational Attributes
			insertNewFeature.setString(9, relationalProperty);

			//Managing Attributes
			insertNewFeature.setString(10, identifier);
			insertNewFeature.setString(11, registrationAuthority);

			insertNewFeature.executeUpdate();			
		}
		catch(SQLException sqlException){}				
	} //���ο� ���� ��� �Լ� ��
	
	//���̺� �ʵ� ���� - �� 11�� �Ӽ�
	public String createTableStatementField(){
		//Identifying Attributes
		String tableFieldQuery = "(Name varchar(100), " +		//1
				"Classification varchar(100), " +				//2 
				"FType varchar(100), " +						//3
				"SynonymousNames varchar(100), " +				//4
				"Priority double, " +							//5
				"ConditionProperty varchar(100), " +			//6
				"Definition varchar(100), " +					//7

				//Selective Attributes
				"Property varchar(100), " +						//8 

	            //Relational Attributes
	            "RelationalProperty varchar(100), " +			//9

	            //Managing Attributes
	            "Identifier varchar(100), " +					//10
	            "RegistrationAuthority varchar(100))";			//11
		return tableFieldQuery;
	} //���̺� �ʵ� ���� - �� 11�� �Ӽ� �Լ� ��
}
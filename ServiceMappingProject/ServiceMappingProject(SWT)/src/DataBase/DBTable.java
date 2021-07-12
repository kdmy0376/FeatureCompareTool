package DataBase;
import DataBase.DBConnection;

import java.sql.SQLException;
import java.sql.Statement;

public class DBTable {
	private Statement createTableStatement = null;
	
	//������
	public DBTable(String companyName){
		this.createTable(companyName);	//���̺� ���� �Լ� ȣ��
	} //������ ��

	//���̺� ����
	private void createTable(String companyName){
		////////////////////////////////////////////////////////////////////////////////////
		//Table ����
		String tableStructure = 	//Identifying Attributes
									"(Name varchar(100) primary key, " +			//1
									"Definition TEXT, " +							//2
									"DomainTask varchar(100), " +					//3
									"SType varchar(2000), " +						//4
									"SynonymousNames varchar(100), " +				//5
									"Priority double, " +							//6
									"ConditionProperty varchar(100), " +			//7
									"Interface varchar(100), " +					//8
									"BusinessProcess varchar(2000), " +				//9
									"Component varchar(2000), " +					//10

		                             //Functional Attributes
		                             "MandatoryFunction TEXT, " +		//11
		                             "SecondaryFunction TEXT, " +		//12

		                             //Selective Attributes
		                             "Property varchar(100), " +		//13

		                             //Relational Attributes
		                             "andProperty TEXT, " +				//14
		                             "orProperty TEXT, " +				//15

		        					 //�θ� ����, �ڽ� ���� �Ӽ�
		        					 "ParentService varchar(100), " +	//16
		        					 "ChildService varchar(100))";		//17
		////////////////////////////////////////////////////////////////////////////////////
				
		//���̺� ������ ���� ������		
        String createTableQuery = "create table if not exists " + companyName;	//Company ���̺� �������� �ʴٸ� ����        						 
		
        //���̺� ���� ó��
        try{
			createTableStatement = DBConnection.connection.createStatement();
			createTableStatement.executeUpdate(createTableQuery + tableStructure);	//������ + ���̺� ���� ����
		}catch(SQLException e){	e.printStackTrace(); }
        
        //Close �۾�
        try{
        	createTableStatement.close();
        }catch(SQLException e){	e.printStackTrace(); }
	} //���̺� ���� �Լ� ��
}
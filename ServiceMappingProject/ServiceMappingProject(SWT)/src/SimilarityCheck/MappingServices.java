//A�� Component �Ӽ����� (M/V/C pattern base) ������.
package SimilarityCheck;
import java.util.ArrayList;

import DataBase.DBWork;

public class MappingServices {
	private String firstCompanyName = null;
	private String secondCompanyName = null;
	private String firstServiceName = null;
	private String secondServiceName = null;
	private String analysisOption = null;
	private String displayMode = null;
	private SimilarityCheckScreen similarityCheckScreen = null;
	private double totalPercentage = 0;
	private ArrayList<Double> percentageArray = null;
	private boolean boundaryMode = false;
	private String similarityLevel = null;			//���缺 ����
	private String explainSimilarityLevel = null;	//���缺 ������ ����
	private double sumOfArrayListValue = 0.0;		//ArrayList���� ��ҵ��� ���� ���� ����

	//������
	public MappingServices(String firstCompanyName, String secondCompanyName, String firstServiceName, String secondServiceName, 
						   String analysisOption, String displayMode, SimilarityCheckScreen similarityCheckScreen){
		//�ɹ� ���� �ʱ�ȭ �Լ� ȣ��
		this.initializeMember(firstCompanyName, secondCompanyName, firstServiceName, secondServiceName, 
							  analysisOption, displayMode, similarityCheckScreen);
		//���� ���� ó�� ȣ��
		this.processMapping();
	} //������ ��

	//��� ���� �ʱ�ȭ
	public void initializeMember(String firstCompanyName, String secondCompanyName, String firstServiceName, String secondServiceName, 
								 String analysisOption, String displayMode, SimilarityCheckScreen similarityCheckScreen){
		this.firstCompanyName = firstCompanyName;			//ù��° ������ ȸ�� �̸�
		this.secondCompanyName = secondCompanyName;			//�ι�° ������ ȸ�� �̸�
		this.firstServiceName = firstServiceName;			//ù��° ���� �̸�
		this.secondServiceName = secondServiceName;			//�ι�° ���� �̸�
		this.analysisOption = analysisOption;				//�м� �ɼ�
		this.displayMode = displayMode;						//���÷��� ���
		this.similarityCheckScreen = similarityCheckScreen;	
		this.percentageArray = new ArrayList<Double>();	//Pre-Post-Self �ۼ�Ƽ���� ��� ���� ����
	} //��� ���� �ʱ�ȭ �Լ� ��

	//���� ���� ó��
	public void processMapping(){
		//ù��° ���� �Ӽ� ����Ʈ
		String[] firstServicePropertyList = DBWork.getPropertyByServiceName(firstCompanyName, firstServiceName);
		//�ι�° ���� �Ӽ� ����Ʈ
		String[] secondServicePropertyList = DBWork.getPropertyByServiceName(secondCompanyName, secondServiceName);
		//�Ӽ��� ����
		int lengthOfProperty = firstServicePropertyList.length;

		//���� ���񽺵� ���� ����
		if(analysisOption.equals("Semantic Self Similarity")){	
			//Boundary ��带 ��
			boundaryMode = false;
			
			//���÷��� ��尡 ��� ����� ���
			if(displayMode.equals("Function based")){
				//Functional �Ӽ�
				mappingFunctionalProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
			}
			//���÷��� ��尡 ��ü ����� ���
			else{
				//Identidying �Ӽ�
				mappingIdentifyingProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
				//Functional �Ӽ�
				mappingFunctionalProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
				//Selective �Ӽ�
				mappingSelectiveProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
				//Relational �Ӽ�
				mappingRelationalProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
			}
			//ArrayList���� �� ��������
			for(int j=0; j<percentageArray.size(); j++){
				sumOfArrayListValue += percentageArray.get(j);
			}
			String hitRatio = String.format("%.2f", sumOfArrayListValue);	//�Ҽ��� 2�ڸ����� ǥ��
			measureSimilarityLevel(sumOfArrayListValue);					//���缺 ���� ����(High, Middle, Low)
			
			//���� ����� ȭ�鿡 ǥ��
			similarityCheckScreen.setMappingResult(hitRatio, similarityLevel, explainSimilarityLevel, displayMode, boundaryMode);
			
			//����� ���� �ʱ�ȭ
			initializeResultValue();										
		} //���� ���񽺵� ���� ���� ��
		
		//��ü(Pre-Self-Post)���񽺵� ���� ����
		else if(analysisOption.equals("Semantic Boundary Similarity")){
			//Boundary ��带 ��
			boundaryMode = true;
			
			for(int i=0; i<3; i++){
				//Self ����
				if(i==0){
					//���÷��� ��尡 ��� ����� ���
					if(displayMode.equals("Function based")){
						//Functional �Ӽ�
						mappingFunctionalProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
					}
					//���÷��� ��尡 ��ü ����� ���
					else{
						//Identidying �Ӽ�
						mappingIdentifyingProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
						//Functional �Ӽ�
						mappingFunctionalProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
						//Selective �Ӽ�
						mappingSelectiveProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
						//Relational �Ӽ�
						mappingRelationalProperties(firstServicePropertyList, secondServicePropertyList, lengthOfProperty);
					}		
				}
				//Pre ����
				else if(i==1){
					//�θ𼭺� ����(ù��° ȸ��)
					String firstParentService = firstServicePropertyList[firstServicePropertyList.length - 2];
					//�θ𼭺� ����(�ι�° ȸ��)
					String secondParentService = secondServicePropertyList[secondServicePropertyList.length - 2];
					//ù��° ������ �θ� ���� ���� �ش��ϴ� �Ӽ����� ������
					String[] firstPropertyList = DBWork.getPropertyByServiceName(firstCompanyName, firstParentService);
					//�ι�° ������ �θ� ���� ���� �ش��ϴ� �Ӽ����� ������
					String[] secondPropertyList = DBWork.getPropertyByServiceName(secondCompanyName, secondParentService);
					//���÷��� ��尡 ��� ����� ���
					if(displayMode.equals("Function based")){
						//Functional �Ӽ�
						mappingFunctionalProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
					}
					//���÷��� ��尡 ��ü ����� ���
					else{
						//Identidying �Ӽ�
						mappingIdentifyingProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
						//Functional �Ӽ�
						mappingFunctionalProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
						//Selective �Ӽ�
						mappingSelectiveProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
						//Relational �Ӽ�
						mappingRelationalProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
					}
				}
				//Post ����
				else if(i==2){
					//�ڽļ��� ����(ù��° ȸ��)
					String firstChildService = firstServicePropertyList[firstServicePropertyList.length - 1];
					//�ڽļ��� ����(�ι�° ȸ��)
					String secondChildService = secondServicePropertyList[secondServicePropertyList.length - 1];
					//ù��° ������ �ڽ� ���� ���� �ش��ϴ� �Ӽ����� ������
					String[] firstPropertyList = DBWork.getPropertyByServiceName(firstCompanyName, firstChildService);
					//�ι�° ������ �ڽ� ���� ���� �ش��ϴ� �Ӽ����� ������
					String[] secondPropertyList = DBWork.getPropertyByServiceName(secondCompanyName, secondChildService);
					//���÷��� ��尡 ��� ����� ���
					if(displayMode.equals("Function based")){
						//Functional �Ӽ�
						mappingFunctionalProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
					}
					//���÷��� ��尡 ��ü ����� ���
					else{
						//Identidying �Ӽ�
						mappingIdentifyingProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
						//Functional �Ӽ�
						mappingFunctionalProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
						//Selective �Ӽ�
						mappingSelectiveProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
						//Relational �Ӽ�
						mappingRelationalProperties(firstPropertyList, secondPropertyList, lengthOfProperty);
					}
				}
			} //��ü(Pre-Self-Post)���񽺵� ���� ���� ��
			//ArrayList���� �� ��������
			for(int j=0; j<percentageArray.size(); j++){
				sumOfArrayListValue += percentageArray.get(j);
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//�߰� �����ϱ� ���� �ڵ�
			System.out.println("Pre-Post-Self �� �հ� : " + sumOfArrayListValue);
			System.out.println("Pre-Post-Self �� ��Ī �ۼ�Ʈ : " + sumOfArrayListValue/3);
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			String hitRatio = String.format("%.2f", sumOfArrayListValue/3);	//�Ҽ��� 2�ڸ����� ǥ��
			measureSimilarityLevel(sumOfArrayListValue/3);					//���缺 ���� ����(High, Middle, Low)

			//���� ����� ȭ�鿡 ǥ��
			similarityCheckScreen.setMappingResult(hitRatio, similarityLevel, explainSimilarityLevel, displayMode, boundaryMode);
			
			//����� ���� �ʱ�ȭ
			initializeResultValue();
		}
	} //���� ���� ó�� �Լ� ��
	
	//Identifying �Ӽ� ���� ó��
	private void mappingIdentifyingProperties(String[] firstServicePropertyList, String[] secondServicePropertyList, int lengthOfProperty){
		double mappingResult = 0.0;
		for(int i=0; i<lengthOfProperty-7; i++){
			//�� �Ӽ��� ���� ��ġ�ϸ�
			if(firstServicePropertyList[i].compareTo(secondServicePropertyList[i]) == 0){
				mappingResult += 100; 
			}
			//�� �Ӽ��� ���� ��ġ���� ������
			else{
				//�� �Ӽ� ���� �߿� �޸��� �ִ��� Ȯ��
				if(firstServicePropertyList[i].indexOf(",") != -1 || secondServicePropertyList[i].indexOf(",") != -1){
					//��ü ���ڿ� ���� - �޸� ������ ���ڿ� ���� = �޸� ����
					int firstServiceCommaCount = firstServicePropertyList[i].length() - (firstServicePropertyList[i].replaceAll(",", "")).length();
					int secondServiceCommaCount = secondServicePropertyList[i].length() - (secondServicePropertyList[i].replaceAll(",", "")).length();

					mappingResult += handleAttributes(firstServiceCommaCount, secondServiceCommaCount, 
							firstServicePropertyList[i], secondServicePropertyList[i], i);
				}
				//�Ӽ��� Priority�̸�((1.0 - (�� ���� ��)) * 100)
				else if(i==5){
					double firstVal = Double.parseDouble(firstServicePropertyList[i]);
					double secondVal = Double.parseDouble(secondServicePropertyList[i]);
					double subtractResult = firstVal>secondVal ? firstVal-secondVal : secondVal-firstVal;
					subtractResult = 1.0 - subtractResult;
					mappingResult += subtractResult*100;
				}
			}
		}
		totalPercentage += mappingResult/10;
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//�߰� �����ϱ� ���� �ڵ�
		System.out.println("�ĺ��Ӽ� 10�� ��Ī �ۼ�Ʈ : " +mappingResult/10);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	} //Identifying �Ӽ� ���� ó�� �Լ� ��

	//Functional �Ӽ� ���� ó��
	private void mappingFunctionalProperties(String[] firstServicePropertyList, String[] secondServicePropertyList, int lengthOfProperty){
		double mappingResult = 0.0;
		for(int i=10; i<lengthOfProperty-5; i++){
			//�� �Ӽ��� ���� ��ġ�ϸ�
			if(firstServicePropertyList[i].compareTo(secondServicePropertyList[i]) == 0){
				//�Ӽ��� MandatoryFunction �̸�
				if(i == 10){
					mappingResult += 75;	//75%
				}
				//�Ӽ��� SecondaryFunction �̸�
				else if(i == 11){
					mappingResult += 25;	//25%
				}
			}
			//�� �Ӽ��� ���� ��ġ���� ������
			else{
				//�� �Ӽ� ���� �߿� �޸��� �ִ��� Ȯ��
				if(firstServicePropertyList[i].indexOf(",") != -1 || secondServicePropertyList[i].indexOf(",") != -1){
					//��ü ���ڿ� ���� - �޸� ������ ���ڿ� ���� = �޸� ����
					int firstServiceCommaCount = firstServicePropertyList[i].length() - (firstServicePropertyList[i].replaceAll(",", "")).length();
					int secondServiceCommaCount = secondServicePropertyList[i].length() - (secondServicePropertyList[i].replaceAll(",", "")).length();

					mappingResult += handleAttributes(firstServiceCommaCount, secondServiceCommaCount, 
							firstServicePropertyList[i], secondServicePropertyList[i], i);
				}
			}
		}
		totalPercentage += mappingResult;
		
		//���÷��� ��尡 ��� ����� ���
		if(displayMode.equals("Function based")){
			percentageArray.add(totalPercentage);
			totalPercentage = 0.0;
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//�߰� �����ϱ� ���� �ڵ�
		System.out.println("��ɼӼ� 2�� ��Ī �ۼ�Ʈ : " +mappingResult);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	} //Functional �Ӽ� ���� ó�� �Լ� ��

	//Selective �Ӽ� ���� ó��
	private void mappingSelectiveProperties(String[] firstServicePropertyList, String[] secondServicePropertyList, int lengthOfProperty){
		double mappingResult = 0.0;
		if(firstServicePropertyList[12].compareTo(secondServicePropertyList[12]) == 0){
			mappingResult = 100;
		}
		totalPercentage += mappingResult;
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//�߰� �����ϱ� ���� �ڵ�
		System.out.println("���üӼ� 1�� ��Ī �ۼ�Ʈ : " +mappingResult);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	} //Selective �Ӽ� ���� ó�� �Լ� ��

	//Relational �Ӽ� ���� ó��
	private void mappingRelationalProperties(String[] firstServicePropertyList, String[] secondServicePropertyList, int lengthOfProperty){
		double mappingResult = 0.0;
		for(int i=13; i<lengthOfProperty-2; i++){
			//�� �Ӽ��� ���� ��ġ�ϸ�
			if(firstServicePropertyList[i].compareTo(secondServicePropertyList[i]) == 0){
				mappingResult += 100;
			}
			//�� �Ӽ��� ���� ��ġ���� ������
			else{
				//�� �Ӽ� ���� �߿� �޸��� �ִ��� Ȯ��
				if(firstServicePropertyList[i].indexOf(",") != -1 || secondServicePropertyList[i].indexOf(",") != -1){
					//��ü ���ڿ� ���� - �޸� ������ ���ڿ� ���� = �޸� ����
					int firstServiceCommaCount = firstServicePropertyList[i].length() - (firstServicePropertyList[i].replaceAll(",", "")).length();
					int secondServiceCommaCount = secondServicePropertyList[i].length() - (secondServicePropertyList[i].replaceAll(",", "")).length();

					mappingResult += handleAttributes(firstServiceCommaCount, secondServiceCommaCount, 
							firstServicePropertyList[i], secondServicePropertyList[i], i);
				}
			}
		}
		totalPercentage += mappingResult/2;
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//�߰� �����ϱ� ���� �ڵ�
		System.out.println("����Ӽ� 2�� ��Ī �ۼ�Ʈ : " +mappingResult/2);
		System.out.println("��ü�Ӽ� 15�� ��Ī �ۼ�Ʈ : " + totalPercentage/4 + "\n");
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		percentageArray.add(totalPercentage/4);
		totalPercentage = 0.0;
	} //Relational �Ӽ� ���� ó�� �Լ� ��

	//�޸��� �ִ� �Ӽ��� ó��
	public double handleAttributes(int firstServiceCommaCount, int secondServiceCommaCount,
			String firstServiceMandatoryFunction, String secondServiceMandatoryFunction, int index){		
		//�޸��� ������ ����� ��Ÿ���� ���ڿ��� ��� ���� �迭 ����
		String[] firstServiceFunctionString = new String[firstServiceCommaCount + 1];
		String[] secondServiceFunctionString = new String[secondServiceCommaCount + 1];

		//ù��° ������ MandatoryFunction�Ӽ��� ��ɵ� ����
		extractMandatoryFunction(firstServiceFunctionString, firstServiceMandatoryFunction);

		//�ι�° ������ MandatoryFunction�Ӽ��� ��ɵ� ����
		extractMandatoryFunction(secondServiceFunctionString, secondServiceMandatoryFunction);

		//��� ������ ���� �� �˾Ƴ���
		int maxLength = 0;		//�޸��� ���е� ����� �� �� �ִ밪
		if(firstServiceFunctionString.length > secondServiceFunctionString.length){
			maxLength = firstServiceFunctionString.length;
		}
		else if(firstServiceFunctionString.length < secondServiceFunctionString.length){
			maxLength = secondServiceFunctionString.length;
		}
		else{
			maxLength = firstServiceFunctionString.length;
		}

		int matchFunctionNumber = 0;
		for(int secondFunctionIndex = 0; secondFunctionIndex < secondServiceFunctionString.length; secondFunctionIndex++){
			for(int firstFunctionIndex = 0; firstFunctionIndex < firstServiceFunctionString.length; firstFunctionIndex++){
				//2���� ���񽺵��� ����� ��ġ�ϸ�
				if((secondServiceFunctionString[secondFunctionIndex].trim()).equals(firstServiceFunctionString[firstFunctionIndex].trim())){
					matchFunctionNumber++;	//��ġ Ƚ���� ����
					break;
				}
			}
		}
		///////////////////////////////////////////////////////////
		////����� �ִ���� ��ġ Ƚ���� ����
		//
		//MandatoryFunction�̸�
		if(index==10){
			return ((double)matchFunctionNumber)*75 / maxLength;
		}
		//SecondaryFunction�̸�
		else if(index==11){
			return ((double)matchFunctionNumber)*25 / maxLength;
		}
		return ((double)matchFunctionNumber)*100 / maxLength;
		///////////////////////////////////////////////////////////
	} //�޸��� �ִ� �Ӽ��� ó�� �Լ� ��

	//�޸��� �Ӽ� �߿� �Ӽ��� ����
	public void extractMandatoryFunction(String[] serviceFunctionString, String serviceMandatoryFunction){

		int findIndex = -1;
		int functionStringIndex = 0;
		while((findIndex = serviceMandatoryFunction.indexOf(",")) != -1){	//���ڿ��ȿ� �޸��� �ִٸ�

			//�޸� �������� ���ڿ��� ����
			serviceFunctionString[functionStringIndex++] = serviceMandatoryFunction.substring(0, findIndex);

			//�޸� ���� �ε������� ������ ���ڿ��� ���� ����
			serviceMandatoryFunction = serviceMandatoryFunction.substring(findIndex+1);
		}
		//������ ���ڿ� ����
		serviceFunctionString[functionStringIndex] = serviceMandatoryFunction;
	} //�޸��� �Ӽ� �߿� �Ӽ��� ���� �Լ� ��
	
	//���缺 ���� ����
	private void measureSimilarityLevel(double result){
		//�������� 75%�̻��̸�
		if(result >= (double)75){	
			similarityLevel = "High";
			explainSimilarityLevel = "Service similarity occurs";
			System.out.println("High");
			System.out.println("Service similarity occurs" + "\n");
		}
		//�������� 50�̻� 75�̸��̸�
		else if(result >= (double)50){
			similarityLevel = "Middle";
			explainSimilarityLevel = "Service similarity can occur";
			System.out.println("Middle");
			System.out.println("Service similarity can occur" + "\n");
		}
		//�������� 50�̸��̸�
		else{
			similarityLevel = "Low";
			explainSimilarityLevel = "Service similarity doesn't occur";
			System.out.println("Low");
			System.out.println("Service similarity doesn't occur" + "\n");
		}
	} //���缺 ���� ���� �Լ� ��
	
	//���� �ʱ�ȭ
	private void initializeResultValue(){
		percentageArray.clear(); //ArrayList���� ��ҵ��� ����
		sumOfArrayListValue = 0.0;
		similarityLevel = null;
	} //���� �ʱ�ȭ �Լ� ��
}
public class AnalyzeFeature {
	private AnalyzeFeatureScreen analyzeFeatureScreen = null;
	private String firstFeature = null;
	private String secondFeature = null;
	private String resultOfCollisionAnalysis = null;
	private String resultOfDuplicationAnalysis = null;
	
	//������
	public AnalyzeFeature(AnalyzeFeatureScreen analyzeFeatureScreen, String firstFeature, String secondFeature){
		this.initializeMember(analyzeFeatureScreen, firstFeature, secondFeature);
		this.analyzeFeature();
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(AnalyzeFeatureScreen analyzeFeatureScreen, String firstFeature, String secondFeature){
		this.analyzeFeatureScreen = analyzeFeatureScreen;
		this.firstFeature = firstFeature;
		this.secondFeature = secondFeature;
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//��ó�� �ߺ��� �浹�� ����
	public void analyzeFeature(){
		//��ü �Ӽ� 11��
		String[] totalPropertyOfFirstFeature = MainFrame.featureQueries.getPropertyByFeatureName(firstFeature);
		String[] totalPropertyOfSecondFeature = MainFrame.featureQueries.getPropertyByFeatureName(secondFeature);
		
		//�ø�ƽ�� �Ӽ� Classification, FType, Priority, Definition
		String[] semanticsOfFirstFeature = MainFrame.featureQueries.getSemanticsByFeatureName(firstFeature);
		String[] semanticsOfSecondFeature = MainFrame.featureQueries.getSemanticsByFeatureName(secondFeature);
		
		//Selective Attributes, Condition
		String[] relationshipOfFirstFeature = MainFrame.featureQueries.getRelationshipByFeatureName(firstFeature);
		String firstSelectiveAttributes = relationshipOfFirstFeature[0];	//Selective Attribute	
		String firstCondition = relationshipOfFirstFeature[1];				//Condition
		String[] relationshipOfSecondFeature = MainFrame.featureQueries.getRelationshipByFeatureName(secondFeature);
		String secondSelectiveAttributes = relationshipOfSecondFeature[0];
		String secondCondition = relationshipOfSecondFeature[1];
		
		//�ߺ� ����
		resultOfDuplicationAnalysis = analyzeDuplication(semanticsOfFirstFeature, semanticsOfSecondFeature, 
				 		   									 firstSelectiveAttributes, firstCondition, secondSelectiveAttributes, secondCondition);
		//�浹 ����
		if(resultOfCollisionAnalysis == null){
			resultOfCollisionAnalysis = analyzeCollision(semanticsOfFirstFeature, semanticsOfSecondFeature, 
							firstSelectiveAttributes, firstCondition, secondSelectiveAttributes, secondCondition);
		}
		//��ó �� Selective �Ӽ� ��
		String resultOfSelectiveMapping = mappingProperty(firstSelectiveAttributes, secondSelectiveAttributes);
		
		//��ó �� Condtition ��
		String resultOfConditionMapping = mappingProperty(firstCondition, secondCondition);
		
		//��ó �� �ۼ�Ƽ�� ����
		String resultOfMapping = mappingFeature(totalPropertyOfFirstFeature, totalPropertyOfSecondFeature);
		
		//�ߺ�, �浹 ��� ǥ��
		analyzeFeatureScreen.analyzedResult(resultOfDuplicationAnalysis, resultOfCollisionAnalysis, 
											resultOfSelectiveMapping, resultOfConditionMapping, resultOfMapping);
	} //��ó�� �ߺ��� �浹�� ���� �Լ� ��
	
	//��ó Selective�� Condition �Ӽ� ��
	public String mappingProperty(String firstProperty, String secondProperty){
		if(firstProperty.equals(secondProperty)){
			return "100";
		}
		return "0";
	} //��ó Selective�� Condition �Ӽ� �� �Լ� ��
	
	//��ó ��Ī �ۼ�Ƽ�� ����
	public String mappingFeature(String[] totalPropertyOfFirstFeature, String[] totalPropertyOfSecondFeature){
		int numberOfMatch = 0;
		int numberOfProperties = totalPropertyOfFirstFeature.length;	//11��
		for(int i=0; i<numberOfProperties; i++){
			if(totalPropertyOfFirstFeature[i].equals(totalPropertyOfSecondFeature[i])){
				numberOfMatch++;
			}
		}
		return String.format("%.2f", ((double)(numberOfMatch*100)) / numberOfProperties);
	} //��ó ��Ī �ۼ�Ƽ�� ���� �Լ� ��
	
	//��ó �ø�ƽ�� �˻�
	public boolean checkSimantics(String[] semanticsOfFirstFeature, String[] semanticsOfSecondFeature){
		for(int i=0; i<semanticsOfFirstFeature.length; i++){
			if(!(semanticsOfFirstFeature[i].equals(semanticsOfSecondFeature[i]))){
				System.out.println(!false);
				return false;
			}
		}
		return true;
	} //��ó �ø�ƽ�� �˻� �Լ� ��
	
	//�ߺ� ����
	public String analyzeDuplication(String[] semanticsOfFirstFeature, String[] semanticsOfSecondFeature, 
			                         String firstSelectiveAttributes, String firstCondition, 
			                         String secondSelectiveAttributes, String secondCondition){
		String result = null;
		
		//2���� ��ó�� �ø�ƽ�� �Ӽ��� ���ٸ�
		if(checkSimantics(semanticsOfFirstFeature, semanticsOfSecondFeature)){	
			System.out.println("Collision doesn't occur");
			resultOfCollisionAnalysis = "Collision doesn't occur";
			
			//2���� ��ó�� Mandatory �Ӽ��̶��
			if(firstSelectiveAttributes.equals(secondSelectiveAttributes) && firstSelectiveAttributes.equals("Mandatory")){
				System.out.println("Duplication occurs");
				result = "Duplication occurs";
				
			//1���� ��ó�� Mandatory �Ӽ��̰�
			}else if(firstSelectiveAttributes.equals("Mandatory")){
				//�ٸ� ��ó�� Optional �Ǵ� Alternative �Ӽ��̰�
				if(secondSelectiveAttributes.equals("Optional") || secondSelectiveAttributes.equals("Alternative")){
					//�ι�° ��ó�� ���õ� �����̸�
					if((secondCondition.substring(0, 8)).equals("requires")){	
						System.out.println("Duplication occurs");
						result = "Duplication occurs";
					}else{
						System.out.println("Duplication doesn't occurs");
						result = "Duplication doesn't occurs";
					}
				}
			//1���� ��ó�� Mandatory �Ӽ��� �ƴϰ�
			}else{
				//�ٸ� ��ó�� Optional �Ǵ� Alternative �Ӽ��̰�
				if(secondSelectiveAttributes.equals("Optional") || secondSelectiveAttributes.equals("Alternative")){
					//��ó �� �� ���õ� �����̸�
					if((firstCondition.substring(0, 8)).equals("requires") && (secondCondition.substring(0, 8)).equals("requires")){	
						System.out.println("Duplication occurs");
						result = "Duplication occurs";
					}else{
						System.out.println("Duplication doesn't occurs");
						result = "Duplication doesn't occurs";
					}
				}
			}
		//2���� ��ó�� �ø�ƽ�� �Ӽ��� �ٸ��ٸ�
		}else{	
			System.out.println("Duplication doesn't occur");
			result = "Duplication doesn't occurs";
		}
		return result;
	} //�ߺ� ���� �Լ� ��
	
	//�浹 ����
	public String analyzeCollision(String[] semanticsOfFirstFeature, String[] semanticsOfSecondFeature, 
            					   String firstSelectiveAttributes, String firstCondition, String secondSelectiveAttributes, String secondCondition){
		String result = null;
		//2���� ��ó�� Mandatory �Ӽ��̰�
		if(firstSelectiveAttributes.equals("Mandatory") && secondSelectiveAttributes.equals("Mandatory")){
			//�ΰ��� ��ó �߿� ���� ����Ǵ� �ø�ƽ���� �ִٸ�
			if(!(checkSimantics(semanticsOfFirstFeature, semanticsOfSecondFeature))){	
				System.out.println("Collision occurs");
				result = "Collision occurs";
			}else{
				System.out.println("Collision doesn't occur");
				result = "Collision doesn't occur";
			}
		}
		//1���� ��ó�� Mandatory �Ӽ��̰�
		if(firstSelectiveAttributes.equals("Mandatory")){
			//�ٸ� ��ó�� Optional �Ǵ� Alternative �Ӽ��̰�
			if(secondSelectiveAttributes.equals("Optional") || secondSelectiveAttributes.equals("Alternative")){
				//�ΰ��� ��ó �߿� ���� ����Ǵ� �ø�ƽ���� �ְ� 2���� ��ó�� Condition �Ӽ��� ���ٸ�
				if(!(checkSimantics(semanticsOfFirstFeature, semanticsOfSecondFeature)) 
															&& ((secondCondition.substring(0, 8)).equals("requires"))){	
					System.out.println("Collision occurs");
					result = "Collision occurs";
				}else{
					System.out.println("Collision doesn't occur");
					result = "Collision doesn't occur";
				}
			}
		//1���� ��ó�� Optional �Ӽ� �Ǵ� Alternative �Ӽ��̰�
		}else{	
			//�ٸ� ��ó�� Optional �Ǵ� Alternative �Ӽ��̰�
			if(secondSelectiveAttributes.equals("Optional") || secondSelectiveAttributes.equals("Alternative")){
				//�ΰ��� ��ó �߿� ���� ����Ǵ� �ø�ƽ���� �ְ� 2���� ��ó�� Condition �Ӽ��� ���ٸ�
				if(!(checkSimantics(semanticsOfFirstFeature, semanticsOfSecondFeature)) && 
				    ((firstCondition.substring(0, 8)).equals("requires") && (secondCondition.substring(0, 8)).equals("requires"))){
					System.out.println("Collision occurs");
					result = "Collision occurs";
				}else{
					System.out.println("Duplication doesn't occur");
					result = "Collision doesn't occur";
				}
			}
		}
		return result;
	} //�浹 ���� �Լ� ��
}
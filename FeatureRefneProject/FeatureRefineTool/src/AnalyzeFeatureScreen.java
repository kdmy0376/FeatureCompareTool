import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AnalyzeFeatureScreen extends Composite implements SelectionListener{
	private GridLayout gridLayout = null;
	private Color labelColor = null;
	private Color buttonColor = null;
	private Font groupFont = null;
	private Font buttonFont = null;
	static Text openFirstFeatureText = null;
	static Text openSecondFeatureText = null;
	private Button openFirstFeatureButton = null;	
	private Button openSecondFeatureButton = null;
	private Button analyzeButton = null;
	private Button initialButton = null;
	private Button cancelButton = null;
	private Group leftResultGroup = null;
	private Group featureAnalysisResultGroup = null;
	private Group percentageGroup = null;
	private Group resultOfDuplicationAndCollisionGroup = null;
	private Group canvasGroup = null;
	private Label selectiveMappingLabel = null;
	private Label conditionMappingLabel = null;
	private Label totalMappingLabel = null;
	private Label selectivePercentageLabel = null;
	private Label conditionPercentageLabel = null;
	private Label totalPercentageLabel = null;
	private Label resultOfDuplicationCheckLabel = null;
	private Label resultOfCollisionCheckLabel = null;
	
	//������
	public AnalyzeFeatureScreen(){
		super(MainFrame.shell, SWT.NONE);
		this.initializeMember();
		this.setLayout(gridLayout);
		this.createInputFeatureForm();
		this.createFeatureAnalysisResult();
		this.createButton();
		this.mountEventHandler();
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){
		gridLayout = new GridLayout(1, false);
		labelColor = new Color(this.getParent().getDisplay(), 240,210,230);
		buttonColor = new Color(this.getParent().getDisplay(), 70,210,210);
		groupFont = new Font(this.getParent().getDisplay(), "Miriam", 11, SWT.BOLD);
		buttonFont = new Font(this.getParent().getDisplay(), "Miriam", 10, SWT.BOLD);
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//��ó �Է� ��
	public void createInputFeatureForm(){
		Group inputFeatureGroup = new Group(this, SWT.SHADOW_ETCHED_IN);
		inputFeatureGroup.setText("Input Feature");		 
		inputFeatureGroup.setFont(groupFont);
		gridLayout = new GridLayout(7, false);
		inputFeatureGroup.setLayout(gridLayout);		
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		inputFeatureGroup.setLayoutData(gridData);

		Label openFirstFeatureLabel = new Label(inputFeatureGroup, SWT.BORDER);	//ù��° ��ó ����
		openFirstFeatureLabel.setText("First Feature:");		
		openFirstFeatureLabel.setBackground(labelColor);
		gridData = new GridData();
		gridData.widthHint = 90;
		openFirstFeatureLabel.setLayoutData(gridData);
		openFirstFeatureText = new Text(inputFeatureGroup, SWT.BORDER);
		openFirstFeatureText.setEnabled(false);
		gridData = new GridData();
		gridData.horizontalSpan = 5;
		gridData.horizontalAlignment = SWT.FILL;
		openFirstFeatureText.setLayoutData(gridData);
		openFirstFeatureButton = new Button(inputFeatureGroup, SWT.PUSH);
		openFirstFeatureButton.setText("Get Feature");
		openFirstFeatureButton.setBackground(buttonColor);

		Label openSecondFeatureLabel = new Label(inputFeatureGroup, SWT.BORDER);	//�ι�° ��ó ����
		openSecondFeatureLabel.setText("Second Feature:");	
		openSecondFeatureLabel.setBackground(labelColor);
		gridData = new GridData();
		gridData.widthHint = 90;
		openSecondFeatureLabel.setLayoutData(gridData);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		openSecondFeatureLabel.setLayoutData(gridData);
		openSecondFeatureText = new Text(inputFeatureGroup, SWT.BORDER);
		openSecondFeatureText.setEnabled(false);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.horizontalSpan = 5;		
		gridData.grabExcessHorizontalSpace = true;
		openSecondFeatureText.setLayoutData(gridData);
		openSecondFeatureButton = new Button(inputFeatureGroup, SWT.NONE);
		openSecondFeatureButton.setText("Get Feature");
		openSecondFeatureButton.setBackground(buttonColor);
		
		//�׽�Ʈ��
		openFirstFeatureText.setText("Drafting");
		openSecondFeatureText.setText("Waiting Drafting");
		
		analyzeButton = new Button(inputFeatureGroup, SWT.PUSH);	//�м� ��ư
		analyzeButton.setText("Analyze");
		analyzeButton.setBackground(buttonColor);
		analyzeButton.setFont(buttonFont);
		gridData = new GridData();
		gridData.horizontalSpan = 7;
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.widthHint = 73;
		analyzeButton.setLayoutData(gridData);
	} //��ó �Է� �� �Լ� ��
	
	//��ó ���� ��� ǥ��
	public void createFeatureAnalysisResult(){
		featureAnalysisResultGroup = new Group(this, SWT.SHADOW_ETCHED_IN);
		featureAnalysisResultGroup.setFont(groupFont);
		featureAnalysisResultGroup.setText("Analyzed Result");
		GridLayout gridLayout = new GridLayout(2, false);
		featureAnalysisResultGroup.setLayout(gridLayout);
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		featureAnalysisResultGroup.setLayoutData(gridData);
		
		leftResultGroup = new Group(featureAnalysisResultGroup, SWT.SHADOW_ETCHED_IN);
		leftResultGroup.setFont(groupFont);
		leftResultGroup.setLayout(new GridLayout(1, false));
		gridData = new GridData();
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		leftResultGroup.setLayoutData(gridData);
		
		percentageGroup = new Group(leftResultGroup, SWT.SHADOW_ETCHED_IN);	//�ۼ�Ʈ 
		percentageGroup.setText("��Attribute based Similarity Analysis");
		gridLayout = new GridLayout(2, false); 
		percentageGroup.setLayout(gridLayout);
		gridData = new GridData();
		gridData.verticalIndent = 10;
		gridData.horizontalIndent = 15;
		percentageGroup.setLayoutData(gridData); 
		percentageGroup.setVisible(false);
		
		//Selective Percentage
		selectiveMappingLabel = new Label(percentageGroup, SWT.NONE);
		gridData = new GridData();
		gridData.widthHint = 203;
		gridData.horizontalIndent = 25;
		selectiveMappingLabel.setLayoutData(gridData);
		
		selectivePercentageLabel = new Label(percentageGroup, SWT.NONE);
		selectivePercentageLabel.setFont(new Font(this.getParent().getDisplay(), "Miriam", 12, SWT.BOLD));
		gridData = new GridData();
		gridData.widthHint = 70;
		selectivePercentageLabel.setLayoutData(gridData); 
		
		//Condition Percentage
		conditionMappingLabel = new Label(percentageGroup, SWT.NONE);
		gridData = new GridData();
		gridData.widthHint = 210;
		gridData.horizontalIndent = 25;
		conditionMappingLabel.setLayoutData(gridData);
		
		conditionPercentageLabel = new Label(percentageGroup, SWT.NONE);
		conditionPercentageLabel.setFont(new Font(this.getParent().getDisplay(), "Miriam", 12, SWT.BOLD));
		gridData = new GridData();
		gridData.widthHint = 70;
		conditionPercentageLabel.setLayoutData(gridData);
		
		//Total Percentage
		totalMappingLabel = new Label(percentageGroup, SWT.NONE);
		gridData = new GridData();
		gridData.widthHint = 203;
		gridData.horizontalIndent = 25;
		totalMappingLabel.setLayoutData(gridData);
		
		totalPercentageLabel = new Label(percentageGroup, SWT.NONE);
		totalPercentageLabel.setFont(new Font(this.getParent().getDisplay(), "Miriam", 15, SWT.BOLD));
		gridData = new GridData();
		gridData.widthHint = 80;
		totalPercentageLabel.setLayoutData(gridData);
		
		resultOfDuplicationAndCollisionGroup = new Group(leftResultGroup, SWT.SHADOW_ETCHED_IN);	//�ߺ�
		resultOfDuplicationAndCollisionGroup.setText("��Results of checking the Duplicate and the Collision");
		gridLayout = new GridLayout(1, false); 
		resultOfDuplicationAndCollisionGroup.setLayout(gridLayout);
		gridData = new GridData();
		gridData.verticalIndent = 30;
		gridData.horizontalIndent = 15;
		resultOfDuplicationAndCollisionGroup.setLayoutData(gridData); 
		resultOfDuplicationAndCollisionGroup.setVisible(false);
		
		resultOfDuplicationCheckLabel = new Label(resultOfDuplicationAndCollisionGroup, SWT.NONE);
		resultOfDuplicationCheckLabel.setFont(new Font(this.getParent().getDisplay(), "Miriam", 14, SWT.BOLD));
		resultOfDuplicationCheckLabel.setForeground(new Color(this.getParent().getDisplay(), 255, 50, 50));
		gridData = new GridData();
		gridData.widthHint = 250;
		gridData.verticalIndent = 5;
		gridData.horizontalIndent = 25;
		resultOfDuplicationCheckLabel.setLayoutData(gridData);
		
		resultOfCollisionCheckLabel = new Label(resultOfDuplicationAndCollisionGroup, SWT.NONE);
		resultOfCollisionCheckLabel.setFont(new Font(this.getParent().getDisplay(), "Miriam", 14, SWT.BOLD));
		resultOfCollisionCheckLabel.setForeground(new Color(this.getParent().getDisplay(), 50, 50, 255));
		gridData = new GridData();
		gridData.widthHint = 250;
		gridData.verticalIndent = 5;
		gridData.horizontalIndent = 25;
		resultOfCollisionCheckLabel.setLayoutData(gridData);
		
		//������ �׷�(�׷��� ǥ�ÿ�)
		canvasGroup = new Group(featureAnalysisResultGroup, SWT.SHADOW_ETCHED_IN);
		canvasGroup.setLayout(new GridLayout(1, false));
		gridData = new GridData();
		gridData.verticalAlignment = SWT.FILL;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		canvasGroup.setLayoutData(gridData);
	} //��ó ���� ��� ǥ�� �Լ� ��
	
	//��ư ����
	public void createButton(){
		Group buttonGroup = new Group(this, SWT.SHADOW_ETCHED_IN);
		GridLayout gridLayout = new GridLayout(4, false);
		buttonGroup.setLayout(gridLayout);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		buttonGroup.setLayoutData(gridData);
		
		initialButton = new Button(buttonGroup, SWT.PUSH);	//�ʱ�ȭ ��ư
		initialButton.setBackground(buttonColor);
		initialButton.setText("Initialize");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.RIGHT;
		gridData.grabExcessHorizontalSpace = true;
		initialButton.setLayoutData(gridData);

		cancelButton = new Button(buttonGroup, SWT.PUSH);	//��� ��ư
		cancelButton.setBackground(buttonColor);
		cancelButton.setText("Cancel");	
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.LEFT;
		gridData.grabExcessHorizontalSpace = true;
		cancelButton.setLayoutData(gridData);
	} //��ư ���� �Լ� ��
	
	//�̺�Ʈ ó���� ���
	public void mountEventHandler(){
		openFirstFeatureButton.addSelectionListener(this);
		openSecondFeatureButton.addSelectionListener(this);
		analyzeButton.addSelectionListener(this);
		initialButton.addSelectionListener(this);
		cancelButton.addSelectionListener(this);
	} //�̺�Ʈ ó���� ��� �Լ� ��
	
	//�̺�Ʈ ó����
	public void widgetSelected(SelectionEvent e){
		//ù��° ��ó ���� ��ư
		if(e.getSource() == openFirstFeatureButton){	
			new SelectFeatureDialog("First Feature");
		}
		//�ι�° ��ó ���� ��ư
		else if(e.getSource() == openSecondFeatureButton){	
			new SelectFeatureDialog("Second Feature");
		}
		//�м���ư
		else if(e.getSource() == analyzeButton){	
			if(openFirstFeatureText.getText().length() > 0 && openSecondFeatureText.getText().length() > 0){
				new AnalyzeFeature(this, openFirstFeatureText.getText(), openSecondFeatureText.getText());
			}else{
				
			}
		}
		//�ʱ�ȭ ��ư
		else if(e.getSource() == initialButton){	
			initializeWidget();
		}
		//��� ��ư
		else if(e.getSource() == cancelButton){	
			MainFrame.stackLayout.topControl = new Composite(MainFrame.shell, SWT.NONE);
			MainFrame.shell.layout();
		}
	} //�̺�Ʈ ó���� �Լ� ��
	public void widgetDefaultSelected(SelectionEvent e){}
	
	//�м� ���
	public void analyzedResult(String resultOfDuplicationCheck, String resultOfCollisionCheck, 
							   String resultOfSelectiveMapping, String resultOfConditionMapping, String resultOfMapping){
		
		percentageGroup.setVisible(true);
		resultOfDuplicationAndCollisionGroup.setVisible(true);
		resultOfDuplicationCheckLabel.setText(resultOfDuplicationCheck);
		resultOfCollisionCheckLabel.setText(resultOfCollisionCheck);
		
		selectiveMappingLabel.setText("Hit Ratio(%) of Selective attribute(1) : ");
		conditionMappingLabel.setText("Hit Ratio(%) of Condition attribute(1) : ");
		totalMappingLabel.setText("Hit Ratio(%) of Total attribute(11) : ");
		
		selectivePercentageLabel.setText(resultOfSelectiveMapping + "%");
		conditionPercentageLabel.setText(resultOfConditionMapping + "%");
		totalPercentageLabel.setText(resultOfMapping + "%");
		
		//�׷��� �׸���
		double valueOfMapping = Double.parseDouble(resultOfMapping);
		final int hitRatio = (int)valueOfMapping;

		canvasGroup.addPaintListener(new PaintListener() { 
			public void paintControl(PaintEvent e) { 
				//���׷���
				int commonAngle = (int)((360*hitRatio)/100);
				int variAngle = -(int)(360-commonAngle);

				//�� �ȳ� ��Ʈ��
				e.gc.setForeground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				e.gc.setFont(new Font(AnalyzeFeatureScreen.this.getParent().getDisplay(), "Miriam", 10, SWT.BOLD));
				e.gc.drawText("Similarity", 60, 33, true);
				e.gc.drawText("Variability", 60, 49, true);

				e.gc.setForeground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawText("0 / 100", 210, 150, true);
				e.gc.drawLine(190, 155, 205, 155);

				e.gc.setForeground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawText("25(%)", 125, 70, true);
				e.gc.drawLine(135, 83, 135, 98);

				e.gc.setForeground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawText("50", 45, 149, true);
				e.gc.drawLine(65, 154, 70, 154);

				e.gc.setForeground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawText("75", 128, 230, true);
				e.gc.drawLine(135, 210, 135, 225);

				//�� �ȳ� ǥ��
				e.gc.setBackground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_BLUE));
				e.gc.fillRectangle(20, 35, 30, 10);

				e.gc.setBackground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_RED));
				e.gc.fillRectangle(20, 50, 30, 10);

				//���缺, �����缺 ���̱�
				e.gc.setBackground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_BLUE));
				e.gc.fillArc(70, 90, 130, 130, 0, commonAngle);

				e.gc.setBackground(MainFrame.shell.getDisplay().getSystemColor(SWT.COLOR_RED));
				e.gc.fillArc(70, 90, 130, 130, 360, variAngle);
			} 
		}); 
		canvasGroup.redraw();
	} //�м� ��� �Լ� ��
	
	//�ʱ�ȭ
	public void initializeWidget(){
		openFirstFeatureText.setText("");
		openSecondFeatureText.setText("");
		resultOfDuplicationAndCollisionGroup.setVisible(false);
	} //�ʱ�ȭ �Լ� ��
}
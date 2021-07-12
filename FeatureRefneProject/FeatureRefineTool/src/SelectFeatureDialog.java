import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SelectFeatureDialog{
	private Shell featureSelectDialog = null;
	private Combo featureNameCombo = null;
	private GridLayout gridLayout = null;
	private Button applyButton = null;
	private Button cancelButton = null;
	private Color labelColor = null;
	private Color buttonColor = null;

	//������
	public SelectFeatureDialog(String titleName){
		this.initializeMember();
		this.createInputForm();
		this.setFeatureNameCombo();
		this.mountEventHandler();
		featureSelectDialog.setLayout(gridLayout);
		featureSelectDialog.setText(titleName);

		featureSelectDialog.pack();	//���̾�α� â ũ�� ����
		featureSelectDialog.open();	//���̾�α� ������

		Rectangle shellBounds = MainFrame.shell.getBounds();	//���� �簢�� ����
		Point dialogSize = featureSelectDialog.getSize();		//���̾�α� â�� ũ�� ����

		featureSelectDialog.setLocation(shellBounds.x + (shellBounds.width - dialogSize.x) / 2,		//�� �߾����� ǥ�� 
				shellBounds.y + (shellBounds.height - dialogSize.y) / 2);
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){
		featureSelectDialog = new Shell(MainFrame.shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);	//��� ���̾�α�
		gridLayout = new GridLayout(2, false);
		labelColor = new Color(MainFrame.shell.getDisplay(), 180,250,210);
		buttonColor = new Color(MainFrame.shell.getDisplay(), 70,210,210);
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//�Է� ��� ����
	public void createInputForm(){
		Group formGroup = new Group(featureSelectDialog, SWT.NONE);
		formGroup.setText("The Feature's Name you wish to select");
		GridLayout gridLayout = new GridLayout(2, false);
		GridData gridData = new GridData();	
		formGroup.setLayout(gridLayout);	
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		formGroup.setLayoutData(gridData);
		
		Label featureNameLabel = new Label(formGroup, SWT.BORDER);	//��ó
		featureNameLabel.setBackground(labelColor);
		featureNameLabel.setText("Feature Name:");
		gridData = new GridData();
		gridData.widthHint = 90;
		featureNameLabel.setLayoutData(gridData);
		featureNameCombo = new Combo(formGroup, SWT.BORDER);
		featureNameCombo.setText("Drafting");
		gridData = new GridData();
		gridData.widthHint = 90;
		gridData.horizontalAlignment = SWT.FILL;				
		gridData.grabExcessHorizontalSpace = true;
		featureNameCombo.setLayoutData(gridData);
		
		Group buttonGroup = new Group(featureSelectDialog, SWT.NONE);
		gridLayout = new GridLayout(2, false);
		gridData = new GridData();	
		buttonGroup.setLayout(gridLayout);	
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		buttonGroup.setLayoutData(gridData);

		applyButton = new Button(buttonGroup, SWT.NONE);	//���� ��ư
		applyButton.setText("Apply");
		applyButton.setBackground(buttonColor);
		gridData = new GridData();
		gridData.widthHint = 90;
		gridData.horizontalAlignment = SWT.CENTER;				
		gridData.grabExcessHorizontalSpace = true;
		applyButton.setLayoutData(gridData);

		cancelButton = new Button(buttonGroup, SWT.NONE);	//��� ��ư
		cancelButton.setText("Cancel");
		cancelButton.setBackground(buttonColor);
		gridData = new GridData();
		gridData.widthHint = 90;
		gridData.horizontalAlignment = SWT.CENTER;				
		gridData.grabExcessHorizontalSpace = true;
		cancelButton.setLayoutData(gridData);
	} //�Է� ��� ���� �Լ� ��
	
	//��ó ��� ����
	public void setFeatureNameCombo(){
		String[] featureList = MainFrame.featureQueries.getFeatureList();
		featureNameCombo.setItems(featureList);	
	} //��ó ��� ���� �Լ� ��
	
	//�̺�Ʈ ó����
	public void mountEventHandler(){
		//����
		applyButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				if((featureSelectDialog.getText()).equals("First Feature")){
					AnalyzeFeatureScreen.openFirstFeatureText.setText(featureNameCombo.getText());
				}else{
					AnalyzeFeatureScreen.openSecondFeatureText.setText(featureNameCombo.getText());
				}
				featureSelectDialog.dispose();
			}
		});
		//���
		cancelButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				featureSelectDialog.dispose();
			}
		});
	} //�̺�Ʈ ó���� �Լ� ��
}
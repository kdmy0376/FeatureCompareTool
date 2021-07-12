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

public class SearchFeatureDialog{
	private Shell featureSearchDialog = null;
	private Combo featureNameCombo = null;
	private GridLayout gridLayout = null;
	private Button searchButton = null;
	private Button cancelButton = null;
	private Color labelColor = null;
	private Color buttonColor = null;

	//������
	public SearchFeatureDialog(){
		initializeMember();
		featureSearchDialog.setLayout(gridLayout);
		featureSearchDialog.setText("Search Feature");
		this.createInputForm();
		this.setFeatureNameCombo();
		this.mountEventHandler();

		featureSearchDialog.pack();	//���̾�α� â ũ�� ����
		featureSearchDialog.open();	//���̾�α� ������

		Rectangle shellBounds = MainFrame.shell.getBounds();			//���� �簢�� ����
		Point dialogSize = featureSearchDialog.getSize();				//���̾�α� â�� ũ�� ����

		featureSearchDialog.setLocation(shellBounds.x + (shellBounds.width - dialogSize.x) / 2,		//�� �߾����� ǥ�� 
										shellBounds.y + (shellBounds.height - dialogSize.y) / 2);
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){
		featureSearchDialog = new Shell(MainFrame.shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);	//��� ���̾�α�
		gridLayout = new GridLayout(2, false);
		labelColor = new Color(MainFrame.shell.getDisplay(), 200,250,210);
		buttonColor = new Color(MainFrame.shell.getDisplay(), 70,210,210);
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//�Է� ��� ����
	public void createInputForm(){
		Group formGroup = new Group(featureSearchDialog, SWT.NONE);
		formGroup.setText("The Feature's Name you wish to search for");
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
		gridData = new GridData();
		gridData.widthHint = 90;
		gridData.horizontalAlignment = SWT.FILL;				
		gridData.grabExcessHorizontalSpace = true;
		featureNameCombo.setLayoutData(gridData);
		
		Group buttonGroup = new Group(featureSearchDialog, SWT.NONE);
		gridLayout = new GridLayout(2, false);
		gridData = new GridData();	
		buttonGroup.setLayout(gridLayout);	
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		buttonGroup.setLayoutData(gridData);

		searchButton = new Button(buttonGroup, SWT.NONE);	//�˻� ��ư
		searchButton.setText("Search");
		searchButton.setBackground(buttonColor);
		gridData = new GridData();
		gridData.widthHint = 90;
		gridData.horizontalAlignment = SWT.CENTER;				
		gridData.grabExcessHorizontalSpace = true;
		searchButton.setLayoutData(gridData);

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
		//�˻�
		searchButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				SearchFeatureScreen searchFeatureScreen = new SearchFeatureScreen();
				searchFeatureScreen.createWidgetValue(featureNameCombo.getText());
				featureSearchDialog.dispose();

				MainFrame.stackLayout.topControl = searchFeatureScreen;
				MainFrame.shell.layout();
			}
		});
		//���
		cancelButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				featureSearchDialog.dispose();
			}
		});
	} //�̺�Ʈ ó���� �Լ� ��
}
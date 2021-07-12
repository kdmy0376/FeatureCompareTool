package Search;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import DataBase.DBWork;
import Main.MainFrame;
import WidgetProperty.WidgetProperty;

public class SearchServiceDialog{
	private Shell serviceSearchDialog = null;
	private GridLayout gridLayout = null;
	private Combo companyNameCombo = null;
	private Combo serviceNameCombo = null;
	private Button searchButton = null;
	private Button cancelButton = null;
	private Color labelColor = null;
	private Color buttonColor = null;

	//������
	public SearchServiceDialog(){
		this.initializeMember();
		this.serviceSearchDialog.setLayout(gridLayout);
		this.serviceSearchDialog.setText("Search Service");
		this.createInputForm();
		this.setCombo();
		this.mountEventHandler();
		
		this.serviceSearchDialog.pack();	//���̾�α� â ũ�� ����
		this.serviceSearchDialog.open();	//���̾�α� ������

		Rectangle shellBounds = MainFrame.shell.getBounds();	//���� �簢�� ����
		Point dialogSize = this.serviceSearchDialog.getSize();	//���̾�α� â�� ũ�� ����

		this.serviceSearchDialog.setLocation(shellBounds.x + (shellBounds.width - dialogSize.x) / 2,	//�� �߾����� ǥ�� 
									         shellBounds.y + (shellBounds.height - dialogSize.y) / 2);
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){
		serviceSearchDialog = new Shell(MainFrame.shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);	//��� ���̾�α�
		gridLayout = new GridLayout(2, false);
		labelColor = new Color(MainFrame.shell.getDisplay(), 180,230,250);
		buttonColor = new Color(MainFrame.shell.getDisplay(), 70,210,210);
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//�Է� ��� ����
	public void createInputForm(){
		Group formGroup = new Group(serviceSearchDialog, SWT.NONE);
		formGroup.setText("The Service's Name you wish to search for");
		GridLayout gridLayout = new GridLayout(2, false);
		formGroup.setLayout(gridLayout);	
		formGroup.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(2, SWT.FILL));
		
		Label companyNameLabel = new Label(formGroup, SWT.BORDER);	//ȸ��
		companyNameLabel.setBackground(labelColor);
		companyNameLabel.setText("Company Name:");
		companyNameLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(90));
		companyNameCombo = new Combo(formGroup, SWT.BORDER);	
		companyNameCombo.setLayoutData(WidgetProperty.setGridLayoutProperty(1, 90, SWT.FILL, true));

		Label serviceNameLabel = new Label(formGroup, SWT.BORDER);	//����
		serviceNameLabel.setBackground(labelColor);
		serviceNameLabel.setText("Service Name:");
		serviceNameLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(90));
		serviceNameCombo = new Combo(formGroup, SWT.BORDER);  
		serviceNameCombo.setLayoutData(WidgetProperty.setGridLayoutProperty(1, 90, SWT.FILL, true));
		serviceNameCombo.setEnabled(false);
		
		Group buttonGroup = new Group(serviceSearchDialog, SWT.NONE);
		gridLayout = new GridLayout(2, false);
		buttonGroup.setLayout(gridLayout);	
		buttonGroup.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(2, SWT.FILL));

		searchButton = new Button(buttonGroup, SWT.NONE);	//�˻� ��ư
		searchButton.setText("Search");
		searchButton.setBackground(buttonColor);
		searchButton.setLayoutData(WidgetProperty.setGridLayoutProperty(1, 90, SWT.CENTER, true));

		cancelButton = new Button(buttonGroup, SWT.NONE);	//��� ��ư
		cancelButton.setText("Cancel");
		cancelButton.setBackground(buttonColor);
		cancelButton.setLayoutData(WidgetProperty.setGridLayoutProperty(1, 90, SWT.CENTER, true));
	} //�Է� ��� ���� �Լ� ��
	
	//ȸ�� ��� ����
	public void setCombo(){
		String[] companyList = DBWork.setCompanyNameComboList();	//ȸ�� ���
		companyNameCombo.setItems(companyList);	
	} //ȸ�� ��� ���� �Լ� ��
	
	//�̺�Ʈ ó����
	public void mountEventHandler(){
		//�˻� ��ư
		searchButton.addSelectionListener(new SelectionAdapter(){	
			public void widgetSelected(SelectionEvent selectionEvent){	
				SearchServiceScreen searchServiceScreen = new SearchServiceScreen();
				searchServiceScreen.createWidgetValue(companyNameCombo.getText(), serviceNameCombo.getText());
				serviceSearchDialog.dispose();
				
				MainFrame.stackLayout.topControl = searchServiceScreen;
				MainFrame.shell.layout();
			}
		});
		//��� ��ư
		cancelButton.addSelectionListener(new SelectionAdapter(){			
			public void widgetSelected(SelectionEvent selectionEvent){
				serviceSearchDialog.dispose();
			}
		});
		//ȸ�� �޺�
		companyNameCombo.addSelectionListener(new SelectionAdapter(){			
			public void widgetSelected(SelectionEvent selectionEvent){
				serviceNameCombo.setEnabled(true);
				String[] serviceList = DBWork.setParentServiceNameComboList(companyNameCombo.getText());
				serviceNameCombo.setItems(serviceList);
			}
		});
	} //�̺�Ʈ ó���� �Լ� ��
}
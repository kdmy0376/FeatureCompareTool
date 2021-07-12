package Menu_Delete;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import DataBase.DBWork;
import Main.MainFrame;
import WidgetProperty.WidgetProperty;

public class DeleteScreen extends Composite implements SelectionListener{
	private GridLayout gridLayout = null;
	private Color buttonColor = null;
	private Color tableCellColor = null;
	private Font groupFont = null;
	private Group treeGroup = null;
	private Button selectAllTreeItemButton = null;
	private Tree deleteTree = null;
	private TreeColumn deleteTreeColumn = null;
	private Group tableGroup = null;
	private Button selectAllTableItemButton = null;
	private Table deleteTable = null;
	private TableColumn tableColumn = null;
	private Button viewBriefButton = null;
	private Button viewDetailsButton = null;
	private Group buttonGroup = null;
	private Button initializeButton = null;
	private Button cancelButton = null;

	//������
	public DeleteScreen(){
		super(MainFrame.shell, SWT.NONE);
		this.initializeMember();
		this.setLayout(gridLayout);
		this.createTitleLabel();
		this.createTree();				
		this.createBriefButton(); 		
		this.createTable();
		this.createViewDetailButton();
		this.createButton();
		this.mountEventHandler();
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){
		gridLayout = new GridLayout(5, false);
		buttonColor = new Color(this.getParent().getDisplay(), 70,210,210);
		tableCellColor = new Color(this.getParent().getDisplay(), 180,230,240);
		groupFont = new Font(this.getParent().getDisplay(), "Miriam", 10, SWT.BOLD);
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//�����̺� ����
	public void createTitleLabel(){
		Label titleLabel = new Label(this, SWT.BORDER);
		titleLabel.setText("�� Delete Services ��");
		titleLabel.setFont(new Font(this.getParent().getDisplay(), "Miriam", 15, SWT.BOLD));
		titleLabel.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(5, SWT.FILL));
	} //�����̺� ���� �Լ� ��
	
	//Ʈ�� ����
	public void createTree(){
		treeGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);	//Ʈ�� �׷� ����
		treeGroup.setText("Tree Classification");	
		treeGroup.setFont(groupFont);
		treeGroup.setLayout(new GridLayout(1, false));
		treeGroup.setLayoutData(WidgetProperty.setGridLayoutNotGrabVerticalProperty(1, SWT.FILL));

		selectAllTreeItemButton = new Button(treeGroup, SWT.CHECK);	//Ʈ�� ��ü ���� ��ư
		selectAllTreeItemButton.setText("Select All");
		deleteTree = new Tree(treeGroup, SWT.BORDER | SWT.V_SCROLL | SWT.CHECK);	//Ʈ�� ����
		deleteTree.setLinesVisible(true);	
		deleteTree.setHeaderVisible(true);
		deleteTreeColumn = new TreeColumn(deleteTree, SWT.LEFT);
		deleteTreeColumn.setWidth(130); 
		deleteTreeColumn.setResizable(false);
		deleteTreeColumn.setText("Companies / Services");
		deleteTree.setLayoutData(WidgetProperty.setGridLayoutVerticalProperty(1, 450, SWT.FILL, true));
		String[] companyNameTree = DBWork.setCompanyNameComboList();	//ȸ�� ����Ʈ�� ������
		int companyNumber = companyNameTree.length;	//ȸ���� ����
		for(int index = 0; index < companyNumber; index++){	//ȸ�� �̸�(���̺� ��)
			TreeItem companyTreeItem = new TreeItem(deleteTree, SWT.NONE);	//ȸ���̸� Ʈ�� ������ ���� 
			companyTreeItem.setText(companyNameTree[index]);
			String[] serviceNameTree = DBWork.setParentServiceNameComboList(companyNameTree[index]);
			int serviceNumber = serviceNameTree.length;	//���� ����
			for(int j = 0; j < serviceNumber; j++){	//���� ���� ���
				TreeItem serviceTreeItem = new TreeItem(companyTreeItem, SWT.NONE);
				serviceTreeItem.setText(serviceNameTree[j]);
			}			
		}
		Label explainTreeLabel = new Label(treeGroup, SWT.NONE);
		explainTreeLabel.setText("�� Registered Services List");
		explainTreeLabel.setLayoutData(WidgetProperty.setGridLayoutAlignProperty(SWT.CENTER));
	} //Ʈ�� ���� �Լ� ��
	
	//���̺� ����
	public void createTable(){
		tableGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);	//���̺� �׷� ����
		tableGroup.setText("Service Attribute Table");	
		tableGroup.setFont(groupFont);
		tableGroup.setLayout(new GridLayout(2, false));	
		tableGroup.setLayoutData(WidgetProperty.setGridLayoutProperty(3, SWT.FILL, true));

		selectAllTableItemButton = new Button(tableGroup, SWT.CHECK);	//���̺� ��ü ���� ��ư
		selectAllTableItemButton.setText("Select All");
		deleteTable = new Table(tableGroup, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);	//���̺� ����
		deleteTable.setLayoutData(WidgetProperty.setGridLayoutVerticalProperty(2, 440, SWT.FILL, true)); 

		String[] tableColumnList = {"Company", "Name", "DomainTask", "Stype"};	//���̺� �÷� 4�� ����
		for(int index = 0; index < tableColumnList.length; index++){
			tableColumn = new TableColumn(deleteTable, SWT.LEFT); 
			tableColumn.setText(tableColumnList[index]);
			if(index == 3){
				tableColumn.setWidth(100);	//�÷� ������ ����
			}else{
				tableColumn.setWidth(135);	//�÷� ������ ����
			}			
		} 
		deleteTable.setHeaderVisible(true);	//���̺� �÷� ���̱� 
		deleteTable.setLinesVisible(true);	//���̺� ���� ���̱�
		Label explainTableLabel = new Label(tableGroup, SWT.NONE);
		explainTableLabel.setText("�� Selected Services's Properties Table List ");
		explainTableLabel.setLayoutData(WidgetProperty.setGridLayoutAlignProperty(SWT.LEFT));
	} //���̺� ���� �Լ� ��
	
	//����ư ����
	public void createBriefButton(){
		viewBriefButton = new Button(this, SWT.PUSH); //����ư	 
		viewBriefButton.setBackground(buttonColor);
		viewBriefButton.setText("��");
		viewBriefButton.setSize(50,50);
		viewBriefButton.setLayoutData(WidgetProperty.setGridLayoutVerticalProperty(1, 50));
	} //����ư ���� �Լ� ��
	
	//�����ڼ��� ���� ��ư ����
	public void createViewDetailButton(){
		viewDetailsButton = new Button(tableGroup, SWT.NONE);
		viewDetailsButton.setBackground(buttonColor);
		viewDetailsButton.setText("View Service Details ����");
		viewDetailsButton.setSize(50,50);
		viewDetailsButton.setLayoutData(WidgetProperty.setGridLayoutAlignProperty(SWT.RIGHT));
	} //�����ڼ��� ���� ��ư ���� �Լ� ��
	
	//��ư ����
	public void createButton(){
		buttonGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
		buttonGroup.setLayout(new GridLayout(4, false));
		buttonGroup.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(5, SWT.FILL));

		initializeButton = new Button(buttonGroup, SWT.PUSH); 
		initializeButton.setBackground(buttonColor);
		initializeButton.setText("Initialize");
		initializeButton.setLayoutData(WidgetProperty.setGridLayoutProperty(2, 80, SWT.RIGHT, true));

		cancelButton = new Button(buttonGroup, SWT.PUSH);
		cancelButton.setBackground(buttonColor);
		cancelButton.setText("Cancel");
		cancelButton.setLayoutData(WidgetProperty.setGridLayoutProperty(2, 80, SWT.LEFT, true));	
	} //��ư ���� �Լ� ��
	
	//�̺�Ʈ ó���� ���
	public void mountEventHandler(){
		deleteTree.addSelectionListener(this);
		selectAllTreeItemButton.addSelectionListener(this);
		selectAllTableItemButton.addSelectionListener(this);
		viewBriefButton.addSelectionListener(this);
		viewDetailsButton.addSelectionListener(this);
		initializeButton.addSelectionListener(this);
		cancelButton.addSelectionListener(this);
	} //�̺�Ʈ ó���� ��� �Լ� ��
	
	//�̺�Ʈ ó����
	public void widgetSelected(SelectionEvent e){
		//Ʈ�� ��ü ���� üũ ��ư
		if(e.getSource() == selectAllTreeItemButton){	
			Button button = (Button)e.widget;
			TreeItem[] companyTreeItem = deleteTree.getItems();
			if (button.getSelection()){		            
				for(int i=0; i<companyTreeItem.length; i++){
					companyTreeItem[i].setChecked(true);
					TreeItem[] serviceTreeItem = companyTreeItem[i].getItems();
					for(int j=0; j<serviceTreeItem.length; j++){
						serviceTreeItem[j].setChecked(true);
					}
				}
			}else{
				for(int i=0; i<companyTreeItem.length; i++){
					companyTreeItem[i].setChecked(false);
					TreeItem[] serviceTreeItem = companyTreeItem[i].getItems();
					for(int j=0; j<serviceTreeItem.length; j++){
						serviceTreeItem[j].setChecked(false);
					}
				}
			}
		}
		//����ư
		else if(e.getSource() == viewBriefButton){	
			TreeItem[] companyTreeItemList = deleteTree.getItems();	//ȸ�� Ʈ�� ������ ����
			for(int i=0; i < companyTreeItemList.length; i++){
				TreeItem[] serviceTreeItemList = companyTreeItemList[i].getItems();	//ȸ�� ���� ���񽺵� ����
				for(int j=0; j < serviceTreeItemList.length; j++){
					if(serviceTreeItemList[j].getChecked()){
						String companyNameString = companyTreeItemList[i].getText();	//ȸ�� �̸�(�θ� Ʈ�� ���)
						String serviceNameString = serviceTreeItemList[j].getText();	//���� �̸�
						String[] tablePropertyList = DBWork.getTableProperty(companyNameString, serviceNameString);
						TableItem tableItem = new TableItem(deleteTable, SWT.NONE);	//���̺� ������ ����
						tableItem.setBackground(0, tableCellColor);
						for(int columnIndex = 0; columnIndex < 4; columnIndex++){	//���̺� Į�� ũ�⸸ŭ �ؽ�Ʈ ����
							tableItem.setText(columnIndex, tablePropertyList[columnIndex]);
						}
					}
				}
			}
		}
		//�����ڼ��� ���� ��ư
		else if(e.getSource() == viewDetailsButton){	
			TableItem[] tableItem = deleteTable.getItems();
			int numberOfCheckedItem = 0;
			for(int i=0; i<tableItem.length; i++){
				if(tableItem[i].getChecked()){	//���̺� �������� üũ �����̸�
					numberOfCheckedItem++;
				}
			}
			DeleteDetailScreen deleteDetailScreen = new DeleteDetailScreen();
			for(int i=0; i<tableItem.length; i++){
				if(tableItem[i].getChecked()){	//���̺� �������� üũ �����̸�
					deleteDetailScreen.createTabItem(tableItem[i].getText(1), tableItem[i].getText(0));	//����, ȸ��
				}
			}
			if(numberOfCheckedItem != 0){
				MainFrame.stackLayout.topControl = deleteDetailScreen;
				MainFrame.shell.layout();
			}
		}
		//���̺� ��ü ���� üũ ��ư
		else if(e.getSource() == selectAllTableItemButton){	
			Button button = (Button) e.widget;
			TableItem[] tableItem = deleteTable.getItems();
			if (button.getSelection()){		            
				for(int i=0; i<tableItem.length; i++){
					tableItem[i].setChecked(true);
				}
			}else{
				for(int i=0; i<tableItem.length; i++){
					tableItem[i].setChecked(false);
				}
			}
		}
		//Ʈ��
		else if(e.getSource() == deleteTree){	
			if(e.detail == SWT.CHECK){
				TreeItem item = (TreeItem)e.item;
				boolean checked = item.getChecked();
				checkItems(item, checked);
				checkPath(item.getParentItem(), checked, false);
			}
		}
		//��� ��ư
		else if(e.getSource() == cancelButton){	
			MainFrame.stackLayout.topControl = new Composite(MainFrame.shell, SWT.NONE);
			MainFrame.shell.layout();
		}
		//�ʱ�ȭ ��ư
		else if(e.getSource() == initializeButton){	
			selectAllTreeItemButton.setSelection(false);
			selectAllTableItemButton.setSelection(false);
			TreeItem[] treeItem = deleteTree.getItems();
			for(int i=0; i<treeItem.length; i++){
				if(treeItem[i].getChecked()){
					treeItem[i].setChecked(false); 
				}
				if(treeItem[i].getExpanded()){
					treeItem[i].setExpanded(false);
				}
				if(treeItem[i].getItemCount() > 0){
					for(int j=0; j<treeItem[i].getItems().length; j++){
						if(treeItem[i].getItems()[j].getChecked()){
							treeItem[i].getItems()[j].setChecked(false);
						}
					}
				}
			}
			deleteTable.removeAll();
		}
	}
	public void widgetDefaultSelected(SelectionEvent e){}
	
	//Ʈ�� üũ ��ƾ
	///////////////////////////////////////////////////////////////////////////////////////
	private void checkPath(TreeItem item, boolean checked, boolean grayed){
		if(item == null){
			return;
		}
		if(grayed){
			checked = true;
		}else{
			int index = 0;
			TreeItem[] items = item.getItems();
			while(index < items.length){
				TreeItem child = items[index];
				if(child.getGrayed() || checked != child.getChecked()){
					checked = grayed = true;
					break;
				}
				index++;
			}
		}
		item.setChecked(checked);
		item.setGrayed(grayed);
		checkPath(item.getParentItem(), checked, grayed);
	}
	private void checkItems(TreeItem item, boolean checked){
		item.setGrayed(false);
		item.setChecked(checked);
		TreeItem[] items = item.getItems();
		for(int i = 0; i < items.length; i++){
			checkItems(items[i], checked);
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////
	//Ʈ�� üũ ��ƾ �Լ� ��
}
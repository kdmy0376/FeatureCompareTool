package MenuControl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

import Main.MainFrame;
import Menu_Delete.DeleteScreen;
import Menu_Register.RegisterScreen;
import Menu_Update.UpdateScreen;
import Search.SearchServiceDialog;
import SimilarityCheck.SimilarityCheckScreen;

public class ToolMenu implements SelectionListener{	
	private Menu menuBar = null;
	
	//File �޴� ����
	private MenuItem cascadeFileMenu = null;			
	private Menu fileMenu = null;
	private MenuItem openFirstServiceItem = null;
	private MenuItem openSecondServiceItem = null;
	private MenuItem searchServiceItem = null;
	private MenuItem exitItem = null;
	
	//Edit �޴� ����
	private MenuItem cascadeEditMenu = null;			
	private Menu editMenu = null;
	private MenuItem registerItem = null;
	private MenuItem updateItem = null;
	private MenuItem deleteItem = null;	
	
	//Tool �޴� ����
	private MenuItem cascadeToolMenu = null;			
	private Menu toolMenu = null;
	private MenuItem similarityCheckItem = null;
	
	//Help �޴� ����
	private MenuItem cascadeHelpMenu = null;			
	private Menu helpMenu = null;
	private MenuItem informationItem = null;
	
    private SimilarityCheckScreen similarityCheckScreen = null;
	
	//������
	public ToolMenu(){
		this.initializeMember();
		this.setFileMenu(menuBar);	
		this.setEditMenu(menuBar);	
		this.setToolMenu(menuBar);	
		this.setHelpMenu(menuBar);
		this.setEventHandler();
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){				
		menuBar = new Menu(MainFrame.shell, SWT.BAR);	
		similarityCheckScreen = new SimilarityCheckScreen();
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//File �޴� ����
	public void setFileMenu(Menu menuBar){
		cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");

		fileMenu = new Menu(MainFrame.shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);
		openFirstServiceItem = new MenuItem(fileMenu, SWT.PUSH); 
		openFirstServiceItem.setText("Open F&irst Service");
		openSecondServiceItem = new MenuItem(fileMenu, SWT.PUSH);
		openSecondServiceItem.setText("&Open S&econd Service");
		searchServiceItem = new MenuItem(fileMenu, SWT.PUSH);
		searchServiceItem.setText("&Service Search");
		exitItem = new MenuItem(fileMenu, SWT.PUSH);
		exitItem.setText("&Exit");
	} //File �޴� ���� �Լ� ��
	
	//Edit �޴� ����
	public void setEditMenu(Menu menuBar){
		cascadeEditMenu = new MenuItem(menuBar, SWT.CASCADE); 
		cascadeEditMenu.setText("&Edit");

		editMenu = new Menu(MainFrame.shell, SWT.DROP_DOWN);
		cascadeEditMenu.setMenu(editMenu);		
		registerItem = new MenuItem(editMenu, SWT.PUSH);
		registerItem.setText("&Register Service");
		updateItem = new MenuItem(editMenu, SWT.PUSH);
		updateItem.setText("&Update Service");
		deleteItem = new MenuItem(editMenu, SWT.PUSH);
		deleteItem.setText("&Delete Service");
	} //Edit �޴� ���� �Լ� ��
	
	//Tool �޴� ����
	public void setToolMenu(Menu menuBar){
		cascadeToolMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeToolMenu.setText("&Tool");

		toolMenu = new Menu(MainFrame.shell, SWT.DROP_DOWN);
		cascadeToolMenu.setMenu(toolMenu);
		similarityCheckItem = new MenuItem(toolMenu, SWT.PUSH);
		similarityCheckItem.setText("Similarity Check");
	} //Tool �޴� ���� �Լ� ��
	
	//Help �޴� ����
	public void setHelpMenu(Menu menuBar){
		cascadeHelpMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeHelpMenu.setText("&Help");

		helpMenu = new Menu(MainFrame.shell, SWT.DROP_DOWN);
		cascadeHelpMenu.setMenu(helpMenu);
		informationItem = new MenuItem(helpMenu, SWT.PUSH);
		informationItem.setText("Information");
		
		MainFrame.shell.setMenuBar(menuBar);
	} //Help �޴� ���� �Լ� ��
	
	//�̺�Ʈ ó���� ���
	public void setEventHandler(){
		openFirstServiceItem.addSelectionListener(this);
		openSecondServiceItem.addSelectionListener(this);
		searchServiceItem.addSelectionListener(this);
		exitItem.addSelectionListener(this);
		registerItem.addSelectionListener(this);
		updateItem.addSelectionListener(this);
		deleteItem.addSelectionListener(this);
		similarityCheckItem.addSelectionListener(this);
		informationItem.addSelectionListener(this);
	} //�̺�Ʈ ó���� ��� �Լ� ��
	
	//�̺�Ʈ ó����
	public void widgetSelected(SelectionEvent e){
		//ù��° ���� ����
		if(e.getSource() == openFirstServiceItem){
			MainFrame.stackLayout.topControl = similarityCheckScreen;
			MainFrame.shell.layout();
			new SelectServiceDialog("First Service");
		}
		//�ι�° ���� ����
		else if(e.getSource() == openSecondServiceItem){
			MainFrame.stackLayout.topControl = similarityCheckScreen;
			MainFrame.shell.layout();
			new SelectServiceDialog("Second Service");
		}
		//���� �˻�
		else if(e.getSource() == searchServiceItem){
			MainFrame.stackLayout.topControl = new Composite(MainFrame.shell, SWT.NONE);
			MainFrame.shell.layout();
			new SearchServiceDialog();
		}
		//���α׷� ����
		else if(e.getSource() == exitItem){
			MainFrame.shell.getDisplay().dispose();
		}
		//���� ���
		else if(e.getSource() == registerItem){
			MainFrame.stackLayout.topControl = new RegisterScreen();        		
			MainFrame.shell.layout();
		}
		//���� ����
		else if(e.getSource() == updateItem){
			MainFrame.stackLayout.topControl = new UpdateScreen();
			MainFrame.shell.layout();
		}
		//���� ����
		else if(e.getSource() == deleteItem){
			MainFrame.stackLayout.topControl = new DeleteScreen();
			MainFrame.shell.layout();
		}
		//���񽺵� �� ���缺 ����
		else if(e.getSource() == similarityCheckItem){
			MainFrame.stackLayout.topControl = similarityCheckScreen;
			MainFrame.shell.layout();
		}
		//���α׷� ����
		else if(e.getSource() == informationItem){
			MessageBox informationMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			informationMessageBox.setText("Information");
			informationMessageBox.setMessage("Semantic similarity analyser of service");
			informationMessageBox.open();
		}
	} //�̺�Ʈ ó���� �Լ� ��
	public void widgetDefaultSelected(SelectionEvent e){}		
}
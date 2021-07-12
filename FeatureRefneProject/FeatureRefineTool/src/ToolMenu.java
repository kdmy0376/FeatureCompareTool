import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;

public class ToolMenu implements SelectionListener{	
	private Menu menuBar = null;
	
	//File �޴� ����
	private MenuItem cascadeFileMenu = null;			
	private Menu fileMenu = null;
	private MenuItem openFirstFeatureItem = null;
	private MenuItem openSecondFeatureItem = null;
	private MenuItem searchFeatureItem = null;
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
	private MenuItem checkFeatureItem = null;
	
	//Help �޴� ����
	private MenuItem cascadeHelpMenu = null;			
	private Menu helpMenu = null;
	private MenuItem informationItem = null;
	
    private AnalyzeFeatureScreen analyzeFeatureScreen = null;
    
	//������
	public ToolMenu(){
		this.initializeMember();
		this.setFileMenu(menuBar);	
		this.setEditMenu(menuBar);	
		this.setToolMenu(menuBar);	
		this.setHelpMenu(menuBar);
		this.mountEventHandler();
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){				
		menuBar = new Menu(MainFrame.shell, SWT.BAR);	
		analyzeFeatureScreen = new AnalyzeFeatureScreen();
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//File �޴� ����
	public void setFileMenu(Menu menuBar){
		cascadeFileMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeFileMenu.setText("&File");

		fileMenu = new Menu(MainFrame.shell, SWT.DROP_DOWN);
		cascadeFileMenu.setMenu(fileMenu);
		openFirstFeatureItem = new MenuItem(fileMenu, SWT.PUSH); 
		openFirstFeatureItem.setText("Open F&irst Feature");
		openSecondFeatureItem = new MenuItem(fileMenu, SWT.PUSH);
		openSecondFeatureItem.setText("&Open S&econd Feature");
		searchFeatureItem = new MenuItem(fileMenu, SWT.PUSH);
		searchFeatureItem.setText("Search &Feature");
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
		registerItem.setText("&Register Feature");
		updateItem = new MenuItem(editMenu, SWT.PUSH);
		updateItem.setText("&Update Feature");
		deleteItem = new MenuItem(editMenu, SWT.PUSH);
		deleteItem.setText("&Delete Feature");
	} //Edit �޴� ���� �Լ� ��
	
	//Tool �޴� ����
	public void setToolMenu(Menu menuBar){
		cascadeToolMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeToolMenu.setText("&Tool");

		toolMenu = new Menu(MainFrame.shell, SWT.DROP_DOWN);
		cascadeToolMenu.setMenu(toolMenu);
		checkFeatureItem = new MenuItem(toolMenu, SWT.PUSH);
		checkFeatureItem.setText("Analyze Feature");
	} //Tool �޴� ���� �Լ� ��
	
	//Help �޴� ����
	public void setHelpMenu(Menu menuBar){
		cascadeHelpMenu = new MenuItem(menuBar, SWT.CASCADE);
		cascadeHelpMenu.setText("&Help");

		helpMenu = new Menu(MainFrame.shell, SWT.DROP_DOWN);
		cascadeHelpMenu.setMenu(helpMenu);
		informationItem = new MenuItem(helpMenu, SWT.PUSH);
		informationItem.setText("Information...");
		
		MainFrame.shell.setMenuBar(menuBar);
	} //Help �޴� ���� �Լ� ��
	
	//�̺�Ʈ ó���� ���
	public void mountEventHandler(){
		openFirstFeatureItem.addSelectionListener(this);
		openSecondFeatureItem.addSelectionListener(this);
		searchFeatureItem.addSelectionListener(this);
		exitItem.addSelectionListener(this);
		registerItem.addSelectionListener(this);
		updateItem.addSelectionListener(this);
		deleteItem.addSelectionListener(this);
		checkFeatureItem.addSelectionListener(this);
		informationItem.addSelectionListener(this);
	} //�̺�Ʈ ó���� ��� �Լ� ��
	
	//�̺�Ʈ ó����
	public void widgetSelected(SelectionEvent e){
		//ù��° ��ó ����
		if(e.getSource() == openFirstFeatureItem){
			MainFrame.stackLayout.topControl = analyzeFeatureScreen;
			MainFrame.shell.layout();
			new SelectFeatureDialog("First Service");
		}
		//�ι�° ��ó ����
		else if(e.getSource() == openSecondFeatureItem){
			MainFrame.stackLayout.topControl = analyzeFeatureScreen;
			MainFrame.shell.layout();
			new SelectFeatureDialog("Second Service");
		}
		//��ó �˻�
		else if(e.getSource() == searchFeatureItem){
			MainFrame.stackLayout.topControl = new Composite(MainFrame.shell, SWT.NONE);
			MainFrame.shell.layout();
			new SearchFeatureDialog();
		}
		//���α׷� ����
		else if(e.getSource() == exitItem){
			MainFrame.shell.getDisplay().dispose();
		}
		//��ó ���
		else if(e.getSource() == registerItem){
			MainFrame.stackLayout.topControl = new RegisterScreen();        		
			MainFrame.shell.layout();
		}
		//��ó ����
		else if(e.getSource() == updateItem){}
		//��ó ����
		else if(e.getSource() == deleteItem){}
		//��ó�� �� �м�
		else if(e.getSource() == checkFeatureItem){
			MainFrame.stackLayout.topControl = analyzeFeatureScreen;
			MainFrame.shell.layout();
		}
		//���α׷� ����
		else if(e.getSource() == informationItem){
			MessageBox informationMessageBox = new MessageBox(MainFrame.shell, SWT.ICON_INFORMATION);
			informationMessageBox.setText("Information");
			informationMessageBox.setMessage("Feature Refinement Analyzer");
			informationMessageBox.open();
		}
	} //�̺�Ʈ ó���� �Լ� ��
	public void widgetDefaultSelected(SelectionEvent e){}		
}
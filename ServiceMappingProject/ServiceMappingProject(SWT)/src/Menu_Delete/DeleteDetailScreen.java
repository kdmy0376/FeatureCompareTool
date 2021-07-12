package Menu_Delete;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import Main.MainFrame;

public class DeleteDetailScreen extends Composite{
	static TabFolder tabFolder = null;
	private FillLayout fillLayout = null;	
	
	//������
	public DeleteDetailScreen(){
		super(MainFrame.shell, SWT.NONE);
		initializeMember();
		this.setLayout(fillLayout);
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(){
		tabFolder = new TabFolder(this, SWT.TOP);
		fillLayout = new FillLayout();		
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//�� ������ ����
	public void createTabItem(String service, String company){
		TabItem tabItem = new TabItem(tabFolder, SWT.NULL);
		tabItem.setText(service + "(" + company + ")");	//����(ȸ��)
		DeleteServiceForm deleteServiceForm = new DeleteServiceForm();
		deleteServiceForm.createWidgetValue(service, company);
		tabItem.setControl(deleteServiceForm);
	} //�� ������ ���� �Լ� ��
}
package Menu_Update;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import Main.MainFrame;

public class UpdateDetailScreen extends Composite{
	static TabFolder tabFolder = null;
	private FillLayout fillLayout = null;	
	
	//������
	public UpdateDetailScreen(){
		super(MainFrame.shell, SWT.NONE);
		this.initializeMember();
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
		UpdateServiceForm updateServiceForm = new UpdateServiceForm();
		updateServiceForm.createWidgetValue(service, company);
		tabItem.setControl(updateServiceForm);
	} //�� ������ ���� �Լ� ��
}
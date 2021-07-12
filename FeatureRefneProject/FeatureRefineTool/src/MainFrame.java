import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;

public class MainFrame {
	static Shell shell = null;
	static StackLayout stackLayout = null;
	static FeatureQueries featureQueries = null;

	//������
	public MainFrame(Display display){			
		this.initializeMember(display);							//������� �ʱ�ȭ	
		new ToolMenu();										//�޴� ����
		shell.setLayout(stackLayout);						//��ü ���̾ƿ� ����
		shell.setSize(710, 530);							//�� ������
		shell.setText("Feature Refinement Analyzer");		//Ÿ��Ʋ ����

		int x = (display.getBounds().width - shell.getSize().x) / 2;
		int y = (display.getBounds().height - shell.getSize().y) / 2;	
		shell.setLocation(x,y);								//�� ��ġ ����
		shell.open(); 				

		while (!shell.isDisposed()){			//���� ������� �ʾ�����
			if (!display.readAndDispatch()) {
				display.sleep();				//ȭ���� ������� �ʰ� ����
			}
		}
	} //������ ��
	
	//��� ���� �ʱ�ȭ
	public void initializeMember(Display display){
		shell = new Shell(display);
		featureQueries = new FeatureQueries();	//�����ͺ��̽� ����
		stackLayout = new StackLayout();		//��ü ���̾ƿ�
	} //��� ���� �ʱ�ȭ �Լ� ��
	
	//Main
	public static void main(String[] args) {
		Display display = new Display();	//�⺻���� ���÷���
		new MainFrame(display);
		display.dispose();					//���� ����Ǹ�, ���� �ִ� ���÷��̸� ����
	} //Main �Լ� ��
}
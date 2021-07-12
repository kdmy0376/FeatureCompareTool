//���� ��ġ�� ���� �Ӽ� Ŭ����
package WidgetProperty;

import org.eclipse.swt.layout.GridData;

public class WidgetProperty {
	private static GridData gridData = null;

	//����
	public static GridData setGridLayoutProperty(int widthHint){
		gridData = new GridData();
		gridData.widthHint = widthHint;
		return gridData;
	}
	
	//���� ���� ���
	public static GridData setGridLayoutAlignProperty(int horizontalAlignment){
		gridData = new GridData();
		gridData.horizontalAlignment = horizontalAlignment;
		return gridData;
	}
	
	//���� �� ��ġ��, ���� ���� ���
	public static GridData setGridLayoutNotGrabProperty(int horizontalSpan, int horizontalAlignment){
		gridData = new GridData();
		gridData.horizontalSpan = horizontalSpan;						
		gridData.horizontalAlignment = horizontalAlignment;
		return gridData;
	}
	
	//���� ���� ���, ���� ���� ���
	public static GridData setGridLayoutNotGrabVerticalProperty(int horizontalSpan, int verticalAlignment){
		gridData = new GridData();
		gridData.horizontalSpan = horizontalSpan;						
		gridData.verticalAlignment = verticalAlignment;
		return gridData;
	}
	
	//���� ���� ���, ���� ���� Ȱ��
	public static GridData setGridLayoutProperty(int horizontalAlignment, boolean grabExcessHorizontalSpace){
		gridData = new GridData();
		gridData.horizontalAlignment = horizontalAlignment;
		gridData.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
		return gridData;
	}
	
	//���� �� ��ġ��, ���� ���� ���, ���� ���� Ȱ��
	public static GridData setGridLayoutProperty(int horizontalSpan, int horizontalAlignment, boolean grabExcessHorizontalSpace){
		gridData = new GridData();
		gridData.horizontalSpan = horizontalSpan;						
		gridData.horizontalAlignment = horizontalAlignment;
		gridData.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
		return gridData;
	}
	
	//���� �� ��ġ��, ����, ���� ���� ���, ���� ���� Ȱ��
	public static GridData setGridLayoutProperty(int horizontalSpan, int widthHint, int horizontalAlignment, boolean grabExcessHorizontalSpace){
		gridData = new GridData();
		gridData.horizontalSpan = horizontalSpan;
		gridData.widthHint = widthHint;
		gridData.horizontalAlignment = horizontalAlignment;
		gridData.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
		return gridData;
	}
	
	//���� �� ��ġ��, ����
	public static GridData setGridLayoutVerticalProperty(int horizontalSpan, int heightHint){
		gridData = new GridData();
		gridData.horizontalSpan = horizontalSpan;
		gridData.heightHint = heightHint;
		return gridData;
	}
	
	//���� �� ��ġ��, ����, ���� ���� ���, ���� ���� Ȱ��
	public static GridData setGridLayoutVerticalProperty(int horizontalSpan, int heightHint, int horizontalAlignment, boolean grabExcessHorizontalSpace){
		gridData = new GridData();
		gridData.horizontalSpan = horizontalSpan;
		gridData.heightHint = heightHint;
		gridData.horizontalAlignment = horizontalAlignment;
		gridData.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
		return gridData;
	}
}
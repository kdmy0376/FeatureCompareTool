package Menu_Update;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import DataBase.DBWork;
import Main.MainFrame;
import WidgetProperty.WidgetProperty;

public class UpdateServiceForm extends Composite{
	private GridLayout gridLayout = null;
	private Text companyNameText = null;
	private Text parentServiceNameText = null;
	private Text childServiceNameText = null;
	private Text nameText = null;
	private Text definitionText = null;
	private Text domainTaskText = null;
	private Text sTypeText = null;
	private Text synonymousNamesText = null;
	private Text priorityText = null;
	private Text conditionText = null;
	private Text interfaceText = null;
	private Text processText = null;
	private Text componentText = null;
	private Text mandatoryFunctionText = null;
	private Text secondaryFunctionText = null;
	private Text propertyText = null;
	private Text andText = null;
	private Text orText = null;
	private Button updateButton = null;
	private Button cancelButton = null;
	private Button cancelAllButton = null;
	private Color labelColor = null;
	private Color buttonColor = null;
	private Font groupFont = null;

	//??????
	public UpdateServiceForm(){
		super(UpdateDetailScreen.tabFolder, SWT.NONE);
		this.initializeMember();
		this.setLayout(gridLayout);
		this.createServicePosition();
		this.createIdentifyingAttributes();	
		this.createFunctionalAttributes();
		this.createSelectiveAttributes();
		this.createRelationalAttributes();
		this.createButton();
		this.mountEventHandler();
	} //?????? ??
	
	//???? ???? ?ʱ?ȭ
	public void initializeMember(){
		gridLayout = new GridLayout(1, false);
		labelColor = new Color(this.getParent().getDisplay(), 180,230,240);
		buttonColor = new Color(this.getParent().getDisplay(), 70,210,210);
		groupFont = new Font(this.getParent().getDisplay(), "Miriam", 10, SWT.BOLD);
	} //???? ???? ?ʱ?ȭ ?Լ? ??
	
	//?????? ??ġ
	public void createServicePosition(){
		Group registerPositionGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
		registerPositionGroup.setText("Company Name / Service Name");	
		registerPositionGroup.setFont(groupFont);
		GridLayout gridLayout = new GridLayout(11, false);
		registerPositionGroup.setLayout(gridLayout);
		registerPositionGroup.setLayoutData(WidgetProperty.setGridLayoutProperty(SWT.FILL, true));

		Label companyNameLabel = new Label(registerPositionGroup, SWT.BORDER);	//?????? ȸ?? ?̸?		
		companyNameLabel.setText("Company Name:");
		companyNameLabel.setBackground(labelColor);
		companyNameLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		companyNameText = new Text(registerPositionGroup, SWT.BORDER);
		companyNameText.setEnabled(false);
		companyNameText.setLayoutData(WidgetProperty.setGridLayoutProperty(2, SWT.FILL, true));
		
		Label parentServiceNameLabel = new Label(registerPositionGroup, SWT.BORDER);	//?θ? ?????? ?̸?	
		parentServiceNameLabel.setText("Parent Service Name:");
		parentServiceNameLabel.setBackground(labelColor);
		parentServiceNameLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(2, SWT.FILL, true));
		parentServiceNameText = new Text(registerPositionGroup, SWT.BORDER);
		parentServiceNameText.setEnabled(false);
		parentServiceNameText.setLayoutData(WidgetProperty.setGridLayoutProperty(2, SWT.FILL, true));

		Label childServiceNameLabel = new Label(registerPositionGroup, SWT.BORDER);	//?ڽ? ?????? ?̸?		
		childServiceNameLabel.setText("Child Service Name:");
		childServiceNameLabel.setBackground(labelColor);
		childServiceNameLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(2, SWT.FILL, true));
		childServiceNameText = new Text(registerPositionGroup, SWT.BORDER);
		childServiceNameText.setEnabled(false);	
		childServiceNameText.setLayoutData(WidgetProperty.setGridLayoutProperty(2, SWT.FILL, true));
	} //?????? ??ġ ?Լ? ??
	
	//Identifying ?Ӽ?
	public void createIdentifyingAttributes(){
		Group identifyingAttributeGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
		identifyingAttributeGroup.setText("Identifying Attribute");	
		identifyingAttributeGroup.setFont(groupFont);
		GridLayout gridLayout = new GridLayout(11, false);
		identifyingAttributeGroup.setLayout(gridLayout);	
		identifyingAttributeGroup.setLayoutData(WidgetProperty.setGridLayoutProperty(SWT.FILL, true));

		Label nameLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//Name	
		nameLabel.setText("Name:");
		nameLabel.setBackground(labelColor);
		nameLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		nameText = new Text(identifyingAttributeGroup, SWT.BORDER);
		nameText.setLayoutData(WidgetProperty.setGridLayoutProperty(10, SWT.FILL, true));		

		Label definitionLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//Definition		
		definitionLabel.setText("Definition:");
		definitionLabel.setBackground(labelColor);
		definitionLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		definitionText = new Text(identifyingAttributeGroup, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		definitionText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));
		
		Label domainTaskLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//DomainTask		
		domainTaskLabel.setText("DomainTask:");
		domainTaskLabel.setBackground(labelColor);
		domainTaskLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		domainTaskText = new Text(identifyingAttributeGroup, SWT.BORDER);
		domainTaskText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));
		
		Label sTypeLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//SType		
		sTypeLabel.setText("SType:");
		sTypeLabel.setBackground(labelColor);
		sTypeLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		sTypeText = new Text(identifyingAttributeGroup, SWT.BORDER);
		sTypeText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));
		
		Label snonymousNamesLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//SynonymousNames		
		snonymousNamesLabel.setText("SynonymousNames:");
		snonymousNamesLabel.setBackground(labelColor);
		snonymousNamesLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		synonymousNamesText = new Text(identifyingAttributeGroup, SWT.BORDER);
		synonymousNamesText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));

		Label priorityLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//Priority		
		priorityLabel.setText("Priority:");
		priorityLabel.setBackground(labelColor);
		priorityLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		priorityText = new Text(identifyingAttributeGroup, SWT.BORDER);
		priorityText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));

		Label conditionLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//Condition		
		conditionLabel.setText("Condition:");
		conditionLabel.setBackground(labelColor);
		conditionLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		conditionText = new Text(identifyingAttributeGroup, SWT.BORDER);
		conditionText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));
		
		Label interfaceLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//Interface		
		interfaceLabel.setText("Interface:");
		interfaceLabel.setBackground(labelColor);
		interfaceLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		interfaceText = new Text(identifyingAttributeGroup, SWT.BORDER);
		interfaceText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));
		
		Label processLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//Process			
		processLabel.setText("Business Process:");
		processLabel.setBackground(labelColor);
		processLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		processText = new Text(identifyingAttributeGroup, SWT.BORDER);
		processText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));
		
		Label componentLabel = new Label(identifyingAttributeGroup, SWT.BORDER);	//Component			
		componentLabel.setText("Component:");		
		componentLabel.setBackground(labelColor);
		componentLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		componentText = new Text(identifyingAttributeGroup, SWT.BORDER);
		componentText.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(10, SWT.FILL));
	} //Identifying ?Ӽ? ?Լ? ??
	
	//Functional ?Ӽ?
	public void createFunctionalAttributes(){
		Group functionalAttributeGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
		functionalAttributeGroup.setText("Functional Attribute");	
		functionalAttributeGroup.setFont(groupFont);
		GridLayout gridLayout = new GridLayout(11, false);
		functionalAttributeGroup.setLayout(gridLayout);
		functionalAttributeGroup.setLayoutData(WidgetProperty.setGridLayoutAlignProperty(SWT.FILL));
		
		Label mandatoryFunctionLabel = new Label(functionalAttributeGroup, SWT.BORDER);	//MandatoryFunction		
		mandatoryFunctionLabel.setText("MandatoryFunction:");
		mandatoryFunctionLabel.setBackground(labelColor);
		mandatoryFunctionLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		mandatoryFunctionText = new Text(functionalAttributeGroup, SWT.BORDER);
		mandatoryFunctionText.setLayoutData(WidgetProperty.setGridLayoutProperty(10, SWT.FILL, true));

		Label secondaryFunctionLabel = new Label(functionalAttributeGroup, SWT.BORDER);	//SecondaryFunction		
		secondaryFunctionLabel.setText("SecondaryFunction:");
		secondaryFunctionLabel.setBackground(labelColor);
		secondaryFunctionLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		secondaryFunctionText = new Text(functionalAttributeGroup, SWT.BORDER);
		secondaryFunctionText.setLayoutData(WidgetProperty.setGridLayoutProperty(10, SWT.FILL, true));
	} //Functional ?Ӽ? ?Լ? ??
	
	//Selective ?Ӽ?
	public void createSelectiveAttributes(){
		Group selectiveAttributeGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
		selectiveAttributeGroup.setText("Selective Attribute");	
		selectiveAttributeGroup.setFont(groupFont);
		GridLayout gridLayout = new GridLayout(11, false);
		selectiveAttributeGroup.setLayout(gridLayout);
		selectiveAttributeGroup.setLayoutData(WidgetProperty.setGridLayoutAlignProperty(SWT.FILL));

		Label servicePropertyLabel = new Label(selectiveAttributeGroup, SWT.BORDER);	//Property
		servicePropertyLabel.setText("Service's Property:");
		servicePropertyLabel.setBackground(labelColor);
		servicePropertyLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		propertyText = new Text(selectiveAttributeGroup, SWT.BORDER);
		propertyText.setLayoutData(WidgetProperty.setGridLayoutProperty(10, SWT.FILL, true));
	} //Selective ?Ӽ? ?Լ? ??
	
	//Relational ?Ӽ?
	public void createRelationalAttributes(){
		Group relationalAttributeGroup = new Group(this, SWT.SHADOW_ETCHED_OUT);
		relationalAttributeGroup.setText("Relational Attribute");
		relationalAttributeGroup.setFont(groupFont);
		GridLayout gridLayout = new GridLayout(11, false);
		relationalAttributeGroup.setLayout(gridLayout);
		relationalAttributeGroup.setLayoutData(WidgetProperty.setGridLayoutAlignProperty(SWT.FILL));

		Label andLabel = new Label(relationalAttributeGroup, SWT.BORDER);	//Pre-Self			
		andLabel.setText("Pre-Self:");
		andLabel.setBackground(labelColor);
		andLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		andText = new Text(relationalAttributeGroup, SWT.BORDER);
		andText.setLayoutData(WidgetProperty.setGridLayoutProperty(10, SWT.FILL, true));
		
		Label orLabel = new Label(relationalAttributeGroup, SWT.BORDER);	//Self-Post			
		orLabel.setText("Self-Post:");
		orLabel.setBackground(labelColor);
		orLabel.setLayoutData(WidgetProperty.setGridLayoutProperty(110));
		orText = new Text(relationalAttributeGroup, SWT.BORDER);
		orText.setLayoutData(WidgetProperty.setGridLayoutProperty(10, SWT.FILL, true));	
	} //Relational ?Ӽ? ?Լ? ??
	
	//??ư ????
	public void createButton(){
		Group buttonGroup = new Group(this, SWT.SHADOW_ETCHED_IN);
		GridLayout gridLayout = new GridLayout(5, false);
		buttonGroup.setLayout(gridLayout);		
		buttonGroup.setLayoutData(WidgetProperty.setGridLayoutAlignProperty(SWT.FILL));

		updateButton = new Button(buttonGroup, SWT.PUSH);	//Update ??ư	
		updateButton.setText("Update");
		updateButton.setBackground(buttonColor);
		updateButton.setLayoutData(WidgetProperty.setGridLayoutProperty(2, 80, SWT.RIGHT, true));

		cancelButton = new Button(buttonGroup, SWT.PUSH);	//Cancel ??ư	
		cancelButton.setText("Cancel");	
		cancelButton.setBackground(buttonColor);
		cancelButton.setLayoutData(WidgetProperty.setGridLayoutProperty(1, 80, SWT.CENTER, false));
		
		cancelAllButton = new Button(buttonGroup, SWT.PUSH);	//Cancel All ??ư	
		cancelAllButton.setText("Cancel All");	
		cancelAllButton.setBackground(buttonColor);
		cancelAllButton.setLayoutData(WidgetProperty.setGridLayoutProperty(2, 80, SWT.LEFT, true));
	} //??ư ???? ?Լ? ??
	
	//?? ǥ??
	public void createWidgetValue(String serviceName, String companyName){
		String[] serviceProperty = DBWork.getPropertyByServiceName(companyName, serviceName);
		Text[] textWidget = {nameText, definitionText, domainTaskText, sTypeText, synonymousNamesText, priorityText, conditionText, interfaceText,
							 processText, componentText, mandatoryFunctionText, secondaryFunctionText, propertyText, andText, orText,
							 parentServiceNameText, childServiceNameText};
		companyNameText.setText(companyName);
		for(int i=0; i<textWidget.length; i++){
			textWidget[i].setText(serviceProperty[i]);
		}
	} //?? ǥ?? ?Լ? ??
	
	//?̺?Ʈ ó????
	public void mountEventHandler(){
		//??????ư
		updateButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				DBWork.updateService(companyNameText.getText(), parentServiceNameText.getText(), childServiceNameText.getText(), 
													 nameText.getText(), definitionText.getText(), domainTaskText.getText(), sTypeText.getText(), 
													 synonymousNamesText.getText(), Double.parseDouble(priorityText.getText()), 
													 conditionText.getText(), interfaceText.getText(), processText.getText(), componentText.getText(), 
						                             mandatoryFunctionText.getText(), secondaryFunctionText.getText(), 
						                             propertyText.getText(), andText.getText(), orText.getText());
				UpdateDetailScreen.tabFolder.getItem(UpdateDetailScreen.tabFolder.getSelectionIndex()).dispose();
				
				if(UpdateDetailScreen.tabFolder.getItemCount() <= 0){
					MainFrame.stackLayout.topControl = new UpdateScreen();
					MainFrame.shell.layout();
				}
			}
		});
		//???ҹ?ư
		cancelButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				UpdateDetailScreen.tabFolder.getItem(UpdateDetailScreen.tabFolder.getSelectionIndex()).dispose();
				if(UpdateDetailScreen.tabFolder.getItemCount() <= 0){
					MainFrame.stackLayout.topControl = new UpdateScreen();
					MainFrame.shell.layout();
				}
			}
		});
		//???? ???ҹ?ư
		cancelAllButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				UpdateDetailScreen.tabFolder.dispose();
				MainFrame.stackLayout.topControl = new UpdateScreen();
				MainFrame.shell.layout();
			}
		});
		//Selective ?Ӽ? ?ؽ?Ʈ?ʵ?
		propertyText.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e) {
				createDialog();
			}
		});
	} //?̺?Ʈ ó???? ?Լ? ??
	
	//Selective ?Ӽ??? ???̾??α? â ????
	public void createDialog(){
		final Shell selectSelectivePropertyDialog = new Shell(MainFrame.shell, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		selectSelectivePropertyDialog.setLayout(new GridLayout(2, false));
		selectSelectivePropertyDialog.setText("Selective Attributes");

		Group formGroup = new Group(selectSelectivePropertyDialog, SWT.NONE);
		formGroup.setText("Select the service's selective attributes");
		GridLayout gridLayout = new GridLayout(3, false);
		formGroup.setLayout(gridLayout);	
		formGroup.setLayoutData(WidgetProperty.setGridLayoutNotGrabProperty(2,  SWT.FILL));

		//?????? ??ư 3??
		final Button mandatoryButton = new Button(formGroup, SWT.RADIO);	
		mandatoryButton.setText("Mandatory");

		final Button optionalButton = new Button(formGroup, SWT.RADIO);
		optionalButton.setText("Optional");

		final Button alternativeButton = new Button(formGroup, SWT.RADIO);	
		alternativeButton.setText("Alternative");

		//Ȯ?? ??ư
		Button okButton = new Button(selectSelectivePropertyDialog, SWT.NONE);	
		okButton.setText("OK");
		okButton.setBackground(buttonColor);
		okButton.setLayoutData(WidgetProperty.setGridLayoutProperty(1, 80, SWT.CENTER, true));

		//???? ??ư
		Button cancelButton = new Button(selectSelectivePropertyDialog, SWT.NONE);	
		cancelButton.setText("Cancel");
		cancelButton.setBackground(buttonColor);
		cancelButton.setLayoutData(WidgetProperty.setGridLayoutProperty(1, 80, SWT.LEFT, true));

		selectSelectivePropertyDialog.pack();	//???̾??α? â ũ?? ????
		selectSelectivePropertyDialog.open();	//???̾??α? ??????
		Rectangle shellBounds = MainFrame.shell.getBounds();					//???? ?簢?? ????
		Point dialogSize = selectSelectivePropertyDialog.getSize();				//???̾??α? â?? ũ?? ????
		selectSelectivePropertyDialog.setLocation(shellBounds.x + (shellBounds.width - dialogSize.x) / 2,	//?? ?߾????? ǥ?? 
												  shellBounds.y + (shellBounds.height - dialogSize.y) / 2);

		//Ȯ?ι?ư
		okButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				Button[] button = {mandatoryButton, optionalButton, alternativeButton};
				String buttonText = null;
				for(int i=0; i<button.length; i++){
					if(button[i].getSelection()){
						buttonText = button[i].getText() + " Service";
						break;
					}
				}
				propertyText.setText(buttonText);
				andText.setFocus();
				selectSelectivePropertyDialog.dispose();
			}
		});
		//???ҹ?ư
		cancelButton.addSelectionListener(new SelectionAdapter(){		
			public void widgetSelected(SelectionEvent selectionEvent){
				andText.setFocus();
				selectSelectivePropertyDialog.dispose();
			}
		});
		//???̾??α? â ??????
		selectSelectivePropertyDialog.addShellListener(new ShellAdapter(){		
			public void shellClosed(ShellEvent e){
				andText.setFocus();
				selectSelectivePropertyDialog.dispose();
			}
		});
	} //Selective ?Ӽ??? ???̾??α? â ???? ?Լ? ??
}
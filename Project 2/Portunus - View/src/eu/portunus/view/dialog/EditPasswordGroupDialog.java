package eu.portunus.view.dialog;

import eu.portunus.core.IPasswordGroup;
import eu.portunus.view.image.ImageLibrary;
import javafx.scene.image.Image;

public class EditPasswordGroupDialog extends AddEditPasswordGroupDialog {
	public EditPasswordGroupDialog(IPasswordGroup passwordGroup) {
		super(passwordGroup);
	}
	
	@Override
	protected String createTitle() {
		return "Edit Group...";
	}
	
	@Override
	protected Image createImage() {
		return ImageLibrary.getInstance().getEditPasswordGroupImage();
	}
}

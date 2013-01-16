package pl.wroc.uni.ii.eliga.test.ui;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.Test;

import pl.wroc.uni.ii.eliga.common.EligaInjector;
import pl.wroc.uni.ii.eliga.ui.NoticeAdder;

public class GuiTest {
	
	private FrameFixture window;

	public void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	public void setUp() {
		NoticeAdder frame = GuiActionRunner.execute(new GuiQuery<NoticeAdder>() {
			protected NoticeAdder executeInEDT() {
				return EligaInjector.getInstance(NoticeAdder.class);
			}
		});
		window = new FrameFixture(frame);
		window.show(); // shows the frame to test
	}

	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void shouldCopyTextInLabelWhenClickingButton() {
		setUpOnce();
		setUp();
		
		window.checkBox("jCheckBox1").check();
		window.textBox("jTextArea1").setText("Some notice");
		window.textBox("jTextField1").enterText("89114499551");
		window.button("jButton1").click();
		window.comboBox("jComboBox1").selectItem(1);
		
		tearDown();
	}

}
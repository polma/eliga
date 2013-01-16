package pl.wroc.uni.ii.eliga.test.ui;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wroc.uni.ii.eliga.common.EligaInjector;
import pl.wroc.uni.ii.eliga.db.DatabaseServiceTest;
import pl.wroc.uni.ii.eliga.test.util.GuiceJUnitRunner;
import pl.wroc.uni.ii.eliga.test.util.TestObjects;
import pl.wroc.uni.ii.eliga.ui.NoticeAdder;

import com.google.inject.Inject;
@RunWith(GuiceJUnitRunner.class)
public class GuiTest {
	
	private FrameFixture window;
	@Inject
	DatabaseServiceTest service;
	
	@Before
	public void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() {
		NoticeAdder frame = GuiActionRunner.execute(new GuiQuery<NoticeAdder>() {
			protected NoticeAdder executeInEDT() {
				return EligaInjector.getInstance(NoticeAdder.class);
			}
		});
		window = new FrameFixture(frame);
		window.show(); // shows the frame to test
		
		service.cleanUp();
		service.insertAll();		
	}

	@After
	public void tearDown() {
		window.cleanUp();
	}

	@Test
	public void shouldCopyTextInLabelWhenClickingButton() throws InterruptedException {
		window.checkBox("jCheckBox1").check();
		window.textBox("jTextArea1").setText("Some notice");
		window.textBox("jTextField1").enterText(TestObjects.STUDENT.getPesel());
		window.button("jButton1").click();
		window.comboBox("jComboBox1").selectItem(0);
	}

}
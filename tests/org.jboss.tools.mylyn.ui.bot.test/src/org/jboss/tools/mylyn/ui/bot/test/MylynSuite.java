package org.jboss.tools.mylyn.ui.bot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Mylyn Test Suite
 * @author ldimaggi
 *
 */

@RunWith(Suite.class)

@SuiteClasses({
	MylynReqTest.class,
	MylynBugzillaQueryTest.class
	})

public class MylynSuite {
	
}

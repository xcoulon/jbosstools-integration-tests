/*******************************************************************************

 * Copyright (c) 2007-2011 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.vpe.ui.bot.test.editor.tags;

import org.jboss.tools.ui.bot.ext.Timing;

/**
 * Tests Rich Faces dataOrderedList Tag behavior 
 * @author vlado pakan
 *
 */
public class ExtendedDataTableTagTest extends AbstractTagTest{
  private static final String COL_0_VALUE = "!-* Col 0 Value";
  private static final String COL_1_VALUE = "!-* Col 1 Value";
  private static final String COL_0_LABEL = "!-* Col 0 Label";
  private static final String COL_1_LABEL = "!-* Col 1 Label";
  @Override
  protected void initTestPage() {
    initTestPage(TestPageType.XHTML,
      "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
      "<html xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
      "  xmlns:f=\"http://java.sun.com/jsf/core\"\n" +
      "  xmlns:rich=\"http://richfaces.org/rich\"\n" +
      "  xmlns:h=\"http://java.sun.com/jsf/html\">\n" +
      "  <head>\n" +
      "  </head>\n" +
      "  <body>\n" +
      "    <f:view>\n" +
      "      <rich:extendedDataTable>\n" +
      "        <rich:column>\n" +
      "          <f:facet name=\"header\">\n" +
      "            <h:outputText value=\"" + ExtendedDataTableTagTest.COL_0_LABEL + "\"/>\n" +
      "          </f:facet>\n" +
      "          <h:outputText value=\"" + ExtendedDataTableTagTest.COL_0_VALUE + "\"/>\n" +
      "        </rich:column>\n" +
      "        <rich:column>\n" +
      "          <f:facet name=\"header\">\n" +
      "           <h:outputText value=\"" + ExtendedDataTableTagTest.COL_1_LABEL + "\"/>\n" +
      "          </f:facet>\n" +
      "          <h:outputText value=\"" + ExtendedDataTableTagTest.COL_1_VALUE + "\"/>\n" +
      "        </rich:column>\n" +
      "      </rich:extendedDataTable>\n" +
      "    </f:view>\n" +
      "  </body>\n" + 
      "</html>");
  }

  @Override
  protected void verifyTag() {
    assertVisualEditorContains(getVisualEditor(),
      "TR", 
      new String[]{"class"},
      new String[]{"dr-table-subheader rich-table-subheader"},
      getTestPageFileName());
    assertVisualEditorContains(getVisualEditor(),
      "TH", 
      new String[]{"class"},
      new String[]{"dr-table-subheadercell rich-table-subheadercell"},
      getTestPageFileName());
    assertVisualEditorContains(getVisualEditor(),
      "TR", 
      new String[]{"class"},
      new String[]{"dr-table-subfooter rich-table-subfooter"},
      getTestPageFileName());
    assertVisualEditorContains(getVisualEditor(),
      "TD", 
      new String[]{"class"},
      new String[]{"dr-table-subfootercell rich-table-subfootercell dr-table-subfooter rich-table-subfooter"},
      getTestPageFileName());
    assertVisualEditorContains(getVisualEditor(),
      "TR", 
      new String[]{"class"},
      new String[]{"dr-body-table-tr"},
      getTestPageFileName());
    assertVisualEditorContains(getVisualEditor(),
      "TD", 
      new String[]{"class"},
      new String[]{"dr-table-cell rich-table-cell"},
      getTestPageFileName());
    assertVisualEditorContainsNodeWithValue(getVisualEditor(),
      ExtendedDataTableTagTest.COL_0_VALUE,
      getTestPageFileName());
    assertVisualEditorContainsNodeWithValue(getVisualEditor(),
      ExtendedDataTableTagTest.COL_1_VALUE,
      getTestPageFileName());
    assertVisualEditorContainsNodeWithValue(getVisualEditor(),
      ExtendedDataTableTagTest.COL_0_LABEL,
      getTestPageFileName());
    assertVisualEditorContainsNodeWithValue(getVisualEditor(),
      ExtendedDataTableTagTest.COL_1_LABEL,
      getTestPageFileName());
    // check tag selecti
    getVisualEditor().selectDomNode(getVisualEditor().getDomNodeByTagName("TABLE",1), 0);
    bot.sleep(Timing.time3S());
    String selectedText = getSourceEditor().getSelection();
    String hasToStartWith = "<rich:extendedDataTable>";
    assertTrue("Selected text in Source Pane has to start with '" + hasToStartWith + "'" +
        "\nbut it is '" + selectedText + "'",
      selectedText.trim().startsWith(hasToStartWith));
    String hasEndWith = "</rich:extendedDataTable>";
    assertTrue("Selected text in Source Pane has to end with '" + hasEndWith + "'" +
        "\nbut it is '" + selectedText + "'",
      selectedText.trim().endsWith(hasEndWith));
  }

}

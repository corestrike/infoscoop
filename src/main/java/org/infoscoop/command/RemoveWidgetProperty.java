package org.infoscoop.command;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infoscoop.command.util.XMLCommandUtil;
import org.infoscoop.dao.WidgetDAO;
import org.infoscoop.dao.model.Widget;

/**
 * The command class to delete a property of a widget.
 * 
 * @author nakata
 * 
 */
public class RemoveWidgetProperty extends XMLCommandProcessor {

    private Log logger = LogFactory.getLog(this.getClass());
    
    /**
     * create a new command class to delete a property of a widget.
     */
    public RemoveWidgetProperty() {
    }

    /**
     * update a property of a widget.
     * 
     * @param uid
     *            an userId that is a target of operaion.
     * @param el
     *            The element of request command. Attributes of "widgetId", "field", and "value" are necessary for the Element.<BR>
     *            <BR>
     *             example of input element：<BR>
     * 
     * <pre>
     *  &lt;command type=&quot;UpdateWidgetProperty&quot; id=&quot;UpdateWidgetProperty_w_5_showLatestNews&quot; widgetId=&quot;w_5&quot; field=&quot;showLatestNews&quot; value=&quot;false&quot;/&gt;
     * </pre>
     */
    public void execute() {
	 	
        String commandId = super.commandXml.getAttribute("id").trim();
        String tabId = super.commandXml.getAttribute("tabId").trim();
        String widgetId = super.commandXml.getAttribute("widgetId").trim();
        String field = super.commandXml.getAttribute("field").trim();

        if(logger.isInfoEnabled()){
        	String logMsg = "uid:[" + uid + "]: processXML: tabId:[" + tabId
					+ "], widgetId:[" + widgetId + "], field:[" + field
					+ "]";
        	logger.info(logMsg);
        }
        if (widgetId == null || "".equals(widgetId) ) {
            String reason = "It's an unjust widgetId．widgetId:[" + widgetId + "]";
            result = XMLCommandUtil.createResultElement(uid, "processXML",
                    logger, commandId, false, reason);
            return;
        }

		if (field == null || "".equals(field) ) {
			String reason = "A name of property is unjust．field:[" + field + "]";
			result = XMLCommandUtil.createResultElement(uid, "processXML",
					logger, commandId, false, reason);
			return;
		}

        Widget widget = WidgetDAO.newInstance().getWidget(uid, tabId, widgetId);
        widget.getUserPrefs().remove(field);
        WidgetDAO.newInstance().updateWidget(widget);

        result = XMLCommandUtil.createResultElement(
        		uid, "processXML",logger, commandId, true, null);
        
    }

}
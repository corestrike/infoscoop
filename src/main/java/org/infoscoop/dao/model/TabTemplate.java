package org.infoscoop.dao.model;

import java.util.ArrayList;
import java.util.Collection;

import org.infoscoop.dao.model.base.BaseTabTemplate;



public class TabTemplate extends BaseTabTemplate {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TabTemplate () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TabTemplate (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public TabTemplate (
		java.lang.Integer id,
		java.lang.String name,
		java.lang.Integer published,
		java.lang.Integer temp) {

		super (
			id,
			name,
			published,
			temp);
	}

	/* [CONSTRUCTOR MARKER END] */
	/*
	List<String> staticGadgets;
	
	public List<String> getStaticGadgets() {
		if(this.staticGadgets != null){
			return this.staticGadgets;
		}else{
			this.staticGadgets = new SGList(this);
			if (super.getTabTemplateStaticGadgets() != null) {
				for (TabTemplateStaticGadget sg : super
						.getTabTemplateStaticGadgets()) {
					staticGadgets.add(sg.getContainerId());
				}
			}
			return this.staticGadgets;
		}
	}
	
	public class SGList extends ArrayList<String>{
		private static final long serialVersionUID = 1L;
		TabTemplate self;
		
		public SGList(TabTemplate tabTemplate) {
			this.self = tabTemplate;
		}
		
		@Override
		public boolean add(String id) {
			super.add(id);
			TabTemplateStaticGadget sg = new TabTemplateStaticGadget();
			sg.setContainerId(id);
			this.self.getTabTemplateStaticGadgets().add(sg);
			return true;
		}

	}
	*/

	/*
	private List<TabTemplateStaticGadget> staticGadgets = LazyList.decorate(
			new ArrayList<TabTemplateStaticGadget>(), FactoryUtils
					.instantiateFactory(TabTemplateStaticGadget.class));

	public java.util.List<org.infoscoop.dao.model.TabTemplateStaticGadget> getStaticGadgets() {
		return staticGadgets;
	}
*/

	public TabTemplateParsonalizeGadget getTabTemplateParsonalizeGadget(String id) {
		for(TabTemplateParsonalizeGadget gadget : super.getTabTemplateParsonalizeGadgets()){
			if(gadget.getId().equals(Integer.valueOf(id)))return gadget;
		}
		return null;
	}

	public TabTemplateParsonalizeGadget getTabTemplateParsonalizeGadgetBySibling(Integer id) {
		for(TabTemplateParsonalizeGadget gadget : super.getTabTemplateParsonalizeGadgets()){
			if(gadget.getSibling() == null)continue;
			
			if(id == gadget.getSibling().getId())return gadget;
		}
		return null;
	}

	public TabTemplateParsonalizeGadget getSubWidgetBySibling(String siblingId, String parentId) {
		Collection<TabTemplateParsonalizeGadget> subWidgets = new ArrayList<TabTemplateParsonalizeGadget>();
		for(TabTemplateParsonalizeGadget gadget : super.getTabTemplateParsonalizeGadgets()){
			//
		}
		return null;
	}

	public TabTemplateParsonalizeGadget getNextSiblingOnColumn(String siblingId, Integer colNum) {
		for(TabTemplateParsonalizeGadget gadget : super.getTabTemplateParsonalizeGadgets()){
			if(gadget.getColumnNum() == null || gadget.getSibling() == null)continue;
			if(colNum.equals(gadget.getColumnNum()) && gadget.getSibling().getId().equals(Integer.valueOf(siblingId)))
				return gadget;
		}
		return null;
	}


}
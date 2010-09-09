<%@ page contentType="text/html; charset=UTF8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<tiles:insertDefinition name="base.definition" flush="true">
	<tiles:putAttribute name="type" value="menu"/>
	<tiles:putAttribute name="title" value="menu.title"/>
	<tiles:putAttribute name="body" type="string">
<p>
	この画面では、メニューツリーを追加/編集/削除できます。<br>
	トップに表示、サイドに表示を選択した場合のみ、そのメニューがポータル画面に表示されます。<br>
</p>
<a href="editMenu" id="add_menu">メニューを追加</a>
<table id="menu_list" class="tablesorter">
	<thead>
		<tr>
			<th>名前</th>
			<th width="100">トップに表示<div class="icon edit_icon" id="change_position_top"></div></th>
			<th width="100">サイドに表示<div class="icon edit_icon" id="change_position_side"></div></th>
			<th width="100">削除</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="menu" items="${menus}">
		<tr>
			<td class="title">
				<a href="editMenu?id=${menu.id}">${menu.title}</a>
				<input type="text" value="${menu.title}" style="display:none">
			</td>
			<td class="radio_cell">
				<span>${menu.top ? "表示" : ""}</span>
				<input type="radio" name="top" ${menu.top ? "checked=\"checked\"" : ""} style="display:none" menu_id="${menu.id}">
			</td>
			<td class="radio_cell">
				<span>${menu.side ? "表示" : ""}</span>
				<input type="radio" name="side" ${menu.side ? "checked=\"checked\"" : ""} style="display:none" menu_id="${menu.id}">
			</td>
			<td class="icon_cell"><div class="icon delete_icon" menu_id="${menu.id}"></div></td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(menus) == 0}">
		<tr>
			<td colspan="4">メニューを追加してください。</td>
		</tr>
		</c:if>
	</tbody>
</table>
<script type="text/javascript">
$("#add_menu").button();
$("#menu_list").tablesorter({
	headers: {1:{sorter:false},2:{sorter:false},3:{sorter:false}}
});
$("#change_position_top, #change_position_side").click(function(){
	var name = this.id === "change_position_top" ? "top" : "side",
		icon = $(this),
		radios = $("#menu_list td.radio_cell input[name="+name+"]");
	if(icon.hasClass("edit_icon")){
		radios.show().prev().hide();
		icon.removeClass("edit_icon").addClass("save_icon");
	} else {
		var menuId = radios.filter(":radio:checked").attr("menu_id");
		$.post("changePosition", {id:menuId, position:name}, function(html){
			radios.hide().prev().each(function(){
				if($(this).next().attr("menu_id") === menuId) $(this).html("表示").show();
				else $(this).empty().show();
			});
			icon.removeClass("save_icon").addClass("edit_icon");
			console.info(html);
		});
	}
});
$("#menu_list .delete_icon").live("click", function(){
	var deleteIcon = $(this);
	if(confirm("削除してよろしいですか？")){
		$.post("deleteMenu", {id:deleteIcon.attr("menu_id")}, function(html){
			deleteIcon.parents("tr:first").remove();
			console.info(html);
		});
	}
});
</script>
	</tiles:putAttribute>
</tiles:insertDefinition>
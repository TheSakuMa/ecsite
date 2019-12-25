<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<header>
	<div class="header-wrapper">
		<div class="header-title">
			<a href='<s:url action="GoHomeAction"/>'>ECSITE</a>
		</div>
		<s:if test="searchErrorMessage != null">
			<p class="header-searchErr">
				<s:property value="searchErrorMessage" />
			</p>
		</s:if>
		<div class="search">
			<s:form action="SearchItemAction" theme="css_xhtml">
				<s:if test="#session.itemSortList!=null && #session.itemSortList.size()>0">
					<s:select name="itemSort" list="#session.itemSortList" id="itemSortValue"/>
				</s:if>
				<s:if test="#session.mCategoryList!=null && #session.mCategoryList.size()>0">
					<s:select name="categoryId" list="#session.mCategoryList" listValue="categoryName" listKey="categoryId" id="categoryId" />
				</s:if>
				<div id="search-box">
					<s:submit value="検索" />
					<s:hidden name="jspName" id="header_jspName" value="" />
					<s:textfield name="keywords" placeholder="検索ワード" class="keywords" id="keywords" />
				</div>
			</s:form>
		</div>
	</div>
	<script type="text/javascript">
	(function() {
		const searchItemActionForm = document.getElementById('SearchItemAction');
		const itemSort = document.getElementById('itemSortValue');
		itemSort.addEventListener('change', function() {
			searchItemActionForm.submit();
		});
	}());
	</script>
</header>
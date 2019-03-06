<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
portletDisplay.setShowBackIcon(true);

LiferayPortletURL usersAdminURL = liferayPortletResponse.createLiferayPortletURL(UsersAdminPortletKeys.USERS_ADMIN, PortletRequest.RENDER_PHASE);

portletDisplay.setURLBack(usersAdminURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "personal-data-erasure")));
%>

<div class="container-fluid container-fluid-max-xl container-form-lg">
	<liferay-ui:empty-result-message
		message="you-have-successfully-anonymized-all-remaining-data"
	/>

	<portlet:actionURL name="/delete_user" var="deleteUserURL">
		<portlet:param name="p_u_i_d" value="<%= String.valueOf(selectedUser.getUserId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		confirmation="all-personal-data-associated-to-this-users-applications-has-been-deleted-or-anonymized"
		cssClass="hidden"
		id="deleteUserIcon"
		message=""
		url="<%= deleteUserURL.toString() %>"
	/>
</div>

<aui:script use="aui-base">
	AUI().ready(
		function() {
			var deleteUserIcon = document.getElementById('<portlet:namespace/>deleteUserIcon');

			deleteUserIcon.click();
		}
	);
</aui:script>
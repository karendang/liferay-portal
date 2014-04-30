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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * The extended model base implementation for the Organization service. Represents a row in the &quot;Organization_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link OrganizationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrganizationImpl
 * @see com.liferay.portal.model.Organization
 * @generated
 */
public abstract class OrganizationBaseImpl extends OrganizationModelImpl
	implements Organization {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a organization model instance should use the {@link Organization} interface instead.
	 */
	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			OrganizationLocalServiceUtil.addOrganization(this);
		}
		else {
			OrganizationLocalServiceUtil.updateOrganization(this);
		}
	}

	@Override
	@SuppressWarnings("unused")
	public String buildTreePath() throws PortalException, SystemException {
		List<Organization> organizations = new ArrayList<Organization>();

		Organization organization = this;

		while (organization != null) {
			organizations.add(organization);

			organization = OrganizationLocalServiceUtil.fetchOrganization(organization.getParentOrganizationId());
		}

		StringBundler sb = new StringBundler((organizations.size() * 2) + 1);

		sb.append(StringPool.SLASH);

		for (int i = organizations.size() - 1; i >= 0; i--) {
			organization = organizations.get(i);

			sb.append(organization.getOrganizationId());
			sb.append(StringPool.SLASH);
		}

		return sb.toString();
	}

	@Override
	public void updateTreePath(String treePath) throws SystemException {
		Organization organization = this;

		organization.setTreePath(treePath);

		OrganizationLocalServiceUtil.updateOrganization(organization);
	}
}
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

package com.liferay.portlet.asset.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.AuditedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the AssetCategoryProperty service. Represents a row in the &quot;AssetCategoryProperty&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.asset.model.impl.AssetCategoryPropertyModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.asset.model.impl.AssetCategoryPropertyImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryProperty
 * @see com.liferay.portlet.asset.model.impl.AssetCategoryPropertyImpl
 * @see com.liferay.portlet.asset.model.impl.AssetCategoryPropertyModelImpl
 * @generated
 */
@ProviderType
public interface AssetCategoryPropertyModel extends AuditedModel,
	BaseModel<AssetCategoryProperty> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a asset category property model instance should use the {@link AssetCategoryProperty} interface instead.
	 */

	/**
	 * Returns the primary key of this asset category property.
	 *
	 * @return the primary key of this asset category property
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this asset category property.
	 *
	 * @param primaryKey the primary key of this asset category property
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the category property ID of this asset category property.
	 *
	 * @return the category property ID of this asset category property
	 */
	public long getCategoryPropertyId();

	/**
	 * Sets the category property ID of this asset category property.
	 *
	 * @param categoryPropertyId the category property ID of this asset category property
	 */
	public void setCategoryPropertyId(long categoryPropertyId);

	/**
	 * Returns the company ID of this asset category property.
	 *
	 * @return the company ID of this asset category property
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this asset category property.
	 *
	 * @param companyId the company ID of this asset category property
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this asset category property.
	 *
	 * @return the user ID of this asset category property
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this asset category property.
	 *
	 * @param userId the user ID of this asset category property
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this asset category property.
	 *
	 * @return the user uuid of this asset category property
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this asset category property.
	 *
	 * @param userUuid the user uuid of this asset category property
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this asset category property.
	 *
	 * @return the user name of this asset category property
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this asset category property.
	 *
	 * @param userName the user name of this asset category property
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this asset category property.
	 *
	 * @return the create date of this asset category property
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this asset category property.
	 *
	 * @param createDate the create date of this asset category property
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this asset category property.
	 *
	 * @return the modified date of this asset category property
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this asset category property.
	 *
	 * @param modifiedDate the modified date of this asset category property
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the category ID of this asset category property.
	 *
	 * @return the category ID of this asset category property
	 */
	public long getCategoryId();

	/**
	 * Sets the category ID of this asset category property.
	 *
	 * @param categoryId the category ID of this asset category property
	 */
	public void setCategoryId(long categoryId);

	/**
	 * Returns the key of this asset category property.
	 *
	 * @return the key of this asset category property
	 */
	@AutoEscape
	public String getKey();

	/**
	 * Sets the key of this asset category property.
	 *
	 * @param key the key of this asset category property
	 */
	public void setKey(String key);

	/**
	 * Returns the value of this asset category property.
	 *
	 * @return the value of this asset category property
	 */
	@AutoEscape
	public String getValue();

	/**
	 * Sets the value of this asset category property.
	 *
	 * @param value the value of this asset category property
	 */
	public void setValue(String value);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(AssetCategoryProperty assetCategoryProperty);

	@Override
	public int hashCode();

	@Override
	public CacheModel<AssetCategoryProperty> toCacheModel();

	@Override
	public AssetCategoryProperty toEscapedModel();

	@Override
	public AssetCategoryProperty toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
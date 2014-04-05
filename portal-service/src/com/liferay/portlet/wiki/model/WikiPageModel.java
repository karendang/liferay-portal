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

package com.liferay.portlet.wiki.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ResourcedModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portal.model.WorkflowedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.trash.model.TrashEntry;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the WikiPage service. Represents a row in the &quot;WikiPage&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.wiki.model.impl.WikiPageModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.wiki.model.impl.WikiPageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WikiPage
 * @see com.liferay.portlet.wiki.model.impl.WikiPageImpl
 * @see com.liferay.portlet.wiki.model.impl.WikiPageModelImpl
 * @generated
 */
@ProviderType
public interface WikiPageModel extends BaseModel<WikiPage>, ResourcedModel,
	StagedGroupedModel, TrashedModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a wiki page model instance should use the {@link WikiPage} interface instead.
	 */

	/**
	 * Returns the primary key of this wiki page.
	 *
	 * @return the primary key of this wiki page
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this wiki page.
	 *
	 * @param primaryKey the primary key of this wiki page
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this wiki page.
	 *
	 * @return the uuid of this wiki page
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this wiki page.
	 *
	 * @param uuid the uuid of this wiki page
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the page ID of this wiki page.
	 *
	 * @return the page ID of this wiki page
	 */
	public long getPageId();

	/**
	 * Sets the page ID of this wiki page.
	 *
	 * @param pageId the page ID of this wiki page
	 */
	public void setPageId(long pageId);

	/**
	 * Returns the resource prim key of this wiki page.
	 *
	 * @return the resource prim key of this wiki page
	 */
	@Override
	public long getResourcePrimKey();

	/**
	 * Sets the resource prim key of this wiki page.
	 *
	 * @param resourcePrimKey the resource prim key of this wiki page
	 */
	@Override
	public void setResourcePrimKey(long resourcePrimKey);

	@Override
	public boolean isResourceMain();

	/**
	 * Returns the group ID of this wiki page.
	 *
	 * @return the group ID of this wiki page
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this wiki page.
	 *
	 * @param groupId the group ID of this wiki page
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this wiki page.
	 *
	 * @return the company ID of this wiki page
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this wiki page.
	 *
	 * @param companyId the company ID of this wiki page
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this wiki page.
	 *
	 * @return the user ID of this wiki page
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this wiki page.
	 *
	 * @param userId the user ID of this wiki page
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this wiki page.
	 *
	 * @return the user uuid of this wiki page
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this wiki page.
	 *
	 * @param userUuid the user uuid of this wiki page
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this wiki page.
	 *
	 * @return the user name of this wiki page
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this wiki page.
	 *
	 * @param userName the user name of this wiki page
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this wiki page.
	 *
	 * @return the create date of this wiki page
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this wiki page.
	 *
	 * @param createDate the create date of this wiki page
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this wiki page.
	 *
	 * @return the modified date of this wiki page
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this wiki page.
	 *
	 * @param modifiedDate the modified date of this wiki page
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the node ID of this wiki page.
	 *
	 * @return the node ID of this wiki page
	 */
	public long getNodeId();

	/**
	 * Sets the node ID of this wiki page.
	 *
	 * @param nodeId the node ID of this wiki page
	 */
	public void setNodeId(long nodeId);

	/**
	 * Returns the title of this wiki page.
	 *
	 * @return the title of this wiki page
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this wiki page.
	 *
	 * @param title the title of this wiki page
	 */
	public void setTitle(String title);

	/**
	 * Returns the version of this wiki page.
	 *
	 * @return the version of this wiki page
	 */
	public double getVersion();

	/**
	 * Sets the version of this wiki page.
	 *
	 * @param version the version of this wiki page
	 */
	public void setVersion(double version);

	/**
	 * Returns the minor edit of this wiki page.
	 *
	 * @return the minor edit of this wiki page
	 */
	public boolean getMinorEdit();

	/**
	 * Returns <code>true</code> if this wiki page is minor edit.
	 *
	 * @return <code>true</code> if this wiki page is minor edit; <code>false</code> otherwise
	 */
	public boolean isMinorEdit();

	/**
	 * Sets whether this wiki page is minor edit.
	 *
	 * @param minorEdit the minor edit of this wiki page
	 */
	public void setMinorEdit(boolean minorEdit);

	/**
	 * Returns the content of this wiki page.
	 *
	 * @return the content of this wiki page
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this wiki page.
	 *
	 * @param content the content of this wiki page
	 */
	public void setContent(String content);

	/**
	 * Returns the summary of this wiki page.
	 *
	 * @return the summary of this wiki page
	 */
	@AutoEscape
	public String getSummary();

	/**
	 * Sets the summary of this wiki page.
	 *
	 * @param summary the summary of this wiki page
	 */
	public void setSummary(String summary);

	/**
	 * Returns the format of this wiki page.
	 *
	 * @return the format of this wiki page
	 */
	@AutoEscape
	public String getFormat();

	/**
	 * Sets the format of this wiki page.
	 *
	 * @param format the format of this wiki page
	 */
	public void setFormat(String format);

	/**
	 * Returns the head of this wiki page.
	 *
	 * @return the head of this wiki page
	 */
	public boolean getHead();

	/**
	 * Returns <code>true</code> if this wiki page is head.
	 *
	 * @return <code>true</code> if this wiki page is head; <code>false</code> otherwise
	 */
	public boolean isHead();

	/**
	 * Sets whether this wiki page is head.
	 *
	 * @param head the head of this wiki page
	 */
	public void setHead(boolean head);

	/**
	 * Returns the parent title of this wiki page.
	 *
	 * @return the parent title of this wiki page
	 */
	@AutoEscape
	public String getParentTitle();

	/**
	 * Sets the parent title of this wiki page.
	 *
	 * @param parentTitle the parent title of this wiki page
	 */
	public void setParentTitle(String parentTitle);

	/**
	 * Returns the redirect title of this wiki page.
	 *
	 * @return the redirect title of this wiki page
	 */
	@AutoEscape
	public String getRedirectTitle();

	/**
	 * Sets the redirect title of this wiki page.
	 *
	 * @param redirectTitle the redirect title of this wiki page
	 */
	public void setRedirectTitle(String redirectTitle);

	/**
	 * Returns the status of this wiki page.
	 *
	 * @return the status of this wiki page
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this wiki page.
	 *
	 * @param status the status of this wiki page
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this wiki page.
	 *
	 * @return the status by user ID of this wiki page
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this wiki page.
	 *
	 * @param statusByUserId the status by user ID of this wiki page
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this wiki page.
	 *
	 * @return the status by user uuid of this wiki page
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getStatusByUserUuid() throws SystemException;

	/**
	 * Sets the status by user uuid of this wiki page.
	 *
	 * @param statusByUserUuid the status by user uuid of this wiki page
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this wiki page.
	 *
	 * @return the status by user name of this wiki page
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this wiki page.
	 *
	 * @param statusByUserName the status by user name of this wiki page
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this wiki page.
	 *
	 * @return the status date of this wiki page
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this wiki page.
	 *
	 * @param statusDate the status date of this wiki page
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * Returns the trash entry created when this wiki page was moved to the Recycle Bin. The trash entry may belong to one of the ancestors of this wiki page.
	 *
	 * @return the trash entry created when this wiki page was moved to the Recycle Bin
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public TrashEntry getTrashEntry() throws PortalException, SystemException;

	/**
	 * Returns the class primary key of the trash entry for this wiki page.
	 *
	 * @return the class primary key of the trash entry for this wiki page
	 */
	@Override
	public long getTrashEntryClassPK();

	/**
	 * Returns the trash handler for this wiki page.
	 *
	 * @return the trash handler for this wiki page
	 */
	@Override
	public TrashHandler getTrashHandler();

	/**
	 * Returns <code>true</code> if this wiki page is in the Recycle Bin.
	 *
	 * @return <code>true</code> if this wiki page is in the Recycle Bin; <code>false</code> otherwise
	 */
	@Override
	public boolean isInTrash();

	/**
	 * Returns <code>true</code> if the parent of this wiki page is in the Recycle Bin.
	 *
	 * @return <code>true</code> if the parent of this wiki page is in the Recycle Bin; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean isInTrashContainer();

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	 */
	@Override
	public boolean getApproved();

	/**
	 * Returns <code>true</code> if this wiki page is approved.
	 *
	 * @return <code>true</code> if this wiki page is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this wiki page is denied.
	 *
	 * @return <code>true</code> if this wiki page is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this wiki page is a draft.
	 *
	 * @return <code>true</code> if this wiki page is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this wiki page is expired.
	 *
	 * @return <code>true</code> if this wiki page is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this wiki page is inactive.
	 *
	 * @return <code>true</code> if this wiki page is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this wiki page is incomplete.
	 *
	 * @return <code>true</code> if this wiki page is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this wiki page is pending.
	 *
	 * @return <code>true</code> if this wiki page is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this wiki page is scheduled.
	 *
	 * @return <code>true</code> if this wiki page is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

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
	public int compareTo(WikiPage wikiPage);

	@Override
	public int hashCode();

	@Override
	public CacheModel<WikiPage> toCacheModel();

	@Override
	public WikiPage toEscapedModel();

	@Override
	public WikiPage toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}
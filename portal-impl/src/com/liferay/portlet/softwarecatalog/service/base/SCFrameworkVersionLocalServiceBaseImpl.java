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

package com.liferay.portlet.softwarecatalog.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion;
import com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService;
import com.liferay.portlet.softwarecatalog.service.persistence.SCFrameworkVersionPersistence;
import com.liferay.portlet.softwarecatalog.service.persistence.SCProductVersionPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the s c framework version local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.softwarecatalog.service.impl.SCFrameworkVersionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.softwarecatalog.service.impl.SCFrameworkVersionLocalServiceImpl
 * @see com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalServiceUtil
 * @generated
 */
public abstract class SCFrameworkVersionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements SCFrameworkVersionLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalServiceUtil} to access the s c framework version local service.
	 */

	/**
	 * Adds the s c framework version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param scFrameworkVersion the s c framework version
	 * @return the s c framework version that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SCFrameworkVersion addSCFrameworkVersion(
		SCFrameworkVersion scFrameworkVersion) throws SystemException {
		scFrameworkVersion.setNew(true);

		return scFrameworkVersionPersistence.update(scFrameworkVersion);
	}

	/**
	 * Creates a new s c framework version with the primary key. Does not add the s c framework version to the database.
	 *
	 * @param frameworkVersionId the primary key for the new s c framework version
	 * @return the new s c framework version
	 */
	@Override
	public SCFrameworkVersion createSCFrameworkVersion(long frameworkVersionId) {
		return scFrameworkVersionPersistence.create(frameworkVersionId);
	}

	/**
	 * Deletes the s c framework version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param frameworkVersionId the primary key of the s c framework version
	 * @return the s c framework version that was removed
	 * @throws PortalException if a s c framework version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SCFrameworkVersion deleteSCFrameworkVersion(long frameworkVersionId)
		throws PortalException, SystemException {
		return scFrameworkVersionPersistence.remove(frameworkVersionId);
	}

	/**
	 * Deletes the s c framework version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scFrameworkVersion the s c framework version
	 * @return the s c framework version that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SCFrameworkVersion deleteSCFrameworkVersion(
		SCFrameworkVersion scFrameworkVersion) throws SystemException {
		return scFrameworkVersionPersistence.remove(scFrameworkVersion);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(SCFrameworkVersion.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return scFrameworkVersionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return scFrameworkVersionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return scFrameworkVersionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return scFrameworkVersionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return scFrameworkVersionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public SCFrameworkVersion fetchSCFrameworkVersion(long frameworkVersionId)
		throws SystemException {
		return scFrameworkVersionPersistence.fetchByPrimaryKey(frameworkVersionId);
	}

	/**
	 * Returns the s c framework version with the primary key.
	 *
	 * @param frameworkVersionId the primary key of the s c framework version
	 * @return the s c framework version
	 * @throws PortalException if a s c framework version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SCFrameworkVersion getSCFrameworkVersion(long frameworkVersionId)
		throws PortalException, SystemException {
		return scFrameworkVersionPersistence.findByPrimaryKey(frameworkVersionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery()
		throws SystemException {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(SCFrameworkVersion.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("frameworkVersionId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery)
		throws SystemException {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(SCFrameworkVersion.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("frameworkVersionId");
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return scFrameworkVersionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the s c framework versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s c framework versions
	 * @param end the upper bound of the range of s c framework versions (not inclusive)
	 * @return the range of s c framework versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCFrameworkVersion> getSCFrameworkVersions(int start, int end)
		throws SystemException {
		return scFrameworkVersionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of s c framework versions.
	 *
	 * @return the number of s c framework versions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getSCFrameworkVersionsCount() throws SystemException {
		return scFrameworkVersionPersistence.countAll();
	}

	/**
	 * Updates the s c framework version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param scFrameworkVersion the s c framework version
	 * @return the s c framework version that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SCFrameworkVersion updateSCFrameworkVersion(
		SCFrameworkVersion scFrameworkVersion) throws SystemException {
		return scFrameworkVersionPersistence.update(scFrameworkVersion);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCProductVersionSCFrameworkVersion(long productVersionId,
		long frameworkVersionId) throws SystemException {
		scProductVersionPersistence.addSCFrameworkVersion(productVersionId,
			frameworkVersionId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCProductVersionSCFrameworkVersion(long productVersionId,
		SCFrameworkVersion scFrameworkVersion) throws SystemException {
		scProductVersionPersistence.addSCFrameworkVersion(productVersionId,
			scFrameworkVersion);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCProductVersionSCFrameworkVersions(long productVersionId,
		long[] frameworkVersionIds) throws SystemException {
		scProductVersionPersistence.addSCFrameworkVersions(productVersionId,
			frameworkVersionIds);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void addSCProductVersionSCFrameworkVersions(long productVersionId,
		List<SCFrameworkVersion> SCFrameworkVersions) throws SystemException {
		scProductVersionPersistence.addSCFrameworkVersions(productVersionId,
			SCFrameworkVersions);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void clearSCProductVersionSCFrameworkVersions(long productVersionId)
		throws SystemException {
		scProductVersionPersistence.clearSCFrameworkVersions(productVersionId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteSCProductVersionSCFrameworkVersion(
		long productVersionId, long frameworkVersionId)
		throws SystemException {
		scProductVersionPersistence.removeSCFrameworkVersion(productVersionId,
			frameworkVersionId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteSCProductVersionSCFrameworkVersion(
		long productVersionId, SCFrameworkVersion scFrameworkVersion)
		throws SystemException {
		scProductVersionPersistence.removeSCFrameworkVersion(productVersionId,
			scFrameworkVersion);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteSCProductVersionSCFrameworkVersions(
		long productVersionId, long[] frameworkVersionIds)
		throws SystemException {
		scProductVersionPersistence.removeSCFrameworkVersions(productVersionId,
			frameworkVersionIds);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteSCProductVersionSCFrameworkVersions(
		long productVersionId, List<SCFrameworkVersion> SCFrameworkVersions)
		throws SystemException {
		scProductVersionPersistence.removeSCFrameworkVersions(productVersionId,
			SCFrameworkVersions);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCFrameworkVersion> getSCProductVersionSCFrameworkVersions(
		long productVersionId) throws SystemException {
		return scProductVersionPersistence.getSCFrameworkVersions(productVersionId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCFrameworkVersion> getSCProductVersionSCFrameworkVersions(
		long productVersionId, int start, int end) throws SystemException {
		return scProductVersionPersistence.getSCFrameworkVersions(productVersionId,
			start, end);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SCFrameworkVersion> getSCProductVersionSCFrameworkVersions(
		long productVersionId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return scProductVersionPersistence.getSCFrameworkVersions(productVersionId,
			start, end, orderByComparator);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getSCProductVersionSCFrameworkVersionsCount(
		long productVersionId) throws SystemException {
		return scProductVersionPersistence.getSCFrameworkVersionsSize(productVersionId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean hasSCProductVersionSCFrameworkVersion(
		long productVersionId, long frameworkVersionId)
		throws SystemException {
		return scProductVersionPersistence.containsSCFrameworkVersion(productVersionId,
			frameworkVersionId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public boolean hasSCProductVersionSCFrameworkVersions(long productVersionId)
		throws SystemException {
		return scProductVersionPersistence.containsSCFrameworkVersions(productVersionId);
	}

	/**
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void setSCProductVersionSCFrameworkVersions(long productVersionId,
		long[] frameworkVersionIds) throws SystemException {
		scProductVersionPersistence.setSCFrameworkVersions(productVersionId,
			frameworkVersionIds);
	}

	/**
	 * Returns the s c framework version local service.
	 *
	 * @return the s c framework version local service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService getSCFrameworkVersionLocalService() {
		return scFrameworkVersionLocalService;
	}

	/**
	 * Sets the s c framework version local service.
	 *
	 * @param scFrameworkVersionLocalService the s c framework version local service
	 */
	public void setSCFrameworkVersionLocalService(
		com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService scFrameworkVersionLocalService) {
		this.scFrameworkVersionLocalService = scFrameworkVersionLocalService;
	}

	/**
	 * Returns the s c framework version remote service.
	 *
	 * @return the s c framework version remote service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService getSCFrameworkVersionService() {
		return scFrameworkVersionService;
	}

	/**
	 * Sets the s c framework version remote service.
	 *
	 * @param scFrameworkVersionService the s c framework version remote service
	 */
	public void setSCFrameworkVersionService(
		com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService scFrameworkVersionService) {
		this.scFrameworkVersionService = scFrameworkVersionService;
	}

	/**
	 * Returns the s c framework version persistence.
	 *
	 * @return the s c framework version persistence
	 */
	public SCFrameworkVersionPersistence getSCFrameworkVersionPersistence() {
		return scFrameworkVersionPersistence;
	}

	/**
	 * Sets the s c framework version persistence.
	 *
	 * @param scFrameworkVersionPersistence the s c framework version persistence
	 */
	public void setSCFrameworkVersionPersistence(
		SCFrameworkVersionPersistence scFrameworkVersionPersistence) {
		this.scFrameworkVersionPersistence = scFrameworkVersionPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group remote service.
	 *
	 * @return the group remote service
	 */
	public com.liferay.portal.service.GroupService getGroupService() {
		return groupService;
	}

	/**
	 * Sets the group remote service.
	 *
	 * @param groupService the group remote service
	 */
	public void setGroupService(
		com.liferay.portal.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the s c product version local service.
	 *
	 * @return the s c product version local service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalService getSCProductVersionLocalService() {
		return scProductVersionLocalService;
	}

	/**
	 * Sets the s c product version local service.
	 *
	 * @param scProductVersionLocalService the s c product version local service
	 */
	public void setSCProductVersionLocalService(
		com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalService scProductVersionLocalService) {
		this.scProductVersionLocalService = scProductVersionLocalService;
	}

	/**
	 * Returns the s c product version remote service.
	 *
	 * @return the s c product version remote service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCProductVersionService getSCProductVersionService() {
		return scProductVersionService;
	}

	/**
	 * Sets the s c product version remote service.
	 *
	 * @param scProductVersionService the s c product version remote service
	 */
	public void setSCProductVersionService(
		com.liferay.portlet.softwarecatalog.service.SCProductVersionService scProductVersionService) {
		this.scProductVersionService = scProductVersionService;
	}

	/**
	 * Returns the s c product version persistence.
	 *
	 * @return the s c product version persistence
	 */
	public SCProductVersionPersistence getSCProductVersionPersistence() {
		return scProductVersionPersistence;
	}

	/**
	 * Sets the s c product version persistence.
	 *
	 * @param scProductVersionPersistence the s c product version persistence
	 */
	public void setSCProductVersionPersistence(
		SCProductVersionPersistence scProductVersionPersistence) {
		this.scProductVersionPersistence = scProductVersionPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion",
			scFrameworkVersionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return SCFrameworkVersion.class;
	}

	protected String getModelClassName() {
		return SCFrameworkVersion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = scFrameworkVersionPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService scFrameworkVersionLocalService;
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService scFrameworkVersionService;
	@BeanReference(type = SCFrameworkVersionPersistence.class)
	protected SCFrameworkVersionPersistence scFrameworkVersionPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.GroupLocalService.class)
	protected com.liferay.portal.service.GroupLocalService groupLocalService;
	@BeanReference(type = com.liferay.portal.service.GroupService.class)
	protected com.liferay.portal.service.GroupService groupService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalService scProductVersionLocalService;
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCProductVersionService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCProductVersionService scProductVersionService;
	@BeanReference(type = SCProductVersionPersistence.class)
	protected SCProductVersionPersistence scProductVersionPersistence;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}
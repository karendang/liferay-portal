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

package com.liferay.portlet.shopping.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
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
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.shopping.model.ShoppingItemField;
import com.liferay.portlet.shopping.service.ShoppingItemFieldLocalService;
import com.liferay.portlet.shopping.service.persistence.ShoppingCartPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingCategoryPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingCouponFinder;
import com.liferay.portlet.shopping.service.persistence.ShoppingCouponPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingItemFieldPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingItemFinder;
import com.liferay.portlet.shopping.service.persistence.ShoppingItemPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingItemPricePersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingOrderFinder;
import com.liferay.portlet.shopping.service.persistence.ShoppingOrderItemPersistence;
import com.liferay.portlet.shopping.service.persistence.ShoppingOrderPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the shopping item field local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.shopping.service.impl.ShoppingItemFieldLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.shopping.service.impl.ShoppingItemFieldLocalServiceImpl
 * @see com.liferay.portlet.shopping.service.ShoppingItemFieldLocalServiceUtil
 * @generated
 */
public abstract class ShoppingItemFieldLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ShoppingItemFieldLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.shopping.service.ShoppingItemFieldLocalServiceUtil} to access the shopping item field local service.
	 */

	/**
	 * Adds the shopping item field to the database. Also notifies the appropriate model listeners.
	 *
	 * @param shoppingItemField the shopping item field
	 * @return the shopping item field that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ShoppingItemField addShoppingItemField(
		ShoppingItemField shoppingItemField) throws SystemException {
		shoppingItemField.setNew(true);

		return shoppingItemFieldPersistence.update(shoppingItemField);
	}

	/**
	 * Creates a new shopping item field with the primary key. Does not add the shopping item field to the database.
	 *
	 * @param itemFieldId the primary key for the new shopping item field
	 * @return the new shopping item field
	 */
	@Override
	public ShoppingItemField createShoppingItemField(long itemFieldId) {
		return shoppingItemFieldPersistence.create(itemFieldId);
	}

	/**
	 * Deletes the shopping item field with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemFieldId the primary key of the shopping item field
	 * @return the shopping item field that was removed
	 * @throws PortalException if a shopping item field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ShoppingItemField deleteShoppingItemField(long itemFieldId)
		throws PortalException, SystemException {
		return shoppingItemFieldPersistence.remove(itemFieldId);
	}

	/**
	 * Deletes the shopping item field from the database. Also notifies the appropriate model listeners.
	 *
	 * @param shoppingItemField the shopping item field
	 * @return the shopping item field that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ShoppingItemField deleteShoppingItemField(
		ShoppingItemField shoppingItemField) throws SystemException {
		return shoppingItemFieldPersistence.remove(shoppingItemField);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ShoppingItemField.class,
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
		return shoppingItemFieldPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.shopping.model.impl.ShoppingItemFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return shoppingItemFieldPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.shopping.model.impl.ShoppingItemFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return shoppingItemFieldPersistence.findWithDynamicQuery(dynamicQuery,
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
		return shoppingItemFieldPersistence.countWithDynamicQuery(dynamicQuery);
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
		return shoppingItemFieldPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ShoppingItemField fetchShoppingItemField(long itemFieldId)
		throws SystemException {
		return shoppingItemFieldPersistence.fetchByPrimaryKey(itemFieldId);
	}

	/**
	 * Returns the shopping item field with the primary key.
	 *
	 * @param itemFieldId the primary key of the shopping item field
	 * @return the shopping item field
	 * @throws PortalException if a shopping item field with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public ShoppingItemField getShoppingItemField(long itemFieldId)
		throws PortalException, SystemException {
		return shoppingItemFieldPersistence.findByPrimaryKey(itemFieldId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return shoppingItemFieldPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the shopping item fields.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.shopping.model.impl.ShoppingItemFieldModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of shopping item fields
	 * @param end the upper bound of the range of shopping item fields (not inclusive)
	 * @return the range of shopping item fields
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<ShoppingItemField> getShoppingItemFields(int start, int end)
		throws SystemException {
		return shoppingItemFieldPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of shopping item fields.
	 *
	 * @return the number of shopping item fields
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getShoppingItemFieldsCount() throws SystemException {
		return shoppingItemFieldPersistence.countAll();
	}

	/**
	 * Updates the shopping item field in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param shoppingItemField the shopping item field
	 * @return the shopping item field that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ShoppingItemField updateShoppingItemField(
		ShoppingItemField shoppingItemField) throws SystemException {
		return shoppingItemFieldPersistence.update(shoppingItemField);
	}

	/**
	 * Returns the shopping cart local service.
	 *
	 * @return the shopping cart local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCartLocalService getShoppingCartLocalService() {
		return shoppingCartLocalService;
	}

	/**
	 * Sets the shopping cart local service.
	 *
	 * @param shoppingCartLocalService the shopping cart local service
	 */
	public void setShoppingCartLocalService(
		com.liferay.portlet.shopping.service.ShoppingCartLocalService shoppingCartLocalService) {
		this.shoppingCartLocalService = shoppingCartLocalService;
	}

	/**
	 * Returns the shopping cart persistence.
	 *
	 * @return the shopping cart persistence
	 */
	public ShoppingCartPersistence getShoppingCartPersistence() {
		return shoppingCartPersistence;
	}

	/**
	 * Sets the shopping cart persistence.
	 *
	 * @param shoppingCartPersistence the shopping cart persistence
	 */
	public void setShoppingCartPersistence(
		ShoppingCartPersistence shoppingCartPersistence) {
		this.shoppingCartPersistence = shoppingCartPersistence;
	}

	/**
	 * Returns the shopping category local service.
	 *
	 * @return the shopping category local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCategoryLocalService getShoppingCategoryLocalService() {
		return shoppingCategoryLocalService;
	}

	/**
	 * Sets the shopping category local service.
	 *
	 * @param shoppingCategoryLocalService the shopping category local service
	 */
	public void setShoppingCategoryLocalService(
		com.liferay.portlet.shopping.service.ShoppingCategoryLocalService shoppingCategoryLocalService) {
		this.shoppingCategoryLocalService = shoppingCategoryLocalService;
	}

	/**
	 * Returns the shopping category remote service.
	 *
	 * @return the shopping category remote service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCategoryService getShoppingCategoryService() {
		return shoppingCategoryService;
	}

	/**
	 * Sets the shopping category remote service.
	 *
	 * @param shoppingCategoryService the shopping category remote service
	 */
	public void setShoppingCategoryService(
		com.liferay.portlet.shopping.service.ShoppingCategoryService shoppingCategoryService) {
		this.shoppingCategoryService = shoppingCategoryService;
	}

	/**
	 * Returns the shopping category persistence.
	 *
	 * @return the shopping category persistence
	 */
	public ShoppingCategoryPersistence getShoppingCategoryPersistence() {
		return shoppingCategoryPersistence;
	}

	/**
	 * Sets the shopping category persistence.
	 *
	 * @param shoppingCategoryPersistence the shopping category persistence
	 */
	public void setShoppingCategoryPersistence(
		ShoppingCategoryPersistence shoppingCategoryPersistence) {
		this.shoppingCategoryPersistence = shoppingCategoryPersistence;
	}

	/**
	 * Returns the shopping coupon local service.
	 *
	 * @return the shopping coupon local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCouponLocalService getShoppingCouponLocalService() {
		return shoppingCouponLocalService;
	}

	/**
	 * Sets the shopping coupon local service.
	 *
	 * @param shoppingCouponLocalService the shopping coupon local service
	 */
	public void setShoppingCouponLocalService(
		com.liferay.portlet.shopping.service.ShoppingCouponLocalService shoppingCouponLocalService) {
		this.shoppingCouponLocalService = shoppingCouponLocalService;
	}

	/**
	 * Returns the shopping coupon remote service.
	 *
	 * @return the shopping coupon remote service
	 */
	public com.liferay.portlet.shopping.service.ShoppingCouponService getShoppingCouponService() {
		return shoppingCouponService;
	}

	/**
	 * Sets the shopping coupon remote service.
	 *
	 * @param shoppingCouponService the shopping coupon remote service
	 */
	public void setShoppingCouponService(
		com.liferay.portlet.shopping.service.ShoppingCouponService shoppingCouponService) {
		this.shoppingCouponService = shoppingCouponService;
	}

	/**
	 * Returns the shopping coupon persistence.
	 *
	 * @return the shopping coupon persistence
	 */
	public ShoppingCouponPersistence getShoppingCouponPersistence() {
		return shoppingCouponPersistence;
	}

	/**
	 * Sets the shopping coupon persistence.
	 *
	 * @param shoppingCouponPersistence the shopping coupon persistence
	 */
	public void setShoppingCouponPersistence(
		ShoppingCouponPersistence shoppingCouponPersistence) {
		this.shoppingCouponPersistence = shoppingCouponPersistence;
	}

	/**
	 * Returns the shopping coupon finder.
	 *
	 * @return the shopping coupon finder
	 */
	public ShoppingCouponFinder getShoppingCouponFinder() {
		return shoppingCouponFinder;
	}

	/**
	 * Sets the shopping coupon finder.
	 *
	 * @param shoppingCouponFinder the shopping coupon finder
	 */
	public void setShoppingCouponFinder(
		ShoppingCouponFinder shoppingCouponFinder) {
		this.shoppingCouponFinder = shoppingCouponFinder;
	}

	/**
	 * Returns the shopping item local service.
	 *
	 * @return the shopping item local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingItemLocalService getShoppingItemLocalService() {
		return shoppingItemLocalService;
	}

	/**
	 * Sets the shopping item local service.
	 *
	 * @param shoppingItemLocalService the shopping item local service
	 */
	public void setShoppingItemLocalService(
		com.liferay.portlet.shopping.service.ShoppingItemLocalService shoppingItemLocalService) {
		this.shoppingItemLocalService = shoppingItemLocalService;
	}

	/**
	 * Returns the shopping item remote service.
	 *
	 * @return the shopping item remote service
	 */
	public com.liferay.portlet.shopping.service.ShoppingItemService getShoppingItemService() {
		return shoppingItemService;
	}

	/**
	 * Sets the shopping item remote service.
	 *
	 * @param shoppingItemService the shopping item remote service
	 */
	public void setShoppingItemService(
		com.liferay.portlet.shopping.service.ShoppingItemService shoppingItemService) {
		this.shoppingItemService = shoppingItemService;
	}

	/**
	 * Returns the shopping item persistence.
	 *
	 * @return the shopping item persistence
	 */
	public ShoppingItemPersistence getShoppingItemPersistence() {
		return shoppingItemPersistence;
	}

	/**
	 * Sets the shopping item persistence.
	 *
	 * @param shoppingItemPersistence the shopping item persistence
	 */
	public void setShoppingItemPersistence(
		ShoppingItemPersistence shoppingItemPersistence) {
		this.shoppingItemPersistence = shoppingItemPersistence;
	}

	/**
	 * Returns the shopping item finder.
	 *
	 * @return the shopping item finder
	 */
	public ShoppingItemFinder getShoppingItemFinder() {
		return shoppingItemFinder;
	}

	/**
	 * Sets the shopping item finder.
	 *
	 * @param shoppingItemFinder the shopping item finder
	 */
	public void setShoppingItemFinder(ShoppingItemFinder shoppingItemFinder) {
		this.shoppingItemFinder = shoppingItemFinder;
	}

	/**
	 * Returns the shopping item field local service.
	 *
	 * @return the shopping item field local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingItemFieldLocalService getShoppingItemFieldLocalService() {
		return shoppingItemFieldLocalService;
	}

	/**
	 * Sets the shopping item field local service.
	 *
	 * @param shoppingItemFieldLocalService the shopping item field local service
	 */
	public void setShoppingItemFieldLocalService(
		com.liferay.portlet.shopping.service.ShoppingItemFieldLocalService shoppingItemFieldLocalService) {
		this.shoppingItemFieldLocalService = shoppingItemFieldLocalService;
	}

	/**
	 * Returns the shopping item field persistence.
	 *
	 * @return the shopping item field persistence
	 */
	public ShoppingItemFieldPersistence getShoppingItemFieldPersistence() {
		return shoppingItemFieldPersistence;
	}

	/**
	 * Sets the shopping item field persistence.
	 *
	 * @param shoppingItemFieldPersistence the shopping item field persistence
	 */
	public void setShoppingItemFieldPersistence(
		ShoppingItemFieldPersistence shoppingItemFieldPersistence) {
		this.shoppingItemFieldPersistence = shoppingItemFieldPersistence;
	}

	/**
	 * Returns the shopping item price local service.
	 *
	 * @return the shopping item price local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingItemPriceLocalService getShoppingItemPriceLocalService() {
		return shoppingItemPriceLocalService;
	}

	/**
	 * Sets the shopping item price local service.
	 *
	 * @param shoppingItemPriceLocalService the shopping item price local service
	 */
	public void setShoppingItemPriceLocalService(
		com.liferay.portlet.shopping.service.ShoppingItemPriceLocalService shoppingItemPriceLocalService) {
		this.shoppingItemPriceLocalService = shoppingItemPriceLocalService;
	}

	/**
	 * Returns the shopping item price persistence.
	 *
	 * @return the shopping item price persistence
	 */
	public ShoppingItemPricePersistence getShoppingItemPricePersistence() {
		return shoppingItemPricePersistence;
	}

	/**
	 * Sets the shopping item price persistence.
	 *
	 * @param shoppingItemPricePersistence the shopping item price persistence
	 */
	public void setShoppingItemPricePersistence(
		ShoppingItemPricePersistence shoppingItemPricePersistence) {
		this.shoppingItemPricePersistence = shoppingItemPricePersistence;
	}

	/**
	 * Returns the shopping order local service.
	 *
	 * @return the shopping order local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingOrderLocalService getShoppingOrderLocalService() {
		return shoppingOrderLocalService;
	}

	/**
	 * Sets the shopping order local service.
	 *
	 * @param shoppingOrderLocalService the shopping order local service
	 */
	public void setShoppingOrderLocalService(
		com.liferay.portlet.shopping.service.ShoppingOrderLocalService shoppingOrderLocalService) {
		this.shoppingOrderLocalService = shoppingOrderLocalService;
	}

	/**
	 * Returns the shopping order remote service.
	 *
	 * @return the shopping order remote service
	 */
	public com.liferay.portlet.shopping.service.ShoppingOrderService getShoppingOrderService() {
		return shoppingOrderService;
	}

	/**
	 * Sets the shopping order remote service.
	 *
	 * @param shoppingOrderService the shopping order remote service
	 */
	public void setShoppingOrderService(
		com.liferay.portlet.shopping.service.ShoppingOrderService shoppingOrderService) {
		this.shoppingOrderService = shoppingOrderService;
	}

	/**
	 * Returns the shopping order persistence.
	 *
	 * @return the shopping order persistence
	 */
	public ShoppingOrderPersistence getShoppingOrderPersistence() {
		return shoppingOrderPersistence;
	}

	/**
	 * Sets the shopping order persistence.
	 *
	 * @param shoppingOrderPersistence the shopping order persistence
	 */
	public void setShoppingOrderPersistence(
		ShoppingOrderPersistence shoppingOrderPersistence) {
		this.shoppingOrderPersistence = shoppingOrderPersistence;
	}

	/**
	 * Returns the shopping order finder.
	 *
	 * @return the shopping order finder
	 */
	public ShoppingOrderFinder getShoppingOrderFinder() {
		return shoppingOrderFinder;
	}

	/**
	 * Sets the shopping order finder.
	 *
	 * @param shoppingOrderFinder the shopping order finder
	 */
	public void setShoppingOrderFinder(ShoppingOrderFinder shoppingOrderFinder) {
		this.shoppingOrderFinder = shoppingOrderFinder;
	}

	/**
	 * Returns the shopping order item local service.
	 *
	 * @return the shopping order item local service
	 */
	public com.liferay.portlet.shopping.service.ShoppingOrderItemLocalService getShoppingOrderItemLocalService() {
		return shoppingOrderItemLocalService;
	}

	/**
	 * Sets the shopping order item local service.
	 *
	 * @param shoppingOrderItemLocalService the shopping order item local service
	 */
	public void setShoppingOrderItemLocalService(
		com.liferay.portlet.shopping.service.ShoppingOrderItemLocalService shoppingOrderItemLocalService) {
		this.shoppingOrderItemLocalService = shoppingOrderItemLocalService;
	}

	/**
	 * Returns the shopping order item persistence.
	 *
	 * @return the shopping order item persistence
	 */
	public ShoppingOrderItemPersistence getShoppingOrderItemPersistence() {
		return shoppingOrderItemPersistence;
	}

	/**
	 * Sets the shopping order item persistence.
	 *
	 * @param shoppingOrderItemPersistence the shopping order item persistence
	 */
	public void setShoppingOrderItemPersistence(
		ShoppingOrderItemPersistence shoppingOrderItemPersistence) {
		this.shoppingOrderItemPersistence = shoppingOrderItemPersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.shopping.model.ShoppingItemField",
			shoppingItemFieldLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.shopping.model.ShoppingItemField");
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
		return ShoppingItemField.class;
	}

	protected String getModelClassName() {
		return ShoppingItemField.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = shoppingItemFieldPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCartLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCartLocalService shoppingCartLocalService;
	@BeanReference(type = ShoppingCartPersistence.class)
	protected ShoppingCartPersistence shoppingCartPersistence;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCategoryLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCategoryLocalService shoppingCategoryLocalService;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCategoryService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCategoryService shoppingCategoryService;
	@BeanReference(type = ShoppingCategoryPersistence.class)
	protected ShoppingCategoryPersistence shoppingCategoryPersistence;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCouponLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCouponLocalService shoppingCouponLocalService;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingCouponService.class)
	protected com.liferay.portlet.shopping.service.ShoppingCouponService shoppingCouponService;
	@BeanReference(type = ShoppingCouponPersistence.class)
	protected ShoppingCouponPersistence shoppingCouponPersistence;
	@BeanReference(type = ShoppingCouponFinder.class)
	protected ShoppingCouponFinder shoppingCouponFinder;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingItemLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingItemLocalService shoppingItemLocalService;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingItemService.class)
	protected com.liferay.portlet.shopping.service.ShoppingItemService shoppingItemService;
	@BeanReference(type = ShoppingItemPersistence.class)
	protected ShoppingItemPersistence shoppingItemPersistence;
	@BeanReference(type = ShoppingItemFinder.class)
	protected ShoppingItemFinder shoppingItemFinder;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingItemFieldLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingItemFieldLocalService shoppingItemFieldLocalService;
	@BeanReference(type = ShoppingItemFieldPersistence.class)
	protected ShoppingItemFieldPersistence shoppingItemFieldPersistence;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingItemPriceLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingItemPriceLocalService shoppingItemPriceLocalService;
	@BeanReference(type = ShoppingItemPricePersistence.class)
	protected ShoppingItemPricePersistence shoppingItemPricePersistence;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingOrderLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingOrderLocalService shoppingOrderLocalService;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingOrderService.class)
	protected com.liferay.portlet.shopping.service.ShoppingOrderService shoppingOrderService;
	@BeanReference(type = ShoppingOrderPersistence.class)
	protected ShoppingOrderPersistence shoppingOrderPersistence;
	@BeanReference(type = ShoppingOrderFinder.class)
	protected ShoppingOrderFinder shoppingOrderFinder;
	@BeanReference(type = com.liferay.portlet.shopping.service.ShoppingOrderItemLocalService.class)
	protected com.liferay.portlet.shopping.service.ShoppingOrderItemLocalService shoppingOrderItemLocalService;
	@BeanReference(type = ShoppingOrderItemPersistence.class)
	protected ShoppingOrderItemPersistence shoppingOrderItemPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
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
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}
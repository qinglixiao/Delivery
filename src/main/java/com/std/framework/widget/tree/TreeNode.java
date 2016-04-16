package com.std.framework.widget.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	public String id;// 节点id
	public String value;// 节点显示的文字
	public TreeNode parent;// 父节点
	public List<TreeNode> children = new ArrayList<TreeNode>();// 子节点
	public boolean isExpanded = true;// 是否处于展开状态
	public boolean isSelected = false;
	public boolean isShowCheckBox = false;
	public int ico = -1;// 图标
	public int stateImg = -1;

	public TreeNode(String id, String value) {
		this.id = id;
		this.value = value;
	}

	/**
	 * 获得父节点
	 * 
	 * @return
	 */
	public TreeNode getParent() {
		return this.parent;
	}

	/**
	 * 设置节点值
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获得节点值
	 * 
	 * @return
	 */
	public String getValue() {
		return this.value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isShowCheckBox() {
		return isShowCheckBox;
	}

	public void setShowCheckBox(boolean isShowCheckBox) {
		this.isShowCheckBox = isShowCheckBox;
	}

	public int getIco() {
		return ico;
	}

	public void setIco(int ico) {
		this.ico = ico;
	}

	/**
	 * 是否根节点
	 * 
	 * @return
	 */
	public boolean isRoot() {
		return parent == null ? true : false;
	}

	/**
	 * 获得子节点
	 * 
	 * @return
	 */
	public List<TreeNode> getChildren() {
		return this.children;
	}

	/**
	 * 添加子节点
	 * 
	 * @param node
	 */
	public void add(TreeNode node) {
		if (!children.contains(node)) {
			node.parent = this;
			children.add(node);
		}
	}

	/**
	 * 清除所有子节点
	 */
	public void clear() {
		children.clear();
	}

	/**
	 * 删除一个子节点
	 * 
	 * @param node
	 */
	public void remove(TreeNode node) {
		if (!children.contains(node)) {
			children.remove(node);
		}
	}

	/**
	 * 删除指定位置的子节点
	 * 
	 * @param location
	 */
	public void remove(int location) {
		children.remove(location);
	}

	/**
	 * 获得节点的级数,根节点为0
	 * 
	 * @return
	 */
	public int getLevel() {
		return parent == null ? 1 : parent.getLevel() + 1;
	}

	/**
	 * 是否叶节点,即没有子节点的节点
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		return children.size() < 1 ? true : false;
	}

	/**
	 * 当前节点是否处于展开状态
	 * 
	 * @return
	 */
	public boolean isExpanded() {
		return isExpanded;
	}

	/**
	 * 设置节点展开状态
	 * 
	 * @return
	 */
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

}
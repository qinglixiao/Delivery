package com.std.framework.comm.view.tree.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.std.framework.comm.view.tree.TreeNode;
import com.std.framework.comm.view.tree.TreeView;


class MainActivity extends Activity{
	private TreeView treeView;
	private List<TreeNode> nodes;
	private TreeView.TreeAdapter treeAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		treeView = (TreeView)findViewById(R.id.treeView1);
//		treeView.setExpandLevel(2);
//		treeView.setLeafImageResourceId(R.drawable.ic_launcher);
//		SampleData();
//		treeView.setAdapter(treeAdapter);
	}
	
	private void SampleData(){
		TreeNode root = new TreeNode("00", "目录");
		TreeNode TreeNode_lv1 = new TreeNode("001", "第一节课");
		TreeNode TreeNode_lv1_1 = new TreeNode("0001", "第一部分");
		TreeNode TreeNode_lv1_1_1 = new TreeNode("00011", "讲解");
		TreeNode TreeNode_lv1_1_2 = new TreeNode("00012", "练习");
		TreeNode TreeNode_lv1_1_2_1 = new TreeNode("000121", "练习");
		TreeNode TreeNode_lv1_1_2_2 = new TreeNode("000122", "练习");
		TreeNode TreeNode_lv1_1_2_3 = new TreeNode("000123", "练习");
		TreeNode TreeNode_lv1_1_2_4 = new TreeNode("000124", "练习");
		TreeNode TreeNode_lv1_1_2_5 = new TreeNode("000125", "练习");
		TreeNode TreeNode_lv1_1_2_5_1 = new TreeNode("0001251", "练习");
		TreeNode TreeNode_lv1_1_2_5_2 = new TreeNode("0001252", "练习");
		TreeNode TreeNode_lv1_1_2_5_2_1 = new TreeNode("00012521", "练习");
		TreeNode TreeNode_lv1_2 = new TreeNode("0002", "第二部分");
		TreeNode TreeNode_lv1_3 = new TreeNode("0003", "第三部分");
		TreeNode TreeNode_lv1_3_1 = new TreeNode("00031", "选择题");
		TreeNode TreeNode_lv2 = new TreeNode("002", "第二节课");
		TreeNode TreeNode_lv2_1 = new TreeNode("0021", "考试部分");
		TreeNode TreeNode_lv2_2 = new TreeNode("0022", "听力考试");
		TreeNode TreeNode_lv3 = new TreeNode("003", "第三节课");
		TreeNode TreeNode_lv3_1 = new TreeNode("0031", "考试部分");
		TreeNode TreeNode_lv3_2 = new TreeNode("0032", "听力考试");
		TreeNode TreeNode_lv3_3 = new TreeNode("0033", "上机部分");
		TreeNode TreeNode_lv3_4 = new TreeNode("0034", "笔试考试");
		TreeNode TreeNode_lv4 = new TreeNode("004", "第四节课");
		TreeNode TreeNode_lv4_1 = new TreeNode("0041", "考试部分");
		TreeNode TreeNode_lv4_2 = new TreeNode("0042", "听力考试");
		TreeNode TreeNode_lv4_3 = new TreeNode("0043", "上机部分");
		TreeNode TreeNode_lv4_4 = new TreeNode("0044", "笔试考试");
		TreeNode TreeNode_lv4_5 = new TreeNode("0045", "考试部分");
		TreeNode TreeNode_lv4_6 = new TreeNode("0046", "听力考试");
		TreeNode TreeNode_lv4_7 = new TreeNode("0047", "上机部分");
		TreeNode TreeNode_lv4_8 = new TreeNode("0048", "笔试考试");
		TreeNode TreeNode_lv5 = new TreeNode("005", "第五节课");
		TreeNode TreeNode_lv5_1 = new TreeNode("0051", "考试部分");
		TreeNode TreeNode_lv5_2 = new TreeNode("0052", "听力考试");
		TreeNode TreeNode_lv5_3 = new TreeNode("0053", "上机部分");
		TreeNode TreeNode_lv5_4 = new TreeNode("0054", "笔试考试");
		TreeNode TreeNode_lv5_5 = new TreeNode("0055", "笔试考试");
		TreeNode_lv1.add(TreeNode_lv1_1);
		TreeNode_lv1.add(TreeNode_lv1_2);
		TreeNode_lv1.add(TreeNode_lv1_3);
		root.add(TreeNode_lv1);
		TreeNode_lv1_1.add(TreeNode_lv1_1_1);
		TreeNode_lv1_1.add(TreeNode_lv1_1_2);
		TreeNode_lv2.add(TreeNode_lv2_1);
		TreeNode_lv2.add(TreeNode_lv2_2);
		root.add(TreeNode_lv2);
		TreeNode_lv3.add(TreeNode_lv3_1);
		TreeNode_lv3.add(TreeNode_lv3_2);
		TreeNode_lv3.add(TreeNode_lv3_3);
		TreeNode_lv3.add(TreeNode_lv3_4);
		root.add(TreeNode_lv3);

		TreeNode_lv4.add(TreeNode_lv4_1);
		TreeNode_lv4.add(TreeNode_lv4_2);
		TreeNode_lv4.add(TreeNode_lv4_3);
		TreeNode_lv4.add(TreeNode_lv4_4);
		TreeNode_lv4.add(TreeNode_lv4_5);
		TreeNode_lv4.add(TreeNode_lv4_6);
		TreeNode_lv4.add(TreeNode_lv4_7);
		TreeNode_lv4.add(TreeNode_lv4_8);
		root.add(TreeNode_lv4);

		TreeNode_lv5.add(TreeNode_lv5_1);
		TreeNode_lv5.add(TreeNode_lv5_2);
		TreeNode_lv5.add(TreeNode_lv5_3);
		TreeNode_lv5.add(TreeNode_lv5_4);
		root.add(TreeNode_lv5);

		TreeNode_lv1_1_2.add(TreeNode_lv1_1_2_1);
		TreeNode_lv1_1_2.add(TreeNode_lv1_1_2_2);
		TreeNode_lv1_1_2.add(TreeNode_lv1_1_2_3);
		TreeNode_lv1_1_2.add(TreeNode_lv1_1_2_4);
		TreeNode_lv1_1_2.add(TreeNode_lv1_1_2_5);
		TreeNode_lv1_1_2_5.add(TreeNode_lv1_1_2_5_1);
		TreeNode_lv1_1_2_5.add(TreeNode_lv1_1_2_5_2);
		TreeNode_lv1_1_2_5_2.add(TreeNode_lv1_1_2_5_2_1);
		TreeNode_lv1_3.add(TreeNode_lv1_3_1);
		nodes = new ArrayList<TreeNode>();
		nodes.add(root);
		nodes.add(TreeNode_lv5_5);
		treeAdapter = treeView.new TreeAdapter(nodes);
	}
}

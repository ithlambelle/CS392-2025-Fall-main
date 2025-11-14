package Library.FnTree;

import Library.LnStrm.*;

public class FnTree<T> {
//
    private Node root;
//    
    private class Node {
	T item; // stored data
	FnTree<T> lchild; // left subtree
	FnTree<T> rchild; // right subtree
	Node(T x0, FnTree<T> lxs, FnTree<T> rxs) {
	    item = x0;
	    lchild = lxs;
	    rchild = rxs;
	}
    }
//
    public FnTree() {
	root = null;
    }
    public FnTree(T x0, FnTree<T> lxs, FnTree<T> rxs) {
	root = new Node(x0, lxs, rxs);
    }
//
    public LnStrm<T> inorder$enumerate() {
	return new LnStrm<T>(
	  () -> {
	      if (root == null) {
		  return new LnStcn<T>();
	      } else {
		  return LnStrmSUtil.eval0(
		    LnStrmSUtil.append0(
		      root.lchild.inorder$enumerate(),
		      LnStrmSUtil.cons0(root.item, root.rchild.inorder$enumerate())));
	      }
	  }
       );
    }
//
    public LnStrm<T> preorder$enumerate() {
	return new LnStrm<T>(
	  () -> {
	      if (root==null) {
		  return new LnStcn<T>();
	      } else {
		  return new LnStcn<T>(
                    root.item,
                    LnStrmSUtil.append0(
		      root.lchild.preorder$enumerate(), root.rchild.preorder$enumerate()));
	      }
	  }
       );
    }
//
    public LnStrm<T> postorder$enumerate() {
	return new LnStrm<T>(
	  () -> {
	      if (root==null) {
		  return new LnStcn<T>();
	      } else {
		  return LnStrmSUtil.eval0(
		    LnStrmSUtil.append0(
		      root.lchild.postorder$enumerate(),
                      LnStrmSUtil.append0(root.rchild.postorder$enumerate(), new LnStrm<T>(root.item))));
	      }
	  }
       );
    }
//
} // end of [public class FnTree<T>{...}]

package Unit_4.AvlTree;

import org.omg.CORBA.Any;

/**
 * Created by nirpis.hu on 2017/9/13.
 */
public class AvlTree<AnyType extends Comparable<? super AnyType>> {
    private AvlNode<AnyType> mRoot;

    private static final int ALLOWED_IMBALANCE = 1;

    private static class AvlNode<AnyType> {
        AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AvlNode(AnyType theElement) {
            this(theElement, null, null);
        }

        AnyType element;
        AvlNode<AnyType> left;
        AvlNode<AnyType> right;
        int height;
    }

    //构造函数
    public AvlTree() {
        mRoot = null;
    }

    //获取树的高度
    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    //取最大值
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    //前序遍历Avl树
    private void preOrder(AvlNode<AnyType> tree) {
        if (tree != null) {
            System.out.println(tree.element + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    //中序遍历
    private void inOrder(AvlNode<AnyType> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.println(tree.element + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    //后序遍历 Avl树
    private void postOrder(AvlNode<AnyType> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println(tree.element + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

    //递归实现 查找Avl树中键值为element的结点
    private AvlNode<AnyType> search(AvlNode<AnyType> x, AnyType element) {
        if (x == null) {
            return null;
        }
        int compareResult = element.compareTo(x.element);
        if (compareResult < 0) {
            return search(x.left, element);
        } else if (compareResult > 0) {
            return search(x.right, element);
        } else {
            return x;
        }
    }

    public AvlNode<AnyType> search(AnyType element) {
        return search(mRoot, element);
    }

    //查找最小结点
    private AvlNode<AnyType> findMin(AvlNode<AnyType> tree) {
        if (tree == null) {
            return null;
        }
        while(tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    public AnyType findMin() {
        AvlNode<AnyType> p = findMin(mRoot);
        if (p != null) {
            return p.element;
        }
        return null;
    }


    //查找最大结点
    private AvlNode<AnyType> findMax(AvlNode<AnyType> tree) {
        if (tree == null) {
            return null;
        }
        if (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    public AnyType findMax() {
        AvlNode<AnyType> p = findMax(mRoot);
        if (p != null) {
            return p.element;
        }
        return null;
    }

    /*
     * LL: 左左对应的情况(左单旋转)
     *
     * 返回值：旋转后的根结点
     */
    private AvlNode<AnyType> leftLeftRotation(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1;

        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;

        return k1;
    }

    /*
     * RR: 右右对应的情况(右单旋转)
     *
     * 返回值：旋转后的根结点
     */
    private AvlNode<AnyType> rightRightRotation(AvlNode<AnyType> k1) {
        AvlNode<AnyType> k2;

        k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        k1.height = max(height(k1.left), height(k2.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;

        return k2;
    }

    /*
     * LR: 左右对应的情况(左双旋转)
     *
     * 返回值：旋转后的根节点
     */
    private AvlNode<AnyType> leftRightRotation(AvlNode<AnyType> k3) {
        k3.left = rightRightRotation(k3.left);
        return leftLeftRotation(k3);
    }

    /*
     * RL: 右左对应的情况(右双旋转)
     *
     * 返回值：旋转后的根节点
     */
    private AvlNode<AnyType> rightLeftRotation(AvlNode<AnyType> k1) {
        k1.right = leftLeftRotation(k1.right);
        return rightRightRotation(k1);
    }

    /*
     * 将结点插入到Avl树中，并返回根节点
     *
     * 参数说明：
     *      tree Avl树的根结点
     *      element 插入的结点的键值
     * 返回值：
     *      根结点
     */
    private AvlNode<AnyType> insert(AvlNode<AnyType> tree, AnyType element) {
        if (tree == null) {
            //新建节点
            tree = new AvlNode<>(element, null, null);
            if (tree == null) {
                System.out.println("Error: create avltree failed!");
                return null;
            }
        }
        else {
            int compareResult = element.compareTo(tree.element);

            if (compareResult < 0) {    //应该将key插入到tree的左子树的情况
                tree.left = insert(tree.left, element);
                //插入节点后,若Avl失去平衡，则进行相应的调节
                if (height(tree.left) - height(tree.right) == 2) {
                    if (element.compareTo(tree.left.element) < 0){
                        tree = leftLeftRotation(tree);
                    } else {
                        tree = leftRightRotation(tree);
                    }
                }
            }
            else if (compareResult > 0) {
                tree.right = insert(tree.right, element);
                //插入节点后，若Avl失去平衡,则进行相应的调节
                if (height(tree.right) - height(tree.left) == 2) {
                    if (element.compareTo(tree.right.element) > 0) {
                        tree = rightRightRotation(tree);
                    } else {
                        tree = rightLeftRotation(tree);
                    }
                }
            }
            else {  //compareResult == 0
                System.out.println("添加失败：不允许添加相同的节点!");
            }
        }
        tree.height = max(height(tree.left), height(tree.right)) + 1;
        return tree;
    }

    public void insert(AnyType element) {
        mRoot = insert(mRoot, element);
    }

    /*
    * 删除节点：返回根节点
    *
    * 参数说明：
    *       tree Avl树的根结点
    *       z    待删除的结点
    * 返回值：
    *       根结点
    * */
    private AvlNode<AnyType> remove(AvlNode<AnyType> tree, AvlNode<AnyType> z) {
        //根为空，直接返回null
        if (tree == null || z == null) {
            return null;
        }

        int compareResult = z.element.compareTo(tree.element);
        if (compareResult < 0) {    //待删除的节点在tree的左子树中
            tree.left = remove(tree.left, z);
            //删除节点后，若Avl树失去平衡，则进行相应的调节
            if (height(tree.right) - height(tree.left) == 2) {  //右节点失去平衡
                AvlNode<AnyType> r = tree.right;
                if (height(r.left) > height(r.right)) {
                    tree = rightLeftRotation(tree);
                } else {
                    tree = rightRightRotation(tree);
                }
            }
        } else if (compareResult > 0) {     //待删除的节点在右子树中
            tree.right = remove(tree.right, z);
            //删除节点后，若Avl树失去平衡,则进行相应的调节
            if (height(tree.left) - height(tree.right) == 2) {  //左子树失去平衡
                AvlNode<AnyType> l = tree.left;
                if (height(l.right) > height(l.left)) {
                    tree = leftRightRotation(tree);
                } else {
                    tree = leftLeftRotation(tree);
                }

            }

        } else {    //tree是对应要删除的节点
            //tree的左右孩子都非空
            if (tree.left != null && tree.right != null) {
                if (height(tree.left) > height(tree.right)) {
                    //如果tree的左子树比右子树高
                    //则 01 找出左子树tree中最大的点
                    //   02 将该最大节点的值赋给tree
                    //   03 删除该最大节点
                    //这类似于用*tree的左子树中最大节点做*tree的替身
                    //采用这种方式的好处是：删除*tree的左子树最大节点后，Avl树仍是平衡的
                    AvlNode<AnyType> max = findMax(tree.left);
                    tree.element = max.element;
                    tree.left = remove(tree.left, max);
                } else {
                    // 如果tree的左子树不比右子树高(即它们相等，或右子树比左子树高1)
                    // 则(01)找出tree的右子树中的最小节点
                    //   (02)将该最小节点的值赋值给tree。
                    //   (03)删除该最小节点。
                    // 这类似于用*tree的右子树中最小节点做*tree的替身；
                    // 采用这种方式的好处是：删除*tree的右子树中最小节点之后，AVL树仍然是平衡的。
                    AvlNode<AnyType> min = findMin(tree.right);
                    tree.element = min.element;
                    tree.right = remove(tree.right, min);
                }

            } else {
                AvlNode<AnyType> tmp = tree;
                tree = tree.left != null ? tree.left : tree.right;
                tmp = null;
            }
        }
        return tree;
    }

    public void remove(AnyType element) {
        AvlNode<AnyType> z;
        if ((z = search(mRoot, element)) != null) {
            mRoot = remove(mRoot, z);
        }
    }

    /*
    * 销毁Avl树
    * */
    private void destory(AvlNode<AnyType> tree) {
        if (tree == null)
            return;
        if (tree.left != null) {
            destory(tree.left);
        }
        if (tree.right != null) {
            destory(tree.right);
        }
        tree = null;
    }

    public void destory() {
        destory(mRoot);
    }

}
